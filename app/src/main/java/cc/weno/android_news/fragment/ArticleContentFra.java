package cc.weno.android_news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cc.weno.android_news.R;


public class ArticleContentFra extends Fragment {

    private View view;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.article_content_fra, container, false);
        webViewInit();
        return view;
    }

    private void webViewInit(){
        webView = view.findViewById(R.id.web_content);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });
    }

    public void refresh(String url){
        webView.loadUrl(url);
    }

}
