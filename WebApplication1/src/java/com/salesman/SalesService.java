/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesman;

import java.util.List;
import java.util.Map;
 
public interface SalesService {
    public List<Map<String, Object>> listSales(String email , int start , int end);
	//获取总的记录数
	public int getItemCount(String email);
	//批处理删除产品
}
