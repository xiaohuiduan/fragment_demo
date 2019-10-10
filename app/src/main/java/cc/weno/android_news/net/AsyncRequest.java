package cc.weno.android_news.net;

import android.os.AsyncTask;

/**
 * 异步请求
 */
public class AsyncRequest extends AsyncTask<ProcessInterface, Integer, Object> {

    @Override
    protected Object doInBackground(ProcessInterface... processInterfaces) {
        return processInterfaces[0].call();
    }

}
