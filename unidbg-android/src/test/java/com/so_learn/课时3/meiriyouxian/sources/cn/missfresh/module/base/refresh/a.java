package cn.missfresh.module.base.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.widget.ImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.SoftReference;

/* compiled from: AnimationsContainer */
public class a {
    public int a;
    private int b;
    private Context c;

    /* compiled from: AnimationsContainer */
    public interface c {
        void a();
    }

    private a() {
        this.a = 10;
        this.b = R.array.loading_anim;
    }

    public static a a(Context context, int i, int i2) {
        AppMethodBeat.i(18939, false);
        C0030a.a.a(i, i2);
        C0030a.a.a(context.getApplicationContext());
        a aVar = C0030a.a;
        AppMethodBeat.o(18939);
        return aVar;
    }

    private void a(Context context) {
        this.c = context;
    }

    /* compiled from: AnimationsContainer */
    /* access modifiers changed from: private */
    /* renamed from: cn.missfresh.module.base.refresh.a$a  reason: collision with other inner class name */
    public static class C0030a {
        private static final a a = new a();

        static {
            AppMethodBeat.i(18883, false);
            AppMethodBeat.o(18883);
        }
    }

    public void a(int i, int i2) {
        this.b = i;
        this.a = i2;
    }

    public b a(ImageView imageView) {
        AppMethodBeat.i(18943, false);
        b bVar = new b(imageView, a(this.b), this.a);
        AppMethodBeat.o(18943);
        return bVar;
    }

    /* compiled from: AnimationsContainer */
    public class b {
        private int[] b;
        private int c;
        private boolean d;
        private boolean e;
        private SoftReference<ImageView> f;
        private Handler g = new Handler();
        private int h;
        private c i;
        private Bitmap j = null;
        private BitmapFactory.Options k;
        private boolean l;

        static /* synthetic */ int f(b bVar) {
            AppMethodBeat.i(18926, false);
            int c = bVar.c();
            AppMethodBeat.o(18926);
            return c;
        }

        public b(ImageView imageView, int[] iArr, int i) {
            AppMethodBeat.i(18902, false);
            this.b = iArr;
            this.c = -1;
            this.f = new SoftReference<>(imageView);
            this.d = false;
            this.e = false;
            this.l = true;
            this.h = 1000 / i;
            imageView.setImageResource(this.b[0]);
            if (Build.VERSION.SDK_INT >= 11) {
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                this.j = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                this.k = new BitmapFactory.Options();
                BitmapFactory.Options options = this.k;
                options.inBitmap = this.j;
                options.inMutable = true;
                options.inSampleSize = 1;
            }
            AppMethodBeat.o(18902);
        }

        private int c() {
            AppMethodBeat.i(18908, false);
            this.c++;
            if (this.c >= this.b.length) {
                if (!this.l) {
                    b();
                }
                this.c = 0;
            }
            int i = this.b[this.c];
            AppMethodBeat.o(18908);
            return i;
        }

        public synchronized void a() {
            AppMethodBeat.i(18910, false);
            if (this.e) {
                AppMethodBeat.o(18910);
                return;
            }
            this.d = true;
            this.g.post(new AnonymousClass1());
            AppMethodBeat.o(18910);
        }

        /* compiled from: AnimationsContainer */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.refresh.a$b$1  reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmap;
                AppMethodBeat.i(18893, false);
                ImageView imageView = (ImageView) b.this.f.get();
                if (!b.this.d || imageView == null) {
                    b.this.e = false;
                    if (b.this.i != null) {
                        b.this.i.a();
                    }
                    AppMethodBeat.o(18893);
                    return;
                }
                b.this.e = true;
                b.this.g.postDelayed(this, (long) b.this.h);
                if (imageView.isShown()) {
                    int f = b.f(b.this);
                    if (b.this.j != null) {
                        try {
                            bitmap = BitmapFactory.decodeResource(imageView.getResources(), f, b.this.k);
                        } catch (Exception e) {
                            cn.missfresh.mfdbmanagementlib.db.a.a(getClass().getName(), e.toString());
                            bitmap = null;
                        }
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        } else {
                            imageView.setImageResource(f);
                            b.this.j = null;
                        }
                    } else {
                        imageView.setImageResource(f);
                    }
                }
                AppMethodBeat.o(18893);
            }
        }

        public synchronized void b() {
            this.d = false;
        }
    }

    private int[] a(int i) {
        AppMethodBeat.i(18945, false);
        TypedArray obtainTypedArray = this.c.getResources().obtainTypedArray(i);
        int length = obtainTypedArray.length();
        int[] iArr = new int[obtainTypedArray.length()];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = obtainTypedArray.getResourceId(i2, 0);
        }
        obtainTypedArray.recycle();
        AppMethodBeat.o(18945);
        return iArr;
    }
}
