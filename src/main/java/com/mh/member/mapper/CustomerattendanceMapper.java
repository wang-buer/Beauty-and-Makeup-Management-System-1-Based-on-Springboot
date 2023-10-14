package com.mh.member.mapper;

import com.mh.member.entity.Customerattendance;
import com.mh.member.entity.Echarts;
import com.mh.model.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author MH
 * @Date 2020/2/4 21:33
 */
@Repository
public interface CustomerattendanceMapper {
    List<Customerattendance> getCustomerattendanceList(@Param("id") Integer id,@Param("page") Integer page,@Param("limit") Integer limit);

    void addCustomerattendance(Customerattendance dance);

    void addCheckingInfo(@Param("uid") Integer uid,@Param("cid") Integer cid,@Param("isin") Integer isin,@Param("whyNo") String whyNo);

    List<Integer> selectCheckingInfo(@Param("cid") Integer cid);

    Customerattendance selectCheckingByid(@Param("id") Integer id);

    List<Echarts> getEchartsData(@Param("month") int month);
}
