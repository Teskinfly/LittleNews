package com.teskinfly.littlenewskr.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter@Getter@NoArgsConstructor
public class Topics implements Serializable {
    String tid;
    String content;
    String addDay;
    int discussNum;
    String img;
    String openid;
    int hotLevel;
    String title;

    public Topics(String content, String addDay, int discussNum, String img, String openid, int hotLevel, String title) {
        this.content = content;
        this.addDay = addDay;
        this.discussNum = discussNum;
        this.img = img;
        this.openid = openid;
        this.hotLevel = hotLevel;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Topics{" +
                "tid='" + tid + '\'' +
                ", content='" + content + '\'' +
                ", addDay='" + addDay + '\'' +
                ", discussNum=" + discussNum +
                ", img='" + img + '\'' +
                ", openid='" + openid + '\'' +
                ", hotLevel=" + hotLevel +
                ", title='" + title + '\'' +
                '}';
    }
}
