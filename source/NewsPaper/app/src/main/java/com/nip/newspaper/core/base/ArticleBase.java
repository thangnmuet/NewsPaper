package com.nip.newspaper.core.base;

import java.util.Date;

/**
 * Created by nguyenminhthang on 5/28/15.
 */
public abstract class ArticleBase {

    private String image_link = "";
    private String title = "";
    private String sortDesciption = "";
    private Date pubDate = null;
    private String link = "";

    public ArticleBase() {
    }

    public ArticleBase(String image_link, String title, String sortDesciption, Date pubDate, String link) {
        this.image_link = image_link;
        this.title = title;
        this.sortDesciption = sortDesciption;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSortDesciption() {
        return sortDesciption;
    }

    public void setSortDesciption(String sortDesciption) {
        this.sortDesciption = sortDesciption;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
