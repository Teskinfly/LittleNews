package com.teskinfly.littlenewskr;

import com.teskinfly.littlenewskr.service.CommentService;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class CommentTest {
    @Autowired
    RestHighLevelClient client;
    @Test
    public void test() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(CommentService.DB);
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }
}
