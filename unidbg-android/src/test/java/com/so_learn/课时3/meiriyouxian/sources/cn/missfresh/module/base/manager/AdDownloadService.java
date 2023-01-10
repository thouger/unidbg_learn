package cn.missfresh.module.base.manager;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import java.io.File;

public class AdDownloadService extends Service {
    private String a = getClass().getSimpleName();
    private BroadcastReceiver b;
    private HttpHandler<File> c;
    private String d;
    private String e;
    private HttpUtils f;
    private RequestCallBack<File> g;

    public AdDownloadService() {
        AppMethodBeat.i(13914, false);
        AppMethodBeat.o(13914);
    }

    public static void a(Context context, String str, String str2, ServiceConnection serviceConnection) {
        AppMethodBeat.i(13918, false);
        if (((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).isAppInForeGround()) {
            Intent intent = new Intent(context, AdDownloadService.class);
            intent.putExtra("fileUrl", str);
            intent.putExtra("filePath", str2);
            context.startService(intent);
            context.bindService(intent, serviceConnection, 4);
        } else {
            d.c("AdDownloadService", "App is switched to bg");
        }
        AppMethodBeat.o(13918);
    }

    public static void a(Context context) {
        AppMethodBeat.i(13921, false);
        context.stopService(new Intent(context, AdDownloadService.class));
        AppMethodBeat.o(13921);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        AppMethodBeat.i(13925, false);
        a aVar = new a();
        AppMethodBeat.o(13925);
        return aVar;
    }

    @Override // android.app.Service
    public void onCreate() {
        AppMethodBeat.i(13928, false);
        super.onCreate();
        this.b = new b(this, null);
        registerReceiver(this.b, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        this.f = new HttpUtils(5000);
        AppMethodBeat.o(13928);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        AppMethodBeat.i(13932, false);
        a(intent);
        a();
        AppMethodBeat.o(13932);
        return 3;
    }

    private void a(Intent intent) {
        AppMethodBeat.i(13935, false);
        this.d = intent.getStringExtra("fileUrl");
        this.e = intent.getStringExtra("filePath");
        AppMethodBeat.o(13935);
    }

    public void a(RequestCallBack<File> requestCallBack) {
        this.g = requestCallBack;
    }

    public void a() {
        AppMethodBeat.i(13938, false);
        if (cn.missfresh.utils.b.a(this.d)) {
            d.c(this.a, "\u4e0b\u8f7dURL\u4e3a\u7a7a");
            AppMethodBeat.o(13938);
            return;
        }
        String str = this.a;
        d.d(str, "\u76ee\u524d\u6b63\u5728\u4e0b\u8f7d\u7684\u6587\u4ef6\u4e3a: " + this.d + ", \u6587\u4ef6\u7684\u4fdd\u5b58\u8def\u5f84\u4e3a: " + this.e);
        this.c = this.f.download(this.d, this.e, true, (RequestCallBack<File>) new AnonymousClass1());
        AppMethodBeat.o(13938);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.AdDownloadService$1  reason: invalid class name */
    public class AnonymousClass1 extends RequestCallBack<File> {
        AnonymousClass1() {
        }

        @Override // com.lidroid.xutils.http.callback.RequestCallBack
        public void onSuccess(ResponseInfo<File> responseInfo) {
            AppMethodBeat.i(13884, false);
            String str = AdDownloadService.this.a;
            d.d(str, "\u4e0b\u8f7d\u5df2\u5b8c\u6210\uff0c\u6587\u4ef6\u5b58\u653e\u5728: " + responseInfo.result.getAbsolutePath());
            if (AdDownloadService.this.g != null) {
                AdDownloadService.this.g.onSuccess(responseInfo);
            }
            AppMethodBeat.o(13884);
        }

        @Override // com.lidroid.xutils.http.callback.RequestCallBack
        public void onFailure(HttpException httpException, String str) {
            AppMethodBeat.i(13888, false);
            String str2 = AdDownloadService.this.a;
            d.d(str2, "\u4e0b\u8f7d\u5df2\u53d6\u6d88, \u9047\u5230\u95ee\u9898\uff1a " + str);
            if (AdDownloadService.this.g != null) {
                AdDownloadService.this.g.onFailure(httpException, str);
            }
            AppMethodBeat.o(13888);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        AppMethodBeat.i(13942, false);
        super.onDestroy();
        try {
            unregisterReceiver(this.b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        d.d(this.a, "\u670d\u52a1\u5df2\u5173\u95ed\uff0c\u7f51\u7edc\u76d1\u542c\u5e7f\u64ad\u5df2\u6ce8\u9500");
        AppMethodBeat.o(13942);
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        /* synthetic */ b(AdDownloadService adDownloadService, AnonymousClass1 r2) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppMethodBeat.i(13904, false);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                AppMethodBeat.o(13904);
                return;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            d.b(AdDownloadService.this.a, "\u76d1\u6d4b\u5230\u7f51\u7edc\u72b6\u51b5\u53d8\u5316");
            if (activeNetworkInfo == null) {
                AppMethodBeat.o(13904);
            } else if (AdDownloadService.this.c == null) {
                AppMethodBeat.o(13904);
            } else {
                HttpHandler.State state = AdDownloadService.this.c.getState();
                String str = AdDownloadService.this.a;
                d.d(str, "HttpHandler.State = " + state);
                if (activeNetworkInfo.getType() != 1 || !activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) {
                    if (activeNetworkInfo.getType() == 0 && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                        AdDownloadService.this.c.cancel();
                        d.d(AdDownloadService.this.a, "\u5f53\u524d\u4e3a\u79fb\u52a8\u7f51\u7edc\uff0c\u4e0b\u8f7d\u5df2\u53d6\u6d88");
                    }
                } else if (!(state == HttpHandler.State.STARTED || state == HttpHandler.State.LOADING || state == HttpHandler.State.WAITING)) {
                    d.d(AdDownloadService.this.a, "\u5f53\u524d\u4e3aWIFI\u7f51\u7edc\uff0c\u4e0b\u8f7d\u5df2\u91cd\u65b0\u5f00\u59cb");
                    AdDownloadService.this.a();
                }
                AppMethodBeat.o(13904);
            }
        }
    }

    public class a extends Binder {
        public a() {
        }

        /* access modifiers changed from: package-private */
        public AdDownloadService a() {
            return AdDownloadService.this;
        }
    }
}
