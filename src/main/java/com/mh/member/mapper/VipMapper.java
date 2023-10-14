package com.mh.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.mh.member.entity.Vip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author MH
 * @Date 2020/2/1 21:26
 */
@Repository
public interface VipMapper extends BaseMapper<Vip> {
     Integer getVipById(Integer vipid);
    List<Vip> getVipAndPackageList(@Param("id") Integer id);
}
