package com.smart.mall.core.starter.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


/**
 * 分页结果集
 * @author guwenchang
 * @date 2019-08-03
 */
@ApiModel("分页结果")
@Data
@Accessors(chain = true)
public final class PageResult<T> implements Serializable {

    @ApiModelProperty(value = "数据", required = true)
    private List<T> list;

    @ApiModelProperty(value = "总量", required = true)
    private Integer total;

}
