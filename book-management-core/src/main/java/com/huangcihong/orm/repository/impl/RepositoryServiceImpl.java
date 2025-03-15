package com.huangcihong.orm.repository.impl;

import com.huangcihong.orm.repository.RepositoryService;
import com.mybatisflex.core.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 通用数据访问层实现类，提供基础的 CRUD 操作和数据权限控制。
 *
 * @param <M> 实体对应的 Mapper 类型
 * @param <T> 实体类型
 */
public class RepositoryServiceImpl<M extends BaseMapper<T>, T> implements RepositoryService<T> {


    protected final Class<T> clazz; // 实体类类型

    @Autowired
    protected M mapper; // 实体对应的 Mapper

    @Override
    public BaseMapper<T> getMapper() {
        return this.mapper;
    }

    /**
     * 构造函数，初始化实体类类型。
     */
    protected RepositoryServiceImpl() {
        Type superClass = getClass().getGenericSuperclass();
        this.clazz = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[1];
    }

}