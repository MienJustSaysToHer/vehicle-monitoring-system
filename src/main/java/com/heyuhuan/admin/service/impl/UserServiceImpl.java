package com.heyuhuan.admin.service.impl;

import com.heyuhuan.admin.mapper.UserMapper;
import com.heyuhuan.admin.pojo.User;
import com.heyuhuan.admin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertUser() {
        User user = new User();
        user.setUserName("hyh");
        user.setPassword("huan");
        user.setAge(24);
        return userMapper.insert(user);
    }
}
