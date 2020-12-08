package com.teskinfly.littlenewskr;

import com.alibaba.fastjson.JSON;
import com.teskinfly.littlenewskr.domain.Topics;
import com.teskinfly.littlenewskr.service.TopicsService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class TopicsTest {
    @Autowired
    TopicsService topicsService;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Test
    public void create() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(TopicsService.DB);
        restHighLevelClient.indices().create(request,RequestOptions.DEFAULT);
    }
    @Test
    public void test() throws IOException {
        List<Topics> byDate = topicsService.findByDate(0, 100);
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < byDate.size(); i++){
            request.add(new IndexRequest(TopicsService.DB).id(byDate.get(i).getTid()).source(JSON.toJSONString(byDate.get(i)), XContentType.JSON));
        }
        restHighLevelClient.bulk(request,RequestOptions.DEFAULT);
    }
    @Test
    public void get() throws IOException {
        System.out.println(topicsService.findByDate(0,100
        ));
    }
    @Test
    public void del() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(TopicsService.DB);
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete);
    }
}
