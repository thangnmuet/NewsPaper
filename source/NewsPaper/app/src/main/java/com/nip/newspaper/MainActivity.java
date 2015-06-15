package com.nip.newspaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.nip.newspaper.core.base.CategoryBase;
import com.nip.newspaper.core.paser.ArticleParserHtml;
import com.nip.newspaper.core.paser.IParserSuccess;
import com.nip.newspaper.zingnews.adapter.ArticleAdapter;
import com.nip.newspaper.zingnews.pages.ZingArticle;
import com.nip.newspaper.zingnews.pages.ZingArticleDetail;
import com.nip.newspaper.zingnews.pages.ZingPage;
import com.nip.newspaper.zingnews.parserHtml.ArticleDetailPaser;
import com.nip.newspaper.zingnews.parserHtml.HomeParser;
import com.nip.newspaper.zingnews.parserHtml.NormalPageParser;
import com.nip.newspaper.zingnews.view.GridViewWithHeaderAndFooter;


public class MainActivity extends Activity {


    private LinearLayout llHome;
    private LinearLayout llXahoi;
    private LinearLayout llThegoi;
    private LinearLayout llthiTruong;
    private LinearLayout llThethao;
    private LinearLayout llSongtre;
    private LinearLayout llPhapluat;
    private LinearLayout llGiaitri;
    private LinearLayout llAmnhac;
    private LinearLayout llPhimanh;
    private LinearLayout llthegoisach;
    private LinearLayout llcongnghe;
    private LinearLayout llotoxemay;
    private LinearLayout llSuckhoe;
    private LinearLayout llGiaoduc;
    private LinearLayout llDulich;

    private ArticleAdapter adapter;
    private GridViewWithHeaderAndFooter gridView;
    private IParserSuccess<ZingPage> homePage = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
//            addContent(value, llHome);


            loadPage(value);

        }
    };
    private IParserSuccess<ZingPage> xaHoi = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llXahoi);
        }
    };
    private IParserSuccess<ZingPage> thegoi = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llThegoi);
        }
    };
    private IParserSuccess<ZingPage> thitruong = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llthiTruong);
        }
    };
    private IParserSuccess<ZingPage> thethao = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llThethao);
        }
    };
    private IParserSuccess<ZingPage> songtre = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llSongtre);
        }
    };
    private IParserSuccess<ZingPage> phapluat = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llPhapluat);
        }
    };
    private IParserSuccess<ZingPage> giaitri = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llGiaitri);
        }
    };
    private IParserSuccess<ZingPage> amnhac = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llAmnhac);
        }
    };
    private IParserSuccess<ZingPage> phimanh = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llPhimanh);
        }
    };
    private IParserSuccess<ZingPage> thegioisach = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llthegoisach);
        }
    };
    private IParserSuccess<ZingPage> congnghe = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llcongnghe);
        }
    };
    private IParserSuccess<ZingPage> otoxemay = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llotoxemay);
        }
    };
    private IParserSuccess<ZingPage> suckhoe = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llSuckhoe);
        }
    };
    private IParserSuccess<ZingPage> giaoduc = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llGiaoduc);
        }
    };
    private IParserSuccess<ZingPage> dulich = new IParserSuccess<ZingPage>() {
        @Override
        public void succes(ZingPage value) {
            addContent(value, llDulich);
        }
    };

    private void loadPage(ZingPage value) {


        LayoutInflater inflater = LayoutInflater.from(this);
        View header = inflater.inflate(R.layout.header_gridview_page, null);
        TextView tv = (TextView) header.findViewById(R.id.tvHotTitle);
        TextView tvs = (TextView) header.findViewById(R.id.tvSumury);
        ImageView img = (ImageView) header.findViewById(R.id.mimPic);
        tv.setText(value.getHighlight().getTitle());
        tvs.setText(value.getHighlight().getSortDesciption());
//        value.getHighlight().getBitImage(img);
        Ion.with(img).placeholder(R.drawable.image_defaul).error(R.drawable.cat_img_default).load(value.getHighlight().getImage_link());

        gridView.addHeaderView(header);
        gridView.setAdapter(adapter);
        adapter.addPage(value);
    }
    ArticleDetailPaser articleParserHtml = new ArticleDetailPaser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

