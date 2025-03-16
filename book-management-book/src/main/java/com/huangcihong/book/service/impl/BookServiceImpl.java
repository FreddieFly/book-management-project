// BookServiceImpl.java
package com.huangcihong.book.service.impl;

// ... 其他导入
import com.huangcihong.book.entity.po.BookBorrowPo;
import com.huangcihong.book.entity.po.BookPo;
import com.huangcihong.book.repository.BookBorrowRepository;
import com.huangcihong.book.repository.BookRepository;
import com.huangcihong.book.service.BookService;
import com.huangcihong.common.entity.enums.book.BorrowStatusEnum;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Override
    public String addBook(BookPo book) {
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
        return bookRepository.save(book) ? "添加成功" : "添加失败";
    }

    @Override
    public String deleteBook(String id) {
        return bookRepository.removeById(id) ? "删除成功" : "删除失败";
    }

    @Override
    public String updateBook(BookPo book) {
        // 检查其他记录是否有相同名称
        Long count = bookRepository.count(
                QueryWrapper.create()
                        .eq(BookPo::getName,book.getName())
                        .eq(BookPo::getAuthor,book.getAuthor())
                        .eq(BookPo::getPublisher,book.getPublisher())
        );
        if (count > 0) {
            return "书名已被其他图书使用";
        }
        return bookRepository.updateById(book) ? "更新成功" : "更新失败";
    }

    @Override
    public BookPo getBook(String id) {
        return bookRepository.getById(id);
    }

    @Override
    public Page<BookPo> listBooks(Page<BookPo> page) {
        return bookRepository.page(page);
    }

    @Transactional
    @Override
    public String borrowBook(String bookId, String userId) {
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
    public String returnBook(String bookId, String userId) {
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
}