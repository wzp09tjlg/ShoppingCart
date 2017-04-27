package junit.zyb.com.restaurant.interfaces;

/**
 * Created by zhouyibo on 2017/4/18.
 */

public interface CheckInterface {
    /**
     * 组选框状态改变触发的事件
     *
     * @param position  元素位置
     * @param isChecked 元素选中与否
     */
    void checkGroup(int position, boolean isChecked);
}
