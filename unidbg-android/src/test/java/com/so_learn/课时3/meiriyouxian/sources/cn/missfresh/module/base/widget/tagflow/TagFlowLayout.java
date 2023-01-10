package cn.missfresh.module.base.widget.tagflow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.FlowLayout;
import cn.missfresh.module.base.widget.tagflow.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import java.util.HashSet;
import java.util.Set;

public class TagFlowLayout extends FlowLayout implements a.AbstractC0036a {
    private a d;
    private boolean e;
    private int f;
    private MotionEvent g;
    private Set<Integer> h;
    private a i;

    public interface a {
        void a(int i);

        boolean a(View view, int i, FlowLayout flowLayout);
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(24369, false);
        this.e = true;
        this.f = -1;
        this.h = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.e = obtainStyledAttributes.getBoolean(R.styleable.TagFlowLayout_auto_select_effect, true);
        this.f = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_max_select, -1);
        obtainStyledAttributes.recycle();
        if (this.e) {
            setClickable(true);
        }
        AppMethodBeat.o(24369);
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    public static int a(Context context, float f) {
        AppMethodBeat.i(24370, false);
        int i = (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(24370);
        return i;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.widget.FlowLayout, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(24371, false);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt instanceof TagView) {
                TagView tagView = (TagView) childAt;
                if (tagView.getVisibility() != 8 && tagView.getTagView().getVisibility() == 8) {
                    tagView.setVisibility(8);
                }
            }
        }
        super.onMeasure(i, i2);
        AppMethodBeat.o(24371);
    }

    public void setOnTagClickListener(a aVar) {
        AppMethodBeat.i(24372, false);
        this.i = aVar;
        if (aVar != null) {
            setClickable(true);
        }
        AppMethodBeat.o(24372);
    }

    public void setAdapter(a aVar) {
        AppMethodBeat.i(24373, false);
        this.d = aVar;
        this.d.a(this);
        this.h.clear();
        b();
        AppMethodBeat.o(24373);
    }

    public a getmTagAdapter() {
        return this.d;
    }

    private void b() {
        AppMethodBeat.i(24374, false);
        removeAllViews();
        a aVar = this.d;
        HashSet<Integer> a2 = aVar.a();
        for (int i = 0; i < aVar.b(); i++) {
            View a3 = aVar.a(this, i, aVar.a(i));
            TagView tagView = new TagView(getContext());
            a3.setDuplicateParentStateEnabled(true);
            if (a3.getLayoutParams() != null) {
                tagView.setLayoutParams(a3.getLayoutParams());
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
                marginLayoutParams.setMargins(a(getContext(), 5.0f), a(getContext(), 5.0f), a(getContext(), 5.0f), a(getContext(), 5.0f));
                tagView.setLayoutParams(marginLayoutParams);
            }
            tagView.addView(a3);
            addView(tagView);
            if (this.d.a(i, aVar.a(i))) {
                this.h.add(Integer.valueOf(i));
                tagView.setChecked(true);
            }
        }
        this.h.addAll(a2);
        AppMethodBeat.o(24374);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(24375, false);
        if (motionEvent.getAction() == 1) {
            this.g = MotionEvent.obtain(motionEvent);
        }
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        AppMethodBeat.o(24375);
        return onTouchEvent;
    }

    @Override // android.view.View
    public boolean performClick() {
        AppMethodBeat.i(24376, false);
        MotionEvent motionEvent = this.g;
        if (motionEvent == null) {
            boolean performClick = super.performClick();
            AppMethodBeat.o(24376);
            return performClick;
        }
        this.g = null;
        TagView a2 = a((int) motionEvent.getX(), (int) this.g.getY());
        int a3 = a(a2);
        if (!(a2 == null || this.i == null)) {
            if (a2.isChecked()) {
                a(a2, a3);
                this.i.a(a2.getTagView(), a3, this);
            } else {
                int size = getSelectedList().size();
                int i = this.f;
                if (size < i) {
                    a(a2, a3);
                    this.i.a(a2.getTagView(), a3, this);
                } else {
                    this.i.a(i);
                }
            }
        }
        AppMethodBeat.o(24376);
        return true;
    }

    public Set<Integer> getSelectedList() {
        AppMethodBeat.i(24377, false);
        HashSet hashSet = new HashSet(this.h);
        AppMethodBeat.o(24377);
        return hashSet;
    }

    private void a(TagView tagView, int i) {
        AppMethodBeat.i(24378, false);
        if (this.e) {
            if (tagView.isChecked()) {
                tagView.setChecked(false);
                this.h.remove(Integer.valueOf(i));
            } else if (this.f == 1 && this.h.size() == 1) {
                Integer next = this.h.iterator().next();
                ((TagView) getChildAt(next.intValue())).setChecked(false);
                tagView.setChecked(true);
                this.h.remove(next);
                this.h.add(Integer.valueOf(i));
            } else if (this.f <= 0 || this.h.size() < this.f) {
                tagView.setChecked(true);
                this.h.add(Integer.valueOf(i));
            } else {
                AppMethodBeat.o(24378);
                return;
            }
        }
        AppMethodBeat.o(24378);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        AppMethodBeat.i(24379, false);
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_default", super.onSaveInstanceState());
        String str = "";
        if (this.h.size() > 0) {
            for (Integer num : this.h) {
                str = str + num.intValue() + HiAnalyticsConstant.REPORT_VAL_SEPARATOR;
            }
            str = str.substring(0, str.length() - 1);
        }
        bundle.putString("key_choose_pos", str);
        AppMethodBeat.o(24379);
        return bundle;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        AppMethodBeat.i(24380, false);
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            String string = bundle.getString("key_choose_pos");
            if (!TextUtils.isEmpty(string)) {
                for (String str : string.split("\\|")) {
                    int parseInt = Integer.parseInt(str);
                    this.h.add(Integer.valueOf(parseInt));
                    TagView tagView = (TagView) getChildAt(parseInt);
                    if (tagView != null) {
                        tagView.setChecked(true);
                    }
                }
            }
            super.onRestoreInstanceState(bundle.getParcelable("key_default"));
            AppMethodBeat.o(24380);
            return;
        }
        super.onRestoreInstanceState(parcelable);
        AppMethodBeat.o(24380);
    }

    private int a(View view) {
        AppMethodBeat.i(24381, false);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i) == view) {
                AppMethodBeat.o(24381);
                return i;
            }
        }
        AppMethodBeat.o(24381);
        return -1;
    }

    private TagView a(int i, int i2) {
        AppMethodBeat.i(24382, false);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            TagView tagView = (TagView) getChildAt(i3);
            if (tagView.getVisibility() != 8) {
                Rect rect = new Rect();
                tagView.getHitRect(rect);
                if (rect.contains(i, i2)) {
                    AppMethodBeat.o(24382);
                    return tagView;
                }
            }
        }
        AppMethodBeat.o(24382);
        return null;
    }

    @Override // cn.missfresh.module.base.widget.tagflow.a.AbstractC0036a
    public void a() {
        AppMethodBeat.i(24383, false);
        this.h.clear();
        b();
        AppMethodBeat.o(24383);
    }
}
