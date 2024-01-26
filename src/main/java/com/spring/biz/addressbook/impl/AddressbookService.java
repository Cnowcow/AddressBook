package com.spring.biz.addressbook.impl;

import java.util.List;

import com.spring.biz.addressbook.AddressbookDTO;

public interface AddressbookService {


    public void insert(AddressbookDTO addressbook);
    public void update(AddressbookDTO addressbook);
    public void delete(AddressbookDTO addressbook);
    public AddressbookDTO selectOne(AddressbookDTO addressbook);
    public AddressbookDTO exist(String email, String password);
    public List<AddressbookDTO> searchName(String name);
    public List<AddressbookDTO> searchTel(String tel);
    public List<AddressbookDTO> selectList();
	
}


