package com.mh.package_ljl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mh.member.entity.Produces;
import com.mh.package_ljl.entity.Package;
import com.mh.package_ljl.entity.PagePro;

import java.util.List;

public interface PageproService extends IService<PagePro> {
    List<PagePro> goPackageInfoPage(Integer pid);

    List<Produces> getlist();

    int delete(QueryWrapper queryWrapper);

    int insertBath(List<PagePro> listPro);
}
