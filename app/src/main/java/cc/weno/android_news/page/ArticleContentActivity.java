package cc.weno.android_news.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cc.weno.android_news.R;
import cc.weno.android_news.fragment.ArticleContentFra;
import cc.weno.android_news.fragment.ArticleTitleFra;

public class ArticleContentActivity extends AppCompatActivity {

    private ArticleContentFra articleTitleFra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d("买菜涨价",url);
        setContentView(R.layout.article_content_activity);
        goUrl(url);
    }

    private void goUrl(String url){
        articleTitleFra = (ArticleContentFra) getSupportFragmentManager().findFragmentById(R.id.article_content);
        articleTitleFra.refresh(url);
    }
}
