package com.umeng.message.service;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.format.DateUtils;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    static final String d = "JobIntentService";
    static final boolean e = false;
    static final Object m = new Object();
    static final HashMap<ComponentName, f> n = new HashMap<>();
    CompatJobEngine f;
    f g;
    a h;
    boolean i = false;
    boolean j = false;
    boolean k = false;
    final ArrayList<c> l;

    /* access modifiers changed from: package-private */
    public interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    /* access modifiers changed from: package-private */
    public interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    /* access modifiers changed from: protected */
    public abstract void a(Intent intent);

    public boolean onStopCurrentWork() {
        return true;
    }

    public JobIntentService() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.l = null;
        } else {
            this.l = new ArrayList<>();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        d();
    }

    private void d() {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                this.f = new d(this);
                this.g = null;
                return;
            }
            this.f = null;
            this.g = a(this, new ComponentName(this, getClass()), false, 0);
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                if (this.l != null) {
                    if (this.g == null) {
                        d();
                    }
                    this.g.a();
                    synchronized (this.l) {
                        ArrayList<c> arrayList = this.l;
                        if (intent == null) {
                            intent = new Intent();
                        }
                        arrayList.add(new c(intent, i2));
                        a(true);
                    }
                    return 3;
                }
            } catch (Throwable unused) {
            }
        }
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent != null) {
            try {
                if (this.f != null) {
                    return this.f.compatGetBinder();
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            super.onDestroy();
            if (this.l != null) {
                ArrayList<c> arrayList = this.l;
                synchronized (this.l) {
                    this.k = true;
                    this.g.c();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void enqueueWork(Context context, Class cls, int i, Intent intent) {
        if (context != null && cls != null && intent != null) {
            try {
                enqueueWork(context, new ComponentName(context, cls), i, intent);
            } catch (Throwable unused) {
            }
        }
    }

    public static void enqueueWork(Context context, ComponentName componentName, int i, Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("work must not be null");
        } else if (context != null && componentName != null) {
            try {
                Object obj = m;
                synchronized (m) {
                    f a2 = a(context, componentName, true, i);
                    a2.a(i);
                    a2.a(intent);
                }
            } catch (Throwable unused) {
            }
        }
    }

    static f a(Context context, ComponentName componentName, boolean z, int i) {
        f fVar;
        f fVar2 = n.get(componentName);
        if (fVar2 != null) {
            return fVar2;
        }
        if (Build.VERSION.SDK_INT < 26) {
            fVar = new b(context, componentName);
        } else if (z) {
            fVar = new e(context, componentName, i);
        } else {
            throw new IllegalArgumentException("Can't be here without a job id");
        }
        n.put(componentName, fVar);
        return fVar;
    }

    public void setInterruptIfStopped(boolean z) {
        this.i = z;
    }

    public boolean isStopped() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        a aVar = this.h;
        if (aVar != null) {
            aVar.cancel(this.i);
        }
        this.j = true;
        return onStopCurrentWork();
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        try {
            if (this.h == null) {
                this.h = new a();
                if (this.g != null && z) {
                    this.g.b();
                }
                this.h.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        try {
            if (this.l != null) {
                ArrayList<c> arrayList = this.l;
                synchronized (this.l) {
                    this.h = null;
                    if (this.l != null && this.l.size() > 0) {
                        a(false);
                    } else if (!this.k) {
                        this.g.c();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public GenericWorkItem c() {
        c remove;
        CompatJobEngine compatJobEngine = this.f;
        if (compatJobEngine != null) {
            return compatJobEngine.dequeueWork();
        }
        synchronized (this.l) {
            remove = this.l.size() > 0 ? this.l.remove(0) : null;
        }
        return remove;
    }

    /* access modifiers changed from: package-private */
    public final class a extends AsyncTask<Void, Void, Void> {
        a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            while (true) {
                try {
                    GenericWorkItem c = JobIntentService.this.c();
                    if (c == null) {
                        return null;
                    }
                    JobIntentService.this.a(c.getIntent());
                    c.complete();
                } catch (Throwable unused) {
                    return null;
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onCancelled(Void r1) {
            try {
                JobIntentService.this.b();
            } catch (Throwable unused) {
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void onPostExecute(Void r1) {
            try {
                JobIntentService.this.b();
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final class c implements GenericWorkItem {
        final Intent a;
        final int b;

        c(Intent intent, int i) {
            this.a = intent;
            this.b = i;
        }

        @Override // com.umeng.message.service.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.a;
        }

        @Override // com.umeng.message.service.JobIntentService.GenericWorkItem
        public void complete() {
            try {
                JobIntentService.this.stopSelf(this.b);
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class e extends f {
        private final JobInfo a;
        private JobScheduler b;

        e(Context context, ComponentName componentName, int i) {
            super(context, componentName);
            a(i);
            this.a = new JobInfo.Builder(i, this.c).setOverrideDeadline(0).build();
            try {
                this.b = (JobScheduler) context.getApplicationContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
            } catch (Throwable unused) {
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.umeng.message.service.JobIntentService.f
        public void a(Intent intent) {
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    this.b.enqueue(this.a, new JobWorkItem(intent));
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class d extends JobServiceEngine implements CompatJobEngine {
        static final String a = "JobServiceEngineImpl";
        static final boolean b = false;
        final JobIntentService c;
        final Object d = new Object();
        JobParameters e;

        d(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.c = jobIntentService;
        }

        @Override // com.umeng.message.service.JobIntentService.CompatJobEngine
        public IBinder compatGetBinder() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.e = jobParameters;
            this.c.a(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean a2 = this.c.a();
            synchronized (this.d) {
                this.e = null;
            }
            return a2;
        }

        @Override // com.umeng.message.service.JobIntentService.CompatJobEngine
        public GenericWorkItem dequeueWork() {
            JobWorkItem dequeueWork;
            try {
                Object obj = this.d;
                synchronized (this.d) {
                    if (this.e == null) {
                        return null;
                    }
                    dequeueWork = this.e.dequeueWork();
                }
                if (dequeueWork == null) {
                    return null;
                }
                dequeueWork.getIntent().setExtrasClassLoader(this.c.getClassLoader());
                return new a(dequeueWork);
            } catch (Throwable unused) {
                return null;
            }
        }

        final class a implements GenericWorkItem {
            final JobWorkItem a;

            a(JobWorkItem jobWorkItem) {
                this.a = jobWorkItem;
            }

            @Override // com.umeng.message.service.JobIntentService.GenericWorkItem
            public Intent getIntent() {
                return this.a.getIntent();
            }

            @Override // com.umeng.message.service.JobIntentService.GenericWorkItem
            public void complete() {
                try {
                    Object obj = d.this.d;
                    synchronized (d.this.d) {
                        if (d.this.e != null) {
                            try {
                                d.this.e.completeWork(this.a);
                            } catch (Throwable unused) {
                            }
                        }
                    }
                } catch (Throwable unused2) {
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class b extends f {
        boolean a;
        boolean b;
        private final Context f;
        private final PowerManager.WakeLock g;
        private final PowerManager.WakeLock h;

        b(Context context, ComponentName componentName) {
            super(context, componentName);
            this.f = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            this.g = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.g.setReferenceCounted(false);
            this.h = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.h.setReferenceCounted(false);
        }

        /* access modifiers changed from: package-private */
        @Override // com.umeng.message.service.JobIntentService.f
        public void a(Intent intent) {
            try {
                Intent intent2 = new Intent(intent);
                intent2.setComponent(this.c);
                if (this.f.startService(intent2) != null) {
                    synchronized (this) {
                        if (!this.a) {
                            this.a = true;
                            if (!this.b) {
                                this.g.acquire(DateUtils.MINUTE_IN_MILLIS);
                            }
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }

        @Override // com.umeng.message.service.JobIntentService.f
        public void a() {
            synchronized (this) {
                this.a = false;
            }
        }

        @Override // com.umeng.message.service.JobIntentService.f
        public void b() {
            try {
                synchronized (this) {
                    if (!this.b) {
                        this.b = true;
                        this.h.acquire(600000);
                        this.g.release();
                    }
                }
            } catch (Throwable unused) {
            }
        }

        @Override // com.umeng.message.service.JobIntentService.f
        public void c() {
            try {
                synchronized (this) {
                    if (this.b) {
                        if (this.a) {
                            this.g.acquire(DateUtils.MINUTE_IN_MILLIS);
                        }
                        this.b = false;
                        this.h.release();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class f {
        final ComponentName c;
        boolean d;
        int e;

        public void a() {
        }

        /* access modifiers changed from: package-private */
        public abstract void a(Intent intent);

        public void b() {
        }

        public void c() {
        }

        f(Context context, ComponentName componentName) {
            this.c = componentName;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            if (!this.d) {
                this.d = true;
                this.e = i;
            } else if (this.e != i) {
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.e);
            }
        }
    }
}
