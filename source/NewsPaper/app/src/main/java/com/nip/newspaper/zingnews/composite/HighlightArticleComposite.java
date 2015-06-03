package com.nip.newspaper.zingnews.composite;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nip.newspaper.R;
import com.nip.newspaper.zingnews.pages.ZingArticle;

/**
 * Created by nguyenminhthang on 6/3/15.
 */
public class HighlightArticleComposite extends FrameLayout {

    private ImageView img;
    private TextView txtTitle;
    private TextView txtSumury;
    private float with;

    public HighlightArticleComposite(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.highlight_article_composite,this);

        init();

        caculate_size();
    }

    private void caculate_size() {

        with = com.nip.newspaper.core.utilities.Display.getSizeDevice(getContext()).x - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                2 * getResources().getInteger(R.integer.main_marign_left_right), getResources().getDisplayMetrics());
        Log.i("Size","with " + with);
    }

    void init()
    {
        img = (ImageView) findViewById(R.id.imgHotArticle);
        txtTitle = (TextView)findViewById(R.id.tvHotTitle);
        txtSumury = (TextView)findViewById(R.id.tvHotSumury);
    }

    public void loadParams(ZingArticle article)
    {
        txtTitle.setText(article.getTitle());
        txtSumury.setText(article.getSortDesciption());

        // load image

        article.getBitImage(img,with);

    }
}
