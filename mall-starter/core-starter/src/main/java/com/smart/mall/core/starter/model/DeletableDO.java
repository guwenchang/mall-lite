package com.smart.mall.core.starter.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 删除基础实体
 * @author guwenchang
 * @date 2019-08-03
 */
@Data
@Accessors(chain = true)
public class DeletableDO extends BaseDO {

    /**
     * 是否删除 0 未删除 1 已删除
     */
    @TableLogic
    private Integer deleted;
}
