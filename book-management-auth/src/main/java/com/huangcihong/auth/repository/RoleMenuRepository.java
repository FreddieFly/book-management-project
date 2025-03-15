package com.huangcihong.auth.repository;


import com.huangcihong.auth.entity.po.RoleMenuPo;
import com.huangcihong.auth.repository.mapper.RoleMenuMapper;
import com.huangcihong.orm.repository.impl.RepositoryServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuRepository extends RepositoryServiceImpl<RoleMenuMapper, RoleMenuPo> {


}
