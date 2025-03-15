package com.huangcihong.orm.listener;

import cn.dev33.satoken.stp.StpUtil;
import com.huangcihong.orm.entity.BaseEntity;
import com.mybatisflex.annotation.UpdateListener;

import java.util.Date;

public class MyUpdateListener implements UpdateListener {

    @Override
    public void onUpdate(Object entity) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setUpdateTime(new Date());
            baseEntity.setUpdateUser(StpUtil.getLoginId("defaultUser"));
        }
    }
}
