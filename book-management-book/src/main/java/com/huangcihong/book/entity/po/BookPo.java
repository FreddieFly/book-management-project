package com.huangcihong.book.entity.po;

import com.huangcihong.orm.entity.BaseEntity;
import com.huangcihong.orm.listener.MyInsertListener;
import com.huangcihong.orm.listener.MyUpdateListener;
import com.mybatisflex.annotation.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(value = "book", onInsert = MyInsertListener.class, onUpdate = MyUpdateListener.class)
@EqualsAndHashCode(callSuper = true)
public class BookPo extends BaseEntity {
    private String name;
    private String author;
    private String publisher;
    private String type;
    private String language;
    private Integer totalCopies;    // 改为Integer
    private Integer availableCopies;// 改为Integer
}