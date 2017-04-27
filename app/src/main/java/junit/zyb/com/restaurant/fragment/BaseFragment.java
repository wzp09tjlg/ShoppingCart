package junit.zyb.com.restaurant.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建人：贾林朋
 * 创建时间：2017年04月01日
 * 联系方式：15638181059@163.com
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {
    public Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 有子类实现，实现特有效果
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public void initData() {

    }


}

