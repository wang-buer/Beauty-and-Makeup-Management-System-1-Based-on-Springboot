package com.mh.emp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.emp.entity.EmpManager;
import com.mh.emp.entity.Emprattendance;
import com.mh.model.LayuiTableResult;
import com.mh.model.PageBean;
import com.mh.model.Result;
import com.mh.sys.entity.Emp;

public interface EmpManagerService extends IService<EmpManager> {
    LayuiTableResult getEmps(String empname, String emptype, PageBean pageBean);

    Result signIn(Emprattendance emprattendance);

    Result getEmpRattendance(Emp user);

    LayuiTableResult signList(Emprattendance emprattendance,PageBean pageBean);
}
