package junit.zyb.com.restaurant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.api.Constants;
import junit.zyb.com.restaurant.api.Gsonholder;
import junit.zyb.com.restaurant.bean.ResultBean;
import junit.zyb.com.restaurant.bean.ShoppingCartBean;
import junit.zyb.com.restaurant.bean.ShoppingCartList;
import junit.zyb.com.restaurant.util.SRPreferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsInfoActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView mIvFinish;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_image)
    ImageView mIvImage;
    @BindView(R.id.goodsname)
    TextView mGoodsname;
    @BindView(R.id.goodsexplain)
    TextView mGoodsexplain;
    @BindView(R.id.goodsprice)
    TextView mGoodsprice;
    @BindView(R.id.ev_num)
    EditText mEvNum;
    @BindView(R.id.bt_add)
    Button mBtAdd;

    private Context mContext = this;
    private String goodsid = "";
    private String count = "0";
    String cover_price;
    String name;
    String figure;
    String product_id;
    private String type;

    public static void start(Context context, Serializable bean, String type) {
        Intent starter = new Intent(context, GoodsInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", bean);
        bundle.putString("type", type);
        starter.putExtras(bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        type = getIntent().getExtras().getString("type");
        switch (type) {
            case "0":
                ResultBean.HotInfoBean object = (ResultBean.HotInfoBean) getIntent().getExtras().getSerializable("object");
                cover_price = object.getCover_price();
                name = object.getName();
                figure = object.getFigure();
                product_id = object.getProduct_id();
                break;
            case "1":
                ResultBean.RecommendInfoBean object1 = (ResultBean.RecommendInfoBean) getIntent().getExtras().getSerializable("object");
                cover_price = object1.getCover_price();
                name = object1.getName();
                figure = object1.getFigure();
                product_id = object1.getProduct_id();
                break;
        }
        mTvTitle.setText(name);
        Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE + figure)
                .into(mIvImage);
        mEvNum.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        initData();
    }

    private void initData() {
        mTvTitle.setText(name);
        mGoodsname.setText("商品："+name);
        mGoodsexplain.setText("介绍："+name);
        mGoodsprice.setText("价格："+cover_price);
    }

    @OnClick({R.id.iv_finish, R.id.bt_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                finish();
                break;
            case R.id.bt_add:
                ShoppingCartBean bean = new ShoppingCartBean();
                bean.setType(type);
                bean.setGoods(name);
                bean.setPrice(cover_price);
                bean.setId(product_id);
                bean.setExplain(name);
                bean.setCount(mEvNum.getText().toString());
                bean.setImg(figure);

                String jsonstring = SRPreferences.getInstance().getString(SRPreferences.SRP_SHOP_CART,"");
                ShoppingCartList lists;
                if (jsonstring.equals("")){
                    lists= new ShoppingCartList();
                    List<ShoppingCartBean> empty = new ArrayList<>();
                    lists.setList(empty);
                }else {
                    lists = Gsonholder.getGson().fromJson(jsonstring,ShoppingCartList.class);
                }
                List<ShoppingCartBean> list = lists.getList();
                list.add(bean);
                lists.setList(list);
                SRPreferences.getInstance().setString(SRPreferences.SRP_SHOP_CART,Gsonholder.getGson().toJson(lists));
                Toast.makeText(mContext,"已加入购物车",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
