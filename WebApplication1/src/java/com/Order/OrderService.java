/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Order;

import java.util.List;
import java.util.Map;
public interface OrderService {
        public boolean addOrder(List<Object> params);
        public boolean addDetail(List<Object> params);
	//public boolean updateOrder(List<Object> params);
	//列出产品,为了分页，加上参数 start,end
	public List<Map<String, Object>> listOrder(String username , int start , int end);
	//获取总的记录数
	public int getItemCount(String username);
	//批处理删除产品
	//public boolean delOrder(String[] ids);
	//查询单个产品
	public List<Map<String, Object>> viewOrder(List<Object> params);//??
        public boolean recordTotal(List<Object> params);
        public List<Map<String, Object>> mosttotalOrder(String username);
}
