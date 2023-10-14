package com.mh.member.mapper;

import com.mh.member.entity.Cuspay;
import com.mh.member.entity.CuspayArry;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuspayMapper {
    void addCuspay(Cuspay cuspay);

    List<Cuspay> getCustomerById(@Param("id") Integer id, @Param("empId") Integer empId);

    void updateCuspay(Cuspay cuspay);

    //根据顾客编号查询顾客买办的卡信息
    List<Cuspay> getCuspayList(@Param("id")Integer id);

    void addCuspays(CuspayArry[] cuspayArries);

    void addCuspaysByProduce(CuspayArry[] proTypes);
}
