package com.sina.weibo.sdk.net;

import android.content.Context;
import android.os.AsyncTask;
import com.sina.weibo.sdk.cmd.e;
import com.sina.weibo.sdk.exception.WeiboException;

public class AsyncWeiboRunner {
    private Context a;

    public AsyncWeiboRunner(Context context) {
        this.a = context;
    }

    public void a(String str, e eVar, String str2, c cVar) {
        e.a(this.a, eVar.a()).a();
        new b(this.a, str, eVar, str2, cVar).execute(new Void[1]);
    }

    /* access modifiers changed from: package-private */
    public static class b extends AsyncTask<Void, Void, a<String>> {
        private final Context a;
        private final String b;
        private final e c;
        private final String d;
        private final c e;

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPreExecute() {
        }

        public b(Context context, String str, e eVar, String str2, c cVar) {
            this.a = context;
            this.b = str;
            this.c = eVar;
            this.d = str2;
            this.e = cVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a<String> doInBackground(Void... voidArr) {
            try {
                return new a<>(HttpManager.a(this.a, this.b, this.d, this.c));
            } catch (WeiboException e) {
                return new a<>(e);
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(a<String> aVar) {
            WeiboException b = aVar.b();
            if (b != null) {
                this.e.a(b);
            } else {
                this.e.a((String) aVar.a());
            }
        }
    }

    /* access modifiers changed from: private */
    public static class a<T> {
        private T a;
        private WeiboException b;

        public T a() {
            return this.a;
        }

        public WeiboException b() {
            return this.b;
        }

        public a(T t) {
            this.a = t;
        }

        public a(WeiboException weiboException) {
            this.b = weiboException;
        }
    }
}
