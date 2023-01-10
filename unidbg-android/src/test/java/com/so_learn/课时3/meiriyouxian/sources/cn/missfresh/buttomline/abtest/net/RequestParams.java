package cn.missfresh.buttomline.abtest.net;

import java.util.Map;

public class RequestParams {
    private static Map<String, String> mParams;

    public static void init(Map<String, String> map) {
        mParams = map;
    }

    public static Map<String, String> getParams() {
        return mParams;
    }
}
