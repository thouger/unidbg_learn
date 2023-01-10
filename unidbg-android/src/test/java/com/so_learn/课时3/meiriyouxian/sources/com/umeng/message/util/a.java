package com.umeng.message.util;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* compiled from: BuildProperties */
public class a {
    private Properties a = new Properties();

    private a() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
        this.a.load(fileInputStream);
        fileInputStream.close();
    }

    public boolean a(Object obj) {
        return this.a.containsKey(obj);
    }

    public boolean b(Object obj) {
        return this.a.containsValue(obj);
    }

    public Set<Map.Entry<Object, Object>> a() {
        return this.a.entrySet();
    }

    public String a(String str) {
        return this.a.getProperty(str);
    }

    public String a(String str, String str2) {
        return this.a.getProperty(str, str2);
    }

    public boolean b() {
        return this.a.isEmpty();
    }

    public Enumeration<Object> c() {
        return this.a.keys();
    }

    public Set<Object> d() {
        return this.a.keySet();
    }

    public int e() {
        return this.a.size();
    }

    public Collection<Object> f() {
        return this.a.values();
    }

    public static a g() throws IOException {
        return new a();
    }
}
