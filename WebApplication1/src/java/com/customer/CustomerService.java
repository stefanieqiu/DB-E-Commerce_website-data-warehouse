/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    public boolean addCustomer(List<Object> params);
	public boolean updateCustomer(List<Object> params);
	//列出产品,为了分页，加上参数 start,end
	public List<Map<String, Object>> listCustomer(String username , int start , int end);
	//获取总的记录数
	public int getItemCount(String username);
	//批处理删除产品
	public boolean delCustomer(String[] ids);
	//查询单个产品
	public Map<String, Object> viewCustomer(String username);//??
}
