package cn.missfresh.ad.data;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: NewApiBean */
public class b {
    public String a;
    public String b;
    public int c;
    public String d;
    public String e;
    public a f;

    /* compiled from: NewApiBean */
    public static class a {
        public String a;
        public String b;
        public int c;
        public String d;
    }

    public MFADBean a() {
        AppMethodBeat.i(5977, false);
        MFADBean mFADBean = new MFADBean();
        mFADBean.setCode(0);
        mFADBean.setCountdown_num(this.c);
        mFADBean.setLink(this.a);
        mFADBean.setPath(this.d);
        mFADBean.setSuccess(true);
        mFADBean.setPromotion_id(this.e);
        mFADBean.setType(this.b);
        mFADBean.setImage(this.d);
        mFADBean.setResourceData(this.f);
        AppMethodBeat.o(5977);
        return mFADBean;
    }
}
