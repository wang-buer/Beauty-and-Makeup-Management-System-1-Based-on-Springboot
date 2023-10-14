package com.mh.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;


public interface UserManagerService extends IService<Emp> {
    LayuiTableResult getUsers(String userName, PageBean pageBean);
    Result addOrUpdate(Emp emp);

    Result delete(String id);

    Result setRole(Integer uid, Integer rid, String rname);
}


