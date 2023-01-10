package com.cmic.sso.wy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

/* compiled from: SharedPreferencesUtil */
public class q {
    private static Context a;

    public static void a(Context context) {
        a = context.getApplicationContext();
    }

    public static int a(String str, int i) {
        return a.getSharedPreferences("ssoconfigs", 0).getInt(i.a(str), i);
    }

    public static void a(String str, long j) {
        SharedPreferences sharedPreferences = a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().putLong(i.a(str), j).apply();
    }

    public static long b(String str, long j) {
        return a.getSharedPreferences("ssoconfigs", 0).getLong(i.a(str), j);
    }

    public static void a(String str, String str2) {
        SharedPreferences sharedPreferences = a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().putString(i.a(str), str2).apply();
    }

    public static String b(String str, String str2) {
        return a.getSharedPreferences("ssoconfigs", 0).getString(i.a(str), str2);
    }

    public static void a(String str, boolean z) {
        SharedPreferences sharedPreferences = a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().putBoolean(i.a(str), z).apply();
    }

    static void a(String str) {
        SharedPreferences sharedPreferences = a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().remove(i.a(str)).apply();
    }

    public static SharedPreferences.Editor a() {
        return new a();
    }

    /* compiled from: SharedPreferencesUtil */
    private static class a implements SharedPreferences.Editor {
        private SharedPreferences.Editor a;

        private a() {
            this.a = q.a.getSharedPreferences("ssoconfigs", 0).edit();
        }

        private String a(String str) {
            return i.a(str);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            return this.a.putString(a(str), str2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            return this.a.putStringSet(a(str), set);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            return this.a.putInt(a(str), i);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            return this.a.putLong(a(str), j);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            return this.a.putFloat(a(str), f);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            return this.a.putBoolean(a(str), z);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            return this.a.remove(a(str));
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            return this.a.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return this.a.commit();
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            this.a.apply();
        }
    }
}
