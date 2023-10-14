package com.mh.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mh.member.entity.Produces;
import com.mh.product.entity.Produce;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduceMapper extends BaseMapper<Produces> {
   List<Produces> getProduceList(@Param("id") Integer id);

    void updateRepertory(Produces[] produces);
}
