package com.teskinfly.littlenewskr;

import com.teskinfly.littlenewskr.domain.Topics;
import com.teskinfly.littlenewskr.mapper.TopicsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class LittlenewsKrApplicationTests {
    @Autowired
    TopicsMapper mapper;
    public List<Topics> findById(String id) {
        return null;
    }

    public List<Topics> findByLevel(int page, int size) {
        return null;
    }
    @Test
    public void insert() {
        for (int i = 0; i < 30;i++){
            Topics topics1 = new Topics("你好"+i, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), i, "http://www.32r.com/uppic/180518/201805181425169291.jpg", "ew", (int) (Math.random()*3),"你好"+i);
            mapper.insert(topics1);
        }
    }

    public Topics delById(String id) {
        return null;
    }

    public List<Topics> findByOpenid(String openid) {
        return null;
    }
    @Test
    public void findByDate() {
        System.out.println(mapper.findByDate(1,20));
    }
}
