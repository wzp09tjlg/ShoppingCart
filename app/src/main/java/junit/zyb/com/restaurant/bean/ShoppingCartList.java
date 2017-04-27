package junit.zyb.com.restaurant.bean;

import java.util.List;

/**
 * Created by zhouyibo on 2017/4/18.
 */

public class ShoppingCartList {
    private List<ShoppingCartBean> mList;

    public List<ShoppingCartBean> getList() {
        return mList;
    }

    public void setList(List<ShoppingCartBean> list) {
        mList = list;
    }
}
