package junit.zyb.com.restaurant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.activity.GoodsInfoActivity;
import junit.zyb.com.restaurant.bean.ResultBean;

import java.util.List;

public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String GOODS_BEAN = "goods_bean";
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据Bean对象
     */
    private ResultBean resultBean;
    /**
     * 三种类型
     */
    /**
     * 频道
     */
    public static final int CHANNEL = 0;

    /**
     * 推荐
     */
    public static final int RECOMMEND = 1;
    /**
     * 热卖
     */
    public static final int HOT = 2;

    /**
     * 当前类型
     */
    public int currentType = CHANNEL;
    private final LayoutInflater mLayoutInflater;

    public HomeRecycleAdapter(Context mContext, ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 根据位置得到类型-系统调用
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * 返回总条数，共3种类型
     * @return
     */
    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CHANNEL) {
            return new ChannelViewHolder(mLayoutInflater.inflate(R.layout.channel_item, null), mContext);
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item, null), mContext);
        } else if (viewType == HOT) {
            return new HotViewHolder(mLayoutInflater.inflate(R.layout.hot_item, null), mContext);
        }
        return null;
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }  else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_hot;
        private GridView gv_hot;
        private Context mContext;

        public HotViewHolder(View itemView, Context mContext) {
            super(itemView);
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
            this.mContext = mContext;
        }

        public void setData(final List<ResultBean.HotInfoBean> data) {
            HotGridViewAdapter adapter = new HotGridViewAdapter(mContext, data);
            gv_hot.setAdapter(adapter);

            //点击事件
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    GoodsInfoActivity.start(mContext,data.get(position),"0");
                }
            });
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private Context mContext;

        public RecommendViewHolder(View itemView, Context mContext) {
            super(itemView);
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);
            this.mContext = mContext;
        }

        public void setData(final List<ResultBean.RecommendInfoBean> data) {
            RecommendGridViewAdapter adapter = new RecommendGridViewAdapter(mContext, data);
            gv_recommend.setAdapter(adapter);

            //点击事件
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    GoodsInfoActivity.start(mContext,data.get(position),"1");
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        public GridView gvChannel;
        public Context mContext;

        public ChannelViewHolder(View itemView, Context mContext) {
            super(itemView);
            gvChannel = (GridView) itemView.findViewById(R.id.gv_channel);
            this.mContext = mContext;
        }

        public void setData(final List<ResultBean.ChannelInfoBean> channel_info) {
            gvChannel.setAdapter(new ChannelAdapter(mContext, channel_info));

            //点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    if (position <= 8) {
//                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
//                        intent.putExtra("position", position);
//                        mContext.startActivity(intent);
//                    } else {
//
//                    }
                }
            });
        }

    }


}
