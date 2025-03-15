package com.huangcihong.common.entity.vo.auth;

import com.huangcihong.common.entity.enums.auth.MenuKeyEnum;
import com.huangcihong.orm.entity.BaseEntity;
import com.huangcihong.orm.listener.MyInsertListener;
import com.huangcihong.orm.listener.MyUpdateListener;
import com.mybatisflex.annotation.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(value = "auth_menu", onInsert = MyInsertListener.class, onUpdate = MyUpdateListener.class)
@EqualsAndHashCode(callSuper = true)
public class MenuVo extends BaseEntity {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 父级菜单
     */
    private Long parentId;

    /**
     * 菜单key
     */
    private MenuKeyEnum menuKey;


}
