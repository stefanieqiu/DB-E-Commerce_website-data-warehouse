package com.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jdbc.JdbcUtils;

public class StoreDao implements StoreService {

	private JdbcUtils jdbcUtils;
	public StoreDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public boolean addStore(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into store(sid,zip,num,rname) values(?,?,?,?)";
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
        public boolean updateStore(List<Object> params) {
		
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "update store set num = ? where store.sid = ?";
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
	public List<Map<String, Object>> listStore(String sid ,int start ,int end) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Object> params  = new ArrayList<Object>();		
		try {
			jdbcUtils.getConnection();			
			String sql = "select * from store where 1=1 and sid like ? limit ? ,?";	
			if(sid.equals("")){
				sql = "select * from store limit ? ,?";
				params.add(start);
				params.add(end);
				
			}else{				
				params.add("%"+sid+"%");
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
	public int getItemCount(String sid) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> map = null;
		List<Object> params = null;		
		try {
			jdbcUtils.getConnection();			
			String sql = "select count(*) totalCount from store where 1=1 and sid like ?";	
			if(sid.equals("")){
				sql = "select count(*) totalCount from store";
				
			}else{
				params = new ArrayList<Object>();
				params.add("%"+sid+"%");
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
	public boolean delStore(String[] ids) {
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			if (ids!=null) {
				String[] sql = new String[ids.length];
				for(int i = 0 ; i< ids.length; i++){
					sql[i] = "delete from store where sid = '"+ids[i]+"'";
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
	public Map<String, Object> viewStore(String sid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
		try {
			jdbcUtils.getConnection();
			List<Object> params = new ArrayList<Object>();
			params.add(sid);
			String sql = "select * from store where sid = ?";
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
