package com.itheima.service.impl;

import com.itheima.dao.*;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleLMapper userRoleLMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 获取用户角色与权限
     *
     * @param username
     * @return
     */
    @Override
    public Map<String, Object> getRoleAndPermission(String username) {
        User user = userMapper.selectByUsername(username);
        Map<String, Object> map = new HashMap<>();
        List<Role> roleList = new ArrayList<>();
        List<Permission> permissionList = new ArrayList<>();
        List<Integer> roleIds = userRoleLMapper.selectByUserId(user.getId());
        for (Integer roleId : roleIds) {
            Role role = roleMapper.selectById(roleId);
            roleList.add(role);
            List<Integer> permissionIds = rolePermissionMapper.selectByRoleId(roleId);
            for (Integer permissionId : permissionIds) {
                Permission permission = permissionMapper.selectById(permissionId);
                permissionList.add(permission);
            }
        }
        map.put("roleList", roleList);
        map.put("permissionList", permissionList);
        return map;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
