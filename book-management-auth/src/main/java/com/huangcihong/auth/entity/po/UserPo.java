package com.huangcihong.auth.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huangcihong.orm.entity.BaseEntity;
import com.huangcihong.orm.listener.MyInsertListener;
import com.huangcihong.orm.listener.MyUpdateListener;
import com.mybatisflex.annotation.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(value = "user", onInsert = MyInsertListener.class, onUpdate = MyUpdateListener.class)
public class UserPo extends BaseEntity {

    private String username;

    private String name;

    private String password;

    private String email;

    private String phone;

    private String role;

}
