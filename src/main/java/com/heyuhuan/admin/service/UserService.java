package com.heyuhuan.admin.service;

import com.heyuhuan.admin.pojo.User;

public interface UserService {
    User getUserById(Integer id);

    int insertUser();
}
