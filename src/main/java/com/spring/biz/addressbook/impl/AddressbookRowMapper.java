package com.spring.biz.addressbook.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.biz.addressbook.AddressbookDTO;

public class AddressbookRowMapper implements RowMapper<AddressbookDTO> {

    @Override
    public AddressbookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AddressbookDTO addressbook = new AddressbookDTO();
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
