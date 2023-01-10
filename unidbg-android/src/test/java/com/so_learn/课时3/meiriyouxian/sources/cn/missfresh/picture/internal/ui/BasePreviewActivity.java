package cn.missfresh.picture.internal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.picture.R;
import cn.missfresh.picture.b.b;
import cn.missfresh.picture.internal.a.d;
import cn.missfresh.picture.internal.a.e;
import cn.missfresh.picture.internal.a.g;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.entity.c;
import cn.missfresh.picture.internal.model.SelectedItemCollection;
import cn.missfresh.picture.internal.ui.adapter.PreviewPagerAdapter;
import cn.missfresh.picture.internal.ui.adapter.SmallPictureAdapter;
import cn.missfresh.picture.internal.ui.adapter.SmallPictureItemDecoration;
import cn.missfresh.picture.internal.ui.widget.CheckRadioView;
import cn.missfresh.picture.internal.ui.widget.CheckView;
import cn.missfresh.picture.internal.ui.widget.IncapableDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class BasePreviewActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, b, SmallPictureAdapter.a {
    protected final SelectedItemCollection a = new SelectedItemCollection(this);
    protected c b;
    protected ViewPager c;
    protected PreviewPagerAdapter d;
    protected SmallPictureAdapter e;
    protected CheckView f;
    protected ImageView g;
    protected TextView h;
    protected TextView i;
    protected int j = -1;
    protected boolean k;
    private LinearLayout l;
    private CheckRadioView m;
    private FrameLayout n;
    private RelativeLayout o;
    private RecyclerView p;
    private boolean q = false;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setTheme(c.a().d);
        super.onCreate(bundle);
        if (!c.a().q) {
            setResult(0);
            finish();
            return;
        }
        setContentView(R.layout.activity_media_preview);
        if (e.a()) {
            getWindow().addFlags(67108864);
        }
        this.b = c.a();
        if (this.b.d()) {
            setRequestedOrientation(this.b.e);
        }
        if (bundle == null) {
            this.a.a(getIntent().getBundleExtra("extra_default_bundle"));
            this.k = getIntent().getBooleanExtra("extra_result_original_enable", false);
        } else {
            this.a.a(bundle);
            this.k = bundle.getBoolean("checkState");
        }
        this.g = (ImageView) findViewById(R.id.iv_back);
        this.h = (TextView) findViewById(R.id.button_apply);
        this.i = (TextView) findViewById(R.id.size);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.c = (ViewPager) findViewById(R.id.pager);
        this.c.addOnPageChangeListener(this);
        this.d = new PreviewPagerAdapter(getSupportFragmentManager(), null);
        this.c.setAdapter(this.d);
        this.f = (CheckView) findViewById(R.id.check_view);
        this.f.setCountable(this.b.f);
        this.n = (FrameLayout) findViewById(R.id.bottom_toolbar);
        this.o = (RelativeLayout) findViewById(R.id.top_toolbar);
        this.p = (RecyclerView) findViewById(R.id.rv_small_picture);
        this.f.setOnClickListener(new AnonymousClass1());
        this.l = (LinearLayout) findViewById(R.id.originalLayout);
        this.m = (CheckRadioView) findViewById(R.id.original);
        this.l.setOnClickListener(new AnonymousClass2());
        c();
        this.p.setLayoutManager(new LinearLayoutManager(getBaseContext(), 0, false));
        this.p.addItemDecoration(new SmallPictureItemDecoration((int) g.a(10, getBaseContext())));
        this.e = new SmallPictureAdapter();
        this.e.a(this);
        this.p.setAdapter(this.e);
        this.e.a(this.a.b());
    }

    /* renamed from: cn.missfresh.picture.internal.ui.BasePreviewActivity$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(18625, true);
            LocalMedia a = BasePreviewActivity.this.d.a(BasePreviewActivity.this.c.getCurrentItem());
            if (BasePreviewActivity.this.a.c(a)) {
                BasePreviewActivity.this.a.b(a);
                BasePreviewActivity.this.e.c(a);
                if (BasePreviewActivity.this.b.f) {
                    BasePreviewActivity.this.f.setCheckedNum(Integer.MIN_VALUE);
                } else {
                    BasePreviewActivity.this.f.setChecked(false);
                }
            } else if (BasePreviewActivity.this.c(a)) {
                BasePreviewActivity.this.a.a(a);
                BasePreviewActivity.this.e.b(a);
                if (BasePreviewActivity.this.b.f) {
                    BasePreviewActivity.this.f.setCheckedNum(BasePreviewActivity.this.a.f(a));
                } else {
                    BasePreviewActivity.this.f.setChecked(true);
                }
            }
            BasePreviewActivity.this.c();
            if (BasePreviewActivity.this.b.r != null) {
                BasePreviewActivity.this.b.r.a(BasePreviewActivity.this.a.c(), BasePreviewActivity.this.a.d());
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(18625);
        }
    }

    /* renamed from: cn.missfresh.picture.internal.ui.BasePreviewActivity$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(18422, true);
            int d = BasePreviewActivity.this.d();
            if (d > 0) {
                IncapableDialog.a("", BasePreviewActivity.this.getString(R.string.error_over_original_count, Integer.valueOf(d), Integer.valueOf(BasePreviewActivity.this.b.u))).show(BasePreviewActivity.this.getSupportFragmentManager(), IncapableDialog.class.getName());
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(18422);
                return;
            }
            BasePreviewActivity basePreviewActivity = BasePreviewActivity.this;
            basePreviewActivity.k = true ^ basePreviewActivity.k;
            BasePreviewActivity.this.m.setChecked(BasePreviewActivity.this.k);
            if (!BasePreviewActivity.this.k) {
                BasePreviewActivity.this.m.setColor(-1);
            }
            if (BasePreviewActivity.this.b.v != null) {
                BasePreviewActivity.this.b.v.a(BasePreviewActivity.this.k);
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(18422);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        this.a.b(bundle);
        bundle.putBoolean("checkState", this.k);
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        a(false);
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.button_apply) {
            if (this.a.f() == 0) {
                a();
            }
            a(true);
            finish();
        }
    }

    private void a() {
        ViewPager viewPager;
        PreviewPagerAdapter previewPagerAdapter = this.d;
        if (previewPagerAdapter != null && (viewPager = this.c) != null) {
            LocalMedia a = previewPagerAdapter.a(viewPager.getCurrentItem());
            if (c(a)) {
                this.a.a(a);
            }
        }
    }

    public void b() {
        if (this.b.t) {
            if (this.q) {
                this.o.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) this.o.getMeasuredHeight()).start();
                this.n.animate().translationYBy((float) (-this.n.getMeasuredHeight())).setInterpolator(new FastOutSlowInInterpolator()).start();
            } else {
                this.o.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) (-this.o.getMeasuredHeight())).start();
                this.n.animate().setInterpolator(new FastOutSlowInInterpolator()).translationYBy((float) this.n.getMeasuredHeight()).start();
            }
            this.q = !this.q;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        PreviewPagerAdapter previewPagerAdapter = (PreviewPagerAdapter) this.c.getAdapter();
        int i2 = this.j;
        if (!(i2 == -1 || i2 == i)) {
            ((PreviewItemFragment) previewPagerAdapter.instantiateItem((ViewGroup) this.c, i2)).a();
            LocalMedia a = previewPagerAdapter.a(i);
            if (this.b.f) {
                int f = this.a.f(a);
                this.f.setCheckedNum(f);
                if (f > 0) {
                    this.f.setEnabled(true);
                } else {
                    this.f.setEnabled(true ^ this.a.e());
                }
            } else {
                boolean c = this.a.c(a);
                this.f.setChecked(c);
                if (c) {
                    this.f.setEnabled(true);
                } else {
                    this.f.setEnabled(true ^ this.a.e());
                }
            }
            a(a);
            this.e.a(a);
        }
        this.j = i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        int f = this.a.f();
        this.p.setVisibility(f > 0 ? 0 : 8);
        if (f == 0) {
            this.h.setText(R.string.button_apply_default);
            this.p.setVisibility(8);
        } else if (f != 1 || !this.b.c()) {
            this.h.setText(getString(R.string.button_apply, Integer.valueOf(f)));
        } else {
            this.h.setText(R.string.button_apply_default);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int d() {
        int f = this.a.f();
        int i = 0;
        for (int i2 = 0; i2 < f; i2++) {
            LocalMedia localMedia = this.a.b().get(i2);
            if (localMedia.c() && d.a(localMedia.d) > ((float) this.b.u)) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void a(LocalMedia localMedia) {
        if (localMedia.d()) {
            this.i.setVisibility(0);
            TextView textView = this.i;
            textView.setText(d.a(localMedia.d) + "M");
        } else {
            this.i.setVisibility(8);
        }
        this.e.a(localMedia);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        Intent intent = new Intent();
        intent.putExtra("extra_result_bundle", this.a.a());
        intent.putExtra("extra_result_apply", z);
        intent.putExtra("extra_result_original_enable", this.k);
        setResult(-1, intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean c(LocalMedia localMedia) {
        cn.missfresh.picture.internal.entity.b d = this.a.d(localMedia);
        cn.missfresh.picture.internal.entity.b.a(this, d);
        return d == null;
    }

    @Override // cn.missfresh.picture.internal.ui.adapter.SmallPictureAdapter.a
    public void b(LocalMedia localMedia) {
        this.c.setCurrentItem(this.d.a(localMedia), false);
    }
}
