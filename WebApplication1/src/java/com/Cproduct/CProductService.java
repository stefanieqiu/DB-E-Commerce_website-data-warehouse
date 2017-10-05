package com.Cproduct;

import java.util.List;
import java.util.Map;

public interface CProductService {
	public boolean addProduct(List<Object> params);
	
	//列出产品,为了分页，加上参数 start,end
	public List<Map<String, Object>> listProduct(String proname , int start , int end);
	//获取总的记录数
	public int getItemCount(String proname);
	//批处理删除产品
	public boolean delProduct(String[] ids);
	//查询单个产品
	public Map<String, Object> viewProduct(String proid);
        //加入购物车
        public List<Map<String,Object>> addToCart(String[] ids);
        public List<Map<String, Object>> rankProduct(String proname ,int start ,int end);
        public List<Map<String, Object>> searchProduct(String proname , int start , int end);
        public boolean updateNumber(List<Object> params);
}
