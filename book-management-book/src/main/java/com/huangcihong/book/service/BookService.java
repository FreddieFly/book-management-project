package com.huangcihong.book.service;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.huangcihong.book.entity.po.BookPo;
import com.huangcihong.common.entity.vo.result.ResultInfo;
import com.mybatisflex.core.paginate.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookService {
     String addBook(@RequestBody BookPo book);
     String deleteBook(@PathVariable String id);
     String updateBook(@RequestBody BookPo book);
     BookPo getBook(@PathVariable String id);
     Page<BookPo> listBooks(Page<BookPo> page);
     String borrowBook(@RequestParam String bookId, @RequestParam String userId);
     String returnBook(@RequestParam String bookId, @RequestParam String userId);
}