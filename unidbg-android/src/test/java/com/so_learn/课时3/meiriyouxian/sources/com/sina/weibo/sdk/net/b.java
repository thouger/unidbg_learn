package com.sina.weibo.sdk.net;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;

/* compiled from: NetUtils */
public class b {
    public static String a(Context context, String str, String str2, e eVar) {
        return HttpManager.b(context, str, str2, eVar);
    }

    public static String a(Context context, String str, String str2, String str3) throws WeiboException {
        return HttpManager.a(context, str, str2, str3);
    }

    public static String b(Context context, String str, String str2, e eVar) {
        return HttpManager.a(context, str, str2, eVar);
    }

    public static void a(Context context, String str, e eVar, String str2, c cVar) {
        new AsyncWeiboRunner.b(context, str, eVar, str2, cVar).execute(new Void[1]);
    }
}
