package com.nip.newspaper.core.paser;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by Esoft on 5/29/2015.
 */
public abstract class ParserHtmlBase <E> extends AsyncTask<String,Object,E>{
    protected E result;
    private IParserListener listener = null;
    protected Document parent;
    protected String link;

    public void setListener(IParserListener listener) {
        this.listener = listener;
    }

    public Object getResult() {
        return result;
    }

    @Override
    protected E doInBackground(String... params) {
        try {
            link = params[0];
            parent = Jsoup.connect(link).get();
        } catch (IOException e) {
            Log.d("Parser",e.toString());
            return null;
        }
        preResult();
        return parserHtml();
    }

    protected abstract void preResult();

    protected abstract E parserHtml();
}
