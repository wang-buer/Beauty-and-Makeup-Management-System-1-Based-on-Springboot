package com.mh.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.omg.CORBA.IDLType;
import org.springframework.data.annotation.Id;

/**
 * @Author MH
 * @Date 2020/1/10 17:01
 * 员工角色表
 *
 */
@Data
@TableName(value = "emp_role")
public class UserRole {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer empid;
    private Integer roleid;
}
