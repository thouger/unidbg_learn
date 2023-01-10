package cn.missfresh.module.base.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.AdDownloadService;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import java.io.File;

/* compiled from: CacheFileManager */
public class d extends RequestCallBack<File> implements ServiceConnection {
    private static String a;
    private static d b;
    private a c;

    /* compiled from: CacheFileManager */
    public interface a {
        void a();
    }

    @Override // com.lidroid.xutils.http.callback.RequestCallBack
    public void onFailure(HttpException httpException, String str) {
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }

    private d(Context context) {
        AppMethodBeat.i(14065, false);
        a = r.b() + File.separator + "ad_cache";
        AppMethodBeat.o(14065);
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            AppMethodBeat.i(14066, false);
            if (b == null) {
                b = new d(context.getApplicationContext());
            }
            dVar = b;
            AppMethodBeat.o(14066);
        }
        return dVar;
    }

    public String a(String str) {
        AppMethodBeat.i(14067, false);
        String b2 = b("complete_play_" + c(str));
        AppMethodBeat.o(14067);
        return b2;
    }

    public String b(String str) {
        AppMethodBeat.i(14072, false);
        String str2 = a + File.separator + str;
        AppMethodBeat.o(14072);
        return str2;
    }

    public String c(String str) {
        AppMethodBeat.i(14073, false);
        if (b.a(str) || str.lastIndexOf(".") == -1) {
            AppMethodBeat.o(14073);
            return null;
        }
        String substring = str.substring(str.lastIndexOf(".") + 1);
        String str2 = r.a(str) + "." + substring.toLowerCase();
        cn.missfresh.utils.a.d.c("fileName=>", str2);
        AppMethodBeat.o(14073);
        return str2;
    }

    public static String d(String str) {
        AppMethodBeat.i(14074, false);
        if (b.a(str)) {
            AppMethodBeat.o(14074);
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            AppMethodBeat.o(14074);
            return "";
        } else if (lastIndexOf >= str.length() - 1) {
            AppMethodBeat.o(14074);
            return "";
        } else {
            String substring = str.substring(lastIndexOf + 1);
            cn.missfresh.utils.a.d.d("CacheFileManager", "getUrlExtra...extra:" + substring + ",url:" + str);
            String lowerCase = substring.toLowerCase();
            AppMethodBeat.o(14074);
            return lowerCase;
        }
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(14075, false);
        AdDownloadService.a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication(), str, str2, this);
        AppMethodBeat.o(14075);
    }

    public boolean e(String str) {
        boolean z = false;
        AppMethodBeat.i(14076, false);
        if (b.a(str)) {
            AppMethodBeat.o(14076);
            return false;
        }
        String d = d(str);
        if (b.a(d)) {
            AppMethodBeat.o(14076);
            return false;
        }
        if (d.toLowerCase().contains("gif") || d.toLowerCase().contains("mp4") || d.toLowerCase().contains("jpg") || d.toLowerCase().contains("jpeg") || d.toLowerCase().contains("webp")) {
            z = true;
        }
        AppMethodBeat.o(14076);
        return z;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        AppMethodBeat.i(14077, false);
        ((AdDownloadService.a) iBinder).a().a(this);
        AppMethodBeat.o(14077);
    }

    @Override // com.lidroid.xutils.http.callback.RequestCallBack
    public void onSuccess(ResponseInfo<File> responseInfo) {
        AppMethodBeat.i(14078, false);
        File file = responseInfo.result;
        File parentFile = file.getParentFile();
        File file2 = new File(parentFile, "complete_" + file.getName());
        file.renameTo(file2);
        cn.missfresh.utils.a.d.d("CacheFileManager", "\u547d\u540d\u6210\u529f\uff0c\u4e0b\u8f7d\u597d\u7684\u6587\u4ef6\u540d\u4e3a: " + file2.getName());
        a aVar = this.c;
        if (aVar != null) {
            aVar.a();
        }
        AppMethodBeat.o(14078);
    }

    public void a(a aVar) {
        this.c = aVar;
    }
}
