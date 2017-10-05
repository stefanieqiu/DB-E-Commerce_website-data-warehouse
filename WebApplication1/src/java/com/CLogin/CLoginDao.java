/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CLogin;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.mail.Flags.Flag;

import com.jdbc.JdbcUtils;
/**
 *
 * @author lenovo
 */
public class CLoginDao implements CLoginService{
 
	private JdbcUtils jdbcUtils;
	public CLoginDao() {
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public boolean login(List<Object> params) {
		boolean flag = false;
		
		try {
			jdbcUtils.getConnection();
			String sql = "select * from cUserInfo where username = ? and pswd = ?";
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty()?true:false;			
			
		} catch (Exception e) {
			//  exception
			e.printStackTrace();
		}finally{
			
		//close db
		jdbcUtils.releaseConn();
			
		}
		
		return flag;
	}	   
}
