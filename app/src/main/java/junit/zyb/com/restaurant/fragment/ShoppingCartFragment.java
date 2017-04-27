package junit.zyb.com.restaurant.fragment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.adapter.ShoppingCartAdapter;
import junit.zyb.com.restaurant.api.Gsonholder;
import junit.zyb.com.restaurant.bean.ShoppingCartBean;
import junit.zyb.com.restaurant.bean.ShoppingCartList;
import junit.zyb.com.restaurant.interfaces.CheckInterface;
import junit.zyb.com.restaurant.interfaces.ModifyCountInterface;
import junit.zyb.com.restaurant.util.SRPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener
        , CheckInterface, ModifyCountInterface {

    @BindView(R.id.tv_edit)
    TextView mTvEdit;
    @BindView(R.id.list_shopping_cart)
    ListView mListShoppingCart;
    @BindView(R.id.ck_all)
    CheckBox mCkAll;
    @BindView(R.id.tv_settlement)
    TextView mTvSettlement;
    @BindView(R.id.tv_show_price)
    TextView mTvShowPrice;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;
    Unbinder unbinder;

    private ShoppingCartAdapter shoppingCartAdapter;
    private boolean flag = false;
    private List<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
    private List<ShoppingCartBean> checkList = new ArrayList<>();
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private boolean canSettlement = false;//是否可结算
    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mCkAll.setOnClickListener(this);
        mTvSettlement.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);
        shoppingCartAdapter = new ShoppingCartAdapter(getActivity());
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        mListShoppingCart.setAdapter(shoppingCartAdapter);
        shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);
        refreshData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        refreshData();
    }

    private void refreshData() {
        shoppingCartBeanList.clear();
        String gsonString = SRPreferences.getInstance().getString(SRPreferences.SRP_SHOP_CART,"");
        if (!gsonString.equals("")){
            shoppingCartBeanList.addAll(Gsonholder.getGson().fromJson(gsonString, ShoppingCartList.class).getList());
            shoppingCartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选按钮
            case R.id.ck_all:
                if (shoppingCartBeanList.size() != 0) {
                    if (mCkAll.isChecked()) {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;
            case R.id.tv_edit:
                flag = !flag;
                if (flag) {
                    mTvEdit.setText("完成");
                    changeLocalData();
                    shoppingCartAdapter.isShow(false);
                } else {
                    mTvEdit.setText("编辑");
                    shoppingCartAdapter.isShow(true);
                }
                break;
            case R.id.tv_settlement:
                if (canSettlement) {
                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                    alert.setTitle("操作提示");
                    alert.setMessage("共计消费" + totalPrice + "，是否确认结账？");
                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确认结账",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //网络请求结账
                                    shoppingCartBeanList.removeAll(checkList);
                                    changeLocalData();
                                    shoppingCartAdapter.notifyDataSetChanged();
                                    statistics();
                                    return;
                                }
                            });
                    alert.show();
                } else {
                    Toast.makeText(getContext(), "请选择包间", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoppingCartBeanList.get(position).setChoosed(isChecked);

        if (isAllCheck())
            mCkAll.setChecked(true);
        else
            mCkAll.setChecked(false);

        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = Integer.valueOf(shoppingCartBean.getCount());
        currentCount++;
        shoppingCartBean.setCount(currentCount + "");
        ((TextView) showCountView).setText(currentCount + "");
        changeLocalData();
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = Integer.valueOf(shoppingCartBean.getCount());
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount + "");
        ((TextView) showCountView).setText(currentCount + "");
        changeLocalData();
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    private void changeLocalData() {
        ShoppingCartList list = new ShoppingCartList();
        list.setList(shoppingCartBeanList);
        SRPreferences.getInstance().setString(SRPreferences.SRP_SHOP_CART,Gsonholder.getGson().toJson(list));
    }


    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
        changeLocalData();
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    public boolean isAllCheck() {
        for (ShoppingCartBean group : shoppingCartBeanList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        canSettlement = false;
        checkList.clear();
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
            if (shoppingCartBean.isChoosed()) {
                checkList.add(shoppingCartBeanList.get(i));
                totalCount++;
                totalPrice += Float.valueOf((shoppingCartBean.getPrice())) * Integer.valueOf(shoppingCartBean.getCount());
                if (shoppingCartBean.getType().equals("1")) {
                    canSettlement = true;
                }
            }
        }
        mTvShowPrice.setText("合计:" + totalPrice);
        mTvSettlement.setText("结算(" + totalCount + ")");
    }
}
