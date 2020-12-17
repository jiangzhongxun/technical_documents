package com.bai.config;

import com.bai.mapper.UserMapper;
import com.bai.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/16 22:44
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    /**
     * 认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了->认证doGetAuthorizationInfo");
        return null;
    }

    /**
     * 授权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*
         * authenticationToken 打印信息：
         * org.apache.shiro.authc.UsernamePasswordToken - admin, rememberMe=false
         * */
        System.out.println("执行了->授权doGetAuthenticationInfo -> " + authenticationToken);
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userMapper.queryUser(token.getUsername());
        if (user == null) {
            // 抛出异常 UnknownAccountException 用户名错误
            return null;
        }
        // 密码认证 shiro 自己做
        return new SimpleAuthenticationInfo("", user.getPwd(), "");
    }
}
