package com.mh.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.sys.entity.Emp;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface SysUserMapper extends BaseMapper<Emp> {

     List<String> queryAllPerms(Integer userid);

}
