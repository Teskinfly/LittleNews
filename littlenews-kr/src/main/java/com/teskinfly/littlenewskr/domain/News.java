package com.teskinfly.littlenewskr.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter@Getter
public class News implements Serializable {
    Integer newsId;
    Integer menuId;
    String poster;
    String title;
    String content;
    String addDate;

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", menuId=" + menuId +
                ", poster='" + poster + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", addDate='" + addDate + '\'' +
                '}';
    }
}
