package junit.zyb.com.restaurant.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by zhouyibo on 2017/4/18.
 */

public class Gsonholder {
    private static Gson gson;
    public static  Gson getGson() {
        if (gson==null){
            gson = new GsonBuilder()
                    .serializeNulls()//允许序列化反序列化为null
                    .create();
        }
        return gson;
    }
}
