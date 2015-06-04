package com.nip.newspaper.zingnews.parserHtml;

import com.nip.newspaper.core.paser.ParserHtmlBase;
import com.nip.newspaper.zingnews.pages.ZingArticle;
import com.nip.newspaper.zingnews.pages.ZingCategory;
import com.nip.newspaper.zingnews.pages.ZingPage;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by nguyenminhthang on 6/4/15.
 */
public class NormalPageParser extends ParserHtmlBase<ZingPage> {

    @Override
    protected void preResult() {
        result = new ZingPage();
        result.setTitle(parent.title());
        result.setLink(link);
    }

    @Override
    protected ZingPage parserHtml() {

        ZingCategory category = new ZingCategory();

        Elements  hElements = parent.select("body > div.wrapper > section#category > div.content-wrap > section.cate_content > section.featured");

        if(hElements == null)
        {
            return result;
        }

        ZingArticle article = getArticle(hElements.select(">article.featured").get(0));
        int i = 0;
        if(article != null)
        {
            category.addArticle(article);
            i = 1;
        }

        Elements articles = hElements.select(">article");

        if(article != null)
        {

            for (Element e : articles)
            {
                if(i == 1)
                {
                    i = 2;
                    continue;
                }
                article = getArticle(e);
                if(article != null)
                {
                    category.addArticle(article);
                }
            }
        }

        articles = parent.select("body > div.wrapper > section#category > div.content-wrap > section.cate_content > section.cate_content > article");

        if(articles != null){
            for (Element e : articles)
            {
                article = getArticle(e);
                if(article != null)
                {
                    category.addArticle(article);
                }
            }
        }

        result.addCategory(category);

        return result;
    }

    private ZingArticle getArticle(Element select) {

        if(select == null){
            return null;
        }

        ZingArticle article = new ZingArticle();
        article.setTitle(select.select("header>h1>a").get(0).text());

        Element image = select.select("div.cover").first();

        Element slink = select.select("header>h1>a").get(0);

        if(slink != null)
        {
            article.setLink(main_link+slink.attr("href"));
        }

        if(image != null)
        {
            String imageF = image.attr("style");
            int index = imageF.indexOf("http");
            int end = imageF.indexOf(")",index);

            article.setImage_link(imageF.substring(index,end));
        }

        Elements summary = select.select("header > p.summary");

        if(summary != null && summary.size() > 0)
        {
            article.setSortDesciption(summary.get(0).text());
        }

        return article;
    }
}
