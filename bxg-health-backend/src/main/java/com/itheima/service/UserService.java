package com.itheima.service;

import com.itheima.pojo.User;

import java.util.Map;

public interface UserService {
    /**
     * 获取用户角色与权限
     *
     * @param username
     * @return
     */
    Map<String, Object> getRoleAndPermission(String username);

    User findByUsername(String username);
}
