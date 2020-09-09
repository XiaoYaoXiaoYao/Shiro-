package cn.xiaoyao.shiro_demo.controller;

import cn.xiaoyao.shiro_demo.entity.User;
import cn.xiaoyao.shiro_demo.service.UserService;
import cn.xiaoyao.shiro_demo.utils.SaltUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;


    @RequestMapping("/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "/index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            log.info("用户名错误");

        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            log.info("密码错误");

        }
        return "/login";

    }


    @RequestMapping("/add")
    public String add() {
        return "/user";

    }
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";

    }


    @RequestMapping("/register")
    public String register(String username, String password) {
        User user = new User();
        //生成随机盐
        String salt = SaltUtil.getSalt(8);
        //将密码和盐进行加密(明文密码+salt+hash散列)
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        String s = md5Hash.toHex();
        user.setUsername(username);
        user.setPassword(s);
        user.setSalt(salt);

        userService.insertUser(user);
        return "/login";


    }


}
