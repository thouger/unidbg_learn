package com.sobot.chat.core.http.a;

import android.text.format.DateFormat;
import com.sobot.chat.core.http.d.d;
import com.sobot.chat.core.http.d.e;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PostFormBuilder */
public class c extends b {
    private List<a> e = new ArrayList();
    private d.b f;

    public e a() {
        return new com.sobot.chat.core.http.d.c(this.a, this.b, this.d, this.c, this.e).a(this.f).b();
    }

    public c a(String str, String str2, File file) {
        this.e.add(new a(str, str2, file));
        return this;
    }

    /* compiled from: PostFormBuilder */
    public static class a {
        public String a;
        public String b;
        public File c;

        public a(String str, String str2, File file) {
            this.a = str;
            this.b = str2;
            this.c = file;
        }

        public String toString() {
            return "FileInput{key='" + this.a + DateFormat.QUOTE + ", filename='" + this.b + DateFormat.QUOTE + ", file=" + this.c + '}';
        }
    }

    public c a(String str) {
        this.a = str;
        return this;
    }

    public c a(Object obj) {
        this.b = obj;
        return this;
    }

    public c a(Map<String, String> map) {
        this.d = map;
        return this;
    }

    public c a(String str, String str2) {
        if (this.d == null) {
            this.d = new LinkedHashMap();
        }
        this.d.put(str, str2);
        return this;
    }
}
