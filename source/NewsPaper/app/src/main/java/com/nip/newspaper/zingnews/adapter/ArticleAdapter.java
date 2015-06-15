package com.nip.newspaper.zingnews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.nip.newspaper.R;
import com.nip.newspaper.core.base.ArticleBase;
import com.nip.newspaper.core.base.CategoryBase;
import com.nip.newspaper.core.base.PageBase;
import com.nip.newspaper.zingnews.pages.ZingArticle;
import com.nip.newspaper.zingnews.pages.ZingPage;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by nguyenminhthang on 6/9/15.
 */
public class ArticleAdapter extends BaseAdapter {

    private final List<ZingArticle> datas = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public ArticleAdapter(Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        ImageView img;
        TextView tv;

        if(v == null)
        {
            v = inflater.inflate(R.layout.gridview_item,viewGroup,false);
            v.setTag(R.id.tvTitle, v.findViewById(R.id.tvTitle));
            v.setTag(R.id.mImImage,v.findViewById(R.id.mImImage));

            Log.i("GridView", "load " + i);
        }

        img = (ImageView) v.getTag(R.id.mImImage);
        tv = (TextView) v.getTag(R.id.tvTitle);

        final ZingArticle article  = datas.get(i);

        tv.setText(article.getTitle());
        Ion.with(img).placeholder(R.drawable.image_defaul).error(R.drawable.cat_img_default).load(article.getImage_link()).setCallback(new FutureCallback<ImageView>() {
            @Override
            public void onCompleted(Exception e, ImageView imageView) {
//                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//
//                if (bitmap != null) {
//                    Log.i("Bitmap", article.getTitle() + " > done");
//                }
//                Log.i("Bitmap", article.getTitle() + " > fail.");
            }
        });
        v.setTag(1010101010,article.getLink());

        Log.i("GridView","load out" + i);
        return v;
    }

    public void addCategory(CategoryBase cat)
    {
        for(ArticleBase a : cat.getListArticle())
        {
            datas.add((ZingArticle)a);
        }

        this.notifyDataSetChanged();
    }

    public void addPage(PageBase page)
    {
        for (CategoryBase cat : page.getListCategory()) {
            for(ArticleBase a : cat.getListArticle())
            {
                datas.add((ZingArticle)a);
            }
        }

        notifyDataSetChanged();
    }

    public void addArticle(ZingArticle article){
        datas.add(article);
        this.notifyDataSetChanged();
    }
}
