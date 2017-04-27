package junit.zyb.com.restaurant.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.bean.ResultBean;
import junit.zyb.com.restaurant.api.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/29.
 */
public class RecommendGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.RecommendInfoBean> data;

    public RecommendGridViewAdapter(Context mContext, List<ResultBean.RecommendInfoBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ResultBean.RecommendInfoBean recommendInfoBean = data.get(position);
        Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE +recommendInfoBean.getFigure())
                .into(holder.ivRecommend);
        holder.tvName.setText(recommendInfoBean.getName());
        holder.tvPrice.setText("￥" + recommendInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }
}
