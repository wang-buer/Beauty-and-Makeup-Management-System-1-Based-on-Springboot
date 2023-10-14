package com.mh.package_ljl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.package_ljl.entity.Package;
import com.mh.package_ljl.entity.PagePro;
import com.mh.product.entity.CusPayL;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PackageMapper extends BaseMapper<Package> {
    List<Package> getPackageList();//获取套餐集合

    int addPackage(Package p);//添加套餐
    int addpagepro(PagePro pagePro);//套惨对应的产品


    int delPackage(@Param("pid")Integer pid);//删除套餐
    int delp_pac(@Param("pid")Integer pid);//删除套餐
    List<Package> getPackageList_withStatus();

    int modPackage(Package p);//修改套餐
    List<CusPayL> getpacReport(@Param("year") String year,
                         @Param("month")String month,
                         @Param("prevMonth")String prevMonth,
                         @Param("empid")Integer empid);//个人销售记录（套餐）

    Package getOnePac(  @Param("pid")Integer pid);
    List<PagePro> getpac_pronifoList( @Param("pid")Integer pid);
}
