package com.mh.emp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.emp.entity.EmpManager;
import com.mh.emp.entity.Emprattendance;
import com.mh.model.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工管理
 */
@Repository
public interface EmpManagerMapper extends BaseMapper<EmpManager> {
    List<EmpManager> getEmps(@Param("empname") String empname,
                             @Param("emptype") String emptype,
                             @Param("pageBean") PageBean pageBean);

    void signIn(@Param("emprattendance") Emprattendance emprattendance);

    Emprattendance getEmpRattendance(@Param("dance") Emprattendance dance,
                                     @Param("format") String format);

    List<Emprattendance> signList(@Param("dance") Emprattendance emprattendance,
                                  @Param("pageBean") PageBean pageBean);
}
