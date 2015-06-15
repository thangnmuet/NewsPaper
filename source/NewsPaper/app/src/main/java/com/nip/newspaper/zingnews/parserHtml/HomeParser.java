package com.nip.newspaper.zingnews.parserHtml;

import com.nip.newspaper.core.paser.PagePaserHtml;
import com.nip.newspaper.core.paser.ParserHtmlBase;
import com.nip.newspaper.zingnews.pages.ZingArticle;
import com.nip.newspaper.zingnews.pages.ZingCategory;
import com.nip.newspaper.zingnews.pages.ZingPage;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Esoft on 5/29/2015.
 */
public class HomeParser extends PagePaserHtml<ZingPage> {

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
        ZingCategory videoAndPic = paserVideoAndImage(parent.select("body > div.wrapper > section#homepage > section#multimedia").first());
        result.addCategory(videoAndPic);
        getNormalCategory(parent.select("body > div.wrapper > section#homepage > div.content-wrap > div.content-wrap").first());
        return result;
    }

    /* lay thong tin
    category normal
     */
    private void getNormalCategory(Element normal) {

        if(normal == null)
        {
            return;
        }

        Elements categorys = normal.select(">section");
        Element[] categoryArr = new Element[categorys.size()];
        categorys.toArray(categoryArr);

        for(Element cat : categoryArr)
        {
            ZingCategory zCat = getCategory(cat);
            if(zCat != null) {
                result.addCategory(zCat);
            }
        }


    }

    private ZingCategory getCategory(Element cat) {

        if(cat == null){
            return null;
        }

        ZingCategory zCat = new ZingCategory();

        Element catTitle = cat.select("header > hgroup > h1").first();

        if(catTitle == null)
        {
            catTitle = cat.select("header > h2").first();
        }

        if(catTitle == null)
        {
            return null;
        }

        zCat.setTitle(catTitle.text());

        Elements articles = cat.select(">article");
        if(articles.size() > 0) {
            ZingArticle acti;
            for (Element ar : articles) {
                acti = getHotNewsArticle(ar);
                if (acti != null) {
                    zCat.addArticle(acti);
                }
            }
        }else{
            // truong hop cac bai viet o trong chuyen muc con
            Elements catlv = cat.select(">section");
            ZingCategory zCatChild = null;
            if(catlv.size() > 0){
                for (Element catlvs : catlv){
                    zCatChild = getCategory(catlvs);
                    if(zCatChild != null){
                        result.addCategory(zCatChild);
                    }
                }

            }

            return null;
        }

        return zCat;

    }

    /*
       Lấy thông tin ảnh
        */
    private ZingCategory paserVideoAndImage(Element videopic) {

        if(videopic == null)
        {
            return null;
        }

        ZingCategory category = new ZingCategory();
        category.setTitle(videopic.select("header>h1").first().text());

        Elements articles = videopic.select(">article");

        ZingArticle article;
        for(Element ar : articles)
        {
            article = getHotNewsArticle(ar);
            if(article != null) {
                category.addArticle(article);
            }
        }

        return category;
    }

    /*
     Lấy thông tin category hot news
    */
    private ZingCategory paserHotNews(Element hotNews) {

        if(hotNews == null)
        {
            return null;
        }

        ZingCategory category = new ZingCategory();
        category.setTitle("Tin Mới");

        Elements articles = hotNews.select(">article");

        ZingArticle article;
        for(Element ar : articles)
        {
            article = getHotNewsArticle(ar);
            if (article != null) {
                category.addArticle(article);
            }
        }


        return category;
    }

    private ZingArticle getHotNewsArticle(Element ar) {

        if(ar == null)
        {
            return null;
        }

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
