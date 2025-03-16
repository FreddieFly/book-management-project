package com.huangcihong.auth.service;

import cn.hutool.core.bean.BeanUtil;
import com.huangcihong.auth.entity.po.UserPo;
import com.huangcihong.common.entity.vo.auth.*;
import com.mybatisflex.core.paginate.Page;

import java.util.List;
import java.util.Set;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param userVo 用户信息
     * @return 创建的用户ID
     * @throws RuntimeException 如果用户名已存在
     */
    Long createUser(UserCreateVo userVo);

    /**
     * 更新用户信息
     *
     * @param userVo 用户信息
     * @return 是否更新成功
     * @throws RuntimeException 如果用户名已存在
     */
    Boolean updateUser(UserUpdateVo userVo);

    /**
     * 分页查询用户列表
     *
     * @param page     分页对象（包含页码和每页大小）
     * @param name     用户名搜索关键字（可选）
     * @return 分页用户列表
     */
    Page<UserVo> getUserPage(Page<UserVo> page, String name);
    /**
     * 根据ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserVo getUserById(Long userId);

    /**
     * 根据ID删除用户
     *
     * @param userId 用户ID
     * @return 是否删除成功
     */
    Boolean deleteUserById(Long userId);


    TokenInfoVo doLogin(LoginVo loginVo);

    List<UserVo> getUserList(Set<Long> userIds);

}

