package cn.missfresh.buttomline.abtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.text.TextUtils;
import cn.missfresh.basiclib.tool.SystemUtils;
import cn.missfresh.buttomline.abtest.bean.Data;
import cn.missfresh.buttomline.abtest.bean.Plan;
import cn.missfresh.buttomline.abtest.future.MemoryFuture;
import cn.missfresh.buttomline.abtest.future.RemoteFuture;
import cn.missfresh.buttomline.abtest.inf.ABConfig;
import cn.missfresh.buttomline.abtest.inf.AbtestRequestCallback;
import cn.missfresh.buttomline.abtest.inf.Future;
import cn.missfresh.buttomline.abtest.log.Logger;
import cn.missfresh.buttomline.abtest.net.AbtestRequestParam;
import cn.missfresh.buttomline.abtest.net.ApiManager;
import cn.missfresh.buttomline.abtest.net.BaseAbtestApiResult;
import cn.missfresh.buttomline.abtest.net.RequestParams;
import cn.missfresh.buttomline.abtest.uitl.DataUtil;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ABTest {
    private static final String ABTEST_CONFIG = "version.config";
    private static final String CONFIG_FILE = "abtest.config";
    private static final String DEFAULT_ID = "default";
    private static final String DEFAULT_TAG = "abtest";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_BUZ = "buss";
    public static final String KEY_DEVICE = "device_id";
    public static final String KEY_ENCRYPT = "encrypt";
    public static final String KEY_ID = "user_id";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_ROM_VER = "romVer";
    public static final String KEY_UI_VER = "uiVer";
    public static final String KEY_VER = "app_ver";
    private static final int NO_SETTING = 0;
    private static final String PLANS_TOKEN = "plans_token";
    public static final int UPDATE_SERVER = 2;
    public static final int USE_LOCAL = 1;
    private static final String VERSION = "version";
    private static String cachePath = null;
    private static Context ctx = null;
    private static String id = "default";
    private static AbtestRequestCallback mCallback;
    private static ABFactory mFactory;
    private static int newState;
    private static int sState;
    private static SharedPreferences sharedPreferences;
    private final Future future;
    private final String mPath;

    private ABTest(String str) {
        AppMethodBeat.i(7209, false);
        this.mPath = str;
        this.future = sState == 2 ? new RemoteFuture(this) : new MemoryFuture(this);
        AppMethodBeat.o(7209);
    }

    public static void updateParams(Map<String, String> map) {
        AppMethodBeat.i(7211, false);
        RequestParams.init(map);
        AppMethodBeat.o(7211);
    }

    public static void init(Context context, int i, Map<String, String> map, ABConfig... aBConfigArr) {
        AppMethodBeat.i(7212, false);
        init(context, i, map, null, aBConfigArr);
        AppMethodBeat.o(7212);
    }

    public static void init(Context context, int i, Map<String, String> map, AbtestRequestCallback abtestRequestCallback, ABConfig... aBConfigArr) {
        AppMethodBeat.i(7215, false);
        updateParams(map);
        ctx = context;
        mCallback = abtestRequestCallback;
        newState = i;
        if (cachePath == null) {
            cachePath = context.getDir(DEFAULT_TAG, 0).getPath();
        }
        if (initialize(map)) {
            _init(context, aBConfigArr);
        }
        AppMethodBeat.o(7215);
    }

    private static void _init(Context context, ABConfig... aBConfigArr) {
        AppMethodBeat.i(7219, false);
        sharedPreferences = context.getSharedPreferences(ABTEST_CONFIG, 0);
        mFactory = new ABFactory(context, aBConfigArr);
        AppMethodBeat.o(7219);
    }

    public static int get(String str) {
        AppMethodBeat.i(7220, false);
        int i = get(str, null);
        AppMethodBeat.o(7220);
        return i;
    }

    public static int get(String str, AbtestRequestCallback abtestRequestCallback) {
        AppMethodBeat.i(7222, false);
        int i = -1;
        if (mFactory == null) {
            AppMethodBeat.o(7222);
            return -1;
        }
        if (check(str)) {
            i = ABFactory.access$100(mFactory, str)._get(str, abtestRequestCallback);
        }
        Logger.i("case_id=" + str + ",case_val=" + i);
        AppMethodBeat.o(7222);
        return i;
    }

    public static void set(String str, String str2) {
        AppMethodBeat.i(7224, false);
        if (mFactory != null && check(str)) {
            ABFactory.access$100(mFactory, str)._set(str, str2);
        }
        Logger.i("set case_id=" + str + ",case_val=" + str2);
        AppMethodBeat.o(7224);
    }

    public static void update(String str) {
        AppMethodBeat.i(7226, false);
        if (check(str)) {
            ABFactory.access$100(mFactory, str)._set(str);
        }
        AppMethodBeat.o(7226);
    }

    private static boolean check(String str) {
        AppMethodBeat.i(7228, false);
        boolean z = !TextUtils.isEmpty(str);
        AppMethodBeat.o(7228);
        return z;
    }

    private static boolean initialize(Map<String, String> map) {
        boolean z = false;
        AppMethodBeat.i(7231, false);
        boolean z2 = true;
        boolean z3 = !"default".equals(id);
        if (mFactory == null) {
            z = true;
        }
        int i = sState;
        int i2 = newState;
        if (i != i2) {
            sState = i2;
            z = true;
        }
        String str = map != null ? map.get("user_id") : null;
        if (z3) {
            if (str == null) {
                id = "default";
            } else if (!str.equals(id)) {
                id = str;
            }
            AppMethodBeat.o(7231);
            return z2;
        } else if (str != null) {
            id = str;
            AppMethodBeat.o(7231);
            return z2;
        }
        z2 = z;
        AppMethodBeat.o(7231);
        return z2;
    }

    private int _get(String str) {
        AppMethodBeat.i(7232, false);
        int _get = _get(str, null);
        AppMethodBeat.o(7232);
        return _get;
    }

    private int _get(String str, AbtestRequestCallback abtestRequestCallback) {
        AppMethodBeat.i(7233, false);
        int i = this.future.get(str, abtestRequestCallback);
        AppMethodBeat.o(7233);
        return i;
    }

    private void _set(String str) {
        AppMethodBeat.i(7236, false);
        this.future.requestServer(str);
        AppMethodBeat.o(7236);
    }

    private void _set(String str, String str2) {
        AppMethodBeat.i(7239, false);
        this.future.set(str, str2);
        AppMethodBeat.o(7239);
    }

    public String getPath() {
        return this.mPath;
    }

    public static AbtestRequestParam createAbtestRequest() {
        AppMethodBeat.i(7245, false);
        AbtestRequestParam abtestRequestParam = new AbtestRequestParam(new ArrayList());
        abtestRequestParam.setPlatform(1);
        if (!TextUtils.isEmpty(RequestParams.getParams().get("user_id"))) {
            abtestRequestParam.setUserId(RequestParams.getParams().get("user_id"));
        }
        if (!TextUtils.isEmpty(RequestParams.getParams().get(KEY_BUZ))) {
            abtestRequestParam.setBusiness(RequestParams.getParams().get(KEY_BUZ));
        }
        if (!TextUtils.isEmpty(RequestParams.getParams().get(KEY_MOBILE))) {
            abtestRequestParam.setMobile(RequestParams.getParams().get(KEY_MOBILE));
        }
        if (!TextUtils.isEmpty(RequestParams.getParams().get("device_id"))) {
            abtestRequestParam.setDeviceId(RequestParams.getParams().get("device_id"));
        } else {
            abtestRequestParam.setDeviceId(SystemUtils.d(ctx));
        }
        if (!TextUtils.isEmpty(RequestParams.getParams().get(KEY_VER))) {
            abtestRequestParam.setVersion(RequestParams.getParams().get(KEY_VER));
        } else {
            abtestRequestParam.setVersion(SystemUtils.a(ctx));
        }
        if (!TextUtils.isEmpty(RequestParams.getParams().get("brand"))) {
            abtestRequestParam.setBrand(RequestParams.getParams().get("brand"));
        } else {
            abtestRequestParam.setBrand(Build.MANUFACTURER + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + Build.MODEL);
        }
        if (!TextUtils.isEmpty(RequestParams.getParams().get(KEY_UI_VER))) {
            abtestRequestParam.setUiVer(RequestParams.getParams().get(KEY_UI_VER));
        } else {
            abtestRequestParam.setUiVer(Build.FINGERPRINT);
        }
        if (!TextUtils.isEmpty(RequestParams.getParams().get(KEY_ROM_VER))) {
            abtestRequestParam.setRomVer(RequestParams.getParams().get(KEY_ROM_VER));
        } else {
            abtestRequestParam.setRomVer(SystemUtils.b());
        }
        AppMethodBeat.o(7245);
        return abtestRequestParam;
    }

    /* access modifiers changed from: private */
    public static class ABFactory {
        private final Map<String, ABTest> mABTests;
        private final String mParent;
        private final Map<String, String> mTags;

        static /* synthetic */ ABTest access$100(ABFactory aBFactory, String str) {
            AppMethodBeat.i(7198, false);
            ABTest create = aBFactory.create(str);
            AppMethodBeat.o(7198);
            return create;
        }

        static /* synthetic */ void access$700(ABFactory aBFactory, List list) {
            AppMethodBeat.i(7200, false);
            aBFactory.set(list);
            AppMethodBeat.o(7200);
        }

        private ABFactory(Context context, ABConfig... aBConfigArr) {
            AppMethodBeat.i(7178, false);
            Logger.i("user:" + ABTest.id + " oncreate");
            this.mParent = ABTest.cachePath + File.separator + ABTest.id;
            this.mABTests = new HashMap(aBConfigArr.length);
            this.mTags = new HashMap();
            update(context);
            Logger.i("ABTest init cache path:" + this.mParent);
            int length = aBConfigArr.length;
            for (int i = 0; i < length; i++) {
                ABConfig aBConfig = aBConfigArr[i];
                String tag = aBConfig.getTag();
                Map<String, ABTest> map = this.mABTests;
                map.put(tag, new ABTest(this.mParent + File.separator + tag));
                String[] caseIds = aBConfig.getCaseIds();
                int length2 = caseIds.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    String str = caseIds[i2];
                    if (!this.mTags.containsKey(str)) {
                        this.mTags.put(str, tag);
                    }
                }
            }
            copy(context);
            Logger.i("ABTest init ABTests:" + this.mABTests);
            Logger.i("ABTest init case_ids:" + this.mTags);
            if (ABTest.sState == 2) {
                updateForServer();
            }
            AppMethodBeat.o(7178);
        }

        private ABTest create(String str) {
            AppMethodBeat.i(7182, false);
            ABTest aBTest = this.mABTests.get(this.mTags.get(str));
            if (aBTest == null) {
                ABTest aBTest2 = new ABTest(this.mParent + File.separator + ABTest.DEFAULT_TAG);
                AppMethodBeat.o(7182);
                return aBTest2;
            }
            AppMethodBeat.o(7182);
            return aBTest;
        }

        private void copy(Context context) {
            AppMethodBeat.i(7184, false);
            File file = new File(this.mParent);
            if (!file.exists()) {
                file.mkdir();
            }
            File[] listFiles = file.listFiles();
            if (ABTest.sState == 1 || (listFiles != null && listFiles.length == 0)) {
                InputStream inputStream = null;
                try {
                    inputStream = context.getAssets().open(ABTest.CONFIG_FILE);
                    set(((Data) DataUtil.jsonToObject(DataUtil.inputStream2String(inputStream), new AnonymousClass1())).getData());
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                            Logger.e(e);
                        }
                    }
                } catch (Exception e2) {
                    Logger.e(e2);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                            Logger.e(e3);
                        }
                    }
                    AppMethodBeat.o(7184);
                    throw th;
                }
            }
            AppMethodBeat.o(7184);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.buttomline.abtest.ABTest$ABFactory$1  reason: invalid class name */
        public class AnonymousClass1 extends TypeToken<Data> {
            AnonymousClass1() {
            }
        }

        private void update(Context context) {
            AppMethodBeat.i(7187, false);
            String string = ABTest.sharedPreferences.getString("version", "");
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                String str = packageInfo.versionName + packageInfo.versionCode;
                Logger.i("curVersion in sp:" + str + "  version:" + string);
                if (!string.equals(str)) {
                    clearCache();
                    ABTest.sharedPreferences.edit().putString("version", str).apply();
                }
            } catch (Exception e) {
                Logger.e(e);
                clearCache();
            }
            AppMethodBeat.o(7187);
        }

        private void updateForServer() {
            AppMethodBeat.i(7190, false);
            AbtestRequestParam createAbtestRequest = ABTest.createAbtestRequest();
            Map<String, String> map = this.mTags;
            if (map != null && map.size() > 0) {
                for (String str : this.mTags.keySet()) {
                    createAbtestRequest.getExpIds().add(str);
                }
            }
            if (createAbtestRequest.getExpIds().size() > 0) {
                ApiManager.getAbtestResult(createAbtestRequest, new AnonymousClass2());
            } else {
                Logger.i("no case load from local !");
                if (ABTest.mCallback != null) {
                    ABTest.mCallback.onComplite(-1);
                    AbtestRequestCallback unused = ABTest.mCallback = null;
                }
            }
            AppMethodBeat.o(7190);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.buttomline.abtest.ABTest$ABFactory$2  reason: invalid class name */
        public class AnonymousClass2 extends BaseAbtestApiResult {
            AnonymousClass2() {
            }

            @Override // cn.missfresh.buttomline.abtest.net.BaseAbtestApiResult, cn.missfresh.basiclib.net.a.a
            public /* synthetic */ void onSuccess(Object obj) {
                AppMethodBeat.i(7169, false);
                onSuccess((List) obj);
                AppMethodBeat.o(7169);
            }

            @Override // cn.missfresh.buttomline.abtest.net.BaseAbtestApiResult
            public void onSuccess(List<Plan> list) {
                AppMethodBeat.i(7162, false);
                super.onSuccess(list);
                if (list != null) {
                    ABFactory.access$700(ABFactory.this, list);
                }
                AppMethodBeat.o(7162);
            }

            @Override // cn.missfresh.buttomline.abtest.net.BaseAbtestApiResult, cn.missfresh.basiclib.net.a.a
            public void onComplete() {
                AppMethodBeat.i(7166, false);
                super.onComplete();
                if (ABTest.mCallback != null) {
                    ABTest.mCallback.onComplite(0);
                    AbtestRequestCallback unused = ABTest.mCallback = null;
                }
                AppMethodBeat.o(7166);
            }
        }

        private void set(List<Plan> list) {
            AppMethodBeat.i(7191, false);
            Logger.i(list.toString());
            HashMap hashMap = new HashMap(this.mABTests.size());
            for (Plan plan : list) {
                String str = this.mTags.get(plan.getExpId());
                if (!TextUtils.isEmpty(str)) {
                    List list2 = (List) hashMap.get(str);
                    if (list2 == null) {
                        list2 = new ArrayList();
                        hashMap.put(str, list2);
                    }
                    list2.add(plan);
                }
            }
            for (String str2 : hashMap.keySet()) {
                ABTest aBTest = this.mABTests.get(str2);
                if (aBTest == null) {
                    Logger.e("Native Plan out of sync with Server: id" + str2);
                } else {
                    List<Plan> list3 = (List) hashMap.get(str2);
                    if (list3 != null) {
                        aBTest.future.set(list3);
                    }
                }
            }
            AppMethodBeat.o(7191);
        }

        private void clearCache() {
            AppMethodBeat.i(7192, false);
            FileOpt.clearFileList(ABTest.cachePath);
            AppMethodBeat.o(7192);
        }
    }
}
