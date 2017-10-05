package com.store;

import java.util.List;
import java.util.Map;

public interface StoreService {
	public boolean addStore(List<Object> params);
	public boolean updateStore(List<Object> params);
	//列出产品,为了分页，加上参数 start,end
	public List<Map<String, Object>> listStore(String sid , int start , int end);
	//获取总的记录数
	public int getItemCount(String sid);
	//批处理删除产品
	public boolean delStore(String[] ids);
	//查询单个产品
	public Map<String, Object> viewStore(String sid);
}
