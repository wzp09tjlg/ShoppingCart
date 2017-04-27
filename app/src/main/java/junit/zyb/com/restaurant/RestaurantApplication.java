package junit.zyb.com.restaurant;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 创建人：贾林朋
 * 创建时间：2017年04月01日
 * 联系方式：15638181059@163.com
 */

public class RestaurantApplication extends Application {
    public static String name = "";
    public static Context gContext;
    @Override
    public void onCreate() {
        super.onCreate();
        gContext = this;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