//        init();
//        execute();

        //String[] pages = getPageFromResouce();

        adapter = new ArticleAdapter(this);
        gridView = (GridViewWithHeaderAndFooter) findViewById(R.id.gvArticle);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                showArticle(view, i, l);

            }
        });
        //gridView.setAdapter(adapter);

        HomeParser home = new HomeParser();
        home.setSuccess(homePage);
        home.execute("http://news.zing.vn/");
        articleParserHtml.setSuccess(success);

    }

    private void showArticle(View view, int i, long l) {

        Toast.makeText(this,view.getTag(1010101010).toString(),Toast.LENGTH_SHORT).show();


        showArticle(view.getTag(1010101010).toString());
    }

    private void execute() {
        HomeParser home = new HomeParser();
        home.setSuccess(homePage);
        home.execute("http://news.zing.vn/");

        //========================
        NormalPageParser pageParser = new NormalPageParser();
        pageParser.setMain_link("http://news.zing.vn/");
        pageParser.setSuccess(xaHoi);
        pageParser.execute("http://news.zing.vn/xa-hoi.html");


        //========================
        pageParser = new NormalPageParser();
        pageParser.setMain_link("http://news.zing.vn/");
        pageParser.setSuccess(thegoi);
        pageParser.execute("http://news.zing.vn/the-gioi.html");

        //========================
        pageParser = new NormalPageParser();
        pageParser.setMain_link("http://news.zing.vn/");
        pageParser.setSuccess(thitruong);
        pageParser.execute("http://news.zing.vn/thi-truong.html");


//        //========================
//        pageParser = new NormalPageParser();
//        pageParser.setMain_link("http://news.zing.vn/");
//        pageParser.setSuccess(thethao);
//        pageParser.execute("http://news.zing.vn/the-thao.html");

        //========================
        pageParser = new NormalPageParser();
        pageParser.setMain_link("http://news.zing.vn/");
        pageParser.setSuccess(songtre);
        pageParser.execute("http://news.zing.vn/song-tre.html");

        //========================
        pageParser = new NormalPageParser();
        pageParser.setMain_link("http://news.zing.vn/");
        pageParser.setSuccess(phapluat);
        pageParser.execute("http://news.zing.vn/phap-luat.html");

        //========================
        pageParser = new NormalPageParser();
        pageParser.setMain_link("http://news.zing.vn/");
        pageParser.setSuccess(giaitri);
        pageParser.execute("http://news.zing.vn/giai-tri.html");

        //========================
        pageParser = new NormalPageParser();
        pageParser.setMain_link("http://news.zing.vn/");
        pageParser.setSuccess(amnhac);
        pageParser.execute("http://news.zing.vn/am-nhac.html");

//        //========================
//        pageParser = new NormalPageParser();
//        pageParser.setMain_link("http://news.zing.vn/");
//        pageParser.setSuccess(phimanh);
//        pageParser.execute("http://news.zing.vn/phim-anh.html");

//        //========================
//        pageParser = new NormalPageParser();
//        pageParser.setMain_link("http://news.zing.vn/");
//        pageParser.setSuccess(thegioisach);
//        pageParser.execute("http://news.zing.vn/the-gioi-sach.html");

//        //========================
//        pageParser = new NormalPageParser();
//        pageParser.setMain_link("http://news.zing.vn/");
//        pageParser.setSuccess(congnghe);
//        pageParser.execute("http://news.zing.vn/cong-nghe.html");
//
//        //========================
//        pageParser = new NormalPageParser();
//        pageParser.setMain_link("http://news.zing.vn/");
//        pageParser.setSuccess(otoxemay);
//        pageParser.execute("http://news.zing.vn/oto-xe-may.html");html
//
//        //========================
//        pageParser = new NormalPageParser();
//        pageParser.setMain_link("http://news.zing.vn/");
//        pageParser.setSuccess(suckhoe);
//        pageParser.execute("http://news.zing.vn/suc-khoe.html");
//
//
//
//        //========================
//        pageParser = new NormalPageParser();
//        pageParser.setMain_link("http://news.zing.vn/");
//        pageParser.setSuccess(giaoduc);
//        pageParser.execute("http://news.zing.vn/giao-duc.html");
//
//        //========================
//        pageParser = new NormalPageParser();
//        pageParser.setMain_link("http://news.zing.vn/");
//        pageParser.setSuccess(dulich);
//        pageParser.execute("http://news.zing.vn/du-lich.html");
    }

    private void init() {

        llHome = (LinearLayout) findViewById(R.id.llHome);
        llXahoi = (LinearLayout) findViewById(R.id.llXahoi);
        llThegoi = (LinearLayout) findViewById(R.id.llThegoi);
        llthiTruong = (LinearLayout) findViewById(R.id.llthitruong);
        llThethao = (LinearLayout) findViewById(R.id.llThethao);
        llSongtre = (LinearLayout) findViewById(R.id.llSongtre);
        llPhapluat = (LinearLayout) findViewById(R.id.llphapluat);
        llGiaitri = (LinearLayout) findViewById(R.id.llGiaiTri);
        llAmnhac = (LinearLayout) findViewById(R.id.llAmnhac);
        llPhimanh = (LinearLayout) findViewById(R.id.llPhimanh);
        llthegoisach = (LinearLayout) findViewById(R.id.llTheGioiSach);
        llcongnghe = (LinearLayout) findViewById(R.id.llCongNghe);
        llotoxemay = (LinearLayout) findViewById(R.id.llXemay);
        llSuckhoe = (LinearLayout) findViewById(R.id.llSuckhoe);
        llGiaoduc = (LinearLayout) findViewById(R.id.llGiaoduc);
        llDulich = (LinearLayout) findViewById(R.id.llDulic);

    }

    private String[] getPageFromResouce() {

        return getResources().getStringArray(R.array.zingPage);

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

    private void addContent(ZingPage value, LinearLayout llHome) {

    }

    IParserSuccess<ZingArticleDetail> success = new IParserSuccess<ZingArticleDetail>() {
        @Override
        public void succes(ZingArticleDetail value) {
            showArticle(value.getContentHtml());
        }
    };

    private void showArticle(String contentHtml) {
        Intent i = new Intent(this,Article.class);
        i.putExtra("html",contentHtml);
        startActivity(i);
    }

}
