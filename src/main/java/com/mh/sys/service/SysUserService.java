package com.mh.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;

/**
 * <p>
 * 用户表（pc端） 服务类
 * </p>
 *
 * @author xukh
 * @since 2019-12-02
 */
public interface SysUserService extends IService<Emp> {
    public Result login(String username, String password);

    //修改密码
    public Result updatePassword(String oldpassword, String newpassword);

    public LayuiTableResult getPage(String uname, PageBean pageBean);
}
