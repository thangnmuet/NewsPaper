package com.nip.newspaper.core.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenminhthang on 5/28/15.
 */
public abstract class PageBase {
    private final List<CategoryBase> listCategory = new ArrayList<>();
    private String title;
    private String link;
    private ArticleBase highlight;

    public PageBase() {
    }

    public PageBase(String title) {
        this.title = title;
    }

    public PageBase(String title, String link) {

        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<CategoryBase> getListCategory() {
        return listCategory;
    }

    public void addCategory(CategoryBase category) {
        this.listCategory.add(category);
    }

    public ArticleBase getHighlight() {
        return highlight;
    }

    public void setHighlight(ArticleBase highlight) {
        this.highlight = highlight;
    }
}
