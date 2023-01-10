package cn.missfresh.module.base.widget.integral.widget;

import android.content.Context;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.customview.widget.ViewDragHelper;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.integral.bean.DailySignIn;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import java.util.ArrayList;
import java.util.List;

public class SwitchView extends FrameLayout implements View.OnClickListener {
    private List<DailySignIn.MoodImage> a = new ArrayList();
    private RelativeLayout b;
    private RelativeLayout c;
    private RelativeLayout d;
    private TextView e;
    private TextView f;
    private TextView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private int k = 0;
    private a l;
    private ViewDragHelper m;
    private Point n = new Point();
    private int o;

    public interface a {
        void a(int i);
    }

    public SwitchView(Context context) {
        super(context, null);
        AppMethodBeat.i(24200, false);
        AppMethodBeat.o(24200);
    }

    public SwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24201, false);
        LayoutInflater.from(context).inflate(R.layout.layout_switch, (ViewGroup) this, true);
        b();
        c();
        AppMethodBeat.o(24201);
    }

    private void b() {
        AppMethodBeat.i(24202, false);
        this.b = (RelativeLayout) findViewById(R.id.rl_good);
        this.c = (RelativeLayout) findViewById(R.id.rl_bad);
        this.d = (RelativeLayout) findViewById(R.id.rl_top);
        this.e = (TextView) findViewById(R.id.tv_good);
        this.f = (TextView) findViewById(R.id.tv_bad);
        this.g = (TextView) findViewById(R.id.tv_top);
        this.h = (ImageView) findViewById(R.id.iv_good);
        this.i = (ImageView) findViewById(R.id.iv_bad);
        this.j = (ImageView) findViewById(R.id.iv_scroll);
        d.d("SwitchView", "initView");
        AppMethodBeat.o(24202);
    }

    private void c() {
        AppMethodBeat.i(24204, false);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.m = ViewDragHelper.create(this, 1.0f, new AnonymousClass1());
        AppMethodBeat.o(24204);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.integral.widget.SwitchView$1  reason: invalid class name */
    public class AnonymousClass1 extends ViewDragHelper.Callback {
        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            return false;
        }

        AnonymousClass1() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            AppMethodBeat.i(24190, false);
            int paddingLeft = SwitchView.this.getPaddingLeft();
            int min = Math.min(Math.max(i, paddingLeft), (SwitchView.this.getWidth() - view.getWidth()) - paddingLeft);
            AppMethodBeat.o(24190);
            return min;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            AppMethodBeat.i(24191, false);
            int measuredWidth = SwitchView.this.getMeasuredWidth() - view.getMeasuredWidth();
            AppMethodBeat.o(24191);
            return measuredWidth;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            AppMethodBeat.i(24192, false);
            if (view == SwitchView.this.d) {
                int left = view.getLeft();
                if (SwitchView.this.k == 0) {
                    if (left > SwitchView.this.o) {
                        SwitchView.this.n.x = SwitchView.this.getWidth() - view.getWidth();
                        SwitchView.this.k = 1;
                        SwitchView.this.a();
                    }
                } else if (SwitchView.this.k == 1 && left < SwitchView.this.o) {
                    SwitchView.this.n.x = 0;
                    SwitchView.this.k = 0;
                    SwitchView.this.a();
                }
                SwitchView.this.m.settleCapturedViewAt(SwitchView.this.n.x, SwitchView.this.n.y);
                SwitchView.this.invalidate();
            }
            AppMethodBeat.o(24192);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            AppMethodBeat.i(24193, false);
            super.onViewDragStateChanged(i);
            AppMethodBeat.o(24193);
        }
    }

    public void setDatas(List<DailySignIn.MoodImage> list) {
        AppMethodBeat.i(24205, false);
        if (list == null) {
            this.a = new ArrayList();
        } else {
            this.a = list;
        }
        DailySignIn.MoodImage moodImage = null;
        DailySignIn.MoodImage moodImage2 = !b.a(this.a) ? this.a.get(0) : null;
        if (moodImage2 != null) {
            a(this.e, this.h, a(moodImage2, 0), moodImage2.share_unchoose_img, R.drawable.ic_good_heart_dark);
            a(this.g, this.j, a(moodImage2, 0), moodImage2.share_choose_img, R.drawable.ic_good_heart_light);
        } else {
            a(this.e, this.h, a(moodImage2, 0), "", R.drawable.ic_good_heart_dark);
            a(this.g, this.j, a(moodImage2, 0), "", R.drawable.ic_good_heart_light);
        }
        if (!b.a(this.a) && this.a.size() > 1) {
            moodImage = this.a.get(1);
        }
        if (moodImage != null) {
            a(this.f, this.i, a(moodImage, 1), moodImage.share_unchoose_img, R.drawable.ic_bad_heart_dark);
        } else {
            a(this.f, this.i, a(moodImage, 1), "", R.drawable.ic_bad_heart_dark);
        }
        AppMethodBeat.o(24205);
    }

    public void a(TextView textView, ImageView imageView, String str, String str2, int i) {
        AppMethodBeat.i(24206, false);
        if (!b.a(str)) {
            textView.setText(str);
        }
        cn.missfresh.module.base.network.d.a(getContext(), str2, -1, i, imageView);
        AppMethodBeat.o(24206);
    }

    public void setCurrentHeart(int i) {
        this.k = i;
    }

    public int getCurrentHeart() {
        return this.k;
    }

    public void a() {
        AppMethodBeat.i(24207, false);
        int i = this.k;
        String str = "";
        DailySignIn.MoodImage moodImage = null;
        if (i == 0) {
            if (this.a.size() > 0) {
                moodImage = this.a.get(0);
            }
            TextView textView = this.g;
            ImageView imageView = this.j;
            String a2 = a(moodImage, 0);
            if (moodImage != null) {
                str = moodImage.share_choose_img;
            }
            a(textView, imageView, a2, str, R.drawable.ic_good_heart_light);
        } else if (i == 1) {
            if (this.a.size() > 1) {
                moodImage = this.a.get(1);
            }
            TextView textView2 = this.g;
            ImageView imageView2 = this.j;
            String a3 = a(moodImage, 1);
            if (moodImage != null) {
                str = moodImage.share_choose_img;
            }
            a(textView2, imageView2, a3, str, R.drawable.ic_bad_heart_light);
        }
        a aVar = this.l;
        if (aVar != null) {
            aVar.a(this.k);
        }
        AppMethodBeat.o(24207);
    }

    private String a(DailySignIn.MoodImage moodImage, int i) {
        String str;
        AppMethodBeat.i(24208, false);
        if (moodImage != null && !b.a(moodImage.share_text)) {
            str = moodImage.share_text;
        } else {
            str = "";
        }
        AppMethodBeat.o(24208);
        return str;
    }

    public void setOnHeartChangeListener(a aVar) {
        this.l = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(24209, false);
        d.d("SwitchView", "cuurent state:" + this.k);
        int id = view.getId();
        if (id == R.id.rl_good) {
            d.d("SwitchView", "want change to:0");
            if (this.k != 0) {
                a(0, 0);
            }
        } else if (id == R.id.rl_bad) {
            d.d("SwitchView", "want change to:1");
            if (this.k != 1) {
                a(1, getWidth() - this.d.getWidth());
            }
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(24209);
    }

    public int getBadHeartDx() {
        AppMethodBeat.i(24210, false);
        int width = getWidth() - this.d.getWidth();
        AppMethodBeat.o(24210);
        return width;
    }

    public void a(int i, int i2) {
        AppMethodBeat.i(24211, false);
        Point point = this.n;
        point.x = i2;
        this.k = i;
        this.m.smoothSlideViewTo(this.d, point.x, this.n.y);
        a();
        invalidate();
        AppMethodBeat.o(24211);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(24212, false);
        boolean shouldInterceptTouchEvent = this.m.shouldInterceptTouchEvent(motionEvent);
        AppMethodBeat.o(24212);
        return shouldInterceptTouchEvent;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(24213, false);
        this.m.processTouchEvent(motionEvent);
        AppMethodBeat.o(24213);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(24214, false);
        super.onLayout(z, i, i2, i3, i4);
        this.d.layout(this.n.x, this.n.y, this.n.x + this.d.getWidth(), this.n.y + this.d.getHeight());
        this.o = (getWidth() - this.d.getWidth()) / 2;
        AppMethodBeat.o(24214);
    }

    @Override // android.view.View
    public void computeScroll() {
        AppMethodBeat.i(24215, false);
        if (this.m.continueSettling(true)) {
            invalidate();
        }
        AppMethodBeat.o(24215);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        AppMethodBeat.i(24216, false);
        d.d("SwitchView", "onSaveInstanceState");
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.k;
        AppMethodBeat.o(24216);
        return savedState;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        AppMethodBeat.i(24217, false);
        d.d("SwitchView", "onRestoreInstanceState");
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentHeart(savedState.a);
        AppMethodBeat.o(24217);
    }

    /* access modifiers changed from: package-private */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1();
        int a;

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 r2) {
            this(parcel);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            AppMethodBeat.i(24197, false);
            this.a = parcel.readInt();
            AppMethodBeat.o(24197);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            AppMethodBeat.i(24198, false);
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            AppMethodBeat.o(24198);
        }

        /* renamed from: cn.missfresh.module.base.widget.integral.widget.SwitchView$SavedState$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                AppMethodBeat.i(24196, false);
                SavedState a = a(parcel);
                AppMethodBeat.o(24196);
                return a;
            }

            @Override // android.os.Parcelable.Creator
            public /* synthetic */ Object[] newArray(int i) {
                AppMethodBeat.i(24195, false);
                SavedState[] a = a(i);
                AppMethodBeat.o(24195);
                return a;
            }

            public SavedState a(Parcel parcel) {
                AppMethodBeat.i(24194, false);
                SavedState savedState = new SavedState(parcel, null);
                AppMethodBeat.o(24194);
                return savedState;
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
            }
        }

        static {
            AppMethodBeat.i(24199, false);
            AppMethodBeat.o(24199);
        }
    }
}
