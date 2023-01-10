package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.pro.ai;
import com.umeng.analytics.pro.az;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback;
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.proto.e;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class ImprintHandler implements FileLockCallback {
    private static final String a = "ImprintHandler";
    private static Object b = null;
    private static final String c = ".imprint";
    private static final byte[] d = "pbl0".getBytes();
    private static Map<String, ArrayList<UMImprintChangeCallback>> f = null;
    private static Object g = null;
    private static ImprintHandler j = null;
    private static Context k = null;
    private static FileLockUtil l = null;
    private static final int m = 0;
    private static final int n = 1;
    private static Map<String, UMImprintPreProcessCallback> o = new HashMap();
    private static Object p = new Object();
    private d e;
    private a h = new a();
    private com.umeng.commonsdk.statistics.proto.d i = null;

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str) {
        return false;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str, Object obj) {
        return false;
    }

    static {
        b = new Object();
        f = new HashMap();
        g = new Object();
        j = null;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(File file, int i) {
        if (i == 0) {
            j.e();
        } else if (i == 1) {
            j.a(file);
        }
        return true;
    }

    private ImprintHandler(Context context) {
        k = context.getApplicationContext();
    }

    public static synchronized ImprintHandler getImprintService(Context context) {
        ImprintHandler imprintHandler;
        synchronized (ImprintHandler.class) {
            if (j == null) {
                j = new ImprintHandler(context);
                l = new FileLockUtil();
                if (l != null) {
                    l.doFileOperateion(new File(k.getFilesDir(), c), j, 0);
                }
            }
            imprintHandler = j;
        }
        return imprintHandler;
    }

    private static void a(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        synchronized (g) {
            try {
                int i = 0;
                if (f.containsKey(str)) {
                    ArrayList<UMImprintChangeCallback> arrayList = f.get(str);
                    int size = arrayList.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size);
                    while (i < size) {
                        if (uMImprintChangeCallback == arrayList.get(i)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i++;
                    }
                    arrayList.add(uMImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + arrayList.size());
                } else {
                    ArrayList<UMImprintChangeCallback> arrayList2 = new ArrayList<>();
                    int size2 = arrayList2.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size2);
                    while (i < size2) {
                        if (uMImprintChangeCallback == arrayList2.get(i)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i++;
                    }
                    arrayList2.add(uMImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + arrayList2.size());
                    f.put(str, arrayList2);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(k, th);
            }
        }
    }

    private static void b(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            synchronized (g) {
                try {
                    if (f.containsKey(str)) {
                        ArrayList<UMImprintChangeCallback> arrayList = f.get(str);
                        if (uMImprintChangeCallback != null && arrayList.size() > 0) {
                            int size = arrayList.size();
                            ULog.i("--->>> removeCallback: before remove: callbacks size is: " + size);
                            int i = 0;
                            while (true) {
                                if (i >= size) {
                                    break;
                                } else if (uMImprintChangeCallback == arrayList.get(i)) {
                                    ULog.i("--->>> removeCallback: remove index " + i);
                                    arrayList.remove(i);
                                    break;
                                } else {
                                    i++;
                                }
                            }
                            ULog.i("--->>> removeCallback: after remove: callbacks size is: " + arrayList.size());
                            if (arrayList.size() == 0) {
                                ULog.i("--->>> removeCallback: remove key from map: key = " + str);
                                f.remove(str);
                            }
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(k, th);
                }
            }
        }
    }

    public void registImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            a(str, uMImprintChangeCallback);
        }
    }

    public void unregistImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            b(str, uMImprintChangeCallback);
        }
    }

    public void registPreProcessCallback(String str, UMImprintPreProcessCallback uMImprintPreProcessCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintPreProcessCallback != null) {
            synchronized (p) {
                try {
                    if (!o.containsKey(str)) {
                        o.put(str, uMImprintPreProcessCallback);
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> registPreProcessCallback: key : " + str + " regist success.");
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> key : " + str + " PreProcesser has registed!");
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(k, th);
                }
            }
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (p) {
                try {
                    if (o.containsKey(str)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> unregistPreProcessCallback: unregist [" + str + "] success.");
                        f.remove(str);
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> unregistPreProcessCallback: can't find [" + str + "], pls regist first.");
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(k, th);
                }
            }
        }
    }

    public void a(d dVar) {
        this.e = dVar;
    }

    public String a(com.umeng.commonsdk.statistics.proto.d dVar) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(dVar.c()).entrySet()) {
            sb.append((String) entry.getKey());
            if (((e) entry.getValue()).d()) {
                sb.append(((e) entry.getValue()).b());
            }
            sb.append(((e) entry.getValue()).e());
            sb.append(((e) entry.getValue()).h());
        }
        sb.append(dVar.b);
        return HelperUtils.MD5(sb.toString()).toLowerCase(Locale.US);
    }

    private boolean c(com.umeng.commonsdk.statistics.proto.d dVar) {
        if (!dVar.i().equals(a(dVar))) {
            return false;
        }
        for (e eVar : dVar.c().values()) {
            byte[] reverseHexString = DataHelper.reverseHexString(eVar.h());
            byte[] a2 = a(eVar);
            int i = 0;
            while (true) {
                if (i < 4) {
                    if (reverseHexString[i] != a2[i]) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
    }

    public byte[] a(e eVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(eVar.e());
        byte[] array = allocate.array();
        byte[] bArr = d;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public byte[] a() {
        try {
            synchronized (this) {
                if (this.i == null) {
                    return null;
                }
                if (this.i.b() <= 0) {
                    return null;
                }
                return new az().a(this.i);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(k, th);
            return null;
        }
    }

    private com.umeng.commonsdk.statistics.proto.d d(com.umeng.commonsdk.statistics.proto.d dVar) {
        Map<String, e> c2 = dVar.c();
        if (c2.containsKey(ai.f)) {
            c2.remove(ai.f);
            this.h.a(ai.f);
            dVar.a(dVar.f());
            dVar.a(a(dVar));
        }
        return dVar;
    }

    public void b(com.umeng.commonsdk.statistics.proto.d dVar) {
        com.umeng.commonsdk.statistics.proto.d dVar2;
        boolean z;
        if (dVar == null) {
            if (AnalyticsConstants.UM_DEBUG) {
                UMRTLog.d(UMRTLog.RTLOG_TAG, "Imprint is null");
            }
        } else if (c(dVar)) {
            boolean z2 = AnalyticsConstants.UM_DEBUG;
            HashMap hashMap = new HashMap();
            synchronized (this) {
                com.umeng.commonsdk.statistics.proto.d dVar3 = this.i;
                com.umeng.commonsdk.statistics.proto.d d2 = d(dVar);
                String str = null;
                String i = dVar3 == null ? null : dVar3.i();
                if (dVar3 == null) {
                    dVar2 = e(d2);
                } else {
                    dVar2 = a(dVar3, d2, hashMap);
                }
                this.i = dVar2;
                if (dVar2 != null) {
                    str = dVar2.i();
                }
                z = !a(i, str);
            }
            if (this.i != null) {
                boolean z3 = AnalyticsConstants.UM_DEBUG;
                if (z) {
                    this.h.a(this.i);
                    d dVar4 = this.e;
                    if (dVar4 != null) {
                        dVar4.onImprintChanged(this.h);
                    }
                }
            }
            if (hashMap.size() > 0) {
                synchronized (g) {
                    for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (!TextUtils.isEmpty(key) && f.containsKey(key)) {
                            ULog.i("--->>> target imprint key is: " + key + "; value is: " + value);
                            ArrayList<UMImprintChangeCallback> arrayList = f.get(key);
                            if (arrayList != null) {
                                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                    arrayList.get(i2).onImprintValueChanged(key, value);
                                }
                            }
                        }
                    }
                }
            }
        } else if (AnalyticsConstants.UM_DEBUG) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "Imprint is not valid");
        }
    }

    private boolean a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    private com.umeng.commonsdk.statistics.proto.d a(com.umeng.commonsdk.statistics.proto.d dVar, com.umeng.commonsdk.statistics.proto.d dVar2, Map<String, String> map) {
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        ArrayList<UMImprintChangeCallback> arrayList;
        if (dVar2 == null) {
            return dVar;
        }
        Map<String, e> c2 = dVar.c();
        for (Map.Entry<String, e> entry : dVar2.c().entrySet()) {
            int i = 0;
            if (entry.getValue().d()) {
                String key = entry.getKey();
                String str = entry.getValue().a;
                synchronized (p) {
                    if (!TextUtils.isEmpty(key) && o.containsKey(key) && (uMImprintPreProcessCallback = o.get(key)) != null && uMImprintPreProcessCallback.onPreProcessImprintKey(key, str)) {
                        i = 1;
                    }
                }
                if (i == 0) {
                    c2.put(entry.getKey(), entry.getValue());
                    synchronized (g) {
                        if (!TextUtils.isEmpty(key) && f.containsKey(key) && f.get(key) != null) {
                            map.put(key, str);
                        }
                    }
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> merge: [" + key + "] should be ignored.");
                }
            } else {
                String key2 = entry.getKey();
                synchronized (g) {
                    if (!TextUtils.isEmpty(key2) && f.containsKey(key2) && (arrayList = f.get(key2)) != null) {
                        while (i < arrayList.size()) {
                            arrayList.get(i).onImprintValueChanged(key2, null);
                            i++;
                        }
                    }
                }
                c2.remove(key2);
                this.h.a(key2);
            }
        }
        dVar.a(dVar2.f());
        dVar.a(a(dVar));
        return dVar;
    }

    private com.umeng.commonsdk.statistics.proto.d e(com.umeng.commonsdk.statistics.proto.d dVar) {
        ArrayList<UMImprintChangeCallback> arrayList;
        boolean z;
        ArrayList<UMImprintChangeCallback> arrayList2;
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        Map<String, e> c2 = dVar.c();
        ArrayList<String> arrayList3 = new ArrayList(c2.size() / 2);
        Iterator<Map.Entry<String, e>> it2 = c2.entrySet().iterator();
        while (true) {
            if (it2.hasNext()) {
                Map.Entry<String, e> next = it2.next();
                if (!next.getValue().d()) {
                    arrayList3.add(next.getKey());
                } else {
                    String key = next.getKey();
                    String str = next.getValue().a;
                    synchronized (p) {
                        z = !TextUtils.isEmpty(key) && o.containsKey(key) && (uMImprintPreProcessCallback = o.get(key)) != null && uMImprintPreProcessCallback.onPreProcessImprintKey(key, str);
                    }
                    if (z) {
                        arrayList3.add(key);
                    }
                    synchronized (g) {
                        if (!TextUtils.isEmpty(key) && f.containsKey(key) && (arrayList2 = f.get(key)) != null) {
                            for (int i = 0; i < arrayList2.size(); i++) {
                                arrayList2.get(i).onImprintValueChanged(key, str);
                            }
                        }
                    }
                }
            } else {
                for (String str2 : arrayList3) {
                    synchronized (g) {
                        if (!TextUtils.isEmpty(str2) && f.containsKey(str2) && (arrayList = f.get(str2)) != null) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                arrayList.get(i2).onImprintValueChanged(str2, null);
                            }
                        }
                    }
                    c2.remove(str2);
                }
                return dVar;
            }
        }
    }

    public synchronized com.umeng.commonsdk.statistics.proto.d b() {
        return this.i;
    }

    public a c() {
        return this.h;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0037 A[SYNTHETIC, Splitter:B:20:0x0037] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() {
        /*
            r5 = this;
            java.io.File r0 = new java.io.File
            android.content.Context r1 = com.umeng.commonsdk.statistics.idtracking.ImprintHandler.k
            java.io.File r1 = r1.getFilesDir()
            java.lang.String r2 = ".imprint"
            r0.<init>(r1, r2)
            java.lang.Object r1 = com.umeng.commonsdk.statistics.idtracking.ImprintHandler.b
            monitor-enter(r1)
            boolean r0 = r0.exists()     // Catch:{ all -> 0x0062 }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r1)     // Catch:{ all -> 0x0062 }
            return
        L_0x0019:
            r0 = 0
            android.content.Context r2 = com.umeng.commonsdk.statistics.idtracking.ImprintHandler.k     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            java.lang.String r3 = ".imprint"
            java.io.FileInputStream r2 = r2.openFileInput(r3)     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            byte[] r0 = com.umeng.commonsdk.statistics.common.HelperUtils.readStreamToByteArray(r2)     // Catch:{ Exception -> 0x002b }
        L_0x0027:
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose(r2)
            goto L_0x0035
        L_0x002b:
            r3 = move-exception
            goto L_0x0031
        L_0x002d:
            r2 = move-exception
            goto L_0x005e
        L_0x002f:
            r3 = move-exception
            r2 = r0
        L_0x0031:
            r3.printStackTrace()     // Catch:{ all -> 0x005a }
            goto L_0x0027
        L_0x0035:
            if (r0 == 0) goto L_0x0058
            com.umeng.commonsdk.statistics.proto.d r2 = new com.umeng.commonsdk.statistics.proto.d     // Catch:{ Exception -> 0x0054 }
            r2.<init>()     // Catch:{ Exception -> 0x0054 }
            com.umeng.analytics.pro.at r3 = new com.umeng.analytics.pro.at     // Catch:{ Exception -> 0x0054 }
            r3.<init>()     // Catch:{ Exception -> 0x0054 }
            r3.a(r2, r0)     // Catch:{ Exception -> 0x0054 }
            r5.i = r2     // Catch:{ Exception -> 0x0054 }
            com.umeng.commonsdk.statistics.idtracking.ImprintHandler$a r0 = r5.h     // Catch:{ Exception -> 0x0054 }
            r0.a(r2)     // Catch:{ Exception -> 0x0054 }
            com.umeng.commonsdk.statistics.proto.d r0 = r5.i     // Catch:{ Exception -> 0x0054 }
            com.umeng.commonsdk.statistics.proto.d r0 = r5.d(r0)     // Catch:{ Exception -> 0x0054 }
            r5.i = r0     // Catch:{ Exception -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0058:
            monitor-exit(r1)
            return
        L_0x005a:
            r0 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
        L_0x005e:
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose(r0)
            throw r2
        L_0x0062:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.ImprintHandler.e():void");
    }

    private void a(File file) {
        if (this.i != null) {
            try {
                synchronized (b) {
                    byte[] a2 = new az().a(this.i);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(a2);
                        fileOutputStream.flush();
                    } finally {
                        HelperUtils.safeClose(fileOutputStream);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void d() {
        if (this.i != null && l != null) {
            File file = new File(k.getFilesDir(), c);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException unused) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        UMCrashManager.reportCrash(k, e);
                    }
                }
            }
            l.doFileOperateion(file, j, 1);
        }
    }

    public static class a {
        private Map<String, String> a = new HashMap();

        a() {
        }

        public synchronized void a(String str) {
            if (this.a != null && this.a.size() > 0 && !TextUtils.isEmpty(str) && this.a.containsKey(str)) {
                this.a.remove(str);
            }
        }

        a(com.umeng.commonsdk.statistics.proto.d dVar) {
            a(dVar);
        }

        public void a(com.umeng.commonsdk.statistics.proto.d dVar) {
            if (dVar != null) {
                b(dVar);
            }
        }

        private synchronized void b(com.umeng.commonsdk.statistics.proto.d dVar) {
            e eVar;
            if (dVar != null) {
                try {
                    if (dVar.e()) {
                        Map<String, e> c = dVar.c();
                        for (String str : c.keySet()) {
                            if (!TextUtils.isEmpty(str) && (eVar = c.get(str)) != null) {
                                String b = eVar.b();
                                if (!TextUtils.isEmpty(b)) {
                                    this.a.put(str, b);
                                    if (AnalyticsConstants.UM_DEBUG) {
                                        Log.i(ImprintHandler.a, "imKey is " + str + ", imValue is " + b);
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        }

        public synchronized String a(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                if (this.a.size() > 0) {
                    String str3 = this.a.get(str);
                    if (!TextUtils.isEmpty(str3)) {
                        return str3;
                    }
                    return str2;
                }
            }
            return str2;
        }
    }
}
