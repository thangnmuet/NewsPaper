package com.nip.newspaper.core.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenminhthang on 5/28/15.
 */
public abstract class CategoryBase {

    private String title = "";
    private final List<ArticleBase> listArticle = new ArrayList<ArticleBase>();

    public CategoryBase() {
    }

    public CategoryBase(String title) {
        this.title = title;
    }

    public void addArticle(ArticleBase article)
    {
        this.listArticle.add(article);
    }

    public List<ArticleBase> getListArticle()
    {
        return this.listArticle;
    }

}
