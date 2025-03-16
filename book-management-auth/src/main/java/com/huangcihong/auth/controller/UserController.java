package com.huangcihong.auth.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.huangcihong.auth.service.UserService;
import com.huangcihong.common.entity.vo.auth.*;
import com.huangcihong.common.entity.vo.result.ResultInfo;
import com.mybatisflex.core.paginate.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author huangcihong
 */
@RestController
@RequestMapping("/user/")
@Api(tags = {"用户管理"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    @ApiOperation(value = "创建用户")
    @SaCheckLogin
    @SaCheckRole("admin")
    public ResultInfo<Long> createUser(@RequestBody UserCreateVo userVo) {
        return ResultInfo.success(userService.createUser(userVo));
    }

    @PutMapping("update")
    @ApiOperation(value = "更新用户信息")
    @SaCheckLogin
    @SaCheckRole("admin")
    public ResultInfo<Boolean> updateUser(@RequestBody UserUpdateVo userVo) {
        return ResultInfo.success(userService.updateUser(userVo));
    }

    @GetMapping("page")
    @ApiOperation(value = "分页查询用户列表")
    @SaCheckLogin
    @SaCheckRole("admin")
    public ResultInfo<Page<UserVo>> getUserPage(Page<UserVo> page,
                                                @RequestParam(required = false) String name) {
        return ResultInfo.success(userService.getUserPage(page, name));
    }

    @GetMapping("detail/{userId}")
    @ApiOperation(value = "根据ID查询用户信息")
    @SaCheckLogin
    @SaCheckRole("admin")
    public ResultInfo<UserVo> getUserById(@PathVariable Long userId) {
        return ResultInfo.success(userService.getUserById(userId));
    }

    @DeleteMapping("delete/{userId}")
    @ApiOperation(value = "根据ID删除用户")
    @SaCheckLogin
    @SaCheckRole("admin")
    public ResultInfo<Boolean> deleteUserById(@PathVariable Long userId) {
        return ResultInfo.success(userService.deleteUserById(userId));
    }

    @PostMapping("doLogin")
    @ApiOperation(value = "用户登录")
    public ResultInfo<TokenInfoVo> doLogin(@RequestBody LoginVo loginVo) {
        return ResultInfo.success(userService.doLogin(loginVo));
    }


    @PostMapping("logout")
    @ApiOperation(value = "退出登录")
    @SaCheckLogin
    public ResultInfo<Void> logout() {
        StpUtil.logout();
        return ResultInfo.success();
    }

    @PostMapping("getUserList")
    @ApiOperation(value = "根据用户ID列表批量查询用户信息")
    @SaCheckLogin
    @SaCheckRole("admin")
    public ResultInfo<List<UserVo>> getUserList(@RequestBody Set<Long> userIds) {
        return ResultInfo.success(userService.getUserList(userIds));
    }

}