package com.nip.newspaper.zingnews.parserHtml;

import android.util.Log;

import com.nip.newspaper.core.base.ContentBase;
import com.nip.newspaper.core.paser.ParserHtmlBase;
import com.nip.newspaper.zingnews.pages.ZingArticleDetail;
import com.nip.newspaper.zingnews.pages.ZingContent;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by nguyenminhthang on 6/13/15.
 */
public class ArticleDetailPaser extends ParserHtmlBase<ZingArticleDetail> {

    @Override
    protected void preResult() {
        result = new ZingArticleDetail();
    }

    @Override
    protected ZingArticleDetail parserHtml() {


        Elements contents =  parent.select("body>div.wrapper>div.content-wrap>section#content>article>div.content");

        if(contents != null)
        {
            for(Element el : contents.get(0).children()) {

                ZingContent content = getParagraph(el);
                if(content != null)
                {
                    result.addContent(content);
                }
            }
        }

        result.setContentHtml(contents.html());
        return result;
    }

    private ZingContent getParagraph(Element el) {

        ZingContent content = new ZingContent();

        if(el.tagName().equals("div"))
        {
            if(el.className().equals("inner-video"))
            {
                content.setType(ContentBase.TYPE_VIDEO);
                Element elC = el.select(">a").get(0);
                content.setImagelink(elC.attr("href"));
                content.setText(elC.select(">h1").get(0).text());

            }else {
                return null;
            }
        }else if(el.tagName().equals("table"))
        {
            content.setType(ContentBase.TYPE_WITH_IMAGE);

            Elements elChilds = el.select(">tbody>tr>td.pic>img");

            if(elChilds.size() <= 0)
            {
                elChilds = el.select(">tbody>tr>td.pic>p>img");
            }
            Element elChild;

            if(elChilds.size() > 0) {
                elChild = elChilds.get(0);
                content.setImagelink(elChild.attr("src"));
            }
            elChild = el.select(">tbody>tr>td.caption").get(0);
            content.setText(elChild.text());


        }else if(el.tagName().equals("p")){
            content.setType(ContentBase.TYPE_ONLY_TEXT);
            content.setText(el.toString());
        }




        return content;
    }
}
