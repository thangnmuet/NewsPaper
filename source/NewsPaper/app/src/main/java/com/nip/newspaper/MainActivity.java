package com.nip.newspaper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.nip.newspaper.core.base.ArticleBase;
import com.nip.newspaper.core.base.CategoryBase;
import com.nip.newspaper.core.paser.IParserSuccess;
import com.nip.newspaper.zingnews.composite.CategotyComposite;
import com.nip.newspaper.zingnews.composite.HotCategoryComposite;
import com.nip.newspaper.zingnews.pages.ZingCategory;
import com.nip.newspaper.zingnews.pages.ZingPage;
import com.nip.newspaper.zingnews.parserHtml.HomeParser;
import com.nip.newspaper.zingnews.parserHtml.NormalPageParser;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HomeParser home = new HomeParser();
        home.setSuccess(homePage);
//        home.execute("http://news.zing.vn/");
        NormalPageParser pageParser = new NormalPageParser();
        pageParser.setMain_link("http://news.zing.vn/");
        pageParser.setSuccess(homePage);
        pageParser.execute("http://news.zing.vn/xa-hoi.html");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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

    private IParserSuccess<ZingPage> homePage = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            remove(value);
            for (ArticleBase cat : value.getListCategory().get(0).getListArticle()) {
                Log.d("Main", cat.getTitle() + " " + cat.getSortDesciption());
            }
        }
    };

    void remove(ZingPage cat){
        View myView = findViewById(R.id.tvHello);
        ViewGroup parent = (ViewGroup) myView.getParent();
        parent.removeView(myView);

        CategotyComposite hot = new CategotyComposite(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        parent.addView(hot);
        hot.loadPage(cat);

    }
}
