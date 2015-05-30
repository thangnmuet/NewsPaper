package com.nip.newspaper.zingnews.composite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nip.newspaper.R;
import com.nip.newspaper.core.base.ArticleBase;
import com.nip.newspaper.zingnews.pages.ZingCategory;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Esoft on 5/30/2015.
 */
public class HotCategoryComposite extends LinearLayout {

    private LinearLayout main;
    public HotCategoryComposite(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null){
            inflater.inflate(R.layout.hot_category_composite,this);
        }
        main = (LinearLayout) findViewById(R.id.cat_hot_main);

    }

    public void load(ZingCategory hotCat)
    {
        View articlehigh_light = getLayoutArticleHighLigth(hotCat.getListArticle().get(0));
        main.addView(articlehigh_light);

        View articLayout;
        for (int i = 1 ; i < hotCat.getListArticle().size() ; i+=2)
        {
            if(i+1 < hotCat.getListArticle().size()) {
                articLayout = getLayoutArticle(hotCat.getListArticle().get(i), hotCat.getListArticle().get(i + 1));
            }else
            {
                articLayout = getLayoutArticle(hotCat.getListArticle().get(i), hotCat.getListArticle().get(i + 1));
            }
            if(articLayout != null)
            {
                main.addView(articLayout);
            }
        }
    }

    private View getLayoutArticle(ArticleBase ar1, ArticleBase ar2) {

        LinearLayout linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(Color.TRANSPARENT);
        linearLayout.setOrientation(HORIZONTAL);
        View vAr1 = getLayoutArticle(ar1);

        linearLayout.addView(vAr1);

        if(ar2 != null){
            View vAr2 = getLayoutArticle(ar2);
            linearLayout.addView(vAr2);
        }

        return linearLayout;
    }

    private View getLayoutArticle(ArticleBase ar1) {
        // dung lay out frame

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        // image để hiển thị image của bài

        ImageView img = new ImageView(getContext());
        img.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        ar1.getBitImage(img);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        frameLayout.addView(img);


        // Title
        TextView txtTitle = new TextView(getContext());
        LayoutParams paramTitle = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        paramTitle.gravity = Gravity.BOTTOM;
        paramTitle.setMargins(5, 2, 5, 2);
        txtTitle.setLayoutParams(paramTitle);
        txtTitle.setTextColor(Color.WHITE);
        txtTitle.setText(ar1.getTitle());
        txtTitle.setBackgroundColor(Color.argb(70, 0, 0, 0));
        frameLayout.addView(txtTitle);
        return frameLayout;
    }


    private View getLayoutArticleHighLigth(ArticleBase articleBase) {


        // dung lay out frame

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        // image để hiển thị image của bài

        ImageView img = new ImageView(getContext());
        img.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        articleBase.getBitImage(img);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        frameLayout.addView(img);

        // layout hien thi tile va sortDiscription

        LinearLayout linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(Color.argb(70, 0, 0, 0));
        linearLayout.setOrientation(VERTICAL);

        // Title
        TextView txtTitle = new TextView(getContext());
        LayoutParams paramTitle = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        paramTitle.setMargins(5, 2, 5, 2);
        txtTitle.setLayoutParams(paramTitle);
        txtTitle.setTextColor(Color.WHITE);
        txtTitle.setText(articleBase.getTitle());

        linearLayout.addView(txtTitle);
        // sortDescription


        TextView txtDes = new TextView(getContext());
        LayoutParams paramsDes = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        paramsDes.setMargins(5,0,5,2);
        txtDes.setLayoutParams(paramsDes);
        txtDes.setTextColor(Color.WHITE);
        txtDes.setText(articleBase.getSortDesciption());
        txtDes.setTypeface(null, Typeface.ITALIC);
        linearLayout.addView(txtDes);


        frameLayout.addView(linearLayout);


        return frameLayout;
    }
}
