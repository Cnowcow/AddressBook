package com.spring.biz.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.biz.user.UserDTO;

public class UserRowMapper implements RowMapper<UserDTO> {

    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UserDTO addressbook = new UserDTO();
        addressbook.setId(rs.getInt("id"));
        addressbook.setName(rs.getString("name"));
        addressbook.setEmail(rs.getString("email"));
        addressbook.setPass(rs.getString("pass"));
        addressbook.setComdept(rs.getString("comdept"));
        addressbook.setBirth(rs.getString("birth"));
        addressbook.setTel(rs.getString("tel"));
        addressbook.setMemo(rs.getString("memo"));

        return addressbook;
    }
}
