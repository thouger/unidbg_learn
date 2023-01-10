package cn.missfresh.module.base.support.view.snackbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.support.view.snackbar.BaseTransientBar;
import cn.missfresh.module.base.support.view.snackbar.CustomContentLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public final class MFSnackBar extends BaseTransientBar<MFSnackBar> {

    public static class a extends BaseTransientBar.a<MFSnackBar> {
        public void a(MFSnackBar mFSnackBar) {
        }

        public void a(MFSnackBar mFSnackBar, int i) {
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.BaseTransientBar.a
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(22874, false);
            a((MFSnackBar) obj);
            AppMethodBeat.o(22874);
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.BaseTransientBar.a
        public /* synthetic */ void a(Object obj, int i) {
            AppMethodBeat.i(22875, false);
            a((MFSnackBar) obj, i);
            AppMethodBeat.o(22875);
        }
    }

    private MFSnackBar(ViewGroup viewGroup, View view, BaseTransientBar.c cVar, boolean z) {
        super(viewGroup, view, cVar, z);
    }

    public static MFSnackBar a(View view, View view2, int i, boolean z) {
        AppMethodBeat.i(22878, false);
        ViewGroup a2 = a(view);
        if (a2 != null) {
            CustomContentLayout customContentLayout = (CustomContentLayout) LayoutInflater.from(a2.getContext()).inflate(R.layout.layout_custom_snack_bar, a2, false);
            customContentLayout.addView(view2);
            MFSnackBar mFSnackBar = new MFSnackBar(a2, customContentLayout, customContentLayout, z);
            mFSnackBar.a(i);
            customContentLayout.setFlingCallback(new AnonymousClass1(mFSnackBar));
            AppMethodBeat.o(22878);
            return mFSnackBar;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
        AppMethodBeat.o(22878);
        throw illegalArgumentException;
    }

    /* renamed from: cn.missfresh.module.base.support.view.snackbar.MFSnackBar$1  reason: invalid class name */
    static class AnonymousClass1 implements CustomContentLayout.a {
        final /* synthetic */ MFSnackBar a;

        AnonymousClass1(MFSnackBar mFSnackBar) {
            this.a = mFSnackBar;
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.CustomContentLayout.a
        public void a() {
            AppMethodBeat.i(22871, false);
            this.a.b(0);
            AppMethodBeat.o(22871);
        }

        @Override // cn.missfresh.module.base.support.view.snackbar.CustomContentLayout.a
        public void b() {
            AppMethodBeat.i(22872, false);
            this.a.b(1);
            AppMethodBeat.o(22872);
        }
    }

    public static View a(View view, int i) {
        View view2;
        AppMethodBeat.i(22879, false);
        ViewGroup a2 = a(view);
        LayoutInflater from = LayoutInflater.from(a2.getContext());
        try {
            view2 = from.inflate(i, a2, false);
        } catch (Exception e) {
            e.printStackTrace();
            view2 = null;
        }
        if (view2 == null) {
            view2 = from.inflate(R.layout.layout_custom, a2, false);
        }
        AppMethodBeat.o(22879);
        return view2;
    }

    private static ViewGroup a(View view) {
        AppMethodBeat.i(22880, false);
        ViewGroup viewGroup = null;
        while (!(view instanceof CoordinatorLayout)) {
            if (view instanceof FrameLayout) {
                if (view.getId() == 16908290) {
                    ViewGroup viewGroup2 = (ViewGroup) view;
                    AppMethodBeat.o(22880);
                    return viewGroup2;
                }
                viewGroup = (ViewGroup) view;
            }
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    view = (View) parent;
                    continue;
                } else {
                    view = null;
                    continue;
                }
            }
            if (view == null) {
                AppMethodBeat.o(22880);
                return viewGroup;
            }
        }
        ViewGroup viewGroup3 = (ViewGroup) view;
        AppMethodBeat.o(22880);
        return viewGroup3;
    }

    public static final class SnackBarLayout extends BaseTransientBar.SnackbarBaseLayout {
        public SnackBarLayout(Context context) {
            super(context);
        }

        public SnackBarLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int i, int i2) {
            AppMethodBeat.i(22876, false);
            super.onMeasure(i, i2);
            int childCount = getChildCount();
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getLayoutParams().width == -1) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), 1073741824));
                }
            }
            AppMethodBeat.o(22876);
        }
    }
}
