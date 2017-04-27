package junit.zyb.com.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kpb.restaurant.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import junit.zyb.com.restaurant.bean.GoodsManagerBean;
import junit.zyb.com.restaurant.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagerActivity extends AppCompatActivity {
    @BindView(R.id.tv_add)
    TextView mTvAdd;
    @BindView(R.id.xrecyclerview)
    XRecyclerView mXrecyclerview;
    @BindView(R.id.activity_manager)
    LinearLayout mActivityManager;
    private Context mContext = this;
    private List<GoodsManagerBean> totalList = new ArrayList<>();
    private CommonAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, ManagerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            GoodsManagerBean bean = new GoodsManagerBean();
            bean.setId(i + "");
            bean.setExplain("说明" + i);
            bean.setName("名字" + i);
            bean.setPrice(i * 4 + "");
            totalList.add(bean);
        }
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        mXrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new CommonAdapter<GoodsManagerBean>(mContext, R.layout.item_manager, totalList) {
            @Override
            protected void convert(ViewHolder holder, final GoodsManagerBean goodsManagerBean, int position) {
                holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        CustomDialog dialog = new CustomDialog(mContext) {
                            @Override
                            public void commit() {
                                //提交网络请求
                            }
                        };
                        dialog.setid(goodsManagerBean.getId())
                                .setexplain(goodsManagerBean.getExplain())
                                .setname(goodsManagerBean.getName())
                                .setprice(goodsManagerBean.getPrice())
                                .show();
                        dialog.show();
                        return false;
                    }
                });
            }
        };
        mXrecyclerview.setAdapter(adapter);
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog(mContext) {
                    @Override
                    public void commit() {
                        //提交网络请求
                    }
                };
                dialog.show();
            }
        });
    }
}
