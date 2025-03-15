package com.huangcihong.auth.repository;


import com.huangcihong.auth.entity.po.UserPo;
import com.huangcihong.auth.repository.mapper.UserMapper;
import com.huangcihong.orm.repository.impl.RepositoryServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRepository extends RepositoryServiceImpl<UserMapper, UserPo> {

}
