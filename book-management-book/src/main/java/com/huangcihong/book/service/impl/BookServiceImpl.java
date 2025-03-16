// BookServiceImpl.java
package com.huangcihong.book.service.impl;

// ... 其他导入
import cn.hutool.core.bean.BeanUtil;
import com.huangcihong.book.client.AuthClient;
import com.huangcihong.book.entity.po.BookBorrowPo;
import com.huangcihong.book.entity.po.BookPo;
import com.huangcihong.book.repository.BookBorrowRepository;
import com.huangcihong.book.repository.BookRepository;
import com.huangcihong.book.service.BookService;
import com.huangcihong.common.entity.enums.book.BorrowStatusEnum;
import com.huangcihong.common.entity.vo.auth.UserVo;
import com.huangcihong.common.entity.vo.book.BookBorrowVo;
import com.huangcihong.common.entity.vo.book.BookCreateVo;
import com.huangcihong.common.entity.vo.book.BookUpdateVo;
import com.huangcihong.common.entity.vo.book.BookVo;
import com.huangcihong.common.entity.vo.result.ResultInfo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Autowired
    private AuthClient authClient;

    @Override
    public String addBook(BookCreateVo book) {
        // 检查书名重复
        Long count = bookRepository.count(
            QueryWrapper.create()
                    .eq(BookPo::getName,book.getName())
                    .eq(BookPo::getAuthor,book.getAuthor())
                    .eq(BookPo::getPublisher,book.getPublisher())
        );
        if (count > 0) {
            return "书名已存在";
        }
        BookPo bookPo = new BookPo();
        BeanUtil.copyProperties(book,bookPo);
        return bookRepository.save(bookPo) ? "添加成功" : "添加失败";
    }

    @Override
    public String deleteBook(Long id) {
        return bookRepository.removeById(id) ? "删除成功" : "删除失败";
    }

    @Override
    public String updateBook(BookUpdateVo book) {
        // 检查其他记录是否有相同名称
        Long count = bookRepository.count(
                QueryWrapper.create()
                        .eq(BookPo::getName,book.getName())
                        .eq(BookPo::getAuthor,book.getAuthor())
                        .eq(BookPo::getPublisher,book.getPublisher())
                        .ne(BookPo::getId,book.getId())
        );
        if (count > 0) {
            return "书名已被其他图书使用";
        }
        BookPo bookPo = new BookPo();
        BeanUtil.copyProperties(book,bookPo);
        return bookRepository.updateById(bookPo) ? "更新成功" : "更新失败";
    }

    @Override
    public BookPo getBook(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public Page<BookPo> listBooks(Page page) {

        return bookRepository.page(page);
    }

    @Transactional
    @Override
    public String borrowBook(Long bookId, Long userId) {
        BookPo book = bookRepository.getById(bookId);
        if (book == null) return "图书不存在";
        if (book.getAvailableCopies() <= 0) return "库存不足";

        // 创建借阅记录
        BookBorrowPo borrow = new BookBorrowPo();
        borrow.setBookId(bookId);
        borrow.setUserId(userId);
        borrow.setBorrowDate(new Date());
        borrow.setStatus(BorrowStatusEnum.BORROWED);
        bookBorrowRepository.save(borrow);

        // 更新库存
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return bookRepository.updateById(book) ? "借阅成功" : "借阅失败";
    }

    @Transactional
    @Override
    public String returnBook(Long bookId, Long userId) {
        // 查询借阅记录
        BookBorrowPo borrow = bookBorrowRepository.getOne(
            QueryWrapper.create()
                    .eq(BookBorrowPo::getUserId,userId)
                    .eq(BookBorrowPo::getBookId,bookId)
                    .eq(BookBorrowPo::getStatus,BorrowStatusEnum.BORROWED.name())
        );
        if (borrow == null) return "未找到借阅记录";

        // 更新借阅记录
        borrow.setReturnDate(new Date());
        borrow.setStatus(BorrowStatusEnum.RETURNED);
        bookBorrowRepository.updateById(borrow);

        // 恢复库存
        BookPo book = bookRepository.getById(bookId);
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return bookRepository.updateById(book) ? "归还成功" : "归还失败";
    }

    @Override
    public Page<BookBorrowVo> borrowPage(Page page) {
        // 1. 分页查询借阅记录
        Page<BookBorrowPo> borrowPoPage = bookBorrowRepository.page(page);
        // 2. 提取用户ID列表
        Set<Long> userIds = borrowPoPage.getRecords().stream()
                .map(BookBorrowPo::getUserId)
                .collect(Collectors.toSet());


        Set<Long> bookIds = borrowPoPage.getRecords().stream()
                .map(BookBorrowPo::getBookId)
                .collect(Collectors.toSet());

        // 3. 调用 Auth 服务获取用户信息
        ResultInfo<List<UserVo>> userResult = authClient.getUserList(userIds);
        List<UserVo> users = userResult.getData();
        // 4. 转换为 BookBorrowVo 并填充用户信息
        List<BookBorrowVo> borrowVos = borrowPoPage.getRecords().stream()
                .map(borrow -> {
                    BookBorrowVo vo = new BookBorrowVo();
                    BeanUtil.copyProperties(borrow, vo);
                    // 填充图书信息（需查询图书表）
                    BookPo book = bookRepository.getById(borrow.getBookId());
                    if (book != null) {
                        vo.setBookName(book.getName());
                        vo.setAuthor(book.getAuthor());
                        vo.setPublisher(book.getPublisher());
                    }
                    // 填充用户信息
                    users.stream()
                            .filter(u -> u.getId().equals(borrow.getUserId()))
                            .findFirst()
                            .ifPresent(u -> vo.setUserName(u.getName()));
                    return vo;
                }).collect(Collectors.toList());

        // 5. 构建分页结果
        Page<BookBorrowVo> resultPage = new Page<>();
        resultPage.setRecords(borrowVos);
        resultPage.setPageNumber(page.getPageNumber());
        resultPage.setPageSize(page.getPageSize());
        resultPage.setTotalRow(borrowPoPage.getTotalRow());
        return resultPage;
    }
}