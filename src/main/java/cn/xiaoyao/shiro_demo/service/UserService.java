package cn.xiaoyao.shiro_demo.service;

import cn.xiaoyao.shiro_demo.dao.UserMapper;
import cn.xiaoyao.shiro_demo.entity.Menu;
import cn.xiaoyao.shiro_demo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public User findUserByUserName(String username) {

        User user=userMapper.findUserByUserName(username);
        return user;

    }


    public void insertUser(User user) {
        userMapper.insertUser(user);

    }

    public User findRolesByUserName(String primaryPrincipal) {
       return userMapper.findRolesByUserName(primaryPrincipal);
    }

    public List<Menu> findPermsByRoleId(int id) {
       return userMapper.findPermsByRoleId(id);

    }
}
