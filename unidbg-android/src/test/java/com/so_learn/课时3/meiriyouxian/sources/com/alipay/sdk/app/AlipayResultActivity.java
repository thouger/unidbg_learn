package com.alipay.sdk.app;

import android.app.Activity;
import android.os.Bundle;
import java.util.concurrent.ConcurrentHashMap;

public class AlipayResultActivity extends Activity {
    public static final ConcurrentHashMap<String, a> a = new ConcurrentHashMap<>();

    public interface a {
        void a(int i, String str, String str2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c4 A[ADDED_TO_REGION] */
    @Override // android.app.Activity
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r13) {
        /*
        // Method dump skipped, instructions count: 323
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AlipayResultActivity.onCreate(android.os.Bundle):void");
    }

    private void a(String str, Bundle bundle) {
        a remove = a.remove(str);
        if (remove == null) {
            finish();
            return;
        }
        try {
            remove.a(bundle.getInt("endCode"), bundle.getString("memo"), bundle.getString("result"));
        } finally {
            finish();
        }
    }
}
