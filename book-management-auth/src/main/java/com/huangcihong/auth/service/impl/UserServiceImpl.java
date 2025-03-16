package com.huangcihong.auth.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.huangcihong.auth.entity.po.UserPo;
import com.huangcihong.auth.repository.UserRepository;
import com.huangcihong.auth.service.UserService;
import com.huangcihong.common.entity.vo.auth.*;
import com.huangcihong.common.entity.enums.exception.ErrorCodeEnum;
import com.huangcihong.common.exception.BusinessException;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.huangcihong.auth.entity.po.table.UserPoTableDef.USER_PO;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Long createUser(UserCreateVo userVo) {
        // 检查用户名是否已存在
        if (isNameExists(userVo.getName())) {
            throw new BusinessException(ErrorCodeEnum.USERNAME_ALREADY_EXISTS);
        }

        UserPo userPo = new UserPo();
        BeanUtil.copyProperties(userVo, userPo);
        userRepository.save(userPo);
        return userPo.getId();
    }

    @Override
    @Transactional
    public Boolean updateUser(UserUpdateVo userVo) {
        // 检查用户名是否已存在（排除当前用户）
        if (isNameExists(userVo.getName(), userVo.getId())) {
            throw new BusinessException(ErrorCodeEnum.USERNAME_ALREADY_EXISTS);
        }

        UserPo userPo = userRepository.getOne(QueryWrapper.create().eq(UserPo::getId,userVo.getId()));
        if (Objects.isNull(userPo)) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        BeanUtil.copyProperties(userVo, userPo);
        return userRepository.updateById(userPo);
    }

    @Override
    public Page<UserVo> getUserPage(Page<UserVo> page, String name) {
        return userRepository.pageAs(page, QueryWrapper.create()
                .like(UserPo::getName, name, !StringUtils.isBlank(name)), UserVo.class);
    }

    @Override
    public UserVo getUserById(Long userId) {
        UserPo userPo = userRepository.getById(userId);
        if (Objects.isNull(userPo)) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(userPo, userVo);
        return userVo;
    }

    @Override
    @Transactional
    public Boolean deleteUserById(Long userId) {
        return userRepository.removeById(userId);
    }

    /**
     * 检查用户名是否已存在
     *
     * @param name 用户名
     * @return 是否存在
     */
    private Boolean isNameExists(String name) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select()
                .from(USER_PO)
                .where(USER_PO.NAME.eq(name));
        return userRepository.count(queryWrapper) > 0;
    }

    /**
     * 检查用户名是否已存在（排除指定用户ID）
     *
     * @param name   用户名
     * @param userId 用户ID
     * @return 是否存在
     */
    private Boolean isNameExists(String name, Long userId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select()
                .from(USER_PO)
                .where(USER_PO.ID.eq(userId))
                .and(USER_PO.ID.ne(userId));
        return userRepository.count(queryWrapper) > 0;
    }

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
        SaSession saSession = StpUtil.getSession();
        saSession.set("role", userPo.getRole());

        // 返回结果
        TokenInfoVo tokenInfoVo = new TokenInfoVo();
        tokenInfoVo.setAccess_token(StpUtil.getTokenValue());
        tokenInfoVo.setExpire(StpUtil.getTokenTimeout());
        return tokenInfoVo;
    }

    @Override
    public List<UserVo> getUserList(Set<Long> userIds) {
        // 查询用户列表
        return userRepository.listAs(QueryWrapper.create()
                .in(UserPo::getId,userIds),UserVo.class);
    }
}