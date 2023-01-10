package cn.missfresh.picture.luban;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: Luban */
public class f implements Handler.Callback {
    private static volatile LimitedMap a = new LimitedMap(50);
    private String b;
    private boolean c;
    private int d;
    private h e;
    private g f;
    private b g;
    private List<e> h;
    private Handler i;

    /* synthetic */ f(a aVar, AnonymousClass1 r2) {
        this(aVar);
    }

    static /* synthetic */ File a(f fVar, Context context, e eVar) throws IOException {
        AppMethodBeat.i(18141, false);
        File a2 = fVar.a(context, eVar);
        AppMethodBeat.o(18141);
        return a2;
    }

    static /* synthetic */ List a(f fVar, Context context) throws IOException {
        AppMethodBeat.i(18146, false);
        List<File> c = fVar.c(context);
        AppMethodBeat.o(18146);
        return c;
    }

    static {
        AppMethodBeat.i(18148, false);
        AppMethodBeat.o(18148);
    }

    private f(a aVar) {
        AppMethodBeat.i(18117, false);
        this.b = aVar.b;
        this.e = aVar.d;
        this.h = aVar.g;
        this.f = aVar.e;
        this.d = aVar.c;
        this.g = aVar.f;
        this.i = new Handler(Looper.getMainLooper(), this);
        AppMethodBeat.o(18117);
    }

    public static a a(Context context) {
        AppMethodBeat.i(18118, false);
        a aVar = new a(context);
        AppMethodBeat.o(18118);
        return aVar;
    }

    private File a(Context context, String str, String str2) {
        AppMethodBeat.i(18119, false);
        if (TextUtils.isEmpty(this.b)) {
            this.b = b(context).getAbsolutePath();
        }
        File file = new File(a(str, str2));
        AppMethodBeat.o(18119);
        return file;
    }

    private String a(String str, String str2) {
        AppMethodBeat.i(18122, false);
        String str3 = "";
        try {
            if (!TextUtils.isEmpty(str2)) {
                String[] split = str2.split(NotificationIconUtil.SPLIT_CHAR);
                if (split.length > 0) {
                    str3 = split[split.length - 1].split(".")[0];
                }
            }
        } catch (Exception unused) {
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append(NotificationIconUtil.SPLIT_CHAR);
        sb.append(System.currentTimeMillis());
        sb.append((int) (Math.random() * 1000.0d));
        sb.append(str3);
        if (TextUtils.isEmpty(str)) {
            str = ".jpg";
        }
        sb.append(str);
        String sb2 = sb.toString();
        AppMethodBeat.o(18122);
        return sb2;
    }

    private File b(Context context, String str) {
        AppMethodBeat.i(18124, false);
        if (TextUtils.isEmpty(this.b)) {
            this.b = b(context).getAbsolutePath();
        }
        File file = new File(this.b + NotificationIconUtil.SPLIT_CHAR + str);
        AppMethodBeat.o(18124);
        return file;
    }

    private File b(Context context) {
        AppMethodBeat.i(18125, false);
        File a2 = a(context, "disk_cache");
        AppMethodBeat.o(18125);
        return a2;
    }

    public static File a(Context context, String str) {
        AppMethodBeat.i(18127, false);
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            File file = new File(externalCacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                AppMethodBeat.o(18127);
                return file;
            }
            AppMethodBeat.o(18127);
            return null;
        }
        if (Log.isLoggable("Luban", 6)) {
            Log.e("Luban", "default disk cache dir is null");
        }
        AppMethodBeat.o(18127);
        return null;
    }

