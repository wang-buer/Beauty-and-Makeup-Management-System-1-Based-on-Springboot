package com.mh.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.member.entity.Customer;
import com.mh.model.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author MH
 * @Date 2020/1/16 19:23
 */
@Repository
public interface MemberManagerMapper extends BaseMapper<Customer> {
    List<Customer> pageQuery(@Param("customer") Customer customer,@Param("pageBean") PageBean pageBean);
}
