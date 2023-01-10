package com.alipay.b.a.a.d;

import com.alipay.b.a.a.a.a;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class d {
    private static String a = "";
    private static String b = "";
    private static String c = "";

    public static synchronized void a(String str) {
        synchronized (d.class) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            a(arrayList);
        }
    }

    public static synchronized void a(String str, String str2, String str3) {
        synchronized (d.class) {
            a = str;
            b = str2;
            c = str3;
        }
    }

    public static synchronized void a(Throwable th) {
        String str;
        synchronized (d.class) {
            ArrayList arrayList = new ArrayList();
            if (th != null) {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                str = stringWriter.toString();
            } else {
                str = "";
            }
            arrayList.add(str);
            a(arrayList);
        }
    }

    private static synchronized void a(List<String> list) {
        synchronized (d.class) {
            if (!a.a(b)) {
                if (!a.a(c)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(c);
                    Iterator<String> it2 = list.iterator();
                    while (it2.hasNext()) {
                        stringBuffer.append(", " + it2.next());
                    }
                    stringBuffer.append("\n");
                    try {
                        File file = new File(a);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File file2 = new File(a, b);
                        if (!file2.exists()) {
                            file2.createNewFile();
                        }
                        FileWriter fileWriter = ((long) stringBuffer.length()) + file2.length() <= 51200 ? new FileWriter(file2, true) : new FileWriter(file2);
                        fileWriter.write(stringBuffer.toString());
                        fileWriter.flush();
                        fileWriter.close();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }
}
