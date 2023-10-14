package com.mh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.model.PageBean;
import com.mh.sys.entity.Emp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserManagerMapper extends BaseMapper<Emp> {
    /*查询员工信息*/
    List<Emp> getUsers(@Param("userName") String userName,
                       @Param("pageBean") PageBean pageBean);
}
