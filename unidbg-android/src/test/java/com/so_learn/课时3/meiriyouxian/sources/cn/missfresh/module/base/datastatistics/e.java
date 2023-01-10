package cn.missfresh.module.base.datastatistics;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.datastatistics.bean.StatisticsParams;
import cn.missfresh.sherlock.Sherlock;
import cn.missfresh.sherlock.SherlockViewClickListener;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Constructor;

/* compiled from: StatisticsFactory */
public class e implements LayoutInflater.Factory2 {
    private final AppCompatDelegate a;
    private final Class<?>[] b = {Context.class, AttributeSet.class};
    private final String[] c = {"android.widget.", "android.view.", "android.webkit."};
    private final ArrayMap<String, Constructor<? extends View>> d = new ArrayMap<>();
    private final Object[] e = new Object[2];

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return null;
    }

    /* compiled from: StatisticsFactory */
    /* renamed from: cn.missfresh.module.base.datastatistics.e$1  reason: invalid class name */
    static class AnonymousClass1 implements SherlockViewClickListener {
        AnonymousClass1() {
        }

        @Override // cn.missfresh.sherlock.SherlockViewClickListener
        public void callback(View view) {
            AppMethodBeat.i(12452, false);
            StatisticsManager.onEventToMRYX(view);
            AppMethodBeat.o(12452);
        }
    }

    static {
        AppMethodBeat.i(12470, false);
        Sherlock.registerViewClickListener(new AnonymousClass1());
        AppMethodBeat.o(12470);
    }

    public e(AppCompatDelegate appCompatDelegate) {
        AppMethodBeat.i(12460, false);
        this.a = appCompatDelegate;
        AppMethodBeat.o(12460);
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        AppMethodBeat.i(12462, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.statistics_param);
        int integer = obtainStyledAttributes.getInteger(R.styleable.statistics_param_event_type, 0);
        String string = obtainStyledAttributes.getString(R.styleable.statistics_param_event_id);
        String string2 = obtainStyledAttributes.getString(R.styleable.statistics_param_event_label);
        String string3 = obtainStyledAttributes.getString(R.styleable.statistics_param_service_path);
        String string4 = obtainStyledAttributes.getString(R.styleable.statistics_param_from_value);
        String string5 = obtainStyledAttributes.getString(R.styleable.statistics_param_position_key);
        String string6 = obtainStyledAttributes.getString(R.styleable.statistics_param_event_module);
        int i = integer & 1;
        if (i != 0) {
            if (str.equals("TextView")) {
                str = "cn.missfresh.module.base.datastatistics.view.STextView";
            } else if (str.equals("ImageView")) {
                str = "cn.missfresh.module.base.datastatistics.view.SImageView";
            }
        }
        View createView = this.a.createView((View) null, str, context, attributeSet);
        if (createView == null) {
            createView = a(context, str, attributeSet);
        }
        if (createView != null) {
            if (!((integer & 2) == 0 && i == 0)) {
                createView.setTag(-512, new StatisticsParams(integer, string, string2, string3, string4, string5, string6));
            }
            if ((integer & 4) != 0) {
                ((RecyclerView) createView).addOnScrollListener(new AnonymousClass2());
            }
            obtainStyledAttributes.recycle();
        }
        AppMethodBeat.o(12462);
        return createView;
    }

    /* compiled from: StatisticsFactory */
    /* renamed from: cn.missfresh.module.base.datastatistics.e$2  reason: invalid class name */
    class AnonymousClass2 extends RecyclerView.OnScrollListener {
        AnonymousClass2() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            AppMethodBeat.i(12456, false);
            if (i == 0) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    linearLayoutManager.findFirstVisibleItemPosition();
                    linearLayoutManager.findLastVisibleItemPosition();
                }
            }
            AppMethodBeat.o(12456);
        }
    }

    private View a(Context context, String str, AttributeSet attributeSet) {
        int i = 12465;
        char c = 0;
        AppMethodBeat.i(12465, false);
        Object obj = null;
        String attributeValue = "view".equals(str) ? attributeSet.getAttributeValue(null, "class") : str;
        char c2 = 1;
        try {
            this.e[0] = context;
            this.e[1] = attributeSet;
            if (-1 == str.indexOf(46)) {
                for (String str2 : this.c) {
                    View a = a(context, attributeValue, str2);
                    if (a != null) {
                        return a;
                    }
                }
                Object[] objArr = this.e;
                objArr[0] = null;
                objArr[1] = null;
                AppMethodBeat.o(12465);
                return null;
            }
            View a2 = a(context, attributeValue, (String) null);
            Object[] objArr2 = this.e;
            objArr2[0] = null;
            objArr2[1] = null;
            AppMethodBeat.o(12465);
            return a2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Object[] objArr3 = this.e;
            objArr3[c] = obj;
            objArr3[c2] = obj;
            AppMethodBeat.o(i);
        }
    }

    private View a(Context context, String str, String str2) {
        String str3;
        AppMethodBeat.i(12467, false);
        Constructor constructor = (Constructor) this.d.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception e) {
                    e.printStackTrace();
                    AppMethodBeat.o(12467);
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(this.b);
            this.d.put(str, constructor);
        }
        constructor.setAccessible(true);
        View view = (View) constructor.newInstance(this.e);
        AppMethodBeat.o(12467);
        return view;
    }
}
