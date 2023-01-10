package com.sobot.chat.core.http.f;

import android.net.wifi.WifiEnterpriseConfig;
import com.umeng.message.proguard.l;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StTableEntity */
public class e {
    public String a;
    private List<d> b = new ArrayList();

    public e(String str) {
        this.a = str;
    }

    public e a(d dVar) {
        this.b.add(dVar);
        return this;
    }

    public String a() {
        StringBuilder sb = new StringBuilder(l.o);
        sb.append(this.a);
        sb.append('(');
        for (d dVar : this.b) {
            if (dVar.c != null) {
                sb.append("PRIMARY KEY (");
                for (String str : dVar.c) {
                    sb.append(str);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(l.t);
            } else {
                sb.append(dVar.a);
                sb.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                sb.append(dVar.b);
                if (dVar.e) {
                    sb.append(" NOT NULL");
                }
                if (dVar.d) {
                    sb.append(" PRIMARY KEY");
                }
                if (dVar.f) {
                    sb.append(" AUTOINCREMENT");
                }
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        if (sb.toString().endsWith(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(')');
        return sb.toString();
    }
}
