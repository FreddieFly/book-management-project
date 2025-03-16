package com.huangcihong.book.service;

import com.huangcihong.book.entity.po.BookBorrowPo;
import com.huangcihong.book.entity.po.BookPo;
import com.huangcihong.common.entity.vo.auth.UserVo;
import com.huangcihong.common.entity.vo.book.BookBorrowVo;
import com.huangcihong.common.entity.vo.book.BookCreateVo;
import com.huangcihong.common.entity.vo.book.BookUpdateVo;
import com.huangcihong.common.entity.vo.book.BookVo;
import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.*;

public interface BookService {
     String addBook(@RequestBody BookCreateVo book);
     String deleteBook(@PathVariable Long id);
     String updateBook(@RequestBody BookUpdateVo book);
     BookPo getBook(@PathVariable Long id);
     Page<BookPo> listBooks(Page page);
     String borrowBook(@RequestParam Long bookId, @RequestParam Long userId);
     String returnBook(@RequestParam Long bookId, @RequestParam Long userId);
     Page<BookBorrowVo> borrowPage(Page page);
}