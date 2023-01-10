package com.ta.utdid2.b.a;

import com.ta.utdid2.b.a.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

public class d {
    private static final Object b = new Object();
    private File a;

    /* renamed from: a  reason: collision with other field name */
    private final Object f331a = new Object();

    /* renamed from: a  reason: collision with other field name */
    private HashMap<File, a> f332a = new HashMap<>();

    public d(String str) {
        if (str == null || str.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.a = new File(str);
    }

    private File a(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    private File a() {
        File file;
        synchronized (this.f331a) {
            file = this.a;
        }
        return file;
    }

    private File b(String str) {
        File a2 = a();
        return a(a2, str + ".xml");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004d, code lost:
        if (r0 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0075, code lost:
        if (r3 != null) goto L_0x005f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x006a A[SYNTHETIC, Splitter:B:44:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ta.utdid2.b.a.b a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.io.File r5 = r4.b(r5)
            java.lang.Object r0 = com.ta.utdid2.b.a.d.b
            monitor-enter(r0)
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r1 = r4.f332a     // Catch:{ all -> 0x00a0 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x00a0 }
            com.ta.utdid2.b.a.d$a r1 = (com.ta.utdid2.b.a.d.a) r1     // Catch:{ all -> 0x00a0 }
            if (r1 == 0) goto L_0x0019
            boolean r2 = r1.c()     // Catch:{ all -> 0x00a0 }
            if (r2 != 0) goto L_0x0019
            monitor-exit(r0)     // Catch:{ all -> 0x00a0 }
            return r1
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x00a0 }
            java.io.File r0 = a(r5)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x002a
            r5.delete()
            r0.renameTo(r5)
        L_0x002a:
            boolean r0 = r5.exists()
            r2 = 0
            if (r0 == 0) goto L_0x007d
            boolean r0 = r5.canRead()
            if (r0 == 0) goto L_0x007d
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ XmlPullParserException -> 0x0050, Exception -> 0x004c }
            r0.<init>(r5)     // Catch:{ XmlPullParserException -> 0x0050, Exception -> 0x004c }
            java.util.HashMap r2 = com.ta.utdid2.b.a.e.a(r0)     // Catch:{ XmlPullParserException -> 0x0051, Exception -> 0x004d, all -> 0x0047 }
            r0.close()     // Catch:{ XmlPullParserException -> 0x0051, Exception -> 0x004d, all -> 0x0047 }
        L_0x0043:
            r0.close()     // Catch:{ all -> 0x007d }
            goto L_0x007d
        L_0x0047:
            r5 = move-exception
            r2 = r0
            goto L_0x006e
        L_0x004a:
            r5 = move-exception
            goto L_0x006e
        L_0x004c:
            r0 = r2
        L_0x004d:
            if (r0 == 0) goto L_0x007d
            goto L_0x0043
        L_0x0050:
            r0 = r2
        L_0x0051:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0074, all -> 0x0066 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0074, all -> 0x0066 }
            int r0 = r3.available()     // Catch:{ Exception -> 0x0075, all -> 0x0063 }
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0075, all -> 0x0063 }
            r3.read(r0)     // Catch:{ Exception -> 0x0075, all -> 0x0063 }
        L_0x005f:
            r3.close()     // Catch:{ all -> 0x0078 }
            goto L_0x0078
        L_0x0063:
            r5 = move-exception
            r2 = r3
            goto L_0x0068
        L_0x0066:
            r5 = move-exception
            r2 = r0
        L_0x0068:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ all -> 0x006d }
        L_0x006d:
            throw r5     // Catch:{ all -> 0x004a }
        L_0x006e:
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch:{ all -> 0x0073 }
        L_0x0073:
            throw r5
        L_0x0074:
            r3 = r0
        L_0x0075:
            if (r3 == 0) goto L_0x0078
            goto L_0x005f
        L_0x0078:
            if (r3 == 0) goto L_0x007d
            r3.close()
        L_0x007d:
            java.lang.Object r3 = com.ta.utdid2.b.a.d.b
            monitor-enter(r3)
            if (r1 == 0) goto L_0x0086
            r1.a(r2)     // Catch:{ all -> 0x009d }
            goto L_0x009b
        L_0x0086:
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r0 = r4.f332a     // Catch:{ all -> 0x009d }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x009d }
            r1 = r0
            com.ta.utdid2.b.a.d$a r1 = (com.ta.utdid2.b.a.d.a) r1     // Catch:{ all -> 0x009d }
            if (r1 != 0) goto L_0x009b
            com.ta.utdid2.b.a.d$a r1 = new com.ta.utdid2.b.a.d$a     // Catch:{ all -> 0x009d }
            r1.<init>(r5, r6, r2)     // Catch:{ all -> 0x009d }
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r6 = r4.f332a     // Catch:{ all -> 0x009d }
            r6.put(r5, r1)     // Catch:{ all -> 0x009d }
        L_0x009b:
            monitor-exit(r3)     // Catch:{ all -> 0x009d }
            return r1
        L_0x009d:
            r5 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x009d }
            throw r5
        L_0x00a0:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.d.a(java.lang.String, int):com.ta.utdid2.b.a.b");
    }

    /* access modifiers changed from: private */
    public static File a(File file) {
        return new File(file.getPath() + ".bak");
    }

    private static final class a implements b {
        private static final Object c = new Object();
        private Map a;

        /* renamed from: a  reason: collision with other field name */
        private WeakHashMap<b.AbstractC0152b, Object> f333a;
        private final File b;

        /* renamed from: c  reason: collision with other field name */
        private final int f334c;

        /* renamed from: c  reason: collision with other field name */
        private final File f335c;
        private boolean j = false;

        a(File file, int i, Map map) {
            this.b = file;
            this.f335c = d.a(file);
            this.f334c = i;
            this.a = map == null ? new HashMap() : map;
            this.f333a = new WeakHashMap<>();
        }

        @Override // com.ta.utdid2.b.a.b
        /* renamed from: a  reason: collision with other method in class */
        public boolean mo4075a() {
            File file = this.b;
            return file != null && new File(file.getAbsolutePath()).exists();
        }

        public void a(boolean z) {
            synchronized (this) {
                this.j = z;
            }
        }

        public boolean c() {
            boolean z;
            synchronized (this) {
                z = this.j;
            }
            return z;
        }

        public void a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.a = map;
                }
            }
        }

        @Override // com.ta.utdid2.b.a.b
        public Map<String, ?> getAll() {
            HashMap hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.a);
            }
            return hashMap;
        }

        @Override // com.ta.utdid2.b.a.b
        public String getString(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.a.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        @Override // com.ta.utdid2.b.a.b
        public long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.a.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        /* renamed from: com.ta.utdid2.b.a.d$a$a  reason: collision with other inner class name */
        public final class C0153a implements b.a {
            private final Map<String, Object> b = new HashMap();
            private boolean k = false;

            public C0153a() {
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, String str2) {
                synchronized (this) {
                    this.b.put(str, str2);
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, int i) {
                synchronized (this) {
                    this.b.put(str, Integer.valueOf(i));
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, long j) {
                synchronized (this) {
                    this.b.put(str, Long.valueOf(j));
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, float f) {
                synchronized (this) {
                    this.b.put(str, Float.valueOf(f));
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str, boolean z) {
                synchronized (this) {
                    this.b.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a a(String str) {
                synchronized (this) {
                    this.b.put(str, this);
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public b.a b() {
                synchronized (this) {
                    this.k = true;
                }
                return this;
            }

            @Override // com.ta.utdid2.b.a.b.a
            public boolean commit() {
                boolean z;
                ArrayList arrayList;
                HashSet<b.AbstractC0152b> hashSet;
                boolean d;
                synchronized (d.b) {
                    z = a.this.f333a.size() > 0;
                    arrayList = null;
                    if (z) {
                        arrayList = new ArrayList();
                        hashSet = new HashSet(a.this.f333a.keySet());
                    } else {
                        hashSet = null;
                    }
                    synchronized (this) {
                        if (this.k) {
                            a.this.a.clear();
                            this.k = false;
                        }
                        for (Map.Entry<String, Object> entry : this.b.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            if (value == this) {
                                a.this.a.remove(key);
                            } else {
                                a.this.a.put(key, value);
                            }
                            if (z) {
                                arrayList.add(key);
                            }
                        }
                        this.b.clear();
                    }
                    d = a.this.d();
                    if (d) {
                        a.this.a(true);
                    }
                }
                if (z) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        String str = (String) arrayList.get(size);
                        for (b.AbstractC0152b bVar : hashSet) {
                            if (bVar != null) {
                                bVar.a(a.this, str);
                            }
                        }
                    }
                }
                return d;
            }
        }

        @Override // com.ta.utdid2.b.a.b
        /* renamed from: a */
        public b.a mo4075a() {
            return new C0153a();
        }

        private FileOutputStream a(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException unused) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException unused2) {
                    return null;
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean d() {
            if (this.b.exists()) {
                if (this.f335c.exists()) {
                    this.b.delete();
                } else if (!this.b.renameTo(this.f335c)) {
                    return false;
                }
            }
            try {
                FileOutputStream a = a(this.b);
                if (a == null) {
                    return false;
                }
                e.a(this.a, a);
                a.close();
                this.f335c.delete();
                return true;
            } catch (Exception unused) {
                if (this.b.exists()) {
                    this.b.delete();
                }
                return false;
            }
        }
    }
}
