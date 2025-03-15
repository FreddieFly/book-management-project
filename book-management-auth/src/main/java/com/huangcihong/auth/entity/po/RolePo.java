package com.huangcihong.auth.entity.po;

import com.huangcihong.orm.entity.BaseEntity;
import com.huangcihong.orm.listener.MyInsertListener;
import com.huangcihong.orm.listener.MyUpdateListener;
import com.mybatisflex.annotation.RelationManyToMany;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(value = "auth_role", onInsert = MyInsertListener.class, onUpdate = MyUpdateListener.class)
public class RolePo extends BaseEntity {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 关联菜单
     */
    @RelationManyToMany(
            joinTable = "auth_role_menu",
            selfField = "id", joinSelfColumn = "role_id",
            targetField = "id", joinTargetColumn = "menu_id"
    )
    private List<MenuPo> menuPoList;


    private Boolean isDomainAdmin;


}
