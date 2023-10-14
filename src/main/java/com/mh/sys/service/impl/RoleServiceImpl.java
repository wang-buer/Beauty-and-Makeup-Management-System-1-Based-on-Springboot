package com.mh.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.model.LayuiTableResult;
import com.mh.model.Result;
import com.mh.sys.entity.Resource;
import com.mh.sys.entity.Role;
import com.mh.sys.mapper.RoleMapper;
import com.mh.sys.service.RoleService;
import com.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author MH
 * @Date 2020/1/10 16:23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoles(String rname) {
        return roleMapper.getRoles(rname);
    }

    @Override
    public Result addOrUpdate(Role role) {
        if (role!=null){
            //修改
            if(role.getRid() != null && role.getRid()>0){
                role.setUpdateDate(LocalDateTime.now());
                roleMapper.updateById(role);
                return Result.ok("修改成功！");
            }else{ //添加
                QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
                Role rname = roleMapper.selectOne(queryWrapper.eq("rname", role.getRname()));
                if (rname != null){
                    if (rname.getIsdel() ==1){ //修改isdel字段
                        rname.setIsdel(0);
                        rname.setUpdateDate(LocalDateTime.now());
                        roleMapper.update(rname, queryWrapper.eq("rname", role.getRname()));
                        return Result.ok("添加成功！");
                    }else{
                        return Result.error("不能添加相同的角色名称！");
                    }
                } //添加
                role.setCreateDate(LocalDateTime.now());
                    roleMapper.insert(role);
                    return Result.ok("添加成功！");
            }
        }
        return Result.error("添加或修改失败！");
    }

    @Override
    public Result delete(Role role) {
        if (role != null && role.getRid()!=null){
            role.setIsdel(1);
            role.setUpdateDate(LocalDateTime.now());
            roleMapper.updateById(role);
            return Result.ok("删除成功！");
        }
        return Result.error("删除失败！");
    }

    @Override
    public LayuiTableResult getAllRoles(Integer rid) {
        List<Resource> res = roleMapper.getRes(rid);
        if (res != null){
            return LayuiTableResult.ok(res.size(),res);
        }
        return null;
    }
    @Override
    public Result setRes(String resIds, Integer rid) {
        try {
            roleMapper.delete(rid);
            resIds = resIds.substring(1, resIds.length() - 1);
            if (StringUtil.isNotEmpty(resIds)) {
                roleMapper.setres(resIds, rid);
            }
            return Result.ok("保存成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败！！");
        }
    }
}
