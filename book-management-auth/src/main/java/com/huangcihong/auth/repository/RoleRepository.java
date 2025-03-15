package com.huangcihong.auth.repository;


import com.huangcihong.auth.entity.po.RolePo;
import com.huangcihong.auth.repository.mapper.RoleMapper;
import com.huangcihong.orm.repository.impl.RepositoryServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleRepository extends RepositoryServiceImpl<RoleMapper, RolePo> {


}
