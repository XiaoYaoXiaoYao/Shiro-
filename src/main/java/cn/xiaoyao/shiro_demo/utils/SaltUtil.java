package cn.xiaoyao.shiro_demo.utils;

import java.util.Random;

public class SaltUtil {

    public static  String  getSalt(int  length) {
        StringBuilder stringBuilder = new StringBuilder();
        char [] _index = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+".toCharArray();
        for (int i = 0; i < length; i++) {
            char c = _index[new Random().nextInt(_index.length)];
            stringBuilder.append(c);


        }
        return stringBuilder.toString();
    }
}
