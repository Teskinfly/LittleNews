package com.teskinfly.littlenewskr.utils.warm;

import com.teskinfly.littlenewskr.domain.Menu;
import com.teskinfly.littlenewskr.domain.News;
import com.teskinfly.littlenewskr.mapper.NewsMapper;
import com.teskinfly.littlenewskr.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Component
public class MyWarm {
    static final String BU = "https://www.xinwentoutiao.net";
    @Autowired
    NewsService service;
    static List<Menu> menuList;
    public List<News> getArticle() throws IOException {
        if (menuList == null) getMenuList();
        List<News> ans = new ArrayList<>();
        for (Menu menu: menuList){
            Document document = Jsoup.connect(BU + menu.getMenuUrl()).get();
            Elements li = document.getElementsByClass("gv-list").first().getElementsByTag("li");
            for (Element element: li){
                News news = new News();
                String img = element.getElementsByClass("gv-pic").first().getElementsByTag("img").first().attr("src");
                Element first = element.getElementsByClass("gv-title").first();
                String title = first.text();
                String href = first.getElementsByTag("a").first().attr("href");
                Document artitcle = Jsoup.connect(BU + href).get();
                Element body = artitcle.getElementsByClass("art-body").first();
                Elements ps = body.getElementsByTag("p");
                StringBuffer sb = new StringBuffer();
                for (Element p:ps){
                    sb.append(p.text());
                }
                news.setContent(sb.toString());
                news.setPoster(img);
                news.setTitle(title);
                news.setMenuId(menu.getMenuId());
                news.setAddDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                service.insert(news);
                System.out.println(news);
            }
        }
        return ans;
    }
    public List<Menu> getMenuList() throws IOException{
        int i = 0;
        menuList = new ArrayList<>();
        Document document = Jsoup.connect(BU).get();
        Element nav = document.getElementsByClass("nav").first();
        Elements elementsByTag = nav.getElementsByTag("a");
        for (Element element: elementsByTag){
            if (!element.html().equals("首页")) {
                String title = element.text();
                String url = element.attr("href");
                menuList.add(new Menu(i++,title,url));
            }
        }
        return menuList;
    }
}
