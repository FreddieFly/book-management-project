package com.huangcihong.orm.listener;

import cn.dev33.satoken.stp.StpUtil;
import com.huangcihong.orm.entity.BaseEntity;
import com.mybatisflex.annotation.InsertListener;

import java.util.Date;

public class MyInsertListener implements InsertListener {

    @Override
    public void onInsert(Object entity) {

        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setCreateTime(new Date());
            baseEntity.setUpdateTime(new Date());
            baseEntity.setCreateUser(StpUtil.getLoginId("defaultUser"));
            baseEntity.setUpdateUser(StpUtil.getLoginId("defaultUser"));
        }
    }
}
