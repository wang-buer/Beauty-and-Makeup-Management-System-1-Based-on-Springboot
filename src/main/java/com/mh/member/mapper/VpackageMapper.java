package com.mh.member.mapper;

import com.mh.member.entity.Vpackage;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VpackageMapper {
   List<Vpackage> getPackageAndProduceList(@Param("id")Integer id);

   void addCustomerAndPackage(@Param("cid") Integer cid,@Param("pid") Integer pid);

    List<Vpackage> getPackageList();
}
