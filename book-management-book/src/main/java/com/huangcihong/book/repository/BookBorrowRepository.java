package com.huangcihong.book.repository;

import com.huangcihong.book.entity.po.BookBorrowPo;
import com.huangcihong.book.entity.po.BookPo;
import com.huangcihong.book.repository.mapper.BookBorrowMapper;
import com.huangcihong.book.repository.mapper.BookMapper;
import com.huangcihong.orm.repository.impl.RepositoryServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowRepository extends RepositoryServiceImpl<BookBorrowMapper, BookBorrowPo> {

}
