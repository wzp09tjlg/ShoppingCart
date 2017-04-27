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
 * Created by Administrator on 2016/10/2.
 */
public class HotGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.HotInfoBean> data;

    public HotGridViewAdapter(Context mContext, List<ResultBean.HotInfoBean> data) {
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
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ResultBean.HotInfoBean hotInfoBean = data.get(position);
        Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE + hotInfoBean.getFigure())
                .into(holder.mIvHot);
        holder.mTvName.setText(hotInfoBean.getName());
        holder.mTvPrice.setText("￥" + hotInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView mIvHot;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_price)
        TextView mTvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
