package com.teskinfly.littlenewskr.mapper;

import com.teskinfly.littlenewskr.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    List<News> findByMId(int mId, int page, int size);
    boolean insert(News news);
    boolean delAll();
    News findById(int id);
}
