package com.teskinfly.littlenewskr;

import com.teskinfly.littlenewskr.domain.Menu;
import com.teskinfly.littlenewskr.domain.News;
import com.teskinfly.littlenewskr.utils.warm.MyWarm;
import org.junit.jupiter.api.Test;

import java.util.List;

public class WarmTest {
    @Test
    public void test() throws Exception {
        MyWarm myWarm = new MyWarm();
        List<Menu> menuList = myWarm.getMenuList();
        List<News> article = myWarm.getArticle();
        System.out.println(menuList);
        System.out.println(article);
    }
}
