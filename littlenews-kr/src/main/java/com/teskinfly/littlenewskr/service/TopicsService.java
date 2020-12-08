package com.teskinfly.littlenewskr.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tdunning.math.stats.Sort;
import com.teskinfly.littlenewskr.domain.Topics;
import com.teskinfly.littlenewskr.mapper.TopicsMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
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
public class TopicsService {
    public static final String DB = "topics";
    @Autowired
    TopicsMapper mapper;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    static int size;
    public Topics findById(String id) throws IOException {
//        SearchRequest request = new SearchRequest(TopicsService.DB);
//        SearchSourceBuilder ssb = new SearchSourceBuilder();
//        TermQueryBuilder sid = QueryBuilders.termQuery("sid", id);
//        ssb.query(sid);
//        request.source(ssb);
//        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit: search.getHits().getHits()){
//            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
//            System.out.println(JSON.toJSONString(sourceAsMap));
//            return JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap),Topics.class);
//        }
        GetRequest request = new GetRequest(DB,id);
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        return JSONObject.parseObject(JSONObject.toJSONString(response.getSourceAsMap()),Topics.class);
    }

    public List<Topics> findByLevel(int page, int size) throws IOException {
        List<Topics> ans = new ArrayList<>();
        SearchRequest request = new SearchRequest(TopicsService.DB);
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.sort(new FieldSortBuilder("hotLevel").order(SortOrder.DESC));
        ssb.from(page);
        ssb.size(size);
        request.source(ssb);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit: search.getHits().getHits()){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            ans.add(JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap),Topics.class));
        }
        return ans;
    }

    public boolean insert(Topics topics) throws IOException {
        topics.setAddDay(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
//        return mapper.insert(topics);
        IndexRequest request = new IndexRequest(TopicsService.DB);
        request.id(topics.getTid());
        request.source(JSON.toJSONString(topics), XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        if (index.status().equals(RestStatus.OK)) return true;
        return false;
    }

    public boolean delById(String id) throws IOException {
//        DeleteRequest request = new DeleteRequest(TopicsService.DB);
        DeleteRequest request = new DeleteRequest(DB,id);
        DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        if (delete.status() == RestStatus.OK) return true;
        return false;
    }

    public List<Topics> findByOpenid(String openid) throws IOException {
//        System.out.println("hi");
//        return mapper.findByOpenid(openid);
        System.out.println(openid);
        List<Topics> ans = new ArrayList<>();
        SearchRequest request = new SearchRequest(DB);
        TermQueryBuilder tb = QueryBuilders.termQuery("openid.keyword", openid);
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.query(tb);
        ssb.sort(new FieldSortBuilder("addDay.keyword").order(SortOrder.ASC));
        request.source(ssb);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        System.out.println(search);
//        search.getHits().getTotalHits();
        for (SearchHit hit: search.getHits().getHits()){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            ans.add(JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap),Topics.class));
        }
//        System.out.println("ans"+ans);
        size = ans.size();
        return ans;
    }

    public List<Topics> findByDate(int page, int size) throws IOException {
        List<Topics> ans = new ArrayList<>();
        SearchRequest request = new SearchRequest(DB);
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ssb.sort(new FieldSortBuilder("addDay.keyword").order(SortOrder.DESC));
        ssb.from(page);
        ssb.size(size);
        request.source(ssb);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit: search.getHits().getHits()){
            ans.add(JSONObject.parseObject(JSONObject.toJSONString(hit.getSourceAsMap()),Topics.class));
        }
        return ans;
    }
    public String getTotal(String openid){
        return size+"";
    }
    public List<Topics> findByKeyword(String name, String keyword) throws IOException {
        System.out.println(keyword);
        if (keyword == "") {
            return findByLevel(0,5);
        }
        List<Topics> ans = new ArrayList<>();
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        HighlightBuilder hb = new HighlightBuilder();
        MatchQueryBuilder builder = QueryBuilders.matchQuery(name, keyword);
        ssb.query(builder);
        hb.field(name);
        hb.preTags("<span style='color:red'>");
        hb.postTags("</span>");
        hb.requireFieldMatch(false);
        ssb.highlighter(hb);
        request.source(ssb);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit: search.getHits().getHits()){
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField highlightField = highlightFields.get(name);
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if (highlightField != null){
                Text[] fragments = highlightField.fragments();
                StringBuffer sb = new StringBuffer();
                for (Text text: fragments){
                    sb.append(text);
                }
                System.out.println(sb);
                sourceAsMap.put(name,sb.toString());
            }
            ans.add(JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap),Topics.class));
        }
        return ans;
    }
    public boolean addNum(String id, String name, int amount) throws IOException {
        UpdateRequest request = new UpdateRequest(DB,id);
//        System.out.println(id);
        Topics byId = findById(id);
        System.out.println(byId);
        byId.setDiscussNum(byId.getDiscussNum()+amount);
        request.doc(JSONObject.toJSONString(byId),XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        return true;
    }
}
