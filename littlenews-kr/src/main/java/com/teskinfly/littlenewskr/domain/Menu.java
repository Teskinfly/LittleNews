package com.teskinfly.littlenewskr.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter@Getter@NoArgsConstructor
public class Menu implements Serializable {
    Integer menuId;
    String menuName;
    String menuUrl;

    public Menu(Integer menuId, String menuName, String menuUrl) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                '}';
    }
}
