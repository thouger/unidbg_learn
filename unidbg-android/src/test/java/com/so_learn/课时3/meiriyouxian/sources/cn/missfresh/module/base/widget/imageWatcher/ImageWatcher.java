package cn.missfresh.module.base.widget.imageWatcher;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.WeakReference;
import java.util.List;

public class ImageWatcher extends FrameLayout implements GestureDetector.OnGestureListener, ViewPager.OnPageChangeListener {
    private static final String m = ImageWatcher.class.getSimpleName();
    private ValueAnimator A;
    private boolean B;
    private final GestureDetector C;
    private boolean D;
    private h E;
    private c F;
    private final ViewPager G;
    private int H;
    private int I;
    private f J;
    private i K;
    private d L;
    private View M;
    private g N;
    private boolean O;
    private boolean P;
    private final AnimatorListenerAdapter Q;
    private final TypeEvaluator<Integer> R;
    private final DecelerateInterpolator S;
    private final AccelerateInterpolator T;
    protected final float a;
    protected final float b;
    protected float c;
    protected float d;
    protected int e;
    protected int f;
    protected ImageView g;
    protected SparseArray<ImageView> h;
    protected List<String> i;
    protected SparseArray<ImageView> j;
    protected List<String> k;
    protected int l;
    private final Handler n;
    private ImageView o;
    private int p;
    private int q;
    private int r;
    private int s;
    private final float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private ValueAnimator y;
    private ValueAnimator z;

    public interface d {
        View a(Context context);

        void a(ImageWatcher imageWatcher, int i, List<String> list);
    }

    public interface e {
        void a(Drawable drawable);

        void b(Drawable drawable);

        void c(Drawable drawable);
    }

    public interface f {
        void a(Context context, String str, e eVar);
    }

    public interface g {
        View a(Context context);

        void a(View view);

        void b(View view);
    }

    public interface h {
        void a(ImageView imageView, String str, int i);
    }

    public interface i {
        void a(float f);

        void a(ImageWatcher imageWatcher, int i, String str, int i2);

        void a(ImageWatcher imageWatcher, ImageView imageView, int i, String str, float f, int i2);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    static /* synthetic */ void a(ImageWatcher imageWatcher, int i2, int i3) {
        AppMethodBeat.i(24070, false);
        imageWatcher.a(i2, i3);
        AppMethodBeat.o(24070);
    }

    static /* synthetic */ void a(ImageWatcher imageWatcher, MotionEvent motionEvent) {
        AppMethodBeat.i(24072, false);
        imageWatcher.a(motionEvent);
        AppMethodBeat.o(24072);
    }

    static /* synthetic */ void a(ImageWatcher imageWatcher, ImageView imageView, b bVar) {
        AppMethodBeat.i(24069, false);
        imageWatcher.a(imageView, bVar);
        AppMethodBeat.o(24069);
    }

    static /* synthetic */ void e(ImageWatcher imageWatcher) {
        AppMethodBeat.i(24071, false);
        imageWatcher.g();
        AppMethodBeat.o(24071);
    }

    static {
        AppMethodBeat.i(24073, false);
        AppMethodBeat.o(24073);
    }

    public ImageWatcher(Context context) {
        this(context, null);
    }

