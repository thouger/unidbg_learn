package com.vivo.push;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.WindowManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.taobao.accs.net.r;
import com.vivo.push.b.a;
import com.vivo.push.b.b;
import com.vivo.push.b.f;
import com.vivo.push.d;
import com.vivo.push.util.VivoPushException;
import com.vivo.push.util.y;
import java.util.ArrayList;
import java.util.List;

public class PushClient {
    public static final String DEFAULT_REQUEST_ID = "1";
    private static volatile PushClient sPushClient;

    public String getVersion() {
        return "2.9.0.0";
    }

    private PushClient(Context context) {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_NAVIGATION_BAR_PANEL, false);
        d.a().a(context);
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_NAVIGATION_BAR_PANEL);
    }

    public static synchronized PushClient getInstance(Context context) {
        PushClient pushClient;
        synchronized (PushClient.class) {
            AppMethodBeat.i(WindowManager.LayoutParams.TYPE_DISPLAY_OVERLAY, false);
            if (sPushClient == null) {
                sPushClient = new PushClient(context.getApplicationContext());
            }
            pushClient = sPushClient;
            AppMethodBeat.o(WindowManager.LayoutParams.TYPE_DISPLAY_OVERLAY);
        }
        return pushClient;
    }

    public void initialize() {
        AppMethodBeat.i(2028, false);
        d.a().a(new f());
        AppMethodBeat.o(2028);
    }

    public void checkManifest() throws VivoPushException {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_PRIVATE_PRESENTATION, false);
        d a = d.a();
        if (a.e != null) {
            y.c(a.e);
        }
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_PRIVATE_PRESENTATION);
    }

    private void checkParam(String str) {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY, false);
        if (str != null) {
            AppMethodBeat.o(WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY);
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("PushManager String param should not be " + str);
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY);
        throw illegalArgumentException;
    }

    public void bindAlias(String str, IPushActionListener iPushActionListener) {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_SCREENSHOT, false);
        checkParam(str);
        d a = d.a();
        if (a.e == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
        } else if (TextUtils.isEmpty(a.i) || !a.i.equals(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            a aVar = new a(true, a.e.getPackageName(), arrayList);
            aVar.h = 100;
            if (!a.j) {
                a.a(aVar);
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(0);
                }
                AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
            } else if (!a.f()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                }
                AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
            } else if (!d.a(a.c)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                }
                AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
            } else {
                a.c = SystemClock.elapsedRealtime();
                String a2 = a.a(new d.a(aVar, iPushActionListener));
                aVar.e = a2;
                if (TextUtils.isEmpty(a.h)) {
                    a.a(a2, 30001);
                    AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
                } else if (TextUtils.isEmpty(str)) {
                    a.a(a2, 30002);
                    AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
                } else if (((long) str.length()) > 70) {
                    a.a(a2, 30003);
                    AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
                } else {
                    a.a(aVar);
                    a.c(a2);
                    AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
                }
            }
        } else {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
            AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SCREENSHOT);
        }
    }

    public void unBindAlias(String str, IPushActionListener iPushActionListener) {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, false);
        checkParam(str);
        d a = d.a();
        if (a.e == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        } else if (TextUtils.isEmpty(a.i)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(0);
            }
            AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            a aVar = new a(false, a.e.getPackageName(), arrayList);
            aVar.h = 100;
            if (!a.j) {
                a.a(aVar);
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(0);
                }
                AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            } else if (!a.f()) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                }
                AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            } else if (!d.a(a.d)) {
                if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(1002);
                }
                AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            } else {
                a.d = SystemClock.elapsedRealtime();
                String a2 = a.a(new d.a(aVar, iPushActionListener));
                aVar.e = a2;
                if (TextUtils.isEmpty(a.h)) {
                    a.a(a2, 30001);
                    AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                } else if (TextUtils.isEmpty(str)) {
                    a.a(a2, 30002);
                    AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                } else if (((long) str.length()) > 70) {
                    a.a(a2, 30003);
                    AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                } else {
                    a.a(aVar);
                    a.c(a2);
                    AppMethodBeat.o(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void turnOnPush(com.vivo.push.IPushActionListener r13) {
        /*
        // Method dump skipped, instructions count: 237
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.PushClient.turnOnPush(com.vivo.push.IPushActionListener):void");
    }

    public void turnOffPush(IPushActionListener iPushActionListener) {
        AppMethodBeat.i(2043, false);
        d a = d.a();
        if (a.e == null) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            AppMethodBeat.o(2043);
        } else if ("".equals(a.h)) {
            iPushActionListener.onStateChanged(0);
            AppMethodBeat.o(2043);
        } else if (!d.a(a.b)) {
            if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(1002);
            }
            AppMethodBeat.o(2043);
        } else {
            a.b = SystemClock.elapsedRealtime();
            String packageName = a.e.getPackageName();
            d.a aVar = null;
            if (a.e != null) {
                b bVar = new b(false, packageName);
                bVar.d = null;
                bVar.c = null;
                bVar.i = null;
                bVar.h = 100;
                if (!a.j) {
                    a.a(bVar);
                    if (iPushActionListener != null) {
                        iPushActionListener.onStateChanged(0);
                    }
                } else if (a.f()) {
                    aVar = new d.a(bVar, iPushActionListener);
                    String a2 = a.a(aVar);
                    bVar.e = a2;
                    aVar.b = new d.AnonymousClass4(bVar, a2);
                } else if (iPushActionListener != null) {
                    iPushActionListener.onStateChanged(101);
                }
            } else if (iPushActionListener != null) {
                iPushActionListener.onStateChanged(102);
            }
            if (aVar != null) {
                aVar.a = new d.AnonymousClass3();
                aVar.a();
            }
            AppMethodBeat.o(2043);
        }
    }

    public String getAlias() {
        AppMethodBeat.i(2046, false);
        String str = d.a().i;
        AppMethodBeat.o(2046);
        return str;
    }

    public String getRegId() {
        AppMethodBeat.i(2048, false);
        String e = d.a().e();
        AppMethodBeat.o(2048);
        return e;
    }

    public void setTopic(String str, IPushActionListener iPushActionListener) {
        AppMethodBeat.i(r.DEAMON_JOB_ID, false);
        ArrayList<String> arrayList = new ArrayList<>(1);
        arrayList.add(str);
        d.a().a(arrayList, iPushActionListener);
        AppMethodBeat.o(r.DEAMON_JOB_ID);
    }

    public void delTopic(String str, IPushActionListener iPushActionListener) {
        AppMethodBeat.i(2054, false);
        ArrayList<String> arrayList = new ArrayList<>(1);
        arrayList.add(str);
        d.a().b(arrayList, iPushActionListener);
        AppMethodBeat.o(2054);
    }

    public List<String> getTopics() {
        AppMethodBeat.i(2057, false);
        List<String> b = d.a().b();
        AppMethodBeat.o(2057);
        return b;
    }

    public void setSystemModel(boolean z) {
        AppMethodBeat.i(2058, false);
        d.a().f = z;
        AppMethodBeat.o(2058);
    }

    public boolean isSupport() {
        AppMethodBeat.i(2059, false);
        boolean c = d.a().c();
        AppMethodBeat.o(2059);
        return c;
    }
}
