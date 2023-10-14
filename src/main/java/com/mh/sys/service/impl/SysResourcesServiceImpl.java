package com.mh.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.model.Menu;
import com.mh.sys.entity.Resource;
import com.mh.sys.mapper.SysResourcesMapper;
import com.mh.sys.service.SysResourcesService;
import com.mh.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SysResourcesServiceImpl extends ServiceImpl<SysResourcesMapper, Resource> implements SysResourcesService {

    @Autowired
    private SysResourcesMapper sysResourceMapper;

    @Override
    public List<Menu> getMenu(HttpSession session) {
        Integer userId = SessionUtil.getUserId(session);
        return sysResourceMapper.getResources(userId);
    }
}
