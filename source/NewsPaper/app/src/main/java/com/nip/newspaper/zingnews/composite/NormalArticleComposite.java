package com.nip.newspaper.zingnews.composite;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nip.newspaper.R;
import com.nip.newspaper.zingnews.pages.ZingArticle;

/**
 * Created by nguyenminhthang on 6/3/15.
 */
public class NormalArticleComposite extends FrameLayout {
    private ImageView img;
    private TextView tvTitle;

    public NormalArticleComposite(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.normal_article_composite,this);
        init();
    }

    private void init() {

        img = (ImageView)findViewById(R.id.imgNormalArticle);
        tvTitle = (TextView)findViewById(R.id.tvNormalTitle);
    }
    public void load(ZingArticle article, float with)
    {
        tvTitle.setText(article.getTitle());
        article.getBitImage(img,with);
    }
}
