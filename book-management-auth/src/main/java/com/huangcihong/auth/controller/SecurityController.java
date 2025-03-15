package com.huangcihong.auth.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.huangcihong.auth.service.SecurityService;
import com.huangcihong.common.ResultInfo;
import com.huangcihong.common.entity.vo.auth.LoginVo;
import com.huangcihong.common.entity.vo.auth.TokenInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangcihong
 */
@RestController
@RequestMapping("/security/")
@Api(tags = {"登陆验证"})
public class SecurityController {

    @Autowired
    SecurityService securityService;

    @PostMapping("doLogin")
    public ResultInfo<TokenInfoVo> doLogin(LoginVo loginVo) {
        return new ResultInfo<>(securityService.doLogin(loginVo));
    }


    @SaIgnore
    @PostMapping("logout")
    @ApiOperation(value = "权限退出登录")
    public ResultInfo<Void> logout() {
        StpUtil.logout(StpUtil.getLoginId());
        return ResultInfo.success();
    }

}
