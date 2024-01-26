package com.spring.biz.addressbook.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.addressbook.AddressbookDTO;


@Service("addressbookService")
public class AddressbookServiceImpl implements AddressbookService {

    @Autowired
    private AddressbookDAO addressbookDAO;

    @Override
    public void insert(AddressbookDTO addressbook) {
        addressbookDAO.insert(addressbook);
    }

    @Override
    public void update(AddressbookDTO addressbook) {
        addressbookDAO.update(addressbook);
    }

    @Override
    public void delete(AddressbookDTO addressbook) {
        addressbookDAO.delete(addressbook);
    }

    @Override
    public AddressbookDTO selectOne(AddressbookDTO addressbook) {
        return addressbookDAO.selectOne(addressbook);
    }

    @Override
    public AddressbookDTO exist(String email, String password) {
        return addressbookDAO.exist(email, password);
    }

    @Override
    public List<AddressbookDTO> searchName(String name) {
        return addressbookDAO.searchName(name);
    }

    @Override
    public List<AddressbookDTO> searchTel(String tel) {
        return addressbookDAO.searchTel(tel);
    }

    @Override
    public List<AddressbookDTO> selectList() {
        return addressbookDAO.selectList();
    }
}
