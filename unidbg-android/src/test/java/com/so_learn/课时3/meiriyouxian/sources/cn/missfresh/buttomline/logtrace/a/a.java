package cn.missfresh.buttomline.logtrace.a;

import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import cn.missfresh.basiclib.tool.SystemUtils;
import cn.missfresh.buttomline.logtrace.b.b;
import cn.missfresh.buttomline.logtrace.bean.DeviceBean;
import cn.missfresh.buttomline.logtrace.bean.Invoice;
import cn.missfresh.buttomline.logtrace.bean.RequestBean;
import cn.missfresh.buttomline.logtrace.bean.ResponseBean;
import cn.missfresh.buttomline.logtrace.e.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.c;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LogReporterImpl */
public class a implements b {
    private boolean a = false;

    static /* synthetic */ RequestBean a(a aVar, Invoice invoice) {
        AppMethodBeat.i(16905, false);
        RequestBean a = aVar.a(invoice);
        AppMethodBeat.o(16905);
        return a;
    }

    public a() {
        AppMethodBeat.i(16894, false);
        c.b(b.a, b.b, false);
        AppMethodBeat.o(16894);
    }

    @Override // cn.missfresh.buttomline.logtrace.b.b
    public void a(String str, Map<String, String> map) {
        AppMethodBeat.i(16897, false);
        RequestBean a = a(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(map);
        a.setLogs(arrayList);
        d.a(a);
        cn.missfresh.buttomline.logtrace.d.a.a(a, new AnonymousClass1());
        AppMethodBeat.o(16897);
    }

    /* compiled from: LogReporterImpl */
    /* renamed from: cn.missfresh.buttomline.logtrace.a.a$1  reason: invalid class name */
    class AnonymousClass1 extends cn.missfresh.buttomline.logtrace.d.b {
        AnonymousClass1() {
        }

        @Override // cn.missfresh.buttomline.logtrace.d.b, cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(16868, false);
            a((ResponseBean) obj);
            AppMethodBeat.o(16868);
        }

        @Override // cn.missfresh.buttomline.logtrace.d.b
        public void a(ResponseBean responseBean) {
            AppMethodBeat.i(16866, false);
            super.onSuccess(responseBean);
            AppMethodBeat.o(16866);
        }
    }

    private RequestBean a(String str) {
        AppMethodBeat.i(16898, false);
        RequestBean requestBean = new RequestBean();
        requestBean.setTag(str);
        requestBean.setDevice(b());
        AppMethodBeat.o(16898);
        return requestBean;
    }

    @Override // cn.missfresh.buttomline.logtrace.b.b
    public void a() {
        AppMethodBeat.i(16899, false);
        if (this.a) {
            AppMethodBeat.o(16899);
            return;
        }
        this.a = true;
        cn.missfresh.buttomline.logtrace.a.a().a(new AnonymousClass2(new C0019a(this, null)));
        AppMethodBeat.o(16899);
    }

    /* compiled from: LogReporterImpl */
    /* renamed from: cn.missfresh.buttomline.logtrace.a.a$2  reason: invalid class name */
    class AnonymousClass2 extends cn.missfresh.basiclib.thread.b.a {
        AnonymousClass2(cn.missfresh.basiclib.thread.a.a aVar) {
            super(aVar);
        }

        @Override // cn.missfresh.basiclib.thread.b.a
        public void runInTryCatch() {
            AppMethodBeat.i(16880, false);
            File file = new File(b.b);
            if (!file.exists() || !file.isDirectory()) {
                AppMethodBeat.o(16880);
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                AppMethodBeat.o(16880);
                return;
            }
            for (File file2 : listFiles) {
                String name = file2.getName();
                cn.missfresh.utils.a.d.d("CensusConfig", "Report File:" + name);
                int lastIndexOf = name.lastIndexOf(".");
                if (lastIndexOf < 0) {
                    file2.delete();
                } else {
                    Invoice invoice = new Invoice(file2, name.substring(lastIndexOf + 1));
                    RequestBean a = a.a(a.this, invoice);
                    cn.missfresh.utils.a.d.d("CensusConfig", "invoice content:" + a);
                    if (a != null) {
                        cn.missfresh.utils.a.d.d("CensusConfig", "invoice log.size=" + a.getLogs().size());
                        cn.missfresh.buttomline.logtrace.d.a.a(a, new AnonymousClass1(invoice));
                    } else if (!invoice.file.delete()) {
                        invoice.file.delete();
                    }
                }
            }
            AppMethodBeat.o(16880);
        }

