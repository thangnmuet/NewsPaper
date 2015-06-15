package com.nip.newspaper;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nip.newspaper.R;
import com.nip.newspaper.core.base.ContentBase;
import com.nip.newspaper.core.paser.IParserSuccess;
import com.nip.newspaper.zingnews.composite.ImageParagaraph;
import com.nip.newspaper.zingnews.pages.ZingArticleDetail;
import com.nip.newspaper.zingnews.parserHtml.ArticleDetailPaser;

public class Article extends Activity {

    private String htmlView;
    ArticleDetailPaser articleParserHtml = new ArticleDetailPaser();
    private LinearLayout llContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            htmlView = bundle.getString("html");
        }

        articleParserHtml.setSuccess(success);
        articleParserHtml.execute(htmlView);

//        TextView textView = (TextView) findViewById(R.id.tvContent);
//
//        textView.setText(Html.fromHtml(htmlView));

        llContent = (LinearLayout) findViewById(R.id.llContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    IParserSuccess<ZingArticleDetail> success = new IParserSuccess<ZingArticleDetail>() {
        @Override
        public void succes(ZingArticleDetail value) {

            viewArticelContent(value);

        }
    };

    private void viewArticelContent(ZingArticleDetail value) {

        String text = "";

        ContentBase content;
        for(int i = 1; i <= value.getConten_num() ; i++)
        {
            content = value.getContent(i);

            if(content.getType() == ContentBase.TYPE_ONLY_TEXT)
            {
                text += content.getText() + "\n";
            }else
            {
                if(!text.isEmpty())
                {
                    TextView tv = getTextParagraph(text);

                    llContent.addView(tv);
                    text = "";
                }

                if(content.getType() == ContentBase.TYPE_WITH_IMAGE)
                {
                    LinearLayout llImageParagaraph = getImageParagaph(content);
                    llContent.addView(llImageParagaraph);
                }
            }


        }

        if(!text.isEmpty())
        {
            TextView tv = getTextParagraph(text);

            llContent.addView(tv);
            text = "";
        }


    }

    private LinearLayout getImageParagaph(ContentBase content) {

        ImageParagaraph llImg = new ImageParagaraph(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        llImg.setLayoutParams(params);
        llImg.setImagePagaraph(content);

        return llImg;

    }

    private TextView getTextParagraph(String text) {

        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setText(Html.fromHtml(text));

        return textView;
    }
}
