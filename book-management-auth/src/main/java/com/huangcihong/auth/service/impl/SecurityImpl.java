package com.huangcihong.auth.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.huangcihong.auth.entity.po.UserPo;
import com.huangcihong.auth.repository.UserRepository;
import com.huangcihong.auth.repository.mapper.UserMapper;
import com.huangcihong.auth.service.SecurityService;
import com.huangcihong.common.entity.vo.auth.LoginVo;
import com.huangcihong.common.entity.vo.auth.TokenInfoVo;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangcihong
 */
@Service
public class SecurityImpl implements SecurityService {

    @Autowired
    UserRepository userRepository;

    @Override
    public TokenInfoVo doLogin(LoginVo loginVo) {

        UserPo userPo = this.userRepository.getOne(new QueryWrapper()
                .eq(UserPo::getUsername, loginVo.getUsername())
                .eq(UserPo::getPassword, loginVo.getPassword()));

        if (BeanUtil.isEmpty(userPo)){
            throw ExceptionUtil.wrapRuntime("用户名或密码不正确");
        }
        StpUtil.login(userPo.getUsername());
        SaSession saSession = StpUtil.getTokenSession();

        TokenInfoVo tokenInfoVo = new TokenInfoVo();
        tokenInfoVo.setExpire(saSession.getTimeout());
        tokenInfoVo.setAccess_token(saSession.getToken());
        return tokenInfoVo;
    }
}
