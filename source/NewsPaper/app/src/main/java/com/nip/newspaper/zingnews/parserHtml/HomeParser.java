package com.nip.newspaper.zingnews.parserHtml;

import com.nip.newspaper.core.paser.ParserHtmlBase;
import com.nip.newspaper.zingnews.pages.ZingPage;

import org.jsoup.nodes.Element;

/**
 * Created by Esoft on 5/29/2015.
 */
public class HomeParser extends ParserHtmlBase<ZingPage> {

    @Override
    protected void preResult() {
        result = new ZingPage();
        result.setTitle(parent.title());
        result.setLink(link);
    }

    @Override
    protected ZingPage parserHtml(Element body) {
        return null;
    }

}
