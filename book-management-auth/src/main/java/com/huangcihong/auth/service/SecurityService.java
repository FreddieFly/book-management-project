package com.huangcihong.auth.service;

import com.huangcihong.common.entity.vo.auth.LoginVo;
import com.huangcihong.common.entity.vo.auth.TokenInfoVo;
import org.springframework.stereotype.Service;

/**
 * @author huangcihong
 */

public interface SecurityService {


    TokenInfoVo doLogin(LoginVo loginVo);



}
