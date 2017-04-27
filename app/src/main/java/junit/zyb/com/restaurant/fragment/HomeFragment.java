package junit.zyb.com.restaurant.fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kpb.restaurant.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import junit.zyb.com.restaurant.adapter.HomeRecycleAdapter;
import junit.zyb.com.restaurant.bean.ResultBean;
import junit.zyb.com.restaurant.api.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.rv_home)
    RecyclerView recyclerView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void initData() {
                OkHttpUtils.get()
                .url(Constants.HOME_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResultBean resultBean=processData(response);
                        HomeRecycleAdapter adapter = new HomeRecycleAdapter(mContext, resultBean);
                        recyclerView.setAdapter(adapter);

                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
                        recyclerView.setLayoutManager(manager);
                    }
                });
    }

    @Nullable
    private ResultBean processData(String json) {
        if (!TextUtils.isEmpty(json)) {
            JSONObject jsonObject = JSON.parseObject(json);
            //得到状态码
            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            String result = jsonObject.getString("result");

            //得到resultBean的数据
            JSONObject ResultBean = JSON.parseObject(result, ResultBean.class);
            String banner_info = ResultBean.getString("banner_info");
            String act_info = ResultBean.getString("act_info");
            String channel_info = ResultBean.getString("channel_info");
            String hot_info = ResultBean.getString("hot_info");
            String recommend_info = ResultBean.getString("recommend_info");
            String seckill_info = ResultBean.getString("seckill_info");

            ResultBean  resultBean = new ResultBean();

            //设置BannerInfoBean数据
            List<ResultBean.BannerInfoBean> bannerInfoBeans = JSON.parseArray(banner_info, ResultBean.BannerInfoBean.class);
            resultBean.setBanner_info(bannerInfoBeans);
            String value = jsonObject.getString("value");
            ResultBean.BannerInfoBean.ValueBean valueBean = JSON.parseObject(value, ResultBean.BannerInfoBean.ValueBean.class);

            //设置actInfoBeans数据
            List<ResultBean.ActInfoBean> actInfoBeans = JSON.parseArray(act_info, ResultBean.ActInfoBean.class);
            resultBean.setAct_info(actInfoBeans);

            //设置channelInfoBeans的数据
            List<ResultBean.ChannelInfoBean> channelInfoBeans = JSON.parseArray(channel_info, ResultBean.ChannelInfoBean.class);
            resultBean.setChannel_info(channelInfoBeans);

            //设置hotInfoBeans的数据
            List<ResultBean.HotInfoBean> hotInfoBeans = JSON.parseArray(hot_info, ResultBean.HotInfoBean.class);
            resultBean.setHot_info(hotInfoBeans);

            //设置recommendInfoBeans的数据
            List<ResultBean.RecommendInfoBean> recommendInfoBeans = JSON.parseArray(recommend_info, ResultBean.RecommendInfoBean.class);
            resultBean.setRecommend_info(recommendInfoBeans);

            //设置seckillInfoBean的数据
            ResultBean.SeckillInfoBean seckillInfoBean = JSON.parseObject(seckill_info, ResultBean.SeckillInfoBean.class);
            resultBean.setSeckill_info(seckillInfoBean);

            return  resultBean;
        }
        return  null;

    }

}
