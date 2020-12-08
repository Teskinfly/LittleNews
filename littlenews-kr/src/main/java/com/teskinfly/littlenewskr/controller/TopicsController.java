package com.teskinfly.littlenewskr.controller;

import com.teskinfly.littlenewskr.domain.Topics;
import com.teskinfly.littlenewskr.mapper.TopicsMapper;
import com.teskinfly.littlenewskr.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("/topics")
public class TopicsController  {
    @Autowired
    TopicsService service;
    @GetMapping("/topics/{id}")
    public Topics findById(@PathVariable String id) throws IOException {
        return service.findById(id);
    }
    @GetMapping("/topics/level/{page}/{size}")
    public List<Topics> findByLevel(@PathVariable("page") int page, @PathVariable("size") int size) throws IOException {
        return service.findByLevel(page,size);
    }
    @PostMapping("/topics")
    public boolean insert(@RequestBody Topics topics) throws IOException {
        System.out.println(topics);
        return service.insert(topics);
    }
    @DeleteMapping("/topics/{id}")
    public boolean delById(@PathVariable String id) throws IOException {
        return service.delById(id);
    }
    @GetMapping("/topics/openid/{openid}")
    public List<Topics> findByOpenid(@PathVariable String openid) throws IOException {
        return service.findByOpenid(openid);
    }
    @GetMapping("/topics/date/{page}/{size}")
    public List<Topics> findByDate(@PathVariable("page") int page, @PathVariable("size") int size) throws IOException {
        return service.findByDate(page,size);
    }
    @GetMapping("/topics/total/{openid}")
    public String getTotal(@PathVariable String openid)throws IOException {
        return service.getTotal(openid);
    }
    @GetMapping("/topics/search/{keyword}")
    public List<Topics> search(@PathVariable String keyword) throws IOException {
        return service.findByKeyword("title",keyword);
    }
    @GetMapping("/topics/increase/{id}")
    public boolean increase(@PathVariable String id) throws IOException {
        String name = "discussNum";
        int amount = 1;
        return service.addNum(id,name,amount);
    }
}
