package com.huangcihong.common.entity.vo.auth;

import com.huangcihong.orm.entity.BaseEntity;
import com.huangcihong.orm.listener.MyInsertListener;
import com.huangcihong.orm.listener.MyUpdateListener;
import com.mybatisflex.annotation.RelationOneToOne;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(value = "auth_role_menu", onInsert = MyInsertListener.class, onUpdate = MyUpdateListener.class)
public class RoleMenuVo extends BaseEntity {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单信息
     */
    @RelationOneToOne(selfField = "menuId", targetField = "id")
    private MenuVo menuVo;

    /**
     * 角色信息
     */
    @RelationOneToOne(selfField = "roleId", targetField = "id")
    private RoleVo roleVo;
}
