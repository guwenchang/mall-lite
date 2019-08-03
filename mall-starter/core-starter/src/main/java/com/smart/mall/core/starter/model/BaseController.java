package com.smart.mall.core.starter.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

/**
* 基础controller
* @author xiaobai
* @date 2017/10/30 11:54
**/
@Slf4j
public class BaseController {
	protected <T> Page<T> getPage(PageParam pageParam) {
		Page<T> page = new Page<T>(pageParam.getPageNo(), pageParam.getPageSize());
		if (page.getSize() > PageParam.MAX_PAGE_SIZE){
			page.setSize(PageParam.MAX_PAGE_SIZE);
		}
		page.setOrders(pageParam.getOrders());
		return page;
	}


}
