package com.mh.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;
import com.mh.sys.mapper.SysUserMapper;
import com.mh.sys.service.SysUserService;
import com.mh.util.ServletUtil;
import com.mh.util.SessionUtil;
import com.mh.util.ShiroUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * <p>
 * 用户表（pc端） 服务实现类
 * </p>
 *
 * @author xukh
 * @since 2019-12-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, Emp> implements SysUserService {

    @Autowired
    public SysUserMapper sysUserMapper;
    @Override
    public Result login(String username, String password) {
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return Result.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return Result.error("账号已被锁定,请联系管理员");
        }
        return Result.ok();
    }

    //修改密码
    @Override
    public Result updatePassword(String oldpassword, String newpassword) {
        Emp sysUser = SessionUtil.getUser(ServletUtil.getSession());
        if (sysUser.getPwd().equals(ShiroUtils.sha256(oldpassword,null))) {
            String newPassword1 = ShiroUtils.sha256(newpassword, null);
            Emp suser = new Emp();
            suser.setPwd(newPassword1);
            suser.setId(sysUser.getId());
            Integer total = sysUserMapper.updateById(suser);
            if (total > 0) {
                return Result.ok("密码重置成功！");
            } else {
                return Result.error("修改错误，请联系管理员！");
            }
        } else {
            return Result.error("旧密码填写错误！");
        }
    }

    @Override
    public LayuiTableResult getPage(String uname, PageBean pageBean) {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isdel", 0);
        queryWrapper.eq("isstop", 0);
        if (uname != "" & uname != null) {
            queryWrapper.like("uname", uname);
        }
        IPage<Emp> sysRoleIPage = sysUserMapper.selectPage(new Page<>(pageBean.getPage(),
                pageBean.getLimit()), queryWrapper);
        return LayuiTableResult.ok(sysRoleIPage.getTotal(), sysRoleIPage.getRecords());
    }
}
