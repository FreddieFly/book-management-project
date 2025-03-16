package com.huangcihong.book.service;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.bean.BeanUtil;
import com.huangcihong.book.entity.po.BookBorrowPo;
import com.huangcihong.book.entity.po.BookPo;
import com.huangcihong.common.entity.vo.auth.UserVo;
import com.huangcihong.common.entity.vo.book.BookBorrowVo;
import com.huangcihong.common.entity.vo.result.ResultInfo;
import com.mybatisflex.core.paginate.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookService {
     String addBook(@RequestBody BookPo book);
     String deleteBook(@PathVariable Long id);
     String updateBook(@RequestBody BookPo book);
     BookPo getBook(@PathVariable Long id);
     Page<BookPo> listBooks(Page<BookPo> page);
     String borrowBook(@RequestParam Long bookId, @RequestParam Long userId);
     String returnBook(@RequestParam Long bookId, @RequestParam Long userId);
     Page<BookBorrowVo> borrowPage(Page<BookBorrowPo> page);
}