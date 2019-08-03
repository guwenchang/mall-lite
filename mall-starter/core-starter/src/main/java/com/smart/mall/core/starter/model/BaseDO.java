package com.smart.mall.core.starter.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体
 * @author guwenchang
 * @date 2019-08-03
 */
@Data
@Accessors(chain = true)
public class BaseDO implements Serializable {

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
}