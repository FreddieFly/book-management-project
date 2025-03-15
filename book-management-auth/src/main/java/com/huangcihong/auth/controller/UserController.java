package com.huangcihong.auth.controller;

import com.huangcihong.auth.service.UserService;
import com.huangcihong.common.ResultInfo;
import com.huangcihong.common.entity.vo.auth.UserVo;
import com.mybatisflex.core.paginate.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultInfo<Long> createUser(@RequestBody UserVo userVo) {
        return ResultInfo.success(userService.createUser(userVo));
    }

    @PutMapping("update")
    @ApiOperation(value = "更新用户信息")
    public ResultInfo<Boolean> updateUser(@RequestBody UserVo userVo) {
        return ResultInfo.success(userService.updateUser(userVo));
    }

    @GetMapping("page")
    @ApiOperation(value = "分页查询用户列表")
    public ResultInfo<Page<UserVo>> getUserPage(@RequestParam(defaultValue = "1") int pageNumber,
                                                @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(required = false) String name) {
        Page<UserVo> page = new Page<>(pageNumber, pageSize);
        return ResultInfo.success(userService.getUserPage(page, name));
    }

    @GetMapping("detail/{userId}")
    @ApiOperation(value = "根据ID查询用户信息")
    public ResultInfo<UserVo> getUserById(@PathVariable Long userId) {
        return ResultInfo.success(userService.getUserById(userId));
    }

    @DeleteMapping("delete/{userId}")
    @ApiOperation(value = "根据ID删除用户")
    public ResultInfo<Boolean> deleteUserById(@PathVariable Long userId) {
        return ResultInfo.success(userService.deleteUserById(userId));
    }
}