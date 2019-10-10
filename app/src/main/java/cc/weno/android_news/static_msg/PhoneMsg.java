package cc.weno.android_news.static_msg;

import java.util.ArrayList;
import java.util.List;

import cc.weno.android_news.atiicleUtil.ArticleListAdapter;
import cc.weno.android_news.dao.WxAuthorList;

public class PhoneMsg {
    /**
     * 是否为分开屏占比
     */
    public static boolean isTwoPane;

    public static WxAuthorList wxAuthorList;

    public static List<ArticleListAdapter> articleListAdapters = new ArrayList<>();
}
