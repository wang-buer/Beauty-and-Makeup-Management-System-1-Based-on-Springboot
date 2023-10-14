package com.mh.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mh.member.entity.Produces;
import com.mh.member.mapper.ProduceMapper;
import com.mh.member.service.ProduceService;
import org.springframework.stereotype.Service;

/**
 * @Author MH
 * @Date 2020/4/4 17:38
 */
@Service
public class ProduceServiceImpl extends ServiceImpl<ProduceMapper, Produces> implements ProduceService {
}
