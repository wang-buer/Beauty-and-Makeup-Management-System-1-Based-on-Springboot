package com.mh.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;
import com.mh.sys.entity.UserRole;
import com.mh.sys.mapper.UserManagerMapper;
import com.mh.sys.mapper.UserRoleMapper;
import com.mh.sys.service.UserManagerService;
import com.mh.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @Author MH
 * @Date 2020/1/10 10:20
 */
@Service
public class UserManagerServiceImpl extends ServiceImpl<UserManagerMapper, Emp> implements UserManagerService {
    @Autowired
    private UserManagerMapper userManagerMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public LayuiTableResult getUsers(String userName, PageBean pageBean) {
        if (pageBean!=null){
            pageBean.setPage((pageBean.getPage()-1)*pageBean.getLimit());
        }
        List<Emp> users = userManagerMapper.getUsers(userName, pageBean);
        List<Emp> count = userManagerMapper.getUsers(userName, null);
        System.out.println(users);
        System.out.println(count.size());
        return LayuiTableResult.ok(count.size(),users);
    }

    @Override
    public Result addOrUpdate(Emp emp) {
        System.out.println("--:"+emp);
        try {
            if(emp != null){
                if (emp.getId() != null && emp.getId() > 0) { //修改
                    emp.setUpdateDate(LocalDateTime.now());
                    userManagerMapper.updateById(emp);

                    return Result.ok("更新成功");
                } else { //添加
                    QueryWrapper<Emp> wrapper = new QueryWrapper<>();
                    Emp emp1 = userManagerMapper.selectOne(wrapper.eq("login", emp.getLogin()));
                    System.out.println("emp1:" + emp1);
                    if (emp1 != null) { //数据库有这个用户，但是被禁用了，现在需要改变用户的状态
                        if (emp1.getIsdel() == 1) {
                            emp1.setIsdel(0);
                            emp1.setUpdateDate(LocalDateTime.now());
                            userManagerMapper.update(emp1, wrapper.eq("login", emp1.getLogin()));
                        }
                        return Result.error("账号已存在，请重新输入！");
                    } else { //添加
                        emp.setPwd(ShiroUtils.sha256(emp.getPwd(), null));
                        System.out.println("----------------");
                        System.out.println(emp);
                        emp.setCreateDate(LocalDateTime.now());
                        int insert = userManagerMapper.insert(emp);
                        Emp emp2 = userManagerMapper.selectOne(wrapper.eq("login", emp.getLogin()));
                        System.out.println("emp2:" + emp2);
                        //向用户角色表中添加一条数据
                        UserRole userRole = new UserRole();
                        userRole.setEmpid(emp2.getId());
                        userRole.setRoleid(2); //默认添加为员工的id
                        userRoleMapper.insert(userRole);
                        return Result.ok("添加成功！");

                    }
                }
            }else{
               return Result.error("数据获取异常!");
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.error("用户添加出现错误！");
        }
    }

    @Override
    public Result delete(String id) {
        id = id.replace("[", "").replace("]", "");
        try {
            String[] split = id.split(",");
            if (split.length > 0) {
                Emp emp = new Emp();
                emp.setIsdel(1);
                QueryWrapper<Emp> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper.in("id", Arrays.asList(split));
                userManagerMapper.update(emp, userQueryWrapper);
            }
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！");
        }
    }
    @Override
    public Result setRole(Integer uid, Integer rid, String rname) {
        try {
            int empid = userRoleMapper.delete(new QueryWrapper<UserRole>().eq("empid", uid));
            System.out.println("empid:"+empid);
            UserRole userRole = new UserRole();
            userRole.setRoleid(rid);
            userRole.setEmpid(uid);
            int insert = userRoleMapper.insert(userRole);
            System.out.println("insert:"+insert);
            return Result.ok("设置成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("设置失败！");
        }
    }
}
