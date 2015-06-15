package com.nip.newspaper.core.base;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.nip.newspaper.R;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * Created by nguyenminhthang on 5/28/15.
 */
public abstract class ArticleBase {

    private String image_link = "";
    private String title = "";
    private String sortDesciption = "";
    private Date pubDate = null;
    private String link = "";
    private Bitmap bitmap;
    private float with = -1f;

    public ArticleBase() {
    }

    public ArticleBase(String image_link, String title, String sortDesciption, Date pubDate, String link) {
        this.image_link = image_link;
        this.title = title;
        this.sortDesciption = sortDesciption;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSortDesciption() {
        return sortDesciption;
    }

    public void setSortDesciption(String sortDesciption) {
        this.sortDesciption = sortDesciption;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void getBitImage(ImageView img)
    {
        if(bitmap != null)
        {
            setImage(img,bitmap);
            return;

        }else
        {
            setImage(img,BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.image_defaul));
        }

        new BitmapLoad().execute(img);

        Log.i("image with dbahbda",img.getMeasuredWidth()+"");


    }

    public void getBitImage(ImageView img, float width)
    {
        this.with = width;
        if(bitmap != null)
        {
            setImage(img,bitmap);
            return;

        }

        new BitmapLoad().execute(img);


    }

    public void setImage(ImageView img,Bitmap bitmap)
    {

        if(with > 0)
        {
            int heigh = (int) (bitmap.getHeight() * (with/bitmap.getWidth()));
            img.getLayoutParams().height = heigh;
            img.getLayoutParams().width = (int)with;
        }

        img.setImageBitmap(bitmap);
    }

    // class load bitmap image
    private class BitmapLoad extends AsyncTask<ImageView,Void,Bitmap>{

        private ImageView img;
        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {

            Bitmap bm = null;
            img = imageViews[0];
            try
            {

                bm = BitmapFactory.decodeStream((InputStream)new URL(image_link).getContent());

            }catch(Exception e)
            {
                Log.w("Article",e.toString());
            }

            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap bm) {
            super.onPostExecute(bm);
            if(bm == null)
            {
                return;
            }

            bitmap = bm;

            setImage(img,bm);
        }
    }


}
