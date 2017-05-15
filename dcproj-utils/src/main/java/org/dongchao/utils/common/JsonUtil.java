package org.dongchao.utils.common;


/**
 * Created by zhaodongchao on 2017/5/14.
 */
public final class JsonUtil {

    private static GsonImpl gson = new GsonImpl();

    public static String Object2JsonString(Object object) {
        return gson.toJson(object);
    }

    public static <T> T formJson(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

}
