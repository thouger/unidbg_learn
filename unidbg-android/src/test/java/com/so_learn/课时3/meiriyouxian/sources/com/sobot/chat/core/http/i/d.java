package com.sobot.chat.core.http.i;

import android.text.TextUtils;
import android.util.Log;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.CommonModelBase;
import com.sobot.chat.core.http.c.b;
import com.sobot.chat.core.http.d.d;
import com.sobot.chat.core.http.d.e;
import com.sobot.chat.core.http.e.c;
import com.sobot.chat.core.http.model.SobotProgress;
import com.sobot.chat.core.http.model.a;
import com.sobot.chat.utils.m;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import okhttp3.Call;
import okhttp3.Response;

/* compiled from: SobotUploadTask */
public class d<T> implements Runnable {
    public SobotProgress a = new SobotProgress();
    public Map<Object, c> b;
    private ThreadPoolExecutor c;
    private c d;

    private void f(SobotProgress sobotProgress) {
    }

    public d(String str, e eVar) {
        SobotProgress sobotProgress = this.a;
        sobotProgress.tag = str;
        sobotProgress.isUpload = true;
        sobotProgress.status = 0;
        sobotProgress.totalSize = -1;
        sobotProgress.request = eVar;
        this.c = b.a().b().a();
        this.b = new HashMap();
    }

    public d<T> a(int i) {
        this.a.priority = i;
        return this;
    }

