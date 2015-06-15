package com.nip.newspaper.core.paser;

import com.nip.newspaper.core.base.ArticleBase;
import com.nip.newspaper.core.base.PageBase;

/**
 * Created by nguyenminhthang on 6/13/15.
 */
public abstract class PagePaserHtml <E extends PageBase> extends ParserHtmlBase<E> {


    @Override
    protected void onPostExecute(E e) {

        if(e != null)
        {
            if(e.getListCategory().size() > 0)
            {
                ArticleBase articleBase = e.getListCategory().get(0).getListArticle().get(0);

                if(articleBase != null)
                {
                    e.setHighlight(articleBase);
                    e.getListCategory().get(0).getListArticle().remove(0);
                }

            }
        }

        super.onPostExecute(e);
    }
}
