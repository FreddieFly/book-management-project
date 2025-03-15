package com.huangcihong.orm.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础Entity
 *
 * @author anj
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id(keyType=KeyType.Generator, value= KeyGenerators.snowFlakeId)
    @ApiModelProperty("ID唯一表识")
    private Long id;

    /**
     * 删除状态
     */
    @ApiModelProperty("删除状态")
    private Boolean deleteStatus;

    /**
     * 添加时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 创建数据的用户
     */
    @ApiModelProperty("创建者")
    private String createUser;

    /**
     * 更新数据的用户
     */
    @ApiModelProperty("更新者")
    private String updateUser;
}
