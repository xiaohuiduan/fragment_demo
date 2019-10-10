package cc.weno.android_news.fragment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.HashMap;

/**
 * 构建一个Fragment页面
 */
public class ArticleTitlePageAdapter extends FragmentStatePagerAdapter {

    private int num;
    private HashMap<Integer,ArticleTitleFra> map;

    public ArticleTitlePageAdapter(@NonNull FragmentManager fm, int num) {
        super(fm, num);
        this.num = num;
        map = new HashMap(num);
    }

    /**
     * 返回对应的Fragment
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (map.containsKey(position)){
            return map.get(position);
        }
        return createFragment(position);
    }

    /**
     * 创建一个frame
     * @param position
     * @return
     */
    private Fragment createFragment(int position) {
        Log.d("进行创造一个Fragment","True");
        map.put(position, new ArticleTitleFra(position));
        return map.get(position);
    }

    @Override
    public int getCount() {
        return num;
    }
}
