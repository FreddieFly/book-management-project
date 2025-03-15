package com.huangcihong.auth.repository;

import com.huangcihong.auth.entity.po.MenuPo;
import com.huangcihong.auth.repository.mapper.MenuMapper;

import com.huangcihong.orm.repository.impl.RepositoryServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MenuRepository extends RepositoryServiceImpl<MenuMapper, MenuPo> {

}
