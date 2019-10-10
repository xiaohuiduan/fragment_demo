package cc.weno.android_news;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.ExecutionException;

import cc.weno.android_news.atiicleUtil.ArticleListAdapter;
import cc.weno.android_news.dao.WxAuthorList;
import cc.weno.android_news.fragment.ArticleTitlePageAdapter;
import cc.weno.android_news.net.AsyncRequest;
import cc.weno.android_news.net.GetWxAuthorList;
import cc.weno.android_news.net.ProcessInterface;
import cc.weno.android_news.static_msg.PhoneMsg;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.article_title_view_pager);
//        PhoneMsg.articleListAdapters.clear();
        // 进行初始化任务
        init();
    }

    private void init() {
        if (findViewById(R.id.land_content) != null) {
            PhoneMsg.isTwoPane = true;
        } else {
            PhoneMsg.isTwoPane = false;
        }
        initTab();
    }

    public void initTab() {
        try {

            AsyncTask<ProcessInterface, Integer, Object> asyncTask = new AsyncRequest().execute(new GetWxAuthorList());
            PhoneMsg.wxAuthorList = (WxAuthorList) asyncTask.get();

            tabLayout = findViewById(R.id.tablayout);

            // 创建Tab页
            for (int i = 0; i < PhoneMsg.wxAuthorList.getData().size(); i++) {
                tabLayout.addTab(tabLayout.newTab());
            }

            ArticleTitlePageAdapter adapter = new ArticleTitlePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

            viewPager.setAdapter(adapter);
            // 先创建在setupWithViewPager后面命名
            tabLayout.setupWithViewPager(viewPager);

            // 创建Tab页
            for (int i = 0; i < PhoneMsg.wxAuthorList.getData().size(); i++) {
                String name = PhoneMsg.wxAuthorList.getData().get(i).getName();
                int id =  PhoneMsg.wxAuthorList.getData().get(i).getId();
                tabLayout.getTabAt(i).setText(name);

                // 构建页面数据展示的adapter
                PhoneMsg.articleListAdapters.add(i,new ArticleListAdapter(MainActivity.this,id));
            }
            // 添加选择事件
            tabLayout.addOnTabSelectedListener(new TabClick(this));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class TabClick implements TabLayout.OnTabSelectedListener {
        Context context;

        public TabClick(Context context) {
            this.context = context;

        }

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
