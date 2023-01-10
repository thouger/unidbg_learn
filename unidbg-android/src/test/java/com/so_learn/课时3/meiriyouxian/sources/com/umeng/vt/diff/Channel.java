package com.umeng.vt.diff;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import com.umeng.vt.diff.util.ClassLoadUtil;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Channel {
    private static final String UM_VISUAL_IMPRINT = "utm-visual";

    public void init(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            loadConfig(applicationContext);
            registerListener(applicationContext);
        }
    }

    public void loadConfig(Context context) {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, UM_VISUAL_IMPRINT, null);
        if (!TextUtils.isEmpty(imprintProperty)) {
            String str = new String(Base64.decode(imprintProperty, 0));
            HashMap hashMap = new HashMap();
            hashMap.put("data-track", str);
            storeConfig(hashMap);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.vt.diff.Channel$1  reason: invalid class name */
    public class AnonymousClass1 implements UMImprintChangeCallback {
        final /* synthetic */ Context val$context;

        AnonymousClass1(Context context) {
            this.val$context = context;
        }

        @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
        public void onImprintValueChanged(String str, String str2) {
            if (Channel.UM_VISUAL_IMPRINT.equals(str)) {
                Channel.this.loadConfig(this.val$context);
            }
        }
    }

    public void registerListener(Context context) {
        ImprintHandler.getImprintService(context).registImprintCallback(UM_VISUAL_IMPRINT, new AnonymousClass1(context));
    }

    private void storeConfig(Map<String, String> map) {
        Method method;
        try {
            Class<?> findClass = ClassLoadUtil.findClass("com.umeng.vt.vismode.config.ConfigTools");
            if (findClass != null && (method = findClass.getMethod("storeConfig", Map.class)) != null) {
                method.invoke(findClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void download() {
        Method method;
        try {
            Class<?> findClass = ClassLoadUtil.findClass("com.umeng.vt.vismode.config.ConfigTools");
            if (findClass != null && (method = findClass.getMethod(Context.DOWNLOAD_SERVICE, new Class[0])) != null) {
                method.invoke(findClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
