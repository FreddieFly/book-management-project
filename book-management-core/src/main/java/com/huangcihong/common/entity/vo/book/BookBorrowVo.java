package com.huangcihong.common.entity.vo.book;

import com.huangcihong.common.entity.enums.book.BorrowStatusEnum;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class BookBorrowVo {
    private String bookId;
    private String bookName;
    private String author;
    private String publisher;

    private Long userId;
    private String userName;

    private Date borrowDate;
    private Date returnDate;
    private BorrowStatusEnum status;
}