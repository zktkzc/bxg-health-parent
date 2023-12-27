package com.itheima.controller.backend.security;

import com.itheima.pojo.Permission;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SpringSecurityUserService implements UserDetailsService {
    // 查找服务，实现查询数据库
    @Resource
    private UserService userService;

    // 根据用户名查询数据库中用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Map<String, Object> map = userService.getRoleAndPermission(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissionList = (List<Permission>) (map.get("permissionList"));
        for (Permission permission : permissionList) {
            authorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
