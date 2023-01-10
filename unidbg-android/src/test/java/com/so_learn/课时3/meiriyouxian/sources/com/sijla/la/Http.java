package com.sijla.la;

import android.bluetooth.BluetoothHealth;
import android.os.AsyncTask;
import anet.channel.util.HttpConstant;
import com.huawei.hms.framework.common.ContainerUtils;
import com.sijla.g.a.e;
import com.sijla.lj.c;
import com.umeng.message.util.HttpRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class Http {
    private static HashMap<String, String> sHeader;

    public static void setHeader(HashMap<String, String> hashMap) {
        sHeader = hashMap;
    }

    public static HashMap<String, String> getHeader() {
        return sHeader;
    }

    public static a get(String str, c cVar) {
        a aVar = new a(str, "GET", null, null, null, cVar);
        aVar.execute(new Object[0]);
        return aVar;
    }

    public static a get(String str, HashMap<String, String> hashMap, c cVar) {
        a aVar = new a(str, "GET", null, null, hashMap, cVar);
        aVar.execute(new Object[0]);
        return aVar;
    }

    public static a get(String str, String str2, HashMap<String, String> hashMap, c cVar) {
        a aVar = (!str2.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str2)) ? new a(str, "GET", str2, null, hashMap, cVar) : new a(str, "GET", null, str2, hashMap, cVar);
        aVar.execute(new Object[0]);
        return aVar;
    }

    public static a get(String str, String str2, c cVar) {
        a aVar = (!str2.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str2)) ? new a(str, "GET", str2, null, null, cVar) : new a(str, "GET", null, str2, null, cVar);
        aVar.execute(new Object[0]);
        return aVar;
    }

    public static a get(String str, String str2, String str3, c cVar) {
        a aVar = new a(str, "GET", str2, str3, null, cVar);
        aVar.execute(new Object[0]);
        return aVar;
    }

    public static a get(String str, String str2, String str3, HashMap<String, String> hashMap, c cVar) {
        a aVar = new a(str, "GET", str2, str3, hashMap, cVar);
        aVar.execute(new Object[0]);
        return aVar;
    }

    public static a download(String str, String str2, c cVar) {
        a aVar = new a(str, "GET", null, null, null, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a download(String str, String str2, HashMap<String, String> hashMap, c cVar) {
        a aVar = new a(str, "GET", null, null, hashMap, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a download(String str, String str2, String str3, c cVar) {
        a aVar = new a(str, "GET", str3, null, null, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a download(String str, String str2, String str3, HashMap<String, String> hashMap, c cVar) {
        a aVar = new a(str, "GET", str3, null, hashMap, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a post(String str, String str2, c cVar) {
        a aVar = new a(str, "POST", null, null, null, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a post(String str, String str2, HashMap<String, String> hashMap, c cVar) {
        a aVar = new a(str, "POST", null, null, hashMap, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a post(String str, String str2, String str3, c cVar) {
        a aVar = (!str3.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str3)) ? new a(str, "POST", str3, null, null, cVar) : new a(str, "POST", null, str3, null, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a post(String str, String str2, String str3, HashMap<String, String> hashMap, c cVar) {
        a aVar = (!str3.matches("[\\w\\-\\.:]+") || !Charset.isSupported(str3)) ? new a(str, "POST", str3, null, hashMap, cVar) : new a(str, "POST", null, str3, hashMap, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a post(String str, String str2, String str3, String str4, c cVar) {
        a aVar = new a(str, "POST", str3, str4, null, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static a post(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, c cVar) {
        a aVar = new a(str, "POST", str3, str4, hashMap, cVar);
        aVar.execute(str2);
        return aVar;
    }

    public static void uploadFiles(String str, HashMap<String, String> hashMap, c cVar) {
        if (hashMap != null) {
            HashMap hashMap2 = new HashMap();
            for (String str2 : hashMap.keySet()) {
                File file = new File(hashMap.get(str2));
                if (file.exists() && file.isFile()) {
                    hashMap2.put(file.getName(), file);
                }
            }
            if (hashMap2.keySet().size() > 0) {
                com.sijla.g.b.a.a().a(str, new JSONObject(), hashMap2, new AnonymousClass1(cVar));
            }
        }
    }

    /* renamed from: com.sijla.la.Http$1  reason: invalid class name */
    static class AnonymousClass1 implements com.sijla.g.b.b.a {
        final /* synthetic */ c a;

        AnonymousClass1(c cVar) {
            this.a = cVar;
        }

        public com.sijla.g.b.a.a a(int i, InputStream inputStream) {
            String str;
            try {
                str = e.a(inputStream);
            } catch (Exception e) {
                str = e.toString();
            }
            c cVar = this.a;
            if (cVar != null) {
                try {
                    cVar.b(Integer.valueOf(i), str, null, null);
                } catch (Exception unused) {
                }
            }
            return null;
        }
    }

    public static class a extends AsyncTask {
        private String a;
        private c b;
        private byte[] c;
        private String d;
        private String e;
        private HashMap<String, String> f;
        private String g;

        public a(String str, String str2, String str3, String str4, HashMap<String, String> hashMap, c cVar) {
            this.a = str;
            this.g = str2;
            this.e = str3;
            this.d = str4;
            this.f = hashMap;
            this.b = cVar;
        }

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Object doInBackground(Object[] objArr) {
            HttpURLConnection a;
            try {
                a = com.sijla.g.b.a.a().a(this.a);
                a.setConnectTimeout(BluetoothHealth.HEALTH_OPERATION_SUCCESS);
                HttpURLConnection.setFollowRedirects(true);
                a.setDoInput(true);
                a.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
                if (this.d == null) {
                    this.d = "UTF-8";
                }
                a.setRequestProperty(HttpRequest.HEADER_ACCEPT_CHARSET, this.d);
                if (this.e != null) {
                    a.setRequestProperty(HttpConstant.COOKIE, this.e);
                }
                if (Http.sHeader != null) {
                    for (Map.Entry entry : Http.sHeader.entrySet()) {
                        a.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (this.f != null) {
                    for (Map.Entry<String, String> entry2 : this.f.entrySet()) {
                        a.setRequestProperty(entry2.getKey(), entry2.getValue());
                    }
                }
                if (this.g != null) {
                    a.setRequestMethod(this.g);
                }
                if (!"GET".equals(this.g) && objArr.length != 0) {
                    this.c = a(objArr);
                    a.setDoOutput(true);
                    a.setRequestProperty("Content-length", "" + this.c.length);
                }
                a.connect();
            } catch (Throwable th) {
                th.printStackTrace();
                return new Object[]{-1, th.getMessage()};
            }
            if (!"GET".equals(this.g) || objArr.length == 0) {
                if (objArr.length != 0) {
                    a.getOutputStream().write(this.c);
                }
                int responseCode = a.getResponseCode();
                Map<String, List<String>> headerFields = a.getHeaderFields();
                if (responseCode < 200 || responseCode >= 400) {
                    return new Object[]{Integer.valueOf(responseCode), a.getResponseMessage(), null, headerFields};
                }
                a.getContentEncoding();
                List<String> list = headerFields.get(HttpConstant.SET_COOKIE);
                StringBuffer stringBuffer = new StringBuffer();
                if (list != null) {
                    Iterator<String> it2 = list.iterator();
                    while (it2.hasNext()) {
                        stringBuffer.append(it2.next() + ";");
                    }
                }
                InputStream inputStream = a.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, this.d));
                StringBuffer stringBuffer2 = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null || isCancelled()) {
                        break;
                    }
                    stringBuffer2.append(readLine + '\n');
                }
                inputStream.close();
                return new Object[]{Integer.valueOf(responseCode), new String(stringBuffer2), stringBuffer.toString(), headerFields};
            }
            File file = new File((String) objArr[0]);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            b.a(a.getInputStream(), new FileOutputStream(file));
            return new Object[]{Integer.valueOf(a.getResponseCode()), a.getHeaderFields()};
        }

        private byte[] a(Object[] objArr) {
            byte[] bArr = null;
            if (objArr.length != 1) {
                return null;
            }
            Object obj = objArr[0];
            if (obj instanceof String) {
                bArr = ((String) obj).getBytes(this.d);
            } else if (obj.getClass().getComponentType() == Byte.TYPE) {
                bArr = (byte[]) obj;
            } else if (obj instanceof File) {
                bArr = b.a(new FileInputStream((File) obj));
            }
            return obj instanceof File ? a((Map) obj) : bArr;
        }

        private byte[] a(Map map) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : this.f.entrySet()) {
                sb.append(entry.getKey() + ContainerUtils.KEY_VALUE_DELIMITER + entry.getValue() + "&");
            }
            return sb.toString().getBytes(this.d);
        }

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (!isCancelled()) {
                com.sijla.a.a.a(new AnonymousClass1(obj));
            }
        }

        /* renamed from: com.sijla.la.Http$a$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ Object a;

            AnonymousClass1(Object obj) {
                this.a = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (a.this.b != null && this.a != null) {
                        System.currentTimeMillis();
                        a.this.b.b((Object[]) this.a);
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }
}
