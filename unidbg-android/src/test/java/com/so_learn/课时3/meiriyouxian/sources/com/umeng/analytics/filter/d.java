package com.umeng.analytics.filter;

import android.security.keystore.KeyProperties;
import android.util.Base64;
import com.xiaomi.mipush.sdk.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: SmartDict */
public class d {
    private static final String b = "\u0102";
    private final String a = KeyProperties.DIGEST_MD5;
    private MessageDigest c;
    private Set<Object> d = new HashSet();
    private boolean e;

    public d(boolean z, String str) {
        int i = 0;
        this.e = false;
        this.e = z;
        try {
            this.c = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (str == null) {
            return;
        }
        if (z) {
            try {
                byte[] decode = Base64.decode(str.getBytes(), 0);
                while (i < decode.length / 4) {
                    int i2 = i * 4;
                    this.d.add(Integer.valueOf(((decode[i2 + 0] & 255) << 24) + ((decode[i2 + 1] & 255) << 16) + ((decode[i2 + 2] & 255) << 8) + (decode[i2 + 3] & 255)));
                    i++;
                }
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            }
        } else {
            String[] split = str.split(b);
            int length = split.length;
            while (i < length) {
                this.d.add(split[i]);
                i++;
            }
        }
    }

    private Integer c(String str) {
        try {
            this.c.update(str.getBytes());
            byte[] digest = this.c.digest();
            return Integer.valueOf(((digest[0] & 255) << 24) + ((digest[1] & 255) << 16) + ((digest[2] & 255) << 8) + (digest[3] & 255));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean a(String str) {
        if (this.e) {
            return this.d.contains(c(str));
        }
        return this.d.contains(str);
    }

    public void b(String str) {
        if (this.e) {
            this.d.add(c(str));
        } else {
            this.d.add(str);
        }
    }

    public void a() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : this.d) {
            sb.append(obj);
            if (sb.length() > 0) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        System.out.println(sb.toString());
    }

    public String toString() {
        if (this.e) {
            byte[] bArr = new byte[(this.d.size() * 4)];
            Iterator<Object> it2 = this.d.iterator();
            int i = 0;
            while (it2.hasNext()) {
                int intValue = ((Integer) it2.next()).intValue();
                int i2 = i + 1;
                bArr[i] = (byte) ((-16777216 & intValue) >> 24);
                int i3 = i2 + 1;
                bArr[i2] = (byte) ((16711680 & intValue) >> 16);
                int i4 = i3 + 1;
                bArr[i3] = (byte) ((65280 & intValue) >> 8);
                i = i4 + 1;
                bArr[i4] = (byte) (intValue & 255);
            }
            return new String(Base64.encode(bArr, 0));
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : this.d) {
            if (sb.length() > 0) {
                sb.append(b);
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }
}
