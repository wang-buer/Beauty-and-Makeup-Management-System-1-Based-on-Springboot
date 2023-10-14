package com.mh.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.model.LayuiTableResult;
import com.mh.model.Result;
import com.mh.sys.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Role> getRoles(String rname);

    Result addOrUpdate(Role role);

    Result delete(Role role);

    LayuiTableResult getAllRoles(Integer rid);

    Result setRes(String resIds, Integer rid);
}
