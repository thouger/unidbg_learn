package com.umeng.vt.diff;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.vshelper.PageNameMonitor;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.tunnel.UMChannelAgent;
import com.umeng.vt.diff.util.ClassLoadUtil;
import java.lang.reflect.Method;
import java.util.Map;

public class Event {
    private static final int MAX_PROPERTY_LENGTH = 128;

    public static void init(Context context, String str, String str2, int i, String str3) {
    }

    public static void openDebug(String str) {
    }

    public static void commit(Context context, View view, String str, Map<String, Object> map, Boolean bool) {
        if (!bool.booleanValue()) {
            if (view != null) {
                String textPropertyFromView = textPropertyFromView(view);
                if (!TextUtils.isEmpty(textPropertyFromView)) {
                    map.put(V.VISUAL_TRACK_TEXT, textPropertyFromView);
                }
                map.put(V.VISUAL_TRACK_PG, view.getContext().getClass().getName());
            } else {
                map.put(V.VISUAL_TRACK_PG, context.getClass().getName());
            }
            map.put(V.VISUAL_TRACK_STYLE, 1);
            map.put(V.VISUAL_TRACK_UAPP_PG, getPageName());
            UMRTLog.e(UMRTLog.RTLOG_TAG, "release:\u4e8b\u4ef6\u540d: " + str);
            if (map.containsKey(V.VISUAL_TRACK_TEXT)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "release:\u4e8b\u4ef6\u540d: " + str + "; \u53c2\u6570\uff1a" + ((String) map.get(V.VISUAL_TRACK_TEXT)));
            }
            if (map.containsKey(V.VISUAL_TRACK_PG)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "release:\u4e8b\u4ef6\u540d: " + str + "; \u9875\u9762\uff1a" + ((String) map.get(V.VISUAL_TRACK_PG)));
            }
            if (map.containsKey(V.VISUAL_TRACK_UAPP_PG)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "release:\u4e8b\u4ef6\u540d: " + str + "; UApp\u9875\u9762\u8def\u5f84\uff1a" + ((String) map.get(V.VISUAL_TRACK_UAPP_PG)));
            }
            MobclickAgent.onEventObject(context, str, map);
        } else if (UMChannelAgent.init()) {
            if (view != null) {
                String textPropertyFromView2 = textPropertyFromView(view);
                if (!TextUtils.isEmpty(textPropertyFromView2)) {
                    map.put(V.VISUAL_TRACK_TEXT, textPropertyFromView2);
                }
                map.put(V.VISUAL_TRACK_PG, view.getContext().getClass().getName());
            } else {
                map.put(V.VISUAL_TRACK_PG, context.getClass().getName());
            }
            map.put(V.VISUAL_TRACK_STYLE, 1);
            map.put(V.VISUAL_TRACK_UAPP_PG, getPageName());
            UMRTLog.e(UMRTLog.RTLOG_TAG, "config:\u4e8b\u4ef6\u540d: " + str);
            if (map.containsKey(V.VISUAL_TRACK_TEXT)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "config:\u4e8b\u4ef6\u540d: " + str + "; \u53c2\u6570\uff1a" + ((String) map.get(V.VISUAL_TRACK_TEXT)));
            }
            if (map.containsKey(V.VISUAL_TRACK_PG)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "config:\u4e8b\u4ef6\u540d: " + str + "; \u9875\u9762\uff1a" + ((String) map.get(V.VISUAL_TRACK_PG)));
            }
            if (map.containsKey(V.VISUAL_TRACK_UAPP_PG)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "config:\u4e8b\u4ef6\u540d: " + str + "; UApp\u9875\u9762\u8def\u5f84\uff1a" + ((String) map.get(V.VISUAL_TRACK_UAPP_PG)));
            }
            UMChannelAgent.onDebugEvent(context, str, map);
        }
    }

    private static void reflectTrack(Context context, String str, Map<String, Object> map) {
        Method method;
        try {
            Class<?> findClass = ClassLoadUtil.findClass("com.umeng.analytics.MobclickAgent");
            if (findClass != null && (method = findClass.getMethod("onEvent", Context.class, String.class, Map.class)) != null) {
                method.invoke(null, context, str, map);
            }
        } catch (Exception unused) {
        }
    }

    private static String textPropertyFromView(View view) {
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text != null) {
                return text.toString();
            }
            return null;
        } else if (!(view instanceof ViewGroup)) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            boolean z = false;
            for (int i = 0; i < childCount && sb.length() < 128; i++) {
                String textPropertyFromView = textPropertyFromView(viewGroup.getChildAt(i));
                if (textPropertyFromView != null && textPropertyFromView.length() > 0) {
                    if (z) {
                        sb.append(", ");
                    }
                    sb.append(textPropertyFromView);
                    z = true;
                }
            }
            if (sb.length() > 128) {
                return sb.substring(0, 128);
            }
            if (z) {
                return sb.toString();
            }
            return null;
        }
    }

    public static String getPageName(View view) {
        String pageName = getPageName();
        if (pageName != null && !pageName.equals("") && !pageName.equals("VT")) {
            return pageName;
        }
        String activityName = getActivityName(view);
        if (activityName == null || activityName.equals("")) {
            return "VT";
        }
        return activityName;
    }

    public static String getActivityName(View view) {
        Context context;
        if (view == null || (context = view.getContext()) == null) {
            return null;
        }
        if (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return context.getClass().getCanonicalName();
        }
        return null;
    }

    public static String getPageName() {
        String currenPageName = PageNameMonitor.getInstance().getCurrenPageName();
        return TextUtils.isEmpty(currenPageName) ? "" : currenPageName;
    }
}
