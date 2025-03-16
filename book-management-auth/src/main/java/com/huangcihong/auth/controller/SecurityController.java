package com.huangcihong.auth.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.huangcihong.auth.service.SecurityService;
import com.huangcihong.common.entity.vo.result.ResultInfo;
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
    @ApiOperation(value = "用户登录")
    public ResultInfo<TokenInfoVo> doLogin(@RequestBody LoginVo loginVo) {
        return ResultInfo.success(securityService.doLogin(loginVo));
    }


    @PostMapping("logout")
    @ApiOperation(value = "退出登录")
    @SaCheckLogin
    public ResultInfo<Void> logout() {
        StpUtil.logout();
        return ResultInfo.success();
    }

    @PostMapping("isLogin")
    @ApiOperation(value = "登陆验证")
    @SaCheckLogin
    public ResultInfo<Boolean> isLogin() {
        return ResultInfo.success(StpUtil.isLogin());
    }

}
