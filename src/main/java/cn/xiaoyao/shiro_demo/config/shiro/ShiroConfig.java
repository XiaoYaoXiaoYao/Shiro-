package cn.xiaoyao.shiro_demo.config.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    /*
        1.shirofilter过滤器
        2.安全管理器
        3.自定义的Realm
        4.引入thymeleaf和shiro的整合(shiro的方言)
     */



    //1.shirofilter过滤器
    @Bean
    public ShiroFilterChainDefinition getShiroFilterChainDefinition() {

        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //哪些请求可以匿名访问
        chain.addPathDefinition("/login", "anon");
        chain.addPathDefinition("/register", "anon");
        chain.addPathDefinition("/user/login", "anon");
        chain.addPathDefinition("/user/register", "anon");

        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");
        return chain;

    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    //3.自定义的Realm
    @Bean
    public Realm getRealm() {

        CustomRealm customRealm = new CustomRealm();

        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customRealm.setCredentialsMatcher(credentialsMatcher);

//        //开启缓存管理
//        customRealm.setCacheManager(new RedisCacheManager());
//        customRealm.setCachingEnabled(true);//开启全局缓存
//        customRealm.setAuthenticationCachingEnabled(true);//认证认证缓存
//        customRealm.setAuthenticationCacheName("authenticationCache");
//        customRealm.setAuthorizationCachingEnabled(true);//开启授权缓存
//        customRealm.setAuthorizationCacheName("authorizationCache");

        return customRealm;

    }

    /**
     * 4.thymeleaf模板引擎和shiro框架的整合(引入shiro的方言)
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}