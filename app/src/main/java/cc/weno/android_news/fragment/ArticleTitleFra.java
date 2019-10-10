package cc.weno.android_news.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cc.weno.android_news.R;
import cc.weno.android_news.atiicleUtil.ArticleListAdapter;
import cc.weno.android_news.static_msg.PhoneMsg;

public class ArticleTitleFra extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArticleListAdapter adapter;


    public static ArticleTitleFra newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("Position", position);
        ArticleTitleFra articleTitleFra = new ArticleTitleFra();
        articleTitleFra.setArguments(bundle);
        return articleTitleFra;
    }

    public ArticleTitleFra(){

    }

    public ArticleTitleFra(int position){
        Bundle bundle = new Bundle();
        bundle.putInt("Position", position);
        setArguments(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int position = getArguments().getInt("Position");
        adapter = PhoneMsg.articleListAdapters.get(position);

        view = inflater.inflate(R.layout.article_title_fra, container, false);
        // 设置recyclerView
        initFrame();
        refresh();
        return view;
    }

    private void initFrame() {
        recyclerView = view.findViewById(R.id.article_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // 设置 ItemAnimator动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 刷新数据
     *
     * @param
     */
    public void refresh() {
        recyclerView.setAdapter(this.adapter);
    }

}
