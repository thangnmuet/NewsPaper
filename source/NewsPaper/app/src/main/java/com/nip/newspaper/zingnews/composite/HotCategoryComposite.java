package com.nip.newspaper.zingnews.composite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.nip.newspaper.R;
import com.nip.newspaper.core.IArticleClick;
import com.nip.newspaper.core.base.ArticleBase;
import com.nip.newspaper.zingnews.pages.ZingCategory;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Esoft on 5/30/2015.
 */
public class HotCategoryComposite extends LinearLayout {

    private LinearLayout main;
    private TableLayout articles;
    private IArticleClick articleClick = null;

    public void setArticleClick(IArticleClick articleClick) {
        this.articleClick = articleClick;
    }

    public HotCategoryComposite(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null){
            inflater.inflate(R.layout.hot_category_composite,this);
        }
        main = (LinearLayout) findViewById(R.id.cat_hot_main);
        articles = (TableLayout)findViewById(R.id.article);

    }

    public void load(ZingCategory hotCat)
    {
        View articlehigh_light = getLayoutArticleHighLigth(hotCat.getListArticle().get(0));
        main.addView(articlehigh_light);
        TableRow row;
        for (int i = 1 ; i < hotCat.getListArticle().size() ; i+=2)
        {

            row = new TableRow(getContext());
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            row.addView(getLayoutArticle(hotCat.getListArticle().get(i),i));

            if(i+1 < hotCat.getListArticle().size()){
                row.addView(getLayoutArticle(hotCat.getListArticle().get(i+1),i+1));
            }

            articles.addView(row);

        }
        Log.i("Row", "row done");


//        main.addView(tableLayout);
    }


    private View getLayoutArticle(ArticleBase ar1,int col) {
        // dung lay out frame

        FrameLayout frameLayout = new FrameLayout(getContext());
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        if(col%2 != 0)
        {
            params.setMargins(0,4,4,0);
        }else{
            params.setMargins(0,4,0,0);
        }

        frameLayout.setLayoutParams(params);

        // image để hiển thị image của bài

        ImageView img = new ImageView(getContext());
        img.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        ar1.getBitImage(img);
        img.setImageDrawable(getResources().getDrawable(R.drawable.cat_img_default));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        frameLayout.addView(img);

        Log.i("HotCategory","image size " + img.getMeasuredWidth());

        // Title
        TextView txtTitle = new TextView(getContext());
        FrameLayout.LayoutParams paramTitle = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        paramTitle.gravity = Gravity.BOTTOM;
        paramTitle.setMargins(5, 0, 5, 0);
        txtTitle.setLayoutParams(paramTitle);
        txtTitle.setTextColor(Color.WHITE);
        txtTitle.setText(ar1.getTitle());
        txtTitle.setBackgroundColor(Color.argb(70, 0, 0, 0));
        txtTitle.setMaxEms(10);
        txtTitle.setSingleLine(false);
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        txtTitle.setGravity(Gravity.BOTTOM);
        txtTitle.setTag(ar1.getLink());
        txtTitle.setOnClickListener(onClickListener);
        frameLayout.addView(txtTitle);
        return frameLayout;
    }

// Tin noi bat
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
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(Color.argb(70, 0, 0, 0));
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setTag(articleBase.getLink());
        linearLayout.setOnClickListener(onClickListener);

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
        paramsDes.setMargins(5, 0, 5, 2);
        txtDes.setLayoutParams(paramsDes);
        txtDes.setTextColor(Color.WHITE);
        txtDes.setText(articleBase.getSortDesciption());
        txtDes.setTypeface(null, Typeface.ITALIC);
        txtDes.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        linearLayout.addView(txtDes);


        frameLayout.addView(linearLayout);


        return frameLayout;
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(),(String)view.getTag(),Toast.LENGTH_SHORT).show();
            if(articleClick != null){
                articleClick.clickArticle((String)view.getTag());
            }
        }
    };
}