    /* compiled from: Luban */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.picture.luban.f$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ e b;
        final /* synthetic */ f c;

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(18847, false);
            try {
                this.c.i.sendMessage(this.c.i.obtainMessage(1));
                File a = f.a(this.c, this.a, this.b);
                if (a != null && a.exists()) {
                    f.a.put(this.b.d(), a.getAbsolutePath());
                }
                this.c.i.sendMessage(this.c.i.obtainMessage(0, a));
            } catch (IOException e) {
                this.c.i.sendMessage(this.c.i.obtainMessage(2, e));
            }
            AppMethodBeat.o(18847);
        }
    }

    private List<File> c(Context context) throws IOException {
        AppMethodBeat.i(18133, false);
        ArrayList arrayList = new ArrayList();
        Iterator<e> it2 = this.h.iterator();
        while (it2.hasNext()) {
            arrayList.add(a(context, it2.next()));
            it2.remove();
        }
        AppMethodBeat.o(18133);
        return arrayList;
    }

    private File a(Context context, e eVar) throws IOException {
        int i = 18134;
        AppMethodBeat.i(18134, false);
        try {
            return b(context, eVar);
        } finally {
            eVar.c();
            AppMethodBeat.o(i);
        }
    }

    private File b(Context context, e eVar) throws IOException {
        File file;
        AppMethodBeat.i(18136, false);
        File a2 = a(context, Checker.SINGLE.extSuffix(eVar), eVar.d());
        h hVar = this.e;
        if (hVar != null) {
            a2 = b(context, hVar.a(eVar.d()));
        }
        b bVar = this.g;
        if (bVar != null) {
            if (!bVar.a(eVar.d()) || !Checker.SINGLE.needCompress(this.d, eVar.d())) {
                file = new File(eVar.d());
            } else {
                file = new c(eVar, a2, this.c).a();
            }
        } else if (Checker.SINGLE.needCompress(this.d, eVar.d())) {
            file = new c(eVar, a2, this.c).a();
        } else {
            file = new File(eVar.d());
        }
        if (file != null && file.exists()) {
            a.put(eVar.d(), file.getAbsolutePath());
        }
        AppMethodBeat.o(18136);
        return file;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        AppMethodBeat.i(18138, false);
        if (this.f == null) {
            AppMethodBeat.o(18138);
            return false;
        }
        int i = message.what;
        if (i == 0) {
            this.f.a((File) message.obj);
        } else if (i == 1) {
            this.f.a();
        } else if (i == 2) {
            this.f.a((Throwable) message.obj);
        }
        AppMethodBeat.o(18138);
        return false;
    }

    /* compiled from: Luban */
    public static class a {
        private Context a;
        private String b;
        private int c = 100;
        private h d;
        private g e;
        private b f;
        private List<e> g;

        a(Context context) {
            AppMethodBeat.i(19067, false);
            this.a = context;
            this.g = new ArrayList();
            AppMethodBeat.o(19067);
        }

        private f b() {
            AppMethodBeat.i(19069, false);
            f fVar = new f(this, null);
            AppMethodBeat.o(19069);
            return fVar;
        }

        /* compiled from: Luban */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.picture.luban.f$a$1  reason: invalid class name */
        public class AnonymousClass1 extends d {
            final /* synthetic */ File a;

            AnonymousClass1(File file) {
                this.a = file;
            }

            @Override // cn.missfresh.picture.luban.d
            public InputStream b() throws IOException {
                AppMethodBeat.i(17909, false);
                FileInputStream fileInputStream = new FileInputStream(this.a);
                AppMethodBeat.o(17909);
                return fileInputStream;
            }

            @Override // cn.missfresh.picture.luban.e
            public String d() {
                AppMethodBeat.i(17910, false);
                String absolutePath = this.a.getAbsolutePath();
                AppMethodBeat.o(17910);
                return absolutePath;
            }
        }

        public a a(File file) {
            AppMethodBeat.i(19076, false);
            this.g.add(new AnonymousClass1(file));
            AppMethodBeat.o(19076);
            return this;
        }

        /* compiled from: Luban */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.picture.luban.f$a$2  reason: invalid class name */
        public class AnonymousClass2 extends d {
            final /* synthetic */ String a;

            AnonymousClass2(String str) {
                this.a = str;
            }

            @Override // cn.missfresh.picture.luban.d
            public InputStream b() throws IOException {
                AppMethodBeat.i(19037, false);
                FileInputStream fileInputStream = new FileInputStream(this.a);
                AppMethodBeat.o(19037);
                return fileInputStream;
            }

            @Override // cn.missfresh.picture.luban.e
            public String d() {
                return this.a;
            }
        }

        public a a(String str) {
            AppMethodBeat.i(19077, false);
            this.g.add(new AnonymousClass2(str));
            AppMethodBeat.o(19077);
            return this;
        }

        public <T> a a(List<T> list) {
            AppMethodBeat.i(19079, false);
            for (T t : list) {
                if (t instanceof String) {
                    a((String) t);
                } else if (t instanceof File) {
                    a((File) t);
                } else if (t instanceof Uri) {
                    a((Uri) t);
                } else {
                    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Incoming data type exception, it must be String, File, Uri or Bitmap");
                    AppMethodBeat.o(19079);
                    throw illegalArgumentException;
                }
            }
            AppMethodBeat.o(19079);
            return this;
        }

        /* compiled from: Luban */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.picture.luban.f$a$3  reason: invalid class name */
        public class AnonymousClass3 extends d {
            final /* synthetic */ Uri a;

            AnonymousClass3(Uri uri) {
                this.a = uri;
            }

            @Override // cn.missfresh.picture.luban.d
            public InputStream b() throws IOException {
                AppMethodBeat.i(18946, false);
                InputStream openInputStream = a.this.a.getContentResolver().openInputStream(this.a);
                AppMethodBeat.o(18946);
                return openInputStream;
            }

            @Override // cn.missfresh.picture.luban.e
            public String d() {
                AppMethodBeat.i(18948, false);
                String path = this.a.getPath();
                AppMethodBeat.o(18948);
                return path;
            }
        }

        public a a(Uri uri) {
            AppMethodBeat.i(19082, false);
            this.g.add(new AnonymousClass3(uri));
            AppMethodBeat.o(19082);
            return this;
        }

        public List<File> a() throws IOException {
            AppMethodBeat.i(19096, false);
            List<File> a = f.a(b(), this.a);
            AppMethodBeat.o(19096);
            return a;
        }
    }

    public static LinkedHashMap a() {
        return a;
    }
}
