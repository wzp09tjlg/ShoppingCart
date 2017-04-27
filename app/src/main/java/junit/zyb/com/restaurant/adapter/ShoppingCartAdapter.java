package junit.zyb.com.restaurant.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kpb.restaurant.R;

import junit.zyb.com.restaurant.api.Constants;
import junit.zyb.com.restaurant.bean.ShoppingCartBean;
import junit.zyb.com.restaurant.interfaces.CheckInterface;
import junit.zyb.com.restaurant.interfaces.ModifyCountInterface;

import java.util.List;

/**
 * Created by zhouyibo on 2017/4/18.
 */

public class ShoppingCartAdapter extends BaseAdapter {
    private boolean isShow = true;//是否显示编辑/完成
    private List<ShoppingCartBean> shoppingCartBeanList;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private Context context;

    public ShoppingCartAdapter(Context context) {
        this.context = context;
    }

    public void setShoppingCartBeanList(List<ShoppingCartBean> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
        notifyDataSetChanged();
    }

    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getCount() {
        return shoppingCartBeanList == null ? 0 : shoppingCartBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingCartBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 是否显示可编辑
     *
     * @param flag
     */
    public void isShow(boolean flag) {
        isShow = flag;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_cart, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        holder.tv_commodity_name.setText(shoppingCartBean.getGoods());
        holder.tv_fabric.setText("描述:" + shoppingCartBean.getExplain());
        holder.tv_price.setText("￥:" + shoppingCartBean.getPrice());
        holder.ck_chose.setChecked(shoppingCartBean.isChoosed());
        holder.tv_num.setText("X" + shoppingCartBean.getCount());
        Glide.with(context)
                .load(Constants.BASE_URl_IMAGE + shoppingCartBean.getImg())
                .into(holder.iv_show_pic);

        //单选框按钮
        holder.ck_chose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shoppingCartBean.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );

        //增加按钮
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露增加接口
            }
        });

        //删减按钮
        holder.iv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露删减接口
            }
        });

        //删除弹窗
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                modifyCountInterface.childDelete(position);//删除 目前只是从item中移除

                            }
                        });
                alert.show();
            }
        });

        //判断是否在编辑状态下
        if (isShow) {
            holder.tv_commodity_name.setVisibility(View.VISIBLE);
            holder.tv_fabric.setVisibility(View.VISIBLE);
            holder.rl_edit.setVisibility(View.GONE);
            holder.tv_delete.setVisibility(View.GONE);
        } else {
            holder.tv_commodity_name.setVisibility(View.GONE);
            holder.tv_fabric.setVisibility(View.GONE);
            holder.rl_edit.setVisibility(View.VISIBLE);
            holder.tv_delete.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    //初始化控件
    class ViewHolder {
        ImageView iv_chose;
        ImageView iv_show_pic, iv_sub, iv_add;
        TextView tv_commodity_name, tv_fabric, tv_dress, tv_pants, tv_price, tv_num, tv_delete, tv_show_num;
        CheckBox ck_chose;
        RelativeLayout rl_edit;

        public ViewHolder(View itemView) {
            ck_chose = (CheckBox) itemView.findViewById(R.id.ck_chose);
            iv_show_pic = (ImageView) itemView.findViewById(R.id.iv_show_pic);
            iv_sub = (ImageView) itemView.findViewById(R.id.iv_sub);
            iv_add = (ImageView) itemView.findViewById(R.id.iv_add);

            tv_commodity_name = (TextView) itemView.findViewById(R.id.tv_commodity_name);
            tv_fabric = (TextView) itemView.findViewById(R.id.tv_fabric);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            tv_show_num = (TextView) itemView.findViewById(R.id.tv_show_num);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            rl_edit = (RelativeLayout) itemView.findViewById(R.id.rl_edit);

        }

    }
}
