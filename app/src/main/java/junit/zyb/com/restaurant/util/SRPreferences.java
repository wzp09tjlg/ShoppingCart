package junit.zyb.com.restaurant.util;

import android.content.Context;
import android.content.SharedPreferences;

import junit.zyb.com.restaurant.RestaurantApplication;


/**
 * Created by zyb on 2016/9/12.
 * function:
 * SharedPreferences类
 */
public class SRPreferences {

    public static final String SRP_SHOP_CART = "srp_shop_cart";
    public static final String SRP_USER_USERNAME = "srp_username";
    public static final String SRP_USER_PASSWORD = "srp_password";
    public static final String SRP_USER_PHONE = "srp_user_phone";
    public static final String SRP_USER_SEX = "srp_user_sex";


    private static SRPreferences ourInstance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public static SRPreferences getInstance() {
        if (ourInstance == null) {
            ourInstance = new SRPreferences(RestaurantApplication.gContext);
        }
        return ourInstance;
    }

    private SRPreferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("RestaurantApplication", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setString(final String key, final String value) {
        editor.putString(key, value).commit();
    }

    public String getString(final String key, final String value) {
        return preferences.getString(key, value);
    }

    /**
     * 获取指定Key对应的String值
     *
     * @param key 关键字
     * @return key对应的value，默认为空字符串""
     */
    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public void setLong(final String key, final Long value) {
        editor.putLong(key, value).commit();
    }

    public Long getLong(final String key, final Long value) {
        return preferences.getLong(key, value);
    }

    public void setInt(final String key, final int value) {
        editor.putInt(key, value).commit();
    }

    public int getInt(final String key, final int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void setBoolean(final String key, final boolean value) {
        editor.putBoolean(key, value).commit();
    }

    public Boolean getBoolean(final String key, final boolean value) {
        return preferences.getBoolean(key, value);
    }
}
