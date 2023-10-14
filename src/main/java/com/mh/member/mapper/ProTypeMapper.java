package com.mh.member.mapper;

import com.mh.member.entity.ProTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProTypeMapper {

    List<ProTypes> getProduceList();
}
