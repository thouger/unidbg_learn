package cn.missfresh.ad.data.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import cn.missfresh.ad.b.a;
import cn.missfresh.ad.data.MFADBean;
import cn.missfresh.ad.data.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.tencent.imsdk.BaseConstants;
import java.util.HashMap;
import java.util.Map;

public class MFADDbManager {
    private final Map<String, MFADBean> a = new HashMap();
    private final SharedPreferences b;

    public MFADDbManager(Context context) {
        AppMethodBeat.i(5989, false);
        this.b = context.getSharedPreferences("mf_ad_db", 0);
        AppMethodBeat.o(5989);
    }

    public String a() {
        AppMethodBeat.i(5991, false);
        String string = this.b.getString("ADCacheFileUrl", "");
        AppMethodBeat.o(5991);
        return string;
    }

    public void a(String str) {
        AppMethodBeat.i(5992, false);
        this.b.edit().putString("ADCacheFileUrl", str).apply();
        AppMethodBeat.o(5992);
    }

    public void b() {
        AppMethodBeat.i(5995, false);
        this.b.edit().putLong("mf_ad_cache_date", System.currentTimeMillis()).apply();
        AppMethodBeat.o(5995);
    }

    public boolean c() {
        boolean z = false;
        AppMethodBeat.i(5996, false);
        long j = this.b.getLong("mf_ad_cache_date", System.currentTimeMillis());
        a.a("MFADSDK", "checkIsExpire: cacheDate " + j + " current : " + System.currentTimeMillis() + " ExpireDate " + e());
        if ((System.currentTimeMillis() - j) / 86400000 > e()) {
            z = true;
        }
        AppMethodBeat.o(5996);
        return z;
    }

    public void a(long j) {
        AppMethodBeat.i(5997, false);
        this.b.edit().putLong("mf_ad_expire_day_counts", j).apply();
        AppMethodBeat.o(5997);
    }

    private long e() {
        AppMethodBeat.i(5998, false);
        long j = this.b.getLong("mf_ad_expire_day_counts", 0);
        AppMethodBeat.o(5998);
        return j;
    }

    public void a(MFADBean mFADBean) {
        AppMethodBeat.i(6001, false);
        if (mFADBean != null) {
            a.a("MFADSDK", "add bean: " + mFADBean.toString());
            this.b.edit().putInt("countdown_num", mFADBean.getCountdown_num()).putString("path", mFADBean.getPath()).putString("image", mFADBean.getImage()).putString("link", mFADBean.getLink()).putString("name", mFADBean.getName()).putString("type", mFADBean.getType()).putString("promotion_id", mFADBean.getPromotion_id()).putString("resource_data", JSON.toJSONString(mFADBean.getResourceData())).commit();
        }
        AppMethodBeat.o(6001);
    }

    public MFADBean d() {
        AppMethodBeat.i(6004, false);
        MFADBean mFADBean = new MFADBean();
        mFADBean.setCountdown_num(this.b.getInt("countdown_num", -1));
        mFADBean.setPath(this.b.getString("path", ""));
        mFADBean.setImage(this.b.getString("image", ""));
        mFADBean.setLink(this.b.getString("link", ""));
        mFADBean.setName(this.b.getString("name", ""));
        mFADBean.setType(this.b.getString("type", ""));
        mFADBean.setPromotion_id(this.b.getString("promotion_id", ""));
        try {
            mFADBean.setResourceData((b.a) JSON.parseObject(this.b.getString("resource_data", ""), b.a.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        a.a("MFADSDK", "get bean: " + mFADBean.toString());
        AppMethodBeat.o(6004);
        return mFADBean;
    }

    public void a(String str, MFADBean mFADBean) {
        AppMethodBeat.i(BaseConstants.ERR_FILE_TRANS_NO_SERVER, false);
        if (!TextUtils.isEmpty(str) && mFADBean != null) {
            a.a("MFADSDK", "ad me bean: " + mFADBean.toString());
            this.a.put(str, mFADBean);
        }
        AppMethodBeat.o(BaseConstants.ERR_FILE_TRANS_NO_SERVER);
    }

    public MFADBean b(String str) {
        AppMethodBeat.i(BaseConstants.ERR_FILE_TRANS_UPLOAD_FAILED, false);
        MFADBean mFADBean = !TextUtils.isEmpty(str) ? this.a.get(str) : null;
        AppMethodBeat.o(BaseConstants.ERR_FILE_TRANS_UPLOAD_FAILED);
        return mFADBean;
    }

    public void c(String str) {
        AppMethodBeat.i(BaseConstants.ERR_TO_USER_INVALID, false);
        if (!TextUtils.isEmpty(str)) {
            a.a("MFADSDK", "remove me bean: " + str);
            this.a.remove(str);
        }
        AppMethodBeat.o(BaseConstants.ERR_TO_USER_INVALID);
    }
}
