/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jdbc.JdbcUtils;
/**
 *
 * @author lenovo
 */
public class CustomerDao implements CustomerService {
    private JdbcUtils jdbcUtils;
    public CustomerDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}   	
    
    
        @Override
	public boolean addCustomer(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into cUserInfo(username,pswd,name,email,phone,street,city,state,zip) values(?,?,?,?,?,?,?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			// 关闭数据库连接
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
	}
        @Override
        public boolean updateCustomer (List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "update cUserInfo set email= ? where cUserInfo.username = ?";
			flag = jdbcUtils.updateByPreparedStatement1(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			// 关闭数据库连接
			jdbcUtils.releaseConn();
			
		}
		
		
		return flag;
	}
	@Override
	public List<Map<String, Object>> listCustomer(String username ,int start ,int end) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from cUserinfo where 1=1 and username like ? limit ? ,?";	
			if(username.equals("")){
				sql = "select * from cUserInfo limit ? ,?";
				params.add(start);
				params.add(end);
				
			}else{				
				params.add("%"+username+"%");
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
	public int getItemCount(String username) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> map = null;
		List<Object> params = null;		
		try {
			jdbcUtils.getConnection();			
			String sql = "select count(*) totalCount from cUserInfo where 1=1 and username like ?";	
			if(username.equals("")){
				sql = "select count(*) totalCount from cUserInfo";
				
			}else{
				params = new ArrayList<Object>();
				params.add("%"+username+"%");
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

	@Override
	public boolean delCustomer(String[] ids) {
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			if (ids!=null) {
				String[] sql = new String[ids.length];
				for(int i = 0 ; i< ids.length; i++){
					sql[i] = "delete from cUserInfo where username = '"+ids[i]+"'";
					System.out.println(sql[i]);
				}
				flag = jdbcUtils.deleteByBatch(sql);	
			}
					
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}	
		
		return flag;
	}

	@Override
	public Map<String, Object> viewCustomer(String username) {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
		try {
			jdbcUtils.getConnection();
			List<Object> params = new ArrayList<Object>();
			params.add(username);
			String sql = "select * from cUserInfo where username = ?";
			map = jdbcUtils.findSimpleResult(sql, params);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}
		
		
		return map;
	}
}
