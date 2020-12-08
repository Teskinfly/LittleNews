package com.teskinfly.littlenewskr.mapper;

import com.teskinfly.littlenewskr.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> findAll();
    boolean insert(Menu menu);
    boolean delAll();
}
