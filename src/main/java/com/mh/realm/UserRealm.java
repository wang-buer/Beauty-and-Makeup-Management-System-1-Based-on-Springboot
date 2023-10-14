package com.mh.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mh.sys.entity.Emp;
import com.mh.sys.mapper.SysUserMapper;
import com.mh.util.ConstUtil;
import com.mh.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Li Ruihuan
 * @create 2019-06-30 10:38
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    private SysUserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("进入角色授权");
        Emp user= (Emp) principals.getPrimaryPrincipal();
        Integer userid = user.getId ();
        List<String> permsList= userMapper.queryAllPerms( userid );
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isEmpty( perms )){
                continue;
            }
            permsSet.addAll( Arrays.asList(perms.trim().split(",")));
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        System.out.println("------------start----------------");
        System.out.println(usernamePasswordToken.getUsername());
        System.out.println(usernamePasswordToken.getPassword());
        System.out.println("------------end----------------");
        QueryWrapper<Emp> wrapper = new QueryWrapper<>(  );
        wrapper.eq( "login",((UsernamePasswordToken) token).getUsername() );
        Emp user = userMapper.selectOne( wrapper );
        if(user==null){
            throw new UnknownAccountException("账号或密码不正确");
        }
        ShiroUtils.setSessionAttribute( ConstUtil.USER,user );
        return new SimpleAuthenticationInfo(user, user.getPwd(), null, getName());
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }



}
