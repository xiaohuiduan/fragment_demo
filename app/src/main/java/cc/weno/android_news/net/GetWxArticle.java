package cc.weno.android_news.net;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;

import cc.weno.android_news.dao.WxArticleDao;
import cc.weno.android_news.dao.WxAuthorList;
import lombok.Setter;

@Setter
public class GetWxArticle implements ProcessInterface {

    //    https://wanandroid.com/wxarticle/list/408/1/json
    //    方法：GET
    //    参数：
    //    公众号 ID：拼接在 url 中，eg:405
    //    公众号页码：拼接在url 中，eg:1

    private int page;

    private int id;

    private String url;

    public GetWxArticle(int page, int id) {
        this.page = page;
        this.id = id;
        this.url = String.format("https://wanandroid.com/wxarticle/list/%d/%d/json",this.id,this.page);
    }

    @Override
    public Object call() {
        HttpRequest request = HttpRequest.get(url);
        WxArticleDao wxArticleDao = JSONObject.parseObject(request.body(), WxArticleDao.class);
        return wxArticleDao;
    }
}
