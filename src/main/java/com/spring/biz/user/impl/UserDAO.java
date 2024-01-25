package com.spring.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.spring.biz.user.UserDTO;
import com.spring.biz.common.JDBCUtil;

@Repository("userDAO")
public class UserDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private final String EXIST = "select name, email from book where email=? and pass=?";

	
	public UserDTO exist(String email, String password) {
	    try {
	        conn = JDBCUtil.getConnection();
	        pstmt = conn.prepareStatement(EXIST);
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	UserDTO user = new UserDTO();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            return user;
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // 예외 발생 시 null 반환
	    } finally {
	        JDBCUtil.close(rs, pstmt, conn);
	    }
	}
}
