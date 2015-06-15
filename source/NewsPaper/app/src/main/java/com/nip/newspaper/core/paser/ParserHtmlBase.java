package com.nip.newspaper.core.paser;

import android.os.AsyncTask;
import android.util.Log;

import com.nip.newspaper.core.base.ArticleBase;
import com.nip.newspaper.core.base.PageBase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Esoft on 5/29/2015.
 */
public abstract class ParserHtmlBase <E> extends AsyncTask<String,Object,E>{
    protected E result;
    protected IParserListener listener = null;
    protected IParserSuccess<E> success = null;
    protected Document parent;
    protected String link;
    protected String main_link;

    public void setListener(IParserListener listener) {
        this.listener = listener;
    }

    public void setSuccess(IParserSuccess success) {
        this.success = success;
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

    @Override
    protected void onPostExecute(E e) {
        super.onPostExecute(e);

        if(success != null)
        {
            success.succes(e);
        }
    }

    protected abstract void preResult();

    protected abstract E parserHtml();

    public void setMain_link(String main_link) {
        this.main_link = main_link;
    }
}
