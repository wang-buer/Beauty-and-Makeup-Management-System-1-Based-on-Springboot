package com.mh.package_ljl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.member.entity.Produces;
import com.mh.package_ljl.entity.Package;
import com.mh.package_ljl.entity.PagePro;
import com.mh.product.entity.CusPayL;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PackageService {
    List<Package> getPackageList();//获取套餐集合
    int addPackage(Package p);//添加套餐
    int delPackage(@Param("pid")Integer pid);//删除套餐
    int delp_pac(@Param("pid")Integer pid);//删除套餐
    int modPackage(Package p);//修改套餐
    List<CusPayL> getpacReport(@Param("year") String year,
                         @Param("month")String month,
                         @Param("prevMonth")String prevMonth,
                         @Param("empid")Integer empid);//个人销售记录（套餐）
    Package getOnePac(  @Param("pid")Integer pid);
    List<PagePro> getpac_pronifoList(@Param("pid")Integer pid);
    List<Package> getPackageList_withStatus();

    Package getPackage(Integer pid);

    int updatePackage(Package aPackage, List<Produces> pro);
}