    public d<T> a(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.a.filePath = str;
        }
        return this;
    }

    public d<T> b(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.a.tmpTag = str;
        }
        return this;
    }

    public d<T> a(c cVar) {
        if (cVar != null) {
            this.b.put(cVar.a, cVar);
        }
        return this;
    }

    public d<T> a() {
        if (b.a().a(this.a.tag) == null) {
            Log.i("SobotUploadTask", "you must call SobotUploadTask#save() before SobotUploadTask#start()\uff01");
        }
        if (this.a.status == 1 || this.a.status == 2) {
            Log.w("SobotUploadTask", "the task with tag " + this.a.tag + " is already in the upload queue, current task status is " + this.a.status);
        } else {
            a(this.a);
            b(this.a);
            this.d = new c(this.a.priority, this);
            this.c.execute(this.d);
        }
        return this;
    }

    public void b() {
        c();
        SobotProgress sobotProgress = this.a;
        sobotProgress.status = 0;
        sobotProgress.currentSize = 0;
        sobotProgress.fraction = 0.0f;
        sobotProgress.speed = 0;
        a();
    }

    public void c() {
        this.c.remove(this.d);
        if (this.a.status == 1) {
            c(this.a);
        } else if (this.a.status == 2) {
            SobotProgress sobotProgress = this.a;
            sobotProgress.speed = 0;
            sobotProgress.status = 3;
        } else {
            Log.w("SobotUploadTask", "only the task with status WAITING(1) or LOADING(2) can pause, current status is " + this.a.status);
        }
    }

    public d<T> d() {
        c();
        d<T> dVar = (d<T>) b.a().c(this.a.tag);
        e(this.a);
        return dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        SobotProgress sobotProgress = this.a;
        sobotProgress.status = 2;
        d(sobotProgress);
        try {
            e eVar = this.a.request;
            eVar.b().a(new AnonymousClass1(eVar));
            Response execute = eVar.a((b) null).execute();
            if (execute.isSuccessful()) {
                try {
                    String string = execute.body().string();
                    m.d("uploadFile----->:" + string);
                    CommonModel jsonToCommonModel = GsonUtil.jsonToCommonModel(string);
                    if (jsonToCommonModel == null || !"1".equals(jsonToCommonModel.getCode()) || jsonToCommonModel.getData() == null) {
                        a(this.a, new RuntimeException("\u670d\u52a1\u5668\u5f02\u5e38"));
                        return;
                    }
                    CommonModelBase data = jsonToCommonModel.getData();
                    a obtainUploadFileResult = GsonUtil.obtainUploadFileResult(data.getMsg());
                    if (obtainUploadFileResult == null || TextUtils.isEmpty(obtainUploadFileResult.a())) {
                        a(this.a, new RuntimeException("\u670d\u52a1\u5668\u5f02\u5e38"));
                        return;
                    }
                    this.a.tag = obtainUploadFileResult.a();
                    this.a.url = obtainUploadFileResult.b();
                    a(this.a, data);
                } catch (Exception e) {
                    e.printStackTrace();
                    a(this.a, new RuntimeException("\u670d\u52a1\u5668\u5f02\u5e38"));
                }
            } else {
                a(this.a, new RuntimeException(execute.message()));
            }
        } catch (Exception e2) {
            a(this.a, e2);
        }
    }

    /* compiled from: SobotUploadTask */
    /* renamed from: com.sobot.chat.core.http.i.d$1  reason: invalid class name */
    class AnonymousClass1 implements d.b {
        final /* synthetic */ e a;

        AnonymousClass1(e eVar) {
            this.a = eVar;
        }

        @Override // com.sobot.chat.core.http.d.d.b
        public void a(SobotProgress sobotProgress) {
            Call a = this.a.a();
            if (!a.isCanceled()) {
                if (d.this.a.status != 2) {
                    a.cancel();
                    return;
                }
                d.this.a.from(sobotProgress);
                d dVar = d.this;
                dVar.d(dVar.a);
            }
        }
    }

    private void a(SobotProgress sobotProgress) {
        sobotProgress.speed = 0;
        sobotProgress.status = 0;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass2(sobotProgress));
    }

    /* compiled from: SobotUploadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.i.d$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass2(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (c cVar : d.this.b.values()) {
                cVar.a(this.a);
            }
        }
    }

    private void b(SobotProgress sobotProgress) {
        sobotProgress.speed = 0;
        sobotProgress.status = 1;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass3(sobotProgress));
    }

    /* compiled from: SobotUploadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.i.d$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass3(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (c cVar : d.this.b.values()) {
                cVar.b(this.a);
            }
        }
    }

    private void c(SobotProgress sobotProgress) {
        sobotProgress.speed = 0;
        sobotProgress.status = 3;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass4(sobotProgress));
    }

    /* compiled from: SobotUploadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.i.d$4  reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass4(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (c cVar : d.this.b.values()) {
                cVar.b(this.a);
            }
        }
    }

    /* compiled from: SobotUploadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.i.d$5  reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass5(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (c cVar : d.this.b.values()) {
                cVar.b(this.a);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(SobotProgress sobotProgress) {
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass5(sobotProgress));
    }

    private void a(SobotProgress sobotProgress, Throwable th) {
        sobotProgress.speed = 0;
        sobotProgress.status = 4;
        sobotProgress.exception = th;
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass6(sobotProgress));
    }

    /* compiled from: SobotUploadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.i.d$6  reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass6(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (c cVar : d.this.b.values()) {
                cVar.b(this.a);
                cVar.c(this.a);
            }
        }
    }

    private void a(SobotProgress sobotProgress, CommonModelBase commonModelBase) {
        sobotProgress.speed = 0;
        sobotProgress.fraction = 1.0f;
        sobotProgress.status = 5;
        f(sobotProgress);
        com.sobot.chat.core.http.f.a.a().b((com.sobot.chat.core.http.f.a) sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass7(sobotProgress, commonModelBase));
    }

    /* compiled from: SobotUploadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.i.d$7  reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ SobotProgress a;
        final /* synthetic */ CommonModelBase b;

        AnonymousClass7(SobotProgress sobotProgress, CommonModelBase commonModelBase) {
            this.a = sobotProgress;
            this.b = commonModelBase;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: com.sobot.chat.core.http.i.c */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            for (c cVar : d.this.b.values()) {
                cVar.b(this.a);
                cVar.a(this.b, this.a);
            }
            if (!TextUtils.isEmpty(this.a.tmpTag)) {
                b.a().c(this.a.tmpTag);
            } else {
                b.a().c(this.a.tag);
            }
        }
    }

    /* compiled from: SobotUploadTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.i.d$8  reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ SobotProgress a;

        AnonymousClass8(SobotProgress sobotProgress) {
            this.a = sobotProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (c cVar : d.this.b.values()) {
                cVar.d(this.a);
            }
            d.this.b.clear();
        }
    }

    private void e(SobotProgress sobotProgress) {
        f(sobotProgress);
        com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass8(sobotProgress));
    }
}
