package com.mh.emp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.emp.entity.EmpManager;
import com.mh.emp.entity.Emprattendance;
import com.mh.emp.mapper.EmpManagerMapper;
import com.mh.emp.service.EmpManagerService;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author MH
 * @Date 2020/1/13 12:03
 * 员工管理
 */
@Service
public class EmpManagerServiceImpl extends ServiceImpl<EmpManagerMapper, EmpManager> implements
        EmpManagerService {

    @Autowired
    private EmpManagerMapper empManagerMapper;

    @Override
    public LayuiTableResult getEmps(String empname, String emptype, PageBean pageBean) {
        if(pageBean!=null){
            pageBean.setPage((pageBean.getPage()-1)*pageBean.getLimit());
        }
        List<EmpManager> empManagers= empManagerMapper.getEmps(empname, emptype, pageBean);
        //获取条数
        List<EmpManager> empCount = empManagerMapper.getEmps(empname, emptype, null);
        if (empCount ==null){
            empCount=new ArrayList<>();
        }
        int count=0;
        if (empCount.size()>0){
            count=empCount.size();
        }
        return LayuiTableResult.ok(count,empManagers);
    }

    @Override
    public Result signIn(Emprattendance emprattendance) {

        try{
            //1. 查看有没有签到

            //2.没有签到在签到
            emprattendance.setIndate(LocalDateTime.now());
            empManagerMapper.signIn(emprattendance);
           if (emprattendance.getIsin()){
               return Result.error("签到成功！");
           }else {
               return Result.error("更改成功！");
           }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("签到失败！");
        }
    }

    @Override
    public Result getEmpRattendance(Emp user) {
        Emprattendance dance=new Emprattendance();
        dance.setEmpid(user.getId());
        LocalDateTime localDateTime=LocalDateTime.now();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      try {
          Emprattendance da= empManagerMapper.getEmpRattendance(dance,format);
          if (da !=null){
              return Result.ok("已签到");
          }
          return Result.ok("未签到");
      }catch (Exception e){
          e.printStackTrace();
          return  Result.error("出现错误");
      }
    }

    @Override
    public LayuiTableResult signList(Emprattendance emprattendance, PageBean pageBean) {
        if (pageBean !=null){
            pageBean.setPage((pageBean.getPage()-1)*pageBean.getLimit());
        }
        try {
            List<Emprattendance> dances = empManagerMapper.signList(emprattendance, pageBean);
            List<Emprattendance> count = empManagerMapper.signList(emprattendance, null);
            return LayuiTableResult.ok(count.size(),dances);
        }catch (Exception e){
            e.printStackTrace();
        }
        return LayuiTableResult.ok(0,null);
    }

}
