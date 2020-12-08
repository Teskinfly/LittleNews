package com.teskinfly.littlenewskr.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.teskinfly.littlenewskr.domain.Comment;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    public static final String DB = "comment";
    @Autowired
    RestHighLevelClient client;
    public boolean add(Comment comment) throws IOException {
        comment.setAddDay(new SimpleDateFormat("yyyy-MM-dd hh::dd::ss").format(new Date()));
        IndexRequest request = new IndexRequest(DB);
        request.id(comment.getCid());
        request.source(JSON.toJSONString(comment), XContentType.JSON);
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);
        return index.status() == RestStatus.OK;
    }
    public List<Comment> get(String tid, int page, int size) throws IOException {
        List<Comment> ans = new ArrayList<>();
        SearchRequest request = new SearchRequest(DB);
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.query(QueryBuilders.termQuery("tid",tid));
//        ssb.sort(new FieldSortBuilder("addDay.keyword").order(SortOrder.ASC));
        request.source(ssb);
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit: search.getHits().getHits()){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            ans.add(JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap),Comment.class));
        }
        return ans;
    }
}
