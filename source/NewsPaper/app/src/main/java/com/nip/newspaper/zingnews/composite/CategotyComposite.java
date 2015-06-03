package com.nip.newspaper.zingnews.composite;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.nip.newspaper.R;
import com.nip.newspaper.core.base.ArticleBase;
import com.nip.newspaper.core.base.CategoryBase;
import com.nip.newspaper.zingnews.pages.ZingArticle;
import com.nip.newspaper.zingnews.pages.ZingPage;
import com.nip.newspaper.zingnews.parserHtml.HomeParser;

/**
 * Created by nguyenminhthang on 6/3/15.
 */
public class CategotyComposite extends LinearLayout {
    private  LinearLayout llHighLigthArticle;
    private  LinearLayout llNormalArticle;
    private float img_w;
    private float img_h;


    public CategotyComposite(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.category_composite,this);

        init();

        caculate_img_size();
    }

    private void caculate_img_size() {
        float with = com.nip.newspaper.core.utilities.Display.getSizeDevice(getContext()).x - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                2 * getResources().getInteger(R.integer.main_marign_left_right), getResources().getDisplayMetrics());
        img_w = (float)(with/2.5);
        img_h = (float)(img_w/1.375);

    }

    private void init() {
        llHighLigthArticle = (LinearLayout)findViewById(R.id.llHighLigthArticle);
        llNormalArticle = (LinearLayout)findViewById(R.id.llNormalArticle);
    }

    public void loadPage(ZingPage home)
    {

        // add bai viet noi bat
        addHighLightArticle(home.getListCategory().get(0).getListArticle().get(0));

        int i = 1;
        int length = 0;
        for(CategoryBase category : home.getListCategory())
        {
            length = category.getListArticle().size();
            for(int j = i; j<length;j++)
            {
                addNormalArtucle(category.getListArticle().get(j));
            }
            i = 0;
        }
    }

    private void addNormalArtucle(ArticleBase zArticle) {

        NormalArticleComposite article = new NormalArticleComposite(getContext());

        LayoutParams params = new LayoutParams((int)img_w,LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,5,0);
        article.setLayoutParams(params);
        article.load((ZingArticle)zArticle,img_w);
        llNormalArticle.addView(article);

    }

    // add article hingligt of child page
    private void addHighLightArticle(ArticleBase zArticle) {

        HighlightArticleComposite article = new HighlightArticleComposite(getContext());

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        article.setLayoutParams(params);
        article.loadParams((ZingArticle)zArticle);
        llHighLigthArticle.addView(article);

    }
}
