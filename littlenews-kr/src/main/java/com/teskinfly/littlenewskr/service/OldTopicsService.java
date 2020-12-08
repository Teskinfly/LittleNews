package com.teskinfly.littlenewskr.service;

import com.teskinfly.littlenewskr.domain.Topics;
import com.teskinfly.littlenewskr.mapper.TopicsMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OldTopicsService {
    public static final String DB = "topics";
    @Autowired
    TopicsMapper mapper;
    public Topics findById(String id) throws IOException {
        return mapper.findById(id);
    }

    public List<Topics> findByLevel(int page, int size) {
        return mapper.findByLevel(page,size);
    }

    public boolean insert(Topics topics) {
        topics.setAddDay(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        return mapper.insert(topics);
    }

    public boolean delById(String id) {
        return mapper.delById(id);
    }

    public List<Topics> findByOpenid(String openid) {
        return mapper.findByOpenid(openid);
    }

    public List<Topics> findByDate(int page, int size) {
        return mapper.findByDate(page,size);
    }
    public String getTotal(String openid){
        return mapper.getTotal(openid);
    }
}
