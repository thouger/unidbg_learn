package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.internal.logging.nano.MetricsProto;
import com.sina.weibo.sdk.a.e;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.auth.c;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareRequestParam extends BrowserRequestParamBase {
    private c e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private BaseRequest k;
    private String l;
    private byte[] m;

    public ShareRequestParam(Context context) {
        super(context);
        this.c = BrowserLauncher.SHARE;
    }

    /* access modifiers changed from: protected */
    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void a(Bundle bundle) {
        this.i = bundle.getString("source");
        this.g = bundle.getString("packagename");
        this.j = bundle.getString("key_hash");
        this.h = bundle.getString(Constants.PARAM_ACCESS_TOKEN);
        this.f = bundle.getString("key_listener");
        if (!TextUtils.isEmpty(this.f)) {
            this.e = d.a(this.a).a(this.f);
        }
        d(bundle);
        this.b = c("");
    }

    private void d(Bundle bundle) {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        weiboMultiMessage.toObject(bundle);
        StringBuilder sb = new StringBuilder();
        if (weiboMultiMessage.textObject instanceof TextObject) {
            sb.append(weiboMultiMessage.textObject.text);
        }
        if (weiboMultiMessage.imageObject instanceof ImageObject) {
            ImageObject imageObject = weiboMultiMessage.imageObject;
            a(imageObject.imagePath, imageObject.imageData);
        }
        if (weiboMultiMessage.mediaObject instanceof TextObject) {
            sb.append(((TextObject) weiboMultiMessage.mediaObject).text);
        }
        if (weiboMultiMessage.mediaObject instanceof ImageObject) {
            ImageObject imageObject2 = (ImageObject) weiboMultiMessage.mediaObject;
            a(imageObject2.imagePath, imageObject2.imageData);
        }
        if (weiboMultiMessage.mediaObject instanceof WebpageObject) {
            sb.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            sb.append(((WebpageObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof MusicObject) {
            sb.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            sb.append(((MusicObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof VideoObject) {
            sb.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            sb.append(((VideoObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof VoiceObject) {
            sb.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            sb.append(((VoiceObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        this.l = sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041 A[SYNTHETIC, Splitter:B:20:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0048 A[SYNTHETIC, Splitter:B:28:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r6, byte[] r7) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto L_0x004b
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            boolean r6 = r0.exists()
            if (r6 == 0) goto L_0x004b
            boolean r6 = r0.canRead()
            if (r6 == 0) goto L_0x004b
            long r1 = r0.length()
            r3 = 0
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x004b
            long r1 = r0.length()
            int r6 = (int) r1
            byte[] r6 = new byte[r6]
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0045, all -> 0x003d }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0045, all -> 0x003d }
            r2.read(r6)     // Catch:{ IOException -> 0x0046, all -> 0x003b }
            byte[] r6 = com.sina.weibo.sdk.a.c.b(r6)     // Catch:{ IOException -> 0x0046, all -> 0x003b }
            r5.m = r6     // Catch:{ IOException -> 0x0046, all -> 0x003b }
            r2.close()     // Catch:{ Exception -> 0x003a }
        L_0x003a:
            return
        L_0x003b:
            r6 = move-exception
            goto L_0x003f
        L_0x003d:
            r6 = move-exception
            r2 = r1
        L_0x003f:
            if (r2 == 0) goto L_0x0044
            r2.close()     // Catch:{ Exception -> 0x0044 }
        L_0x0044:
            throw r6
        L_0x0045:
            r2 = r1
        L_0x0046:
            if (r2 == 0) goto L_0x004b
            r2.close()     // Catch:{ SecurityException -> 0x004b }
        L_0x004b:
            if (r7 == 0) goto L_0x0056
            int r6 = r7.length
            if (r6 <= 0) goto L_0x0056
            byte[] r6 = com.sina.weibo.sdk.a.c.b(r7)
            r5.m = r6
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.component.ShareRequestParam.a(java.lang.String, byte[]):void");
    }

    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void b(Bundle bundle) {
        BaseRequest baseRequest = this.k;
        if (baseRequest != null) {
            baseRequest.toBundle(bundle);
        }
        if (!TextUtils.isEmpty(this.g)) {
            this.j = e.a(k.a(this.a, this.g));
        }
        bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.h);
        bundle.putString("source", this.i);
        bundle.putString("packagename", this.g);
        bundle.putString("key_hash", this.j);
        bundle.putString("_weibo_appPackage", this.g);
        bundle.putString("_weibo_appKey", this.i);
        bundle.putInt("_weibo_flag", 538116905);
        bundle.putString("_weibo_sign", this.j);
        if (this.e != null) {
            d a2 = d.a(this.a);
            this.f = a2.a();
            a2.a(this.f, this.e);
            bundle.putString("key_listener", this.f);
        }
    }

    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void a(Activity activity, int i) {
        if (i == 3) {
            a(activity);
            WeiboSdkBrowser.a(activity, this.f, (String) null);
        }
    }

    public boolean a() {
        byte[] bArr = this.m;
        return bArr != null && bArr.length > 0;
    }

    public com.sina.weibo.sdk.net.e a(com.sina.weibo.sdk.net.e eVar) {
        if (!a()) {
            return eVar;
        }
        eVar.a(SocialConstants.PARAM_IMG_URL, new String(this.m));
        return eVar;
    }

    public String c(String str) {
        Uri.Builder buildUpon = Uri.parse("http://service.weibo.com/share/mobilesdk.php").buildUpon();
        buildUpon.appendQueryParameter("title", this.l);
        buildUpon.appendQueryParameter("version", "0031405000");
        if (!TextUtils.isEmpty(this.i)) {
            buildUpon.appendQueryParameter("source", this.i);
        }
        if (!TextUtils.isEmpty(this.h)) {
            buildUpon.appendQueryParameter(Constants.PARAM_ACCESS_TOKEN, this.h);
        }
        String b = k.b(this.a, this.i);
        if (!TextUtils.isEmpty(b)) {
            buildUpon.appendQueryParameter("aid", b);
        }
        if (!TextUtils.isEmpty(this.g)) {
            buildUpon.appendQueryParameter("packagename", this.g);
        }
        if (!TextUtils.isEmpty(this.j)) {
            buildUpon.appendQueryParameter("key_hash", this.j);
        }
        if (!TextUtils.isEmpty(str)) {
            buildUpon.appendQueryParameter("picinfo", str);
        }
        return buildUpon.build().toString();
    }

    private void a(Activity activity, int i, String str) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            Intent intent = new Intent("com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY");
            intent.setFlags(131072);
            intent.setPackage(extras.getString("_weibo_appPackage"));
            intent.putExtras(extras);
            intent.putExtra("_weibo_appPackage", activity.getPackageName());
            intent.putExtra("_weibo_resp_errcode", i);
            intent.putExtra("_weibo_resp_errstr", str);
            try {
                activity.startActivityForResult(intent, MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_BATTERY_DENY);
            } catch (ActivityNotFoundException unused) {
            }
        }
    }

    public void a(Activity activity) {
        a(activity, 1, "send cancel!!!");
    }

    public void b(Activity activity) {
        a(activity, 0, "send ok!!!");
    }

    public void a(Activity activity, String str) {
        a(activity, 2, str);
    }

    public void a(BaseRequest baseRequest) {
        this.k = baseRequest;
    }

    public void d(String str) {
        this.g = str;
    }

    public void e(String str) {
        this.h = str;
    }

    public String b() {
        return this.i;
    }

    public void f(String str) {
        this.i = str;
    }

    public c c() {
        return this.e;
    }

    public String h() {
        return this.f;
    }

    public void a(c cVar) {
        this.e = cVar;
    }

    public static class a {
        private int a = -2;
        private String b;

        private a() {
        }

        public int a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public static a a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            a aVar = new a();
            try {
                JSONObject jSONObject = new JSONObject(str);
                aVar.a = jSONObject.optInt(com.taobao.accs.common.Constants.KEY_HTTP_CODE, -2);
                aVar.b = jSONObject.optString("data", "");
            } catch (JSONException unused) {
            }
            return aVar;
        }
    }
}
