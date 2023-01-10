package com.vivo.push.c;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.vivo.push.d;
import com.vivo.push.e;
import com.vivo.push.g;
import com.vivo.push.sdk.PushMessageCallback;
import com.vivo.push.util.n;
import com.vivo.push.util.s;
import java.security.PublicKey;

/* compiled from: OnReceiveTask */
public abstract class o extends e {
    protected PushMessageCallback c;

    o(g gVar) {
        super(gVar);
    }

    public final void a(PushMessageCallback pushMessageCallback) {
        this.c = pushMessageCallback;
    }

    public final boolean a(PublicKey publicKey, String str, String str2) {
        if (!d.a().c()) {
            n.d("OnVerifyCallBackCommand", "vertify is not support , vertify is ignore");
            return true;
        } else if (publicKey == null) {
            n.d("OnVerifyCallBackCommand", "vertify key is null");
            return false;
        } else if (TextUtils.isEmpty(str)) {
            n.d("OnVerifyCallBackCommand", "contentTag is null");
            return false;
        } else if (!TextUtils.isEmpty(str2)) {
            try {
                n.d("OnVerifyCallBackCommand", str.hashCode() + " = " + str2);
                if (s.a(str.getBytes("UTF-8"), publicKey, Base64.decode(str2, 2))) {
                    n.d("OnVerifyCallBackCommand", "vertify id is success");
                    return true;
                }
                n.d("OnVerifyCallBackCommand", "vertify fail srcDigest is " + str);
                Context context = this.a;
                n.c(context, "vertify fail srcDigest is " + str);
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                n.d("OnVerifyCallBackCommand", "vertify exception");
                return false;
            }
        } else {
            n.d("OnVerifyCallBackCommand", "vertify id is null");
            return false;
        }
    }
}
