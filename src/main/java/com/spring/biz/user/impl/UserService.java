package com.spring.biz.user.impl;

import java.util.List;

import com.spring.biz.user.UserDTO;

public interface UserService {

    public UserDTO exist(String email, String password);
	
}


