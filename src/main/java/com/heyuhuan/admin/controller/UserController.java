package com.heyuhuan.admin.controller;

import com.heyuhuan.admin.pojo.User;
import com.heyuhuan.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用户控制器类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @RequestMapping
    public String index() {
        User user = userService.getUserById(1);
        logger.info(user.toString());
        int result = userService.insertUser();
        logger.info(String.valueOf(result));
        return "index";
    }

}