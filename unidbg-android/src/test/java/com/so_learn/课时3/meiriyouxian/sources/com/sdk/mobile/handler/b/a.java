package com.sdk.mobile.handler.b;

import android.app.Activity;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.framework.c.f;
import com.sdk.mobile.manager.login.manager.TextViewManager;
import java.util.HashMap;
import java.util.Map;

public class a extends b {
    private static final String a = a.class.getSimpleName();
    private Activity b;
    private HashMap<String, View> c = new HashMap<>();
    private TextViewManager d;

    static {
        AppMethodBeat.i(18242, false);
        AppMethodBeat.o(18242);
    }

    public a(Activity activity, HashMap<String, View> hashMap, TextViewManager textViewManager) {
        super(activity, hashMap);
        AppMethodBeat.i(18230, false);
        this.d = textViewManager;
        this.b = activity;
        this.c = hashMap;
        AppMethodBeat.o(18230);
    }

    private <T> void d(Map<T, Integer> map) {
        int a2;
        AppMethodBeat.i(18236, false);
        if (map != null) {
            try {
                for (Map.Entry<T, Integer> entry : map.entrySet()) {
                    T key = entry.getKey();
                    Integer value = entry.getValue();
                    if (!(key == null || value == null)) {
                        if (key instanceof String) {
                            if (c.b(key).booleanValue()) {
                                View view = this.c.get(key);
                                if (view == null && (a2 = com.sdk.base.framework.f.e.a.a(this.b, "id", key)) != 0) {
                                    view = this.b.findViewById(a2);
                                }
                                if (view instanceof TextView) {
                                    ((TextView) view).setTextSize((float) value.intValue());
                                }
                            }
                        } else if (key instanceof TextView) {
                            key.setTextSize((float) value.intValue());
                        }
                    }
                }
            } catch (Throwable th) {
                String str = a;
                c.b(str, "\u300asetViewsTextSize\u300b\u65b9\u6cd5\u5f02\u5e38\uff1a\n" + th, Boolean.valueOf(f.a));
                AppMethodBeat.o(18236);
                return;
            }
        }
        AppMethodBeat.o(18236);
    }

    private <T> void e(Map<T, Boolean> map) {
        TextPaint paint;
        boolean booleanValue;
        int a2;
        AppMethodBeat.i(18237, false);
        if (map != null) {
            try {
                for (Map.Entry<T, Boolean> entry : map.entrySet()) {
                    T key = entry.getKey();
                    Boolean value = entry.getValue();
                    if (!(key == null || value == null)) {
                        if (key instanceof String) {
                            if (c.b(key).booleanValue()) {
                                View view = this.c.get(key);
                                if (view == null && (a2 = com.sdk.base.framework.f.e.a.a(this.b, "id", key)) != 0) {
                                    view = this.b.findViewById(a2);
                                }
                                if (view != null) {
                                    paint = ((TextView) view).getPaint();
                                    booleanValue = value.booleanValue();
                                }
                            }
                        } else if (key instanceof View) {
                            paint = key.getPaint();
                            booleanValue = value.booleanValue();
                        }
                        paint.setFakeBoldText(booleanValue);
                    }
                }
            } catch (Throwable th) {
                String str = a;
                c.b(str, "\u300asetViewsBold\u300b\u65b9\u6cd5\u5f02\u5e38\uff1a\n" + th, Boolean.valueOf(f.a));
                AppMethodBeat.o(18237);
                return;
            }
        }
        AppMethodBeat.o(18237);
    }

    private <T> void f(Map<T, Integer> map) {
        int a2;
        AppMethodBeat.i(18239, false);
        if (map != null) {
            try {
                for (Map.Entry<T, Integer> entry : map.entrySet()) {
                    T key = entry.getKey();
                    Integer value = entry.getValue();
                    if (!(key == null || value == null)) {
                        if (key instanceof String) {
                            if (c.b(key).booleanValue()) {
                                View view = this.c.get(key);
                                if (view == null && (a2 = com.sdk.base.framework.f.e.a.a(this.b, "id", key)) != 0) {
                                    view = this.b.findViewById(a2);
                                }
                                if (view instanceof TextView) {
                                    ((TextView) view).setTextColor(value.intValue());
                                }
                            }
                        } else if (key instanceof TextView) {
                            key.setTextColor(value.intValue());
                        }
                    }
                }
            } catch (Throwable th) {
                String str = a;
                c.b(str, "\u300asetViewsTextColor\u300b\u65b9\u6cd5\u5f02\u5e38\uff1a\n" + th, Boolean.valueOf(f.a));
                AppMethodBeat.o(18239);
                return;
            }
        }
        AppMethodBeat.o(18239);
    }

    public final void a() {
        int a2;
        AppMethodBeat.i(18234, false);
        TextViewManager textViewManager = this.d;
        if (textViewManager != null) {
            a.super.b(textViewManager.getViewsBackgroundResource());
            a.super.c(this.d.getViewsOffsetY());
            a.super.a(this.d.getViewsVisibility());
            Map<String, String> viewsText = this.d.getViewsText();
            if (viewsText != null) {
                try {
                    for (Map.Entry<String, String> entry : viewsText.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (!(key == null || value == null)) {
                            if (key instanceof String) {
                                if (c.b(key).booleanValue()) {
                                    View view = this.c.get(key);
                                    if (view == null && (a2 = com.sdk.base.framework.f.e.a.a(this.b, "id", key)) != 0) {
                                        view = this.b.findViewById(a2);
                                    }
                                    if (view instanceof TextView) {
                                        ((TextView) view).setText(value);
                                    }
                                }
                            } else if (key instanceof TextView) {
                                ((TextView) key).setText(value);
                            }
                        }
                    }
                } catch (Throwable th) {
                    String str = a;
                    c.b(str, "\u300asetViewsText\u300b\u65b9\u6cd5\u5f02\u5e38\uff1a\n" + th, Boolean.valueOf(f.a));
                }
            }
            d(this.d.getViewsTextSize());
            f(this.d.getViewsTextColor());
            e(this.d.getViewsBold());
        }
        AppMethodBeat.o(18234);
    }
}
