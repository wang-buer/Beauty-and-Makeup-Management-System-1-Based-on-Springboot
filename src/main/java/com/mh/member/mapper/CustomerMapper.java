package com.mh.member.mapper;

import com.mh.member.entity.Customer;
import com.mh.member.entity.CustomerEmp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface CustomerMapper {

    int addCustomer(Customer customer);

    void updateById(@Param("id") Integer id);

    void addCustomerEmp(@Param("cid") Integer cid,@Param("eid") Integer id);

    Customer getCustomerById(@Param("id") Integer id);

    List<CustomerEmp> operating(@Param("id") Integer id,@Param("cid") Integer cid);

    void updateCustomer(Customer customer);
}
