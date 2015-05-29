package com.nip.newspaper.zingnews.parserHtml;

import com.nip.newspaper.core.paser.ParserHtmlBase;
import com.nip.newspaper.zingnews.pages.ZingArticle;
import com.nip.newspaper.zingnews.pages.ZingCategory;
import com.nip.newspaper.zingnews.pages.ZingPage;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Esoft on 5/29/2015.
 */
public class HomeParser extends ParserHtmlBase<ZingPage> {

    final String TAG_DIV ="div";
    final String CLASS_WRAPER = "wrapper";
    @Override
    protected void preResult() {
        result = new ZingPage();
        result.setTitle(parent.title());
        result.setLink(link);
    }

    @Override
    protected ZingPage parserHtml() {
        ZingCategory hotNews = paserHotNews(parent.select("body > div.wrapper > section#homepage > div.content-wrap > section.featured").first());
        result.addCategory(hotNews);
        ZingCategory videoAndPic = paserVideoAndImage(parent.select("body > div.wrapper > section#homepage > section.multimedia").first());
        return null;
    }

    /*
       Lấy thông tin ảnh
        */
    private ZingCategory paserVideoAndImage(Element videopic) {

        ZingCategory category = new ZingCategory();
        category.setTitle(videopic.select("header>h1").first().text());

        Elements articles = videopic.getElementsByTag("article");
        Element[] arrArtic = new Element[articles.size()];
        articles.toArray(arrArtic);

        ZingArticle article;
        for(Element ar : arrArtic)
        {
            article = getHotNewsArticle(ar);
            category.addArticle(article);
        }

        return category;
    }

    /*
     Lấy thông tin category hot news
    */
    private ZingCategory paserHotNews(Element hotNews) {
        ZingCategory category = new ZingCategory();
        category.setTitle("Tin Mới");

        Elements articles = hotNews.getElementsByTag("article");
        Element[] arrArtic = new Element[articles.size()];
        articles.toArray(arrArtic);

        ZingArticle article;
        for(Element ar : arrArtic)
        {
            article = getHotNewsArticle(ar);
            category.addArticle(article);
        }


        return category;
    }

    private ZingArticle getHotNewsArticle(Element ar) {

        ZingArticle article = new ZingArticle();

        Element eArticle = ar.select("header>h1>a").first();
        if(eArticle != null)
        {
            article.setTitle(eArticle.text());
            article.setLink(link+eArticle.attr("href"));
        }

        Element sumury = ar.select("header>p.summary").first();
        if(sumury != null)
        {
            article.setSortDesciption(sumury.text());
        }

        Element image = ar.select("div.cover").first();

        if(image != null)
        {
            String imageF = image.attr("style");
            int index = imageF.indexOf("http");
            int end = imageF.indexOf(")",index);

            article.setImage_link(imageF.substring(index,end));
        }

        return article;
    }

}
