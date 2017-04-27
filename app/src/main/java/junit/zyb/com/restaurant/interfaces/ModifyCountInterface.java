package junit.zyb.com.restaurant.interfaces;

import android.view.View;

/**
 * Created by zhouyibo on 2017/4/18.
 */

public interface ModifyCountInterface {
    /**
     * 增加操作
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    void doIncrease(int position, View showCountView, boolean isChecked);

    /**
     * 删减操作
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    void doDecrease(int position, View showCountView, boolean isChecked);

    /**
     * 删除子item
     *
     * @param position
     */
    void childDelete(int position);
}