    public ImageWatcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24037, false);
        this.a = 0.5f;
        this.b = 3.6f;
        this.c = 0.3f;
        this.d = 0.16f;
        this.e = R.drawable.ic_default_100;
        this.r = 0;
        this.s = 0;
        this.D = false;
        this.Q = new AnonymousClass1();
        this.R = new AnonymousClass2();
        this.S = new DecelerateInterpolator();
        this.T = new AccelerateInterpolator();
        this.n = new j(this);
        this.C = new GestureDetector(context, this);
        this.t = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        ViewPager viewPager = new ViewPager(context);
        this.G = viewPager;
        addView(viewPager);
        this.G.addOnPageChangeListener(this);
        setVisibility(4);
        setIndexProvider(new a());
        setLoadingUIProvider(new b());
        AppMethodBeat.o(24037);
    }

    public void setLoader(f fVar) {
        this.J = fVar;
    }

    public void a() {
        this.O = true;
    }

    public void setIndexProvider(d dVar) {
        AppMethodBeat.i(24038, false);
        this.L = dVar;
        if (this.L != null) {
            View view = this.M;
            if (view != null) {
                removeView(view);
            }
            this.M = this.L.a(getContext());
            addView(this.M);
        }
        AppMethodBeat.o(24038);
    }

    public void setLoadingUIProvider(g gVar) {
        this.N = gVar;
    }

    public void setOnStateChangedListener(i iVar) {
        this.K = iVar;
    }

    public void setOnPictureLongPressListener(h hVar) {
        this.E = hVar;
    }

    public class a implements d {
        TextView a;

        public a() {
        }

        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.d
        public View a(Context context) {
            AppMethodBeat.i(24019, false);
            this.a = new TextView(context);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 49;
            this.a.setLayoutParams(layoutParams);
            this.a.setTextColor(-1);
            this.a.setTranslationY(TypedValue.applyDimension(1, 50.0f, context.getResources().getDisplayMetrics()) + 0.5f);
            TextView textView = this.a;
            AppMethodBeat.o(24019);
            return textView;
        }

        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.d
        public void a(ImageWatcher imageWatcher, int i, List<String> list) {
            AppMethodBeat.i(24020, false);
            if (ImageWatcher.this.k.size() > 1) {
                this.a.setVisibility(0);
                this.a.setText((i + 1) + " / " + ImageWatcher.this.k.size());
            } else {
                this.a.setVisibility(8);
            }
            AppMethodBeat.o(24020);
        }
    }

    public class b implements g {
        final FrameLayout.LayoutParams a = new FrameLayout.LayoutParams(-2, -2);

        public b() {
            AppMethodBeat.i(24021, false);
            AppMethodBeat.o(24021);
        }

        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.g
        public View a(Context context) {
            AppMethodBeat.i(24022, false);
            this.a.gravity = 17;
            ProgressView progressView = new ProgressView(context);
            progressView.setLayoutParams(this.a);
            AppMethodBeat.o(24022);
            return progressView;
        }

        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.g
        public void a(View view) {
            AppMethodBeat.i(24023, false);
            view.setVisibility(0);
            ((ProgressView) view).a();
            AppMethodBeat.o(24023);
        }

        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.g
        public void b(View view) {
            AppMethodBeat.i(24024, false);
            ((ProgressView) view).b();
            view.setVisibility(8);
            AppMethodBeat.o(24024);
        }
    }

    public void a(List<String> list, int i2) {
        AppMethodBeat.i(24039, false);
        if (list == null) {
            NullPointerException nullPointerException = new NullPointerException("urlList[null]");
            AppMethodBeat.o(24039);
            throw nullPointerException;
        } else if (i2 >= list.size() || i2 < 0) {
            IndexOutOfBoundsException indexOutOfBoundsException = new IndexOutOfBoundsException("initPos[" + i2 + "]  urlList.size[" + list.size() + "]");
            AppMethodBeat.o(24039);
            throw indexOutOfBoundsException;
        } else {
            this.l = i2;
            a((ImageView) null, (SparseArray<ImageView>) null, list);
            AppMethodBeat.o(24039);
        }
    }

    private void a(ImageView imageView, SparseArray<ImageView> sparseArray, List<String> list) {
        AppMethodBeat.i(24041, false);
        if (this.J == null) {
            NullPointerException nullPointerException = new NullPointerException("please invoke `setLoader` first [loader == null]");
            AppMethodBeat.o(24041);
            throw nullPointerException;
        } else if (imageView != null && imageView.getDrawable() == null) {
            AppMethodBeat.o(24041);
        } else if (!this.D) {
            this.g = imageView;
            this.h = sparseArray;
            this.i = list;
            AppMethodBeat.o(24041);
        } else {
            this.H = this.l;
            ValueAnimator valueAnimator = this.A;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.A = null;
            this.j = sparseArray;
            this.k = list;
            this.o = null;
            setVisibility(0);
            ViewPager viewPager = this.G;
            c cVar = new c();
            this.F = cVar;
            viewPager.setAdapter(cVar);
            this.G.setCurrentItem(this.l);
            d dVar = this.L;
            if (dVar != null) {
                dVar.a(this, this.l, this.k);
            }
            AppMethodBeat.o(24041);
        }
    }

    public int getCurrentPosition() {
        return this.H;
    }

    public String getDisplayingUri() {
        AppMethodBeat.i(24042, false);
        List<String> list = this.k;
        String str = list != null ? list.get(getCurrentPosition()) : null;
        AppMethodBeat.o(24042);
        return str;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.I == 0;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(24043, false);
        if (this.o == null) {
            AppMethodBeat.o(24043);
            return true;
        } else if (this.B) {
            AppMethodBeat.o(24043);
            return true;
        } else {
            ValueAnimator valueAnimator = this.y;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                this.y = null;
                this.s = 1;
            }
            int action = motionEvent.getAction() & 255;
            if (action == 1) {
                a(motionEvent);
            } else if (action != 5) {
                if (action == 6) {
                    if (this.I != 0) {
                        b(motionEvent);
                    } else if (motionEvent.getPointerCount() - 1 < 2) {
                        this.s = 6;
                        a(motionEvent);
                    }
                }
            } else if (this.I == 0) {
                if (this.s != 5) {
                    this.u = 0.0f;
                    this.v = 0.0f;
                    this.w = 0.0f;
                    b.a(this.o, b.h);
                }
                this.s = 5;
            } else {
                b(motionEvent);
            }
            boolean onTouchEvent = this.C.onTouchEvent(motionEvent);
            AppMethodBeat.o(24043);
            return onTouchEvent;
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        AppMethodBeat.i(24044, false);
        this.s = 1;
        b(motionEvent);
        AppMethodBeat.o(24044);
        return true;
    }

    private void a(MotionEvent motionEvent) {
        AppMethodBeat.i(24045, false);
        int i2 = this.s;
        if (i2 == 5 || i2 == 6) {
            d();
        } else if (i2 == 3) {
            f();
        } else if (i2 == 2) {
            e();
        } else if (i2 == 4) {
            b(motionEvent);
        }
        AppMethodBeat.o(24045);
    }

    private void b(MotionEvent motionEvent) {
        AppMethodBeat.i(24046, false);
        a(motionEvent, (MotionEvent) null);
        AppMethodBeat.o(24046);
    }

    private void a(MotionEvent motionEvent, MotionEvent motionEvent2) {
        float f2;
        AppMethodBeat.i(24047, false);
        if (motionEvent != null) {
            float f3 = 0.0f;
            if (motionEvent2 != null) {
                try {
                    f2 = motionEvent.getX() - motionEvent2.getX();
                } catch (Exception unused) {
                }
            } else {
                f2 = 0.0f;
            }
            if (motionEvent2 != null) {
                f3 = motionEvent.getY() - motionEvent2.getY();
            }
            if (Math.abs(f3) > this.t * 3.0f && Math.abs(f2) < this.t && this.I == 0) {
                b.a(this.o, b.g);
                this.s = 3;
            }
        }
        this.G.onTouchEvent(motionEvent);
        AppMethodBeat.o(24047);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        AppMethodBeat.i(24048, false);
        if (this.s == 1) {
            float x = motionEvent != null ? motionEvent2.getX() - motionEvent.getX() : 0.0f;
            float y = motionEvent != null ? motionEvent2.getY() - motionEvent.getY() : 0.0f;
            if (Math.abs(x) > this.t || Math.abs(y) > this.t) {
                b a2 = b.a(this.o, b.d);
                b b2 = b.b(this.o, b.c);
                String str = (String) this.o.getTag(R.id.image_orientation);
                if (b2 == null) {
                    this.s = 4;
                } else if (Math.abs(x) < this.t && y > Math.abs(x) * 3.0f && ((((float) b2.k) * a2.o) / 2.0f) - ((float) (b2.k / 2)) <= this.o.getTranslationY()) {
                    if (this.s != 3) {
                        b.a(this.o, b.g);
                    }
                    this.s = 3;
                } else if (a2.o > b2.o || a2.n > b2.n || a2.o * ((float) this.o.getHeight()) > ((float) this.q)) {
                    if (this.s != 2) {
                        b.a(this.o, b.f);
                    }
                    this.s = 2;
                    if ("horizontal".equals(str)) {
                        float f4 = (((float) b2.j) * (a2.n - 1.0f)) / 2.0f;
                        if (a2.l >= f4 && x > 0.0f) {
                            this.s = 4;
                        } else if (a2.l <= (-f4) && x < 0.0f) {
                            this.s = 4;
                        }
                    } else if ("vertical".equals(str)) {
                        if (((float) b2.j) * a2.n > ((float) this.p)) {
                            float f5 = ((((float) b2.j) * a2.n) / 2.0f) - ((float) (b2.j / 2));
                            float f6 = (((float) this.p) - ((((float) b2.j) * a2.n) / 2.0f)) - ((float) (b2.j / 2));
                            if (a2.l >= f5 && x > 0.0f) {
                                this.s = 4;
                            } else if (a2.l <= f6 && x < 0.0f) {
                                this.s = 4;
                            }
                        } else if (Math.abs(y) < this.t && Math.abs(x) > this.t && Math.abs(x) > Math.abs(y) * 2.0f) {
                            this.s = 4;
                        }
                    }
                } else if (Math.abs(x) > this.t) {
                    this.s = 4;
                }
            }
        }
        int i2 = this.s;
        if (i2 == 4) {
            a(motionEvent2, motionEvent);
        } else if (i2 == 5) {
            c(motionEvent2);
        } else if (i2 == 3) {
            b(motionEvent2, motionEvent);
        } else if (i2 == 2) {
            c(motionEvent2, motionEvent);
        }
        AppMethodBeat.o(24048);
        return false;
    }

    public boolean b() {
        AppMethodBeat.i(24049, false);
        ImageView imageView = this.o;
        if (imageView == null) {
            AppMethodBeat.o(24049);
            return false;
        }
        b a2 = b.a(imageView, b.d);
        b b2 = b.b(this.o, b.c);
        if (b2 == null || (a2.o <= b2.o && a2.n <= b2.n)) {
            this.x = 0.0f;
        } else {
            this.o.setTag(b.g, b2);
            this.x = 1.0f;
        }
        f();
        AppMethodBeat.o(24049);
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        AppMethodBeat.i(24050, false);
        if (this.n.hasMessages(1)) {
            this.n.removeMessages(1);
            c();
            AppMethodBeat.o(24050);
            return true;
        }
        this.n.sendEmptyMessageDelayed(1, 350);
        AppMethodBeat.o(24050);
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        AppMethodBeat.i(24051, false);
        h hVar = this.E;
        if (hVar != null) {
            hVar.a(this.o, this.k.get(this.G.getCurrentItem()), this.G.getCurrentItem());
        }
        AppMethodBeat.o(24051);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0157, code lost:
        if (r7 < r3) goto L_0x015b;
     */
    @Override // android.view.GestureDetector.OnGestureListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onFling(android.view.MotionEvent r14, android.view.MotionEvent r15, float r16, float r17) {
        /*
        // Method dump skipped, instructions count: 380
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.onFling(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
    }

    private void b(MotionEvent motionEvent, MotionEvent motionEvent2) {
        AppMethodBeat.i(24053, false);
        ImageView imageView = this.o;
        if (imageView == null) {
            AppMethodBeat.o(24053);
            return;
        }
        b b2 = b.b(imageView, b.g);
        b b3 = b.b(this.o, b.c);
        if (b2 == null || b3 == null) {
            AppMethodBeat.o(24053);
            return;
        }
        this.x = 1.0f;
        float y = motionEvent.getY() - motionEvent2.getY();
        float x = motionEvent.getX() - motionEvent2.getX();
        if (y > 0.0f) {
            this.x -= y / (((float) this.q) / 2.0f);
        }
        if (this.x < 0.0f) {
            this.x = 0.0f;
        }
        i iVar = this.K;
        if (iVar != null) {
            iVar.a(this.x);
        }
        setBackgroundColor(((Integer) this.R.evaluate(this.x, 0, -16777216)).intValue());
        float f2 = ((b2.n - 0.5f) * this.x) + 0.5f;
        this.o.setScaleX(f2);
        this.o.setScaleY(f2);
        this.o.setTranslationX(b3.l + ((b2.l - b3.l) * this.x) + x);
        this.o.setTranslationY(b2.m + y);
        AppMethodBeat.o(24053);
    }

    private void c(MotionEvent motionEvent, MotionEvent motionEvent2) {
        float f2;
        float f3;
        AppMethodBeat.i(24054, false);
        ImageView imageView = this.o;
        if (imageView == null) {
            AppMethodBeat.o(24054);
            return;
        }
        b b2 = b.b(imageView, b.c);
        b b3 = b.b(this.o, b.f);
        if (b2 == null || b3 == null) {
            AppMethodBeat.o(24054);
            return;
        }
        float x = b3.l + (motionEvent.getX() - motionEvent2.getX());
        float y = b3.m + (motionEvent.getY() - motionEvent2.getY());
        String str = (String) this.o.getTag(R.id.image_orientation);
        if ("horizontal".equals(str)) {
            float f4 = (((float) b2.j) * (b3.n - 1.0f)) / 2.0f;
            if (x > f4) {
                f2 = x - f4;
                f3 = this.d;
            } else {
                f4 = -f4;
                if (x < f4) {
                    f2 = x - f4;
                    f3 = this.d;
                }
                this.o.setTranslationX(x);
            }
            x = (f2 * f3) + f4;
            this.o.setTranslationX(x);
        } else if ("vertical".equals(str)) {
            if (((float) b2.j) * b3.n <= ((float) this.p)) {
                x = b3.l;
            } else {
                float f5 = ((((float) b2.j) * b3.n) / 2.0f) - ((float) (b2.j / 2));
                float f6 = (((float) this.p) - ((((float) b2.j) * b3.n) / 2.0f)) - ((float) (b2.j / 2));
                if (x > f5) {
                    x = ((x - f5) * this.d) + f5;
                } else if (x < f6) {
                    x = ((x - f6) * this.d) + f6;
                }
            }
            this.o.setTranslationX(x);
        }
        if (((float) b2.k) * b3.o > ((float) this.q)) {
            float f7 = ((((float) b2.k) * b3.o) / 2.0f) - ((float) (b2.k / 2));
            float f8 = (((float) this.q) - ((((float) b2.k) * b3.o) / 2.0f)) - ((float) (b2.k / 2));
            if (y > f7) {
                y = ((y - f7) * this.d) + f7;
            } else if (y < f8) {
                y = ((y - f8) * this.d) + f8;
            }
            this.o.setTranslationY(y);
        }
        AppMethodBeat.o(24054);
    }

    private void c(MotionEvent motionEvent) {
        AppMethodBeat.i(24055, false);
        ImageView imageView = this.o;
        if (imageView == null) {
            AppMethodBeat.o(24055);
            return;
        }
        b b2 = b.b(imageView, b.c);
        b b3 = b.b(this.o, b.h);
        if (b2 == null || b3 == null) {
            AppMethodBeat.o(24055);
        } else if (motionEvent.getPointerCount() < 2) {
            AppMethodBeat.o(24055);
        } else {
            float x = motionEvent.getX(1) - motionEvent.getX(0);
            float y = motionEvent.getY(1) - motionEvent.getY(0);
            float sqrt = (float) Math.sqrt((double) ((x * x) + (y * y)));
            if (this.u == 0.0f) {
                this.u = sqrt;
            }
            float f2 = (this.u - sqrt) / (((float) this.p) * this.c);
            float f3 = b3.n - f2;
            if (f3 < 0.5f) {
                f3 = 0.5f;
            } else if (f3 > 3.6f) {
                f3 = 3.6f;
            }
            this.o.setScaleX(f3);
            float f4 = b3.o - f2;
            if (f4 < 0.5f) {
                f4 = 0.5f;
            } else if (f4 > 3.6f) {
                f4 = 3.6f;
            }
            this.o.setScaleY(f4);
            float x2 = (motionEvent.getX(1) + motionEvent.getX(0)) / 2.0f;
            float y2 = (motionEvent.getY(1) + motionEvent.getY(0)) / 2.0f;
            if (this.v == 0.0f && this.w == 0.0f) {
                this.v = x2;
                this.w = y2;
            }
            this.o.setTranslationX((b3.l - (this.v - x2)) + 0.0f);
            this.o.setTranslationY(b3.m - (this.w - y2));
            AppMethodBeat.o(24055);
        }
    }

    private void c() {
        AppMethodBeat.i(24056, false);
        ImageView imageView = this.o;
        if (imageView == null) {
            AppMethodBeat.o(24056);
            return;
        }
        b b2 = b.b(imageView, b.c);
        if (b2 == null) {
            AppMethodBeat.o(24056);
            return;
        }
        b a2 = b.a(this.o, b.d);
        if (a2.o > b2.o || a2.n > b2.n) {
            a(this.o, b2);
        } else {
            float f2 = 3.6f;
            float f3 = ((3.6f - b2.n) * 0.4f) + b2.n;
            if (((String) this.o.getTag(R.id.image_orientation)).equals("horizontal")) {
                b b3 = b.b(this.o, b.c);
                float f4 = (float) (b3.j / b3.k);
                if (f4 > 2.0f) {
                    f2 = (f4 * 3.6f) / 2.0f;
                }
                f3 = ((f2 - b2.n) * 0.4f) + b2.n;
            }
            ImageView imageView2 = this.o;
            a(imageView2, b.a(imageView2, b.e).a(f3).c(f3));
        }
        AppMethodBeat.o(24056);
    }

    private void d() {
        AppMethodBeat.i(24057, false);
        ImageView imageView = this.o;
        if (imageView == null) {
            AppMethodBeat.o(24057);
            return;
        }
        b b2 = b.b(imageView, b.c);
        if (b2 == null) {
            AppMethodBeat.o(24057);
            return;
        }
        b a2 = b.a(this.o, b.d);
        b c2 = b.a(b2, b.e).a(a2.n < b2.n ? b2.n : a2.n).c(a2.o < b2.o ? b2.o : a2.o);
        if (((float) this.o.getWidth()) * a2.n > ((float) this.p)) {
            float f2 = (((float) a2.j) * (a2.n - 1.0f)) / 2.0f;
            if (a2.l <= f2) {
                f2 = -f2;
                if (a2.l >= f2) {
                    f2 = a2.l;
                }
            }
            c2.d(f2);
        }
        if (((float) this.o.getHeight()) * a2.o > ((float) this.q)) {
            float f3 = ((((float) b2.k) * a2.o) / 2.0f) - ((float) (b2.k / 2));
            float f4 = (((float) this.q) - ((((float) b2.k) * a2.o) / 2.0f)) - ((float) (b2.k / 2));
            if (a2.m <= f3) {
                if (a2.m < f4) {
                    f3 = f4;
                } else {
                    f3 = a2.m;
                }
            }
            c2.e(f3);
        }
        this.o.setTag(b.e, c2);
        a(this.o, c2);
        a(-16777216, 0);
        AppMethodBeat.o(24057);
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() {
        /*
        // Method dump skipped, instructions count: 318
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.e():void");
    }

    private void f() {
        AppMethodBeat.i(24059, false);
        ImageView imageView = this.o;
        if (imageView == null) {
            AppMethodBeat.o(24059);
            return;
        }
        if (this.x > 0.75f) {
            b b2 = b.b(imageView, b.g);
            if (b2 != null) {
                a(this.o, b2);
            }
            a(-16777216, 0);
        } else {
            b b3 = b.b(imageView, b.a);
            if (b3 != null) {
                if (b3.p == 0.0f) {
                    b3.d(this.o.getTranslationX()).e(this.o.getTranslationY());
                }
                a(this.o, b3);
            }
            a(0, 4);
            ((FrameLayout) this.o.getParent()).getChildAt(2).animate().alpha(0.0f).start();
        }
        AppMethodBeat.o(24059);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        this.I = i3;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        AppMethodBeat.i(24060, false);
        this.o = (ImageView) this.F.b.get(i2);
        this.H = i2;
        d dVar = this.L;
        if (dVar != null) {
            dVar.a(this, i2, this.k);
        }
        ImageView imageView = (ImageView) this.F.b.get(i2 - 1);
        if (b.b(imageView, b.c) != null) {
            b.e(imageView, b.c).a().start();
        }
        ImageView imageView2 = (ImageView) this.F.b.get(i2 + 1);
        if (b.b(imageView2, b.c) != null) {
            b.e(imageView2, b.c).a().start();
        }
        AppMethodBeat.o(24060);
    }

    /* access modifiers changed from: package-private */
    public class c extends PagerAdapter {
        private final SparseArray<ImageView> b = new SparseArray<>();
        private boolean c;

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        c() {
            AppMethodBeat.i(24029, false);
            AppMethodBeat.o(24029);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            int i = 0;
            AppMethodBeat.i(24030, false);
            if (ImageWatcher.this.k != null) {
                i = ImageWatcher.this.k.size();
            }
            AppMethodBeat.o(24030);
            return i;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            AppMethodBeat.i(24031, false);
            viewGroup.removeView((View) obj);
            this.b.remove(i);
            AppMethodBeat.o(24031);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            AppMethodBeat.i(24032, false);
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            viewGroup.addView(frameLayout);
            ImageView imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            frameLayout.addView(imageView);
            this.b.put(i, imageView);
            View a = ImageWatcher.this.N != null ? ImageWatcher.this.N.a(viewGroup.getContext()) : null;
            if (a == null) {
                a = new View(viewGroup.getContext());
            }
            frameLayout.addView(a);
            ImageView imageView2 = new ImageView(viewGroup.getContext());
            imageView2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView2.setImageResource(ImageWatcher.this.e);
            frameLayout.addView(imageView2);
            imageView2.setVisibility(8);
            if (a(imageView, i, this.c)) {
                this.c = true;
            }
            AppMethodBeat.o(24032);
            return frameLayout;
        }

        /* access modifiers changed from: package-private */
        public void a(int i, boolean z, boolean z2) {
            int i2 = 0;
            AppMethodBeat.i(24033, false);
            ImageView imageView = (ImageView) this.b.get(i);
            if (imageView != null) {
                FrameLayout frameLayout = (FrameLayout) imageView.getParent();
                View childAt = frameLayout.getChildAt(1);
                if (ImageWatcher.this.N != null) {
                    if (z) {
                        ImageWatcher.this.N.a(childAt);
                    } else {
                        ImageWatcher.this.N.b(childAt);
                    }
                }
                ImageView imageView2 = (ImageView) frameLayout.getChildAt(2);
                imageView2.setAlpha(1.0f);
                if (!z2) {
                    i2 = 8;
                }
                imageView2.setVisibility(i2);
            }
            AppMethodBeat.o(24033);
        }

        private boolean a(ImageView imageView, int i, boolean z) {
            boolean z2;
            AppMethodBeat.i(24034, false);
            if (i != ImageWatcher.this.l || z) {
                z2 = false;
            } else {
                ImageWatcher.this.o = imageView;
                z2 = true;
            }
            ImageView imageView2 = null;
            if (ImageWatcher.this.j != null) {
                imageView2 = (ImageView) ImageWatcher.this.j.get(i);
            }
            if (imageView2 != null) {
                int[] iArr = new int[2];
                imageView2.getLocationOnScreen(iArr);
                imageView.setTranslationX((float) iArr[0]);
                imageView.setTranslationY((float) (iArr[1] - ImageWatcher.this.f));
                imageView.getLayoutParams().width = imageView2.getWidth();
                imageView.getLayoutParams().height = imageView2.getHeight();
                b.a(imageView, b.a).a(imageView2.getWidth()).b(imageView2.getHeight());
                Drawable drawable = imageView2.getDrawable();
                if (drawable != null) {
                    int width = drawable.getBounds().width();
                    int height = drawable.getBounds().height();
                    b e = b.a(imageView, b.b).a(width).b(height).d((float) ((ImageWatcher.this.p - width) / 2)).e((float) ((ImageWatcher.this.q - height) / 2));
                    imageView.setImageDrawable(drawable);
                    if (z2) {
                        ImageWatcher.a(ImageWatcher.this, imageView, e);
                    } else {
                        b.d(imageView, e.i);
                    }
                }
            } else {
                imageView.getLayoutParams().width = 0;
                imageView.getLayoutParams().height = 0;
                b.a(imageView, b.a).f(0.0f).a(0).b(0).b(1.5f).c(1.5f);
            }
            b.c(imageView, b.c);
            ImageWatcher.this.J.a(imageView.getContext(), ImageWatcher.this.k.get(i), new AnonymousClass1(imageView, i, z2));
            if (z2) {
                ImageWatcher.a(ImageWatcher.this, -16777216, 3);
            }
            AppMethodBeat.o(24034);
            return z2;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher$c$1  reason: invalid class name */
        public class AnonymousClass1 implements e {
            final /* synthetic */ ImageView a;
            final /* synthetic */ int b;
            final /* synthetic */ boolean c;

            AnonymousClass1(ImageView imageView, int i, boolean z) {
                this.a = imageView;
                this.b = i;
                this.c = z;
            }

            @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.e
            public void a(Drawable drawable) {
                int i;
                int i2;
                int i3;
                AppMethodBeat.i(24026, false);
                float intrinsicWidth = (float) drawable.getIntrinsicWidth();
                float intrinsicHeight = (float) drawable.getIntrinsicHeight();
                if ((intrinsicWidth * 1.0f) / intrinsicHeight > (((float) ImageWatcher.this.p) * 1.0f) / ((float) ImageWatcher.this.q)) {
                    i = ImageWatcher.this.p;
                    i3 = (int) (((((float) i) * 1.0f) / intrinsicWidth) * intrinsicHeight);
                    i2 = (ImageWatcher.this.q - i3) / 2;
                    this.a.setTag(R.id.image_orientation, "horizontal");
                } else {
                    i = ImageWatcher.this.p;
                    i3 = (int) (((((float) i) * 1.0f) / intrinsicWidth) * intrinsicHeight);
                    this.a.setTag(R.id.image_orientation, "vertical");
                    i2 = 0;
                }
                this.a.setImageDrawable(drawable);
                c.this.a(this.b, false, false);
                b e = b.a(this.a, b.c).a(i).b(i3).d((float) 0).e((float) i2);
                if (this.c) {
                    ImageWatcher.a(ImageWatcher.this, this.a, e);
                } else {
                    b.d(this.a, e.i);
                    this.a.setAlpha(0.0f);
                    this.a.animate().alpha(1.0f).start();
                }
                this.a.addOnAttachStateChangeListener(new AnonymousClass1());
                Drawable drawable2 = this.a.getDrawable();
                if (drawable2 instanceof Animatable) {
                    ((Animatable) drawable2).start();
                }
                AppMethodBeat.o(24026);
            }

            /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher$c$1$1  reason: invalid class name */
            class AnonymousClass1 implements View.OnAttachStateChangeListener {
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view) {
                }

                AnonymousClass1() {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view) {
                    AppMethodBeat.i(24025, false);
                    Drawable drawable = AnonymousClass1.this.a.getDrawable();
                    if (drawable instanceof Animatable) {
                        ((Animatable) drawable).stop();
                    }
                    AppMethodBeat.o(24025);
                }
            }

            @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.e
            public void b(Drawable drawable) {
                AppMethodBeat.i(24027, false);
                c.this.a(this.b, true, false);
                AppMethodBeat.o(24027);
            }

            @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.e
            public void c(Drawable drawable) {
                AppMethodBeat.i(24028, false);
                c.this.a(this.b, false, this.a.getDrawable() == null);
                AppMethodBeat.o(24028);
            }
        }
    }

    private static class j extends Handler {
        WeakReference<ImageWatcher> a;

        j(ImageWatcher imageWatcher) {
            AppMethodBeat.i(24035, false);
            this.a = new WeakReference<>(imageWatcher);
            AppMethodBeat.o(24035);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AppMethodBeat.i(24036, false);
            ImageWatcher imageWatcher = this.a.get();
            if (imageWatcher != null) {
                int i = message.what;
                if (i == 1) {
                    imageWatcher.b();
                } else if (i == 2) {
                    ImageWatcher.e(imageWatcher);
                } else {
                    RuntimeException runtimeException = new RuntimeException("Unknown message " + message);
                    AppMethodBeat.o(24036);
                    throw runtimeException;
                }
            }
            AppMethodBeat.o(24036);
        }
    }

    /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher$1  reason: invalid class name */
    class AnonymousClass1 extends AnimatorListenerAdapter {
        AnonymousClass1() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            AppMethodBeat.i(24008, false);
            ImageWatcher.this.B = false;
            AppMethodBeat.o(24008);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(24009, false);
            ImageWatcher.this.B = true;
            ImageWatcher.this.s = 7;
            AppMethodBeat.o(24009);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(24010, false);
            ImageWatcher.this.B = false;
            AppMethodBeat.o(24010);
        }
    }

    /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher$2  reason: invalid class name */
    class AnonymousClass2 implements TypeEvaluator<Integer> {
        AnonymousClass2() {
        }

        @Override // android.animation.TypeEvaluator
        public /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
            AppMethodBeat.i(24012, false);
            Integer a = a(f, (Integer) obj, (Integer) obj2);
            AppMethodBeat.o(24012);
            return a;
        }

        public Integer a(float f, Integer num, Integer num2) {
            AppMethodBeat.i(24011, false);
            float interpolation = ImageWatcher.this.T.getInterpolation(f);
            int intValue = num.intValue();
            int intValue2 = num2.intValue();
            Integer valueOf = Integer.valueOf(Color.argb((int) (((float) Color.alpha(intValue)) + (((float) (Color.alpha(intValue2) - Color.alpha(intValue))) * interpolation)), (int) (((float) Color.red(intValue)) + (((float) (Color.red(intValue2) - Color.red(intValue))) * interpolation)), (int) (((float) Color.green(intValue)) + (((float) (Color.green(intValue2) - Color.green(intValue))) * interpolation)), (int) (((float) Color.blue(intValue)) + (interpolation * ((float) (Color.blue(intValue2) - Color.blue(intValue)))))));
            AppMethodBeat.o(24011);
            return valueOf;
        }
    }

    public void setTranslucentStatus(int i2) {
        this.f = i2;
    }

    public void setErrorImageRes(int i2) {
        this.e = i2;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        AppMethodBeat.i(24061, false);
        this.r = i2;
        super.setBackgroundColor(i2);
        AppMethodBeat.o(24061);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        AppMethodBeat.i(24062, false);
        super.onSizeChanged(i2, i3, i4, i5);
        this.p = i2;
        this.q = i3;
        if (!this.D) {
            this.D = true;
            this.n.sendEmptyMessage(2);
        }
        AppMethodBeat.o(24062);
    }

    private void g() {
        AppMethodBeat.i(24063, false);
        List<String> list = this.i;
        if (list != null) {
            a(this.g, this.h, list);
        }
        AppMethodBeat.o(24063);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(24064, false);
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.A;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.A = null;
        ValueAnimator valueAnimator2 = this.z;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        this.z = null;
        ValueAnimator valueAnimator3 = this.y;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
        this.y = null;
        AppMethodBeat.o(24064);
    }

    private void a(ImageView imageView, b bVar) {
        AppMethodBeat.i(24066, false);
        if (imageView == null) {
            AppMethodBeat.o(24066);
            return;
        }
        ValueAnimator valueAnimator = this.A;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.A = b.e(imageView, bVar.i).a(this.Q).a();
        if (this.A != null) {
            if (bVar.i == b.a) {
                this.A.addListener(new AnonymousClass3());
            }
            this.A.start();
        }
        AppMethodBeat.o(24066);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher$3  reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        AnonymousClass3() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(24013, false);
            ImageWatcher.this.setVisibility(8);
            AppMethodBeat.o(24013);
        }
    }

    private void a(int i2, int i3) {
        i iVar;
        AppMethodBeat.i(24067, false);
        if (i2 == 0 && i3 == 0 && (iVar = this.K) != null) {
            iVar.a(1.0f);
        }
        if (i2 == this.r) {
            AppMethodBeat.o(24067);
            return;
        }
        ValueAnimator valueAnimator = this.z;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        int i4 = this.r;
        this.z = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(200L);
        this.z.addUpdateListener(new AnonymousClass4(i4, i2, i3));
        this.z.addListener(new AnonymousClass5(i3));
        this.z.start();
        AppMethodBeat.o(24067);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher$4  reason: invalid class name */
    public class AnonymousClass4 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ int a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;

        AnonymousClass4(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AppMethodBeat.i(24014, false);
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            ImageWatcher imageWatcher = ImageWatcher.this;
            imageWatcher.setBackgroundColor(((Integer) imageWatcher.R.evaluate(floatValue, Integer.valueOf(this.a), Integer.valueOf(this.b))).intValue());
            if (ImageWatcher.this.K != null) {
                if (floatValue == 1.0f && this.c == 0) {
                    ImageWatcher.this.K.a(1.0f);
                }
                i iVar = ImageWatcher.this.K;
                ImageWatcher imageWatcher2 = ImageWatcher.this;
                iVar.a(imageWatcher2, imageWatcher2.o, ImageWatcher.this.getCurrentPosition(), ImageWatcher.this.getDisplayingUri(), floatValue, this.c);
            }
            AppMethodBeat.o(24014);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher$5  reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ int a;

        AnonymousClass5(int i) {
            this.a = i;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(24015, false);
            super.onAnimationStart(animator);
            if (ImageWatcher.this.K != null && this.a == 3) {
                i iVar = ImageWatcher.this.K;
                ImageWatcher imageWatcher = ImageWatcher.this;
                iVar.a(imageWatcher, imageWatcher.getCurrentPosition(), ImageWatcher.this.getDisplayingUri(), this.a);
            }
            AppMethodBeat.o(24015);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(24016, false);
            if (ImageWatcher.this.K != null && this.a == 4) {
                i iVar = ImageWatcher.this.K;
                ImageWatcher imageWatcher = ImageWatcher.this;
                iVar.a(imageWatcher, imageWatcher.getCurrentPosition(), ImageWatcher.this.getDisplayingUri(), this.a);
            }
            if (ImageWatcher.this.O && this.a == 4) {
                ImageWatcher.this.P = true;
                if (ImageWatcher.this.getParent() != null) {
                    ((ViewGroup) ImageWatcher.this.getParent()).removeView(ImageWatcher.this);
                }
            }
            AppMethodBeat.o(24016);
        }
    }

    private void a(ImageView imageView, b bVar, long j2) {
        AppMethodBeat.i(24068, false);
        if (j2 > 800) {
            j2 = 800;
        } else if (j2 < 100) {
            j2 = 100;
        }
        ValueAnimator valueAnimator = this.y;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.y = b.e(imageView, bVar.i).a(new AnonymousClass6()).a();
        this.y.setInterpolator(this.S);
        this.y.setDuration(j2);
        this.y.start();
        AppMethodBeat.o(24068);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.imageWatcher.ImageWatcher$6  reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        AnonymousClass6() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AppMethodBeat.i(24017, false);
            ImageWatcher.this.s = 7;
            AppMethodBeat.o(24017);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AppMethodBeat.i(24018, false);
            ImageWatcher.this.s = 6;
            ImageWatcher.a(ImageWatcher.this, (MotionEvent) null);
            AppMethodBeat.o(24018);
        }
    }
}
