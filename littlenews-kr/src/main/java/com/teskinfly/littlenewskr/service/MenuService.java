package com.teskinfly.littlenewskr.service;

import com.teskinfly.littlenewskr.domain.Menu;
import com.teskinfly.littlenewskr.mapper.MenuMapper;
import com.teskinfly.littlenewskr.utils.warm.MyWarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    public List<Menu> findAll(){
      return menuMapper.findAll();
    }
    public boolean insert(Menu menu) throws IOException {
        return menuMapper.insert(menu);
    }
    public boolean delAll(){
        return menuMapper.delAll();
    }

}
