package com.huangcihong.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.huangcihong.auth.entity.po.UserPo;
import com.huangcihong.auth.repository.UserRepository;
import com.huangcihong.auth.service.SecurityService;
import com.huangcihong.common.entity.vo.auth.LoginVo;
import com.huangcihong.common.entity.vo.auth.TokenInfoVo;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenInfoVo doLogin(LoginVo loginVo) {
        // 校验用户名和密码
        UserPo userPo = userRepository.getOne(new QueryWrapper()
                .eq(UserPo::getUsername, loginVo.getUsername())
                .eq(UserPo::getPassword, loginVo.getPassword()));
        if (BeanUtil.isEmpty(userPo)) {
            throw new RuntimeException("用户名或密码错误");
        }

        StpUtil.login(userPo.getUsername());

        // 返回结果
        TokenInfoVo tokenInfoVo = new TokenInfoVo();
        tokenInfoVo.setAccess_token(StpUtil.getTokenValue());
        tokenInfoVo.setExpire(StpUtil.getTokenTimeout());
        return tokenInfoVo;
    }
}