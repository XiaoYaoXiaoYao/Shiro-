package cn.xiaoyao.shiro_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";

    }

    @RequestMapping("/register")
    public String regist() {
        return "register";

    }

}
