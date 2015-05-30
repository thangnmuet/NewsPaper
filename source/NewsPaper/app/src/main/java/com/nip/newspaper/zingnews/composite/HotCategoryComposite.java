package com.nip.newspaper.zingnews.composite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nip.newspaper.R;

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

    public void load()
    {
        new ImageLoader().execute(getContext());
        TextView txt;
        for(int i = 0; i < 100 ; i ++ )
        {
            txt = new TextView(getContext());
            txt.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            txt.setText("Text " + i + " " + main.getWidth());
            main.addView(txt);
        }
    }

    class ImageLoader extends AsyncTask<Context,Void,Bitmap>{
        private Context context;
        @Override
        protected Bitmap doInBackground(Context... params) {
            context = params[0];
            Bitmap bm = null;
            try{
                bm  = BitmapFactory.decodeStream((InputStream) new URL("http://img.v3.news.zdn.vn/w660/Uploaded/nokarz/2015_05_30/nong.JPG").getContent());

            }catch (Exception e){
                Log.d("Hot cat",e.toString());
            }
            return  bm;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView img = new ImageView(context);

            img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            img.setImageBitmap(bitmap);

            main.addView(img);
        }
    }
}
