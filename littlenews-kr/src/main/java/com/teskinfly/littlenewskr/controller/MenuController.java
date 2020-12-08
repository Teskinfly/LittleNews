package com.teskinfly.littlenewskr.controller;

import com.teskinfly.littlenewskr.domain.Menu;
import com.teskinfly.littlenewskr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    MenuService service;
    @GetMapping("/menu")
    public List<Menu> get(){
        return service.findAll();
    }
}
