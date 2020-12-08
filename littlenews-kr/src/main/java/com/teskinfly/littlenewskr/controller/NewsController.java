package com.teskinfly.littlenewskr.controller;

import com.teskinfly.littlenewskr.domain.News;
import com.teskinfly.littlenewskr.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;
    @GetMapping("/news/{mid}/{page}/{size}")
    List<News> getNews(@PathVariable("mid") int mid, @PathVariable("page") int page,@PathVariable int size){
        List<News> byMId = newsService.findByMId(mid, page, size);
        return byMId;
    }
    @GetMapping("/news/{id}")
    News getNewsById(@PathVariable("id") int id){

        return newsService.findById(id);
    }
}
