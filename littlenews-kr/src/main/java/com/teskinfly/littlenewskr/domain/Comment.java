package com.teskinfly.littlenewskr.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter@Getter@NoArgsConstructor
public class Comment implements Serializable {
    String cid;
    String userOpenId;
    String content;
    String addDay;
    String tid;
    String username;
    String img;
//    String fid;多级留言
//    List<Comment> sub;
    public Comment(String cid, String userOpenId, String content, String addDay, String tid) {
        this.cid = cid;
        this.userOpenId = userOpenId;
        this.content = content;
        this.addDay = addDay;
    }
}
