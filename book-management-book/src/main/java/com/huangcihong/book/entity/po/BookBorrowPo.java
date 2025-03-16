package com.huangcihong.book.entity.po;

import com.huangcihong.common.entity.enums.book.BorrowStatusEnum;
import com.huangcihong.orm.entity.BaseEntity;
import com.huangcihong.orm.listener.MyInsertListener;
import com.huangcihong.orm.listener.MyUpdateListener;
import com.mybatisflex.annotation.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Table(value = "book_borrow", onInsert = MyInsertListener.class, onUpdate = MyUpdateListener.class)
@EqualsAndHashCode(callSuper = true)
public class BookBorrowPo extends BaseEntity {
    private String bookId;
    private String userId;
    private Date borrowDate;   // 改为LocalDate
    private Date returnDate;   // 改为LocalDate

    private BorrowStatusEnum status;    // 使用枚举类型
}