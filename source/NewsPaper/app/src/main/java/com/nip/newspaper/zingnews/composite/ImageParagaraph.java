package com.nip.newspaper.zingnews.composite;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.nip.newspaper.R;
import com.nip.newspaper.core.base.ContentBase;
import com.nip.newspaper.zingnews.view.AImageView;

/**
 * Created by nguyenminhthang on 6/14/15.
 */
public class ImageParagaraph extends LinearLayout {

    private TextView tvDes;
    private AImageView aImg;

    public ImageParagaraph(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.image_paragaraph,this);

        init();

    }

    private void init() {

        tvDes = (TextView) findViewById(R.id.tvDes);
        aImg = (AImageView) findViewById(R.id.aImge);

    }

    public ImageParagaraph(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageParagaraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImagePagaraph(ContentBase pagaraph)
    {
        tvDes.setText(Html.fromHtml(pagaraph.getText()));

        Ion.with(aImg).placeholder(R.drawable.image_defaul).error(R.drawable.cat_img_default).load(pagaraph.getImagelink());
    }
}
