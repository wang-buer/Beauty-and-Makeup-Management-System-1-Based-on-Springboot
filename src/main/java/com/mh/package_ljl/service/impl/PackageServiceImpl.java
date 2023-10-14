package com.mh.package_ljl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.member.entity.Produces;
import com.mh.package_ljl.entity.Package;
import com.mh.package_ljl.entity.PagePro;
import com.mh.package_ljl.mapper.PackageMapper;
import com.mh.package_ljl.mapper.PageproMapper;
import com.mh.package_ljl.service.PackageService;
import com.mh.package_ljl.service.PageproService;
import com.mh.product.entity.CusPayL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PackageServiceImpl extends ServiceImpl<PackageMapper, Package> implements PackageService {
    @Autowired
    private PackageMapper pm;
    @Autowired
    private PageproService pageproService;

    @Override
    public List<Package> getPackageList() {
        return pm.getPackageList();
    }

    @Override
    public int addPackage(Package p) {
        return pm.addPackage(p);
    }

    @Override
    public int delPackage(Integer pid) {
        return pm.delPackage(pid);
    }

    @Override
    public int delp_pac(Integer pid) {
        return pm.delp_pac(pid);
    }

    @Override
    public int modPackage(Package p) {
        return pm.modPackage(p);
    }

    @Override
    public  List<CusPayL> getpacReport(String year, String month, String prevMonth, Integer empid) {
        return pm.getpacReport(year,month,prevMonth,empid);
    }

    @Override
    public Package getOnePac(Integer pid) {
        return pm.getOnePac(pid);
    }

    @Override
    public List<PagePro> getpac_pronifoList(Integer pid) {
        return pm.getpac_pronifoList(pid);
    }

    @Override
    public Package getPackage(Integer pid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid", pid);
        return pm.selectOne(queryWrapper);
    }


    @Override
    public List<Package> getPackageList_withStatus() {
        return pm.getPackageList_withStatus();
    }

    @Override
    public int updatePackage(Package aPackage, List<Produces> pro) {
        aPackage.setModifyDate(new Date());
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("pid",aPackage.getPid());
        pm.update(aPackage,queryWrapper2);
        //更新关联的产品
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("packageid", aPackage.getPid());
        pageproService.delete(queryWrapper);
        List<PagePro> listPro = new ArrayList<>();
        for (Produces produces : pro) {
            PagePro pagePro = new PagePro();
            pagePro.setPackageid(aPackage.getPid());
            pagePro.setRoduceid(produces.getId());
            pagePro.setNum(produces.getNum());
            listPro.add(pagePro);
        }
         pageproService.insertBath(listPro);
        return 1;
    }
}
