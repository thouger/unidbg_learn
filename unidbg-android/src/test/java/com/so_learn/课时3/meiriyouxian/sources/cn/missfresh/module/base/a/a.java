package cn.missfresh.module.base.a;

import android.content.Context;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.tinkerlib.AbstractHotFixManager;
import cn.missfresh.tinkerlib.b;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;

/* compiled from: HotFixManager */
public class a extends AbstractHotFixManager {
    private static a d;
    private Context c;

    public static a a(Context context) {
        AppMethodBeat.i(13262, false);
        if (d == null) {
            synchronized (a.class) {
                try {
                    d = new a(context);
                } catch (Throwable th) {
                    AppMethodBeat.o(13262);
                    throw th;
                }
            }
        }
        a aVar = d;
        AppMethodBeat.o(13262);
        return aVar;
    }

    private a(Context context) {
        super(context);
        this.c = context;
    }

    @Override // cn.missfresh.tinkerlib.AbstractHotFixManager
    public void a(String str, b bVar) {
        AppMethodBeat.i(13268, false);
        if (cn.missfresh.utils.b.a(str)) {
            if (bVar != null) {
                bVar.b("\u4e0b\u8f7d\u8865\u4e01\u5305\u5931\u8d25\uff0c\u94fe\u63a5\u9519\u8bef\uff01");
                d.b(ShareConstants.PATCH_DIRECTORY_NAME, "\u8865\u4e01\u5305URL\u4e3a\u7a7a");
            }
            AppMethodBeat.o(13268);
            return;
        }
        HttpUtils httpUtils = new HttpUtils(5000);
        String str2 = r.a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication()) + File.separator + "patch_cache";
        String substring = str.substring(str.lastIndexOf(NotificationIconUtil.SPLIT_CHAR) + 1, str.length());
        if (cn.missfresh.utils.b.a(substring)) {
            substring = "Patch_7zip.zip";
        }
        String str3 = str2 + File.separator + substring;
        c.c(str3, false);
        httpUtils.download(str, str3, new AnonymousClass1(str, str3, bVar));
        AppMethodBeat.o(13268);
    }

    /* compiled from: HotFixManager */
    /* renamed from: cn.missfresh.module.base.a.a$1  reason: invalid class name */
    class AnonymousClass1 extends RequestCallBack<File> {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ b c;

        AnonymousClass1(String str, String str2, b bVar) {
            this.a = str;
            this.b = str2;
            this.c = bVar;
        }

        @Override // com.lidroid.xutils.http.callback.RequestCallBack
        public void onSuccess(ResponseInfo<File> responseInfo) {
            AppMethodBeat.i(13256, false);
            b a = b.a();
            a.a(1, "Download Success: PatchUrl : " + this.a + " SavePatchPath: " + this.b);
            b bVar = this.c;
            if (bVar != null) {
                bVar.a(this.b);
                d.b(ShareConstants.PATCH_DIRECTORY_NAME, "\u8865\u4e01\u5305\u4e0b\u8f7d\u6210\u529f");
            }
            AppMethodBeat.o(13256);
        }

        @Override // com.lidroid.xutils.http.callback.RequestCallBack
        public void onFailure(HttpException httpException, String str) {
            AppMethodBeat.i(13259, false);
            b a = b.a();
            a.a(0, "Download Failed: " + str);
            b bVar = this.c;
            if (bVar != null) {
                bVar.b(str);
                d.b(ShareConstants.PATCH_DIRECTORY_NAME, "\u8865\u4e01\u5305\u4e0b\u8f7d\u5931\u8d25");
            }
            AppMethodBeat.o(13259);
        }
    }

    @Override // cn.missfresh.tinkerlib.AbstractHotFixManager
    public void a(PatchResult patchResult) {
        AppMethodBeat.i(13271, false);
        if (patchResult == null || this.a == null) {
            d.b("onPatchInstalled", "data is null");
            AppMethodBeat.o(13271);
            return;
        }
        if (patchResult.isSuccess) {
            b.a().a("patchVersion", this.a.b);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("patchVersion", this.a.b);
            jSONObject.put("oldPatchVersion", e.an());
            jSONObject.put("patchResult", JSON.toJSONString(patchResult));
            b.a().b(1, jSONObject.toString());
            e.J(this.a.b);
        } else {
            b a = b.a();
            String str = "\u8865\u4e01\u5b89\u88c5\u5931\u8d25\uff1a";
            if (patchResult.e != null) {
                str = str + patchResult.e.getMessage();
            }
            a.b(0, str);
        }
        AppMethodBeat.o(13271);
    }
}
