package com.huangcihong.auth.entity.po;

import com.huangcihong.orm.entity.BaseEntity;
import com.huangcihong.orm.listener.MyInsertListener;
import com.huangcihong.orm.listener.MyUpdateListener;
import com.mybatisflex.annotation.RelationOneToOne;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jielum
 */
@Getter
@Setter
@Table(value = "auth_role_user", onInsert = MyInsertListener.class, onUpdate = MyUpdateListener.class)
public class RoleUserPo extends BaseEntity {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 角色Id
     */
    private Long roleId;

    /**
     * 用户信息
     */
    @RelationOneToOne(selfField = "userId", targetField = "id")
    private UserPo userPo;

    /**
     * 角色信息
     */
    @RelationOneToOne(selfField = "roleId", targetField = "id")
    private RolePo rolePo;
}
