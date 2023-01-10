package io.flutter.embedding.engine.loader;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Build;
import io.flutter.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* access modifiers changed from: package-private */
public class ResourceExtractor {
    private static final String[] SUPPORTED_ABIS = getSupportedAbis();
    private static final String TAG = "ResourceExtractor";
    private static final String TIMESTAMP_PREFIX = "res_timestamp-";
    private final AssetManager mAssetManager;
    private final String mDataDirPath;
    private ExtractTask mExtractTask;
    private final PackageManager mPackageManager;
    private final String mPackageName;
    private final HashSet<String> mResources = new HashSet<>();

    static long getVersionCode(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return packageInfo.getLongVersionCode();
        }
        return (long) packageInfo.versionCode;
    }

    /* access modifiers changed from: private */
    public static class ExtractTask extends AsyncTask<Void, Void, Void> {
        private final AssetManager mAssetManager;
        private final String mDataDirPath;
        private final PackageManager mPackageManager;
        private final String mPackageName;
        private final HashSet<String> mResources;

        ExtractTask(String str, HashSet<String> hashSet, String str2, PackageManager packageManager, AssetManager assetManager) {
            this.mDataDirPath = str;
            this.mResources = hashSet;
            this.mAssetManager = assetManager;
            this.mPackageName = str2;
            this.mPackageManager = packageManager;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            File file = new File(this.mDataDirPath);
            String checkTimestamp = ResourceExtractor.checkTimestamp(file, this.mPackageManager, this.mPackageName);
            if (checkTimestamp == null) {
                return null;
            }
            ResourceExtractor.deleteFiles(this.mDataDirPath, this.mResources);
            if (extractAPK(file) && checkTimestamp != null) {
                try {
                    new File(file, checkTimestamp).createNewFile();
                } catch (IOException unused) {
                    Log.w(ResourceExtractor.TAG, "Failed to write resource timestamp");
                }
            }
            return null;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x005b, code lost:
            r2.addSuppressed(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x005e, code lost:
            throw r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0061, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0062, code lost:
            if (r1 != null) goto L_0x0064;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            r1.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0068, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0069, code lost:
            r2.addSuppressed(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x006c, code lost:
            throw r3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean extractAPK(java.io.File r6) {
            /*
                r5 = this;
                java.util.HashSet<java.lang.String> r0 = r5.mResources
                java.util.Iterator r0 = r0.iterator()
            L_0x0006:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0093
                java.lang.Object r1 = r0.next()
                java.lang.String r1 = (java.lang.String) r1
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                r2.<init>()     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                java.lang.String r3 = "assets/"
                r2.append(r3)     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                r2.append(r1)     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                r2.toString()     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                java.io.File r2 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                r2.<init>(r6, r1)     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                boolean r3 = r2.exists()     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                if (r3 == 0) goto L_0x002f
                goto L_0x0006
            L_0x002f:
                java.io.File r3 = r2.getParentFile()     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                if (r3 == 0) goto L_0x003c
                java.io.File r3 = r2.getParentFile()     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                r3.mkdirs()     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
            L_0x003c:
                android.content.res.AssetManager r3 = r5.mAssetManager     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                java.io.InputStream r1 = r3.open(r1)     // Catch:{ FileNotFoundException -> 0x0006, IOException -> 0x006d }
                java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x005f }
                r3.<init>(r2)     // Catch:{ all -> 0x005f }
                io.flutter.embedding.engine.loader.ResourceExtractor.access$200(r1, r3)     // Catch:{ all -> 0x0053 }
                r3.close()
                if (r1 == 0) goto L_0x0006
                r1.close()
                goto L_0x0006
            L_0x0053:
                r2 = move-exception
                throw r2     // Catch:{ all -> 0x0055 }
            L_0x0055:
                r4 = move-exception
                r3.close()     // Catch:{ all -> 0x005a }
                goto L_0x005e
            L_0x005a:
                r3 = move-exception
                r2.addSuppressed(r3)
            L_0x005e:
                throw r4
            L_0x005f:
                r2 = move-exception
                throw r2     // Catch:{ all -> 0x0061 }
            L_0x0061:
                r3 = move-exception
                if (r1 == 0) goto L_0x006c
                r1.close()     // Catch:{ all -> 0x0068 }
                goto L_0x006c
            L_0x0068:
                r1 = move-exception
                r2.addSuppressed(r1)
            L_0x006c:
                throw r3
            L_0x006d:
                r6 = move-exception
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Exception unpacking resources: "
                r0.append(r1)
                java.lang.String r6 = r6.getMessage()
                r0.append(r6)
                java.lang.String r6 = r0.toString()
                java.lang.String r0 = "ResourceExtractor"
                io.flutter.Log.w(r0, r6)
                java.lang.String r6 = r5.mDataDirPath
                java.util.HashSet<java.lang.String> r0 = r5.mResources
                io.flutter.embedding.engine.loader.ResourceExtractor.access$100(r6, r0)
                r6 = 0
                return r6
            L_0x0093:
                r6 = 1
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.engine.loader.ResourceExtractor.ExtractTask.extractAPK(java.io.File):boolean");
        }
    }

    ResourceExtractor(String str, String str2, PackageManager packageManager, AssetManager assetManager) {
        this.mDataDirPath = str;
        this.mPackageName = str2;
        this.mPackageManager = packageManager;
        this.mAssetManager = assetManager;
    }

    /* access modifiers changed from: package-private */
    public ResourceExtractor addResource(String str) {
        this.mResources.add(str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ResourceExtractor addResources(Collection<String> collection) {
        this.mResources.addAll(collection);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ResourceExtractor start() {
        this.mExtractTask = new ExtractTask(this.mDataDirPath, this.mResources, this.mPackageName, this.mPackageManager, this.mAssetManager);
        this.mExtractTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void waitForCompletion() {
        ExtractTask extractTask = this.mExtractTask;
        if (extractTask != null) {
            try {
                extractTask.get();
            } catch (InterruptedException | CancellationException | ExecutionException unused) {
                deleteFiles(this.mDataDirPath, this.mResources);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.embedding.engine.loader.ResourceExtractor$1  reason: invalid class name */
    public static class AnonymousClass1 implements FilenameFilter {
        AnonymousClass1() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith(ResourceExtractor.TIMESTAMP_PREFIX);
        }
    }

    private static String[] getExistingTimestamps(File file) {
        return file.list(new AnonymousClass1());
    }

    /* access modifiers changed from: private */
    public static void deleteFiles(String str, HashSet<String> hashSet) {
        File file = new File(str);
        Iterator<String> it2 = hashSet.iterator();
        while (it2.hasNext()) {
            File file2 = new File(file, it2.next());
            if (file2.exists()) {
                file2.delete();
            }
        }
        String[] existingTimestamps = getExistingTimestamps(file);
        if (existingTimestamps != null) {
            for (String str2 : existingTimestamps) {
                new File(file, str2).delete();
            }
        }
    }

    /* access modifiers changed from: private */
    public static String checkTimestamp(File file, PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return TIMESTAMP_PREFIX;
            }
            String str2 = TIMESTAMP_PREFIX + getVersionCode(packageInfo) + "-" + packageInfo.lastUpdateTime;
            String[] existingTimestamps = getExistingTimestamps(file);
            if (existingTimestamps == null) {
                return str2;
            }
            int length = existingTimestamps.length;
            if (existingTimestamps.length != 1 || !str2.equals(existingTimestamps[0])) {
                return str2;
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return TIMESTAMP_PREFIX;
        }
    }

    /* access modifiers changed from: private */
    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[16384];
        while (true) {
            int read = inputStream.read(bArr);
            if (read >= 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static String[] getSupportedAbis() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(Build.CPU_ABI, Build.CPU_ABI2));
        arrayList.removeAll(Arrays.asList(null, ""));
        return (String[]) arrayList.toArray(new String[0]);
    }
}
