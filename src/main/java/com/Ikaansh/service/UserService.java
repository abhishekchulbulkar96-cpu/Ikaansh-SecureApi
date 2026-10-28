package com.Ikaansh.service;

import com.Ikaansh.entity.User;

public interface UserService {

    User registerUser(String name, String email, String password);
    
    String loginUser(String email, String password);

}