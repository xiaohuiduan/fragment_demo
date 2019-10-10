package cc.weno.android_news.net;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;

import cc.weno.android_news.dao.WxAuthorList;

/**
 * @author xiaohui
 */
public class GetWxAuthorList implements ProcessInterface{
    public static String url = "https://wanandroid.com/wxarticle/chapters/json";
    @Override
    public WxAuthorList call() {
        HttpRequest request = HttpRequest.get(url);
        WxAuthorList wxAuthorList = JSONObject.parseObject(request.body(),WxAuthorList.class);

        return wxAuthorList;
    }
}
