package com.teskinfly.littlenewskr.controller;

import com.teskinfly.littlenewskr.domain.Menu;
import com.teskinfly.littlenewskr.mapper.NewsMapper;
import com.teskinfly.littlenewskr.service.MenuService;
import com.teskinfly.littlenewskr.service.NewsService;
import com.teskinfly.littlenewskr.utils.warm.MyWarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WarmController {
    @Autowired
    MenuService menuService;
    @Autowired
    NewsService newsService;
    @Autowired
    MyWarm myWarm;
    @GetMapping("/title")
    public boolean title() throws IOException {
        menuService.delAll();
        List<Menu> menuList = myWarm.getMenuList();
        for (Menu menu:menuList){
            menuService.insert(menu);
        }
        return true;
    }
    @GetMapping("/article")
    public boolean article() throws IOException {
        myWarm.getArticle();
        return true;
    }
}
