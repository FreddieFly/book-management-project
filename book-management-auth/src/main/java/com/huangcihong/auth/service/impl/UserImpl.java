package com.huangcihong.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.huangcihong.auth.entity.po.UserPo;
import com.huangcihong.auth.repository.UserRepository;
import com.huangcihong.auth.service.UserService;
import com.huangcihong.common.entity.vo.auth.UserVo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.huangcihong.auth.entity.po.table.UserPoTableDef.USER_PO;

/**
 * 用户服务实现类
 */
@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Long createUser(UserVo userVo) {
        // 检查用户名是否已存在
        if (isNameExists(userVo.getName())) {
            throw new RuntimeException("用户名已存在");
        }

        UserPo userPo = new UserPo();
        BeanUtil.copyProperties(userVo, userPo);
        userRepository.save(userPo);
        return userPo.getId();
    }

    @Override
    @Transactional
    public Boolean updateUser(UserVo userVo) {
        // 检查用户名是否已存在（排除当前用户）
        if (isNameExists(userVo.getName(), userVo.getId())) {
            throw new RuntimeException("用户名已存在");
        }

        UserPo userPo = userRepository.getById(userVo.getId());
        if (Objects.isNull(userPo)) {
            throw new RuntimeException("用户不存在");
        }
        BeanUtil.copyProperties(userVo, userPo);
        return userRepository.updateById(userPo);
    }

    /**
     * 分页查询用户列表
     *
     * @param page 分页对象（包含页码和每页大小）
     * @param name 用户名搜索关键字（可选）
     * @return 分页用户列表
     */
    @Override
    public Page<UserVo> getUserPage(Page<UserVo> page, String name) {
        return userRepository.pageAs(page,QueryWrapper.create()
                .like(UserPo::getName,name,!StringUtils.isBlank(name)),UserVo.class);
    }


    @Override
    public UserVo getUserById(Long userId) {
        UserPo userPo = userRepository.getById(userId);
        if (Objects.isNull(userPo)) {
            throw new RuntimeException("用户不存在");
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
                .where(USER_PO.NAME.eq(name))
                .and(USER_PO.ID.ne(userId));
        return userRepository.count(queryWrapper) > 0;
    }
}