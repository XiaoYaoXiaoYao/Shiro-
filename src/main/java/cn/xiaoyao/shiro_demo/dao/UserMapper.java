package cn.xiaoyao.shiro_demo.dao;

import cn.xiaoyao.shiro_demo.entity.Menu;
import cn.xiaoyao.shiro_demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {


    User findUserByUserName(String username);

    void insertUser( User user);

    User findRolesByUserName(String primaryPrincipal);

    List<Menu> findPermsByRoleId(int id);
}
