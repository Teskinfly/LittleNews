package com.teskinfly.littlenewskr.mapper;

import com.teskinfly.littlenewskr.domain.Topics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TopicsMapper {
    Topics findById(String id);
    List<Topics> findByLevel(int page, int size);
    boolean insert(Topics topics);
    boolean delById(String id);
    List<Topics> findByOpenid(String openid);
    List<Topics> findByDate(int page, int size);
    String getTotal(String openid);
}
