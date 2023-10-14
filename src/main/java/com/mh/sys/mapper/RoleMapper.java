package com.mh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.sys.entity.Resource;
import com.mh.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author MH
 * @Date 2020/1/10 16:21
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoles(@Param("rname") String rname);
   List<Resource>  getRes(@Param("rid") Integer rid);

    void setres(@Param("resIds") String resIds,@Param("rid") Integer rid);

    void delete(@Param("rid") Integer rid);
}
