package com.mh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.model.Menu;
import com.mh.sys.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysResourcesMapper extends BaseMapper<Resource> {
    //根据用户id查询对应菜单
   public List<Menu> getResources(@Param("userId") Integer userId);
}
