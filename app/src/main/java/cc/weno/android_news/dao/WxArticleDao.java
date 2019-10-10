package cc.weno.android_news.dao;

import java.util.List;

import lombok.Data;

@Data
public class WxArticleDao {

    private DataBean data;
    private int errorCode;
    private String errorMsg;

   @Data
    public static class DataBean {

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;
        @Data
        public static class DatasBean {

            private String apkLink;
            private int audit;
            private String author;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<TagsBean> tags;

            @Data
            public static class TagsBean {
                private String name;
                private String url;

            }
        }
    }
}
