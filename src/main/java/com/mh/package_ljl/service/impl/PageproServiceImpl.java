package com.mh.package_ljl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.member.entity.Produces;
import com.mh.member.mapper.ProduceMapper;
import com.mh.package_ljl.entity.Package;
import com.mh.package_ljl.entity.PagePro;
import com.mh.package_ljl.mapper.PageproMapper;
import com.mh.package_ljl.service.PageproService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author MH
 * @Date 2020/4/4 17:27
 */
@Service
public class PageproServiceImpl extends ServiceImpl<PageproMapper, PagePro> implements PageproService {
    @Autowired
    private PageproMapper pageproMapper;
    @Autowired
    private ProduceMapper produceMapper;

    @Override
    public List<PagePro> goPackageInfoPage(Integer pid) {
        QueryWrapper<PagePro> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("packageid",pid);
        List<PagePro> list = pageproMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<Produces> getlist() {
        List<Produces> produces = produceMapper.selectList(new QueryWrapper<>());
        return produces;
    }

    @Override
    public int delete(QueryWrapper queryWrapper) {

        return pageproMapper.delete(queryWrapper);
    }

    @Override
    public int insertBath(List<PagePro> listPro) {
        int insert=0;
        for (PagePro pagePro : listPro) {
             insert = pageproMapper.insert(pagePro);
        }
        return insert;
    }
}
