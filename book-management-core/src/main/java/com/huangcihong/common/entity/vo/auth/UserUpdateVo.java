package com.huangcihong.common.entity.vo.auth;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserUpdateVo {

    private Long id;

    private String username;

    private String name;

    private String password;

    private String email;

    private String phone;

    private String role;


}
