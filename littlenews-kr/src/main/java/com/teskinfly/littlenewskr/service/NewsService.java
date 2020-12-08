package com.teskinfly.littlenewskr.service;

import com.teskinfly.littlenewskr.domain.News;
import com.teskinfly.littlenewskr.mapper.NewsMapper;
import com.teskinfly.littlenewskr.utils.warm.MyWarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    MyWarm myWarm;
    public List<News> findByMId(int mId, int page, int size){
        return newsMapper.findByMId(mId, page,size);
    }
    public boolean insert(News news) throws IOException {
        return newsMapper.insert(news);
    }
    public boolean delAll(){
        return newsMapper.delAll();
    }
    public News findById(int id){
        News byId = newsMapper.findById(id);
        return byId;
    }
}
