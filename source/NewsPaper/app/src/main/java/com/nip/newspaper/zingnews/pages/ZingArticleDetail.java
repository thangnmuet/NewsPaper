package com.nip.newspaper.zingnews.pages;

import com.nip.newspaper.core.base.ArticleDetailBase;

/**
 * Created by Esoft on 5/29/2015.
 */
public class ZingArticleDetail extends ArticleDetailBase {
    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    private String contentHtml = null;


}
