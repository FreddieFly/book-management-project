package com.huangcihong.common.entity.vo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huangcihong.orm.entity.BaseEntity;
import com.huangcihong.orm.listener.MyInsertListener;
import com.huangcihong.orm.listener.MyUpdateListener;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserVo extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;


}
