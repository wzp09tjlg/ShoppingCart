package junit.zyb.com.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kpb.restaurant.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import junit.zyb.com.restaurant.bean.BuyRecordBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyRecordActivity extends AppCompatActivity {

    @BindView(R.id.xrecyclerview)
    XRecyclerView mXrecyclerview;
    @BindView(R.id.activity_buy_record)
    LinearLayout mActivityBuyRecord;

    private List<BuyRecordBean> totalList = new ArrayList<>();
    private Context mContext = this;
    private CommonAdapter adapter;
    public static void start(Context context) {
        Intent starter = new Intent(context, BuyRecordActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_record);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        //网络获取当前登陆用户的购买记录用户信息在SRPreferences中有存，获取数据添加到totalList中，
        for (int i = 0; i < 5; i++) {
            BuyRecordBean bean = new BuyRecordBean();
            bean.setGoodsname("包间"+i);
            bean.setPrice(100*(i+1)+"RMB");
            bean.setTime("7-18 11：22：33");
            totalList.add(bean);
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        mXrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new CommonAdapter<BuyRecordBean>(mContext, R.layout.item_buy_record,totalList) {
            @Override
            protected void convert(ViewHolder holder, BuyRecordBean buyRecordBean, int position) {
                ((TextView) holder.getView(R.id.label_tv_left_top)).setText(buyRecordBean.getTime().substring(0,4));
                ((TextView) holder.getView(R.id.label_tv_left_bottom)).setText(buyRecordBean.getTime().substring(5));
                ((TextView) holder.getView(R.id.label_tv_center)).setText(buyRecordBean.getGoodsname());
                ((TextView) holder.getView(R.id.label_tv_right)).setText(buyRecordBean.getPrice());
            }
        };
        mXrecyclerview.setAdapter(adapter);
    }
}
