// BookController.java
package com.huangcihong.book.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.huangcihong.book.entity.po.BookBorrowPo;
import com.huangcihong.book.entity.po.BookPo;
import com.huangcihong.book.service.BookService;
import com.huangcihong.common.entity.vo.book.BookBorrowVo;
import com.huangcihong.common.entity.vo.book.BookCreateVo;
import com.huangcihong.common.entity.vo.book.BookUpdateVo;
import com.huangcihong.common.entity.vo.book.BookVo;
import com.huangcihong.common.entity.vo.result.ResultInfo;
import com.mybatisflex.core.paginate.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/")
@Api(tags = "图书管理接口")
public class BookController {

    @Autowired
    private BookService bookService;

    @SaCheckLogin
    @PostMapping("add")
    @ApiOperation(value = "添加图书")
    @SaCheckRole("admin")
    public ResultInfo<String> addBook(@RequestBody BookCreateVo book) {
        return ResultInfo.success(bookService.addBook(book));
    }

    @SaCheckLogin
    @DeleteMapping("delete/{id}")
    @ApiOperation("删除图书")
    @SaCheckRole("admin")
    public ResultInfo<String> deleteBook(@PathVariable Long id) {
        return ResultInfo.success(bookService.deleteBook(id));
    }

    @SaCheckLogin
    @PutMapping("update")
    @ApiOperation("更新图书")
    @SaCheckRole("admin")
    public ResultInfo<String> updateBook(@RequestBody BookUpdateVo book) {
        return ResultInfo.success(bookService.updateBook(book));
    }

    @SaCheckLogin
    @GetMapping("get/{id}")
    @ApiOperation("获取图书详情")
    public ResultInfo<BookPo> getBook(@PathVariable Long id) {
        return ResultInfo.success(bookService.getBook(id));
    }

    @SaCheckLogin
    @GetMapping("list")
    @ApiOperation(value = "分页查询图书", notes = "页码从1开始，默认每页10条")
    public ResultInfo<Page<BookPo>> listBooks(Page page) {
        return ResultInfo.success(bookService.listBooks(page));
    }

    @SaCheckLogin
    @PostMapping("borrow")
    @ApiOperation("借阅图书")
    public ResultInfo<String> borrowBook(@RequestParam Long bookId, @RequestParam Long userId) {
        return ResultInfo.success(bookService.borrowBook(bookId, userId));
    }

    @SaCheckLogin
    @PostMapping("return")
    @ApiOperation("归还图书")
    public ResultInfo<String> returnBook(@RequestParam Long bookId, @RequestParam Long userId) {
        return ResultInfo.success(bookService.returnBook(bookId, userId));
    }

    @SaCheckLogin
    @GetMapping("borrowPage")
    @ApiOperation(value = "分页查询借阅记录")
    @SaCheckRole("admin")
    public ResultInfo<Page<BookBorrowVo>> borrowPage(Page page) {
        return ResultInfo.success(bookService.borrowPage(page));
    }
}