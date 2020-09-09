package cn.xiaoyao.shiro_demo.config.shiro;

import cn.xiaoyao.shiro_demo.entity.Menu;
import cn.xiaoyao.shiro_demo.entity.Role;
import cn.xiaoyao.shiro_demo.entity.User;
import cn.xiaoyao.shiro_demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private PermService permService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息(用户名)
        User primaryPrincipal = (User) principals.getPrimaryPrincipal();
        //根据用户的用户名在数据库中查询用户的角色和权限
        User user = userService.findRolesByUserName(primaryPrincipal.getUsername());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user != null) {
            System.out.println("开始进行授权操作" + user.toString());
            List<Role> roles = user.getRoles();
            Set<String> roleList = new HashSet<>();
            for (Role role : roles) {
                roleList.add(role.getRoleName());
                List<Menu> perms = userService.findPermsByRoleId(role.getId());
                for (Menu perm : perms) {
                    info.addStringPermission(perm.getPerms());
                }
            }
            info.addRoles(roleList);
            return info;
        }
        return null;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中得到认证信息
        String username = (String) token.getPrincipal();
        User userByUserName = userService.findUserByUserName(username);
        if (userByUserName == null) {
            throw new UnknownAccountException("用户名错误");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userByUserName,
                userByUserName.getPassword(), new SimpleByteSource(userByUserName.getSalt()), this.getName());
        return info;

    }


}
