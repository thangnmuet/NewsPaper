package com.nip.newspaper.core.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nguyenminhthang on 5/28/15.
 */
public abstract class ArticleDetailsBase {
    private int conten_num = 0;
    private final Map<Integer,ContentBase> listContents = new HashMap<Integer,ContentBase>();
    private String title = "";

    public ArticleDetailsBase() {
    }

    public ArticleDetailsBase(String title) {
        this.title = title;
    }

    public void addContent(ContentBase content)
    {
        this.listContents.put(++conten_num,content);
    }

    public ContentBase getContent(int selection)
    {
        return this.listContents.get(selection);
    }

    public int getConten_num()
    {
        return conten_num;
    }
}
