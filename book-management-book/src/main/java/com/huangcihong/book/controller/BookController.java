// BookController.java
package com.huangcihong.book.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.huangcihong.book.entity.po.BookPo;
import com.huangcihong.book.service.BookService;
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
    public ResultInfo<String> addBook(@RequestBody BookPo book) {
        return ResultInfo.success(bookService.addBook(book));
    }

    @SaCheckLogin
    @DeleteMapping("delete/{id}")
    @ApiOperation("删除图书")
    public ResultInfo<String> deleteBook(@PathVariable String id) {
        return ResultInfo.success(bookService.deleteBook(id));
    }

    @SaCheckLogin
    @PutMapping("update")
    @ApiOperation("更新图书")
    public ResultInfo<String> updateBook(@RequestBody BookPo book) {
        return ResultInfo.success(bookService.updateBook(book));
    }

    @SaCheckLogin
    @GetMapping("get/{id}")
    @ApiOperation("获取图书详情")
    public ResultInfo<BookPo> getBook(@PathVariable String id) {
        return ResultInfo.success(bookService.getBook(id));
    }

    @SaCheckLogin
    @GetMapping("list")
    @ApiOperation(value = "分页查询图书", notes = "页码从1开始，默认每页10条")
    public ResultInfo<Page<BookPo>> listBooks(Page<BookPo> page) {
        return ResultInfo.success(bookService.listBooks(page));
    }

    @SaCheckLogin
    @PostMapping("borrow")
    @ApiOperation("借阅图书")
    public ResultInfo<String> borrowBook(@RequestParam String bookId, @RequestParam String userId) {
        return ResultInfo.success(bookService.borrowBook(bookId, userId));
    }

    @SaCheckLogin
    @PostMapping("return")
    @ApiOperation("归还图书")
    public ResultInfo<String> returnBook(@RequestParam String bookId, @RequestParam String userId) {
        return ResultInfo.success(bookService.returnBook(bookId, userId));
    }
}