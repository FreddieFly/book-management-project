package com.huangcihong.common.entity.vo.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVo {
    private String id;
    private String name;
    private String author;
    private String publisher;
    private String type;
    private String language;
    private Integer totalCopies;    // 改为Integer
    private Integer availableCopies;// 改为Integer
}