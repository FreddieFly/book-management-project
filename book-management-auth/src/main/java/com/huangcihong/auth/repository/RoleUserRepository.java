package com.huangcihong.auth.repository;

import com.huangcihong.auth.entity.po.RoleUserPo;
import com.huangcihong.auth.repository.mapper.RoleUserMapper;
import com.huangcihong.orm.repository.impl.RepositoryServiceImpl;
import org.springframework.stereotype.Repository;

@Repository
public class RoleUserRepository extends RepositoryServiceImpl<RoleUserMapper, RoleUserPo> {
}
