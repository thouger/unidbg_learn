package cn.missfresh.sherlock.trace.c;

import com.xiaomi.mipush.sdk.Constants;

/* compiled from: MethodItem */
public class a {
    public int a;
    public int b;
    public int c;
    public int d = 1;

    public a(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public void a(long j) {
        this.d++;
        this.b = (int) (((long) this.b) + j);
    }

    public String toString() {
        return this.c + Constants.ACCEPT_TIME_SEPARATOR_SP + this.a + Constants.ACCEPT_TIME_SEPARATOR_SP + this.d + Constants.ACCEPT_TIME_SEPARATOR_SP + this.b;
    }
}
