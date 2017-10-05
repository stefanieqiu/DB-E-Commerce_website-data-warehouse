package com.salesman;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jdbc.JdbcUtils;

/**
 *
 * @author lenovo
 */
public class SalesDao  implements SalesService  {
    
	private JdbcUtils jdbcUtils;
	public SalesDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public List<Map<String, Object>> listSales(String email ,int start ,int end) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from salesman where 1=1 and email like ? limit ? ,?";	
			if(email.equals("")){
				sql = "select * from  salesman limit ? ,?";
				params.add(start);
				params.add(end);
				
			}else{				
				params.add("%"+email+"%");
				params.add(start);
				params.add(end);
			}		
					
			list = jdbcUtils.findMoreResult(sql, params);			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			
			
			jdbcUtils.releaseConn();
			
		}
		
		
		return list;
	}

	//查询总记录数
	@Override
	public int getItemCount(String email) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> map = null;
		List<Object> params = null;		
		try {
			jdbcUtils.getConnection();			
			String sql = "select count(*) totalCount from salesman where 1=1 and email like ?";	
			if(email.equals("")){
				sql = "select count(*) totalCount from salesman";
				
			}else{
				params = new ArrayList<Object>();
				params.add("%"+email+"%");
			}
		map = jdbcUtils.findSimpleResult(sql, params);
		count = Integer.parseInt(map.get("totalCount").toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}
		
		
		return count;
	}


	

}
