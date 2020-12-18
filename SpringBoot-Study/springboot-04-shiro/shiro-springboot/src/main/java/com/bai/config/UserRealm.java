package com.bai.config;

import com.bai.mapper.UserMapper;
import com.bai.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/16 22:44
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    /**
     * 认证,每次访问接口时都会执行此方法,查询当前用户是否具备某些权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*
         * principalCollection 打印信息：
         * User{id=6, name='root', pwd='123456', perms='user:add,user:update'}
         * */
        System.err.println("执行了->认证doGetAuthorizationInfo ->" + principalCollection);

        // 获取到当前登陆用户
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        // 获取当前用户所具备的全部权限
        if (!StringUtils.hasLength(currentUser.getPerms())) {
            // 当前用户并不具备任何权限
            return null;
        }
        // 将当前登陆用户的权限添加到 SimpleAuthorizationInfo 中并返回认证信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String[] perms = currentUser.getPerms().split(",");
        Stream.of(perms).forEach(info::addStringPermission);
        return info;
    }

    /**
     * 授权,每次登陆时都会执行此方法,查询当前用户账号密码是否正确,正确即可登陆成功,错误则不能正确授权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /*
         * authenticationToken 打印信息：
         * org.apache.shiro.authc.UsernamePasswordToken - admin, rememberMe=false
         * */
        System.err.println("执行了->授权doGetAuthenticationInfo -> " + authenticationToken);
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userMapper.queryUser(token.getUsername());
        if (user == null) {
            // 抛出异常 UnknownAccountException 用户名错误
            return null;
        }
        // 登陆成功后将 user 存入 shiro 的 session 中去
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("loginUser", user);
        // 密码认证 shiro 自己做
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
