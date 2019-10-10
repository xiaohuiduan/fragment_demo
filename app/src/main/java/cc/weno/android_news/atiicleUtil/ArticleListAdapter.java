package cc.weno.android_news.atiicleUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import cc.weno.android_news.MainActivity;
import cc.weno.android_news.R;
import cc.weno.android_news.dao.WxArticleDao;
import cc.weno.android_news.fragment.ArticleContentFra;
import cc.weno.android_news.net.AsyncRequest;
import cc.weno.android_news.net.GetWxArticle;
import cc.weno.android_news.net.ProcessInterface;
import cc.weno.android_news.page.ArticleContentActivity;
import cc.weno.android_news.static_msg.PhoneMsg;

/**
 * 填充recycleView的数据
 */
public class ArticleListAdapter  extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder>{

    private MainActivity context;
    private WxArticleDao wxArticleDao;
    private LayoutInflater layoutInflater;
    private int id;
    private int _page = 1;
    private ArticleContentFra articleContentFra;

    public ArticleListAdapter(MainActivity context, int id) {
        this.id = id;
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);

        AsyncTask<ProcessInterface, Integer, Object> asyncTask = new AsyncRequest().execute(new GetWxArticle(_page,id));
        try {
            // 获得了数据
            wxArticleDao = (WxArticleDao) asyncTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.article_card_view,parent,false);
        final  ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int i = position;
        holder.articleNameView.setText(wxArticleDao.getData().getDatas().get(position).getAuthor());
        holder.authorView.setText(wxArticleDao.getData().getDatas().get(position).getTitle());

        // 进行跳转
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PhoneMsg.isTwoPane){
                    Intent mainIntent = new Intent(context, ArticleContentActivity.class);
                    mainIntent.putExtra("url",wxArticleDao.getData().getDatas().get(i).getLink());
                    context.startActivity(mainIntent);
                }else{
                    articleContentFra = (ArticleContentFra) context.getSupportFragmentManager().findFragmentById(R.id.web_fragment);
                    articleContentFra.refresh(wxArticleDao.getData().getDatas().get(i).getLink());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wxArticleDao.getData().getDatas().size();
    }

    public static class ViewHolder  extends  RecyclerView.ViewHolder{
        TextView authorView;
        TextView articleNameView;
        LinearLayout container;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorView = itemView.findViewById(R.id.author);
            articleNameView = itemView.findViewById(R.id.article_name);
            container = itemView.findViewById(R.id.title_container);
        }
    }
}