        /* compiled from: LogReporterImpl */
        /* renamed from: cn.missfresh.buttomline.logtrace.a.a$2$1  reason: invalid class name */
        class AnonymousClass1 extends cn.missfresh.buttomline.logtrace.d.b {
            final /* synthetic */ Invoice a;

            AnonymousClass1(Invoice invoice) {
                this.a = invoice;
            }

            @Override // cn.missfresh.buttomline.logtrace.d.b, cn.missfresh.basiclib.net.a.a
            public /* synthetic */ void onSuccess(Object obj) {
                AppMethodBeat.i(16875, false);
                a((ResponseBean) obj);
                AppMethodBeat.o(16875);
            }

            @Override // cn.missfresh.buttomline.logtrace.d.b
            public void a(ResponseBean responseBean) {
                AppMethodBeat.i(16873, false);
                super.onSuccess(responseBean);
                if (responseBean.responseSuccess()) {
                    cn.missfresh.utils.a.d.d("CensusConfig", "file update success:" + this.a.file.getAbsolutePath());
                    if (!this.a.file.delete()) {
                        this.a.file.delete();
                    }
                }
                AppMethodBeat.o(16873);
            }
        }
    }

    private RequestBean a(Invoice invoice) {
        AppMethodBeat.i(16901, false);
        RequestBean requestBean = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(invoice.file));
            ArrayList arrayList = new ArrayList();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                Map map = (Map) d.a(readLine, new AnonymousClass3().getType());
                if (map != null) {
                    arrayList.add(map);
                }
            }
            if (arrayList.size() > 0) {
                requestBean = a(invoice.tag);
                requestBean.setLogs(arrayList);
            }
        } catch (Exception e) {
            cn.missfresh.utils.a.d.a("CensusConfig", e);
        }
        AppMethodBeat.o(16901);
        return requestBean;
    }

    /* compiled from: LogReporterImpl */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.buttomline.logtrace.a.a$3  reason: invalid class name */
    public class AnonymousClass3 extends TypeToken<HashMap<String, String>> {
        AnonymousClass3() {
        }
    }

    private DeviceBean b() {
        AppMethodBeat.i(16904, false);
        cn.missfresh.buttomline.logtrace.e.c a = cn.missfresh.buttomline.logtrace.e.c.a();
        DeviceBean deviceBean = new DeviceBean();
        deviceBean.setSystemVersion(a.c());
        deviceBean.setModel(a.d());
        deviceBean.setDeviceName(SystemUtils.a());
        deviceBean.setAndroidId(a.a(cn.missfresh.buttomline.logtrace.a.b()));
        deviceBean.setSimType(SystemUtils.b(cn.missfresh.buttomline.logtrace.a.b()));
        deviceBean.setCpu(Build.HARDWARE + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + Build.CPU_ABI);
        deviceBean.setMemSize(a.e());
        deviceBean.setWidth(a.g(cn.missfresh.buttomline.logtrace.a.b()));
        deviceBean.setHeight(a.h(cn.missfresh.buttomline.logtrace.a.b()));
        deviceBean.setIsRoot(a.f() ? 1 : 0);
        deviceBean.setLanguage(a.b());
        deviceBean.setTimeZone(SystemUtils.c(cn.missfresh.buttomline.logtrace.a.b()));
        deviceBean.setPkgName(a.b(cn.missfresh.buttomline.logtrace.a.b()));
        deviceBean.setAppVersion(a.d(cn.missfresh.buttomline.logtrace.a.b()));
        deviceBean.setBuildVersion(a.e(cn.missfresh.buttomline.logtrace.a.b()));
        AppMethodBeat.o(16904);
        return deviceBean;
    }

    /* compiled from: LogReporterImpl */
    /* renamed from: cn.missfresh.buttomline.logtrace.a.a$a  reason: collision with other inner class name */
    private class C0019a implements cn.missfresh.basiclib.thread.a.a {
        @Override // cn.missfresh.basiclib.thread.a.a
        public void a(Runnable runnable, Object obj) {
        }

        private C0019a() {
        }

        /* synthetic */ C0019a(a aVar, AnonymousClass1 r2) {
            this();
        }

        @Override // cn.missfresh.basiclib.thread.a.a
        public void b(Runnable runnable, Object obj) {
            AppMethodBeat.i(16888, false);
            a.this.a = false;
            AppMethodBeat.o(16888);
        }

        @Override // cn.missfresh.basiclib.thread.a.a
        public void a(Runnable runnable, Throwable th, Object obj) {
            AppMethodBeat.i(16890, false);
            a.this.a = false;
            AppMethodBeat.o(16890);
        }
    }
}
