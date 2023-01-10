package cn.missfresh.picture.luban;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.c.h;
import io.reactivex.disposables.a;
import io.reactivex.g;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.a.b;

public class CompressManager {
    private static final String TAG = "CompressManager";
    private static final CompressManager sInstance = new CompressManager();
    private a mDisposable = new a();

    static /* synthetic */ void access$000(CompressManager compressManager, List list, List list2, a aVar) {
        AppMethodBeat.i(17879, false);
        compressManager.showResult(list, list2, aVar);
        AppMethodBeat.o(17879);
    }

    static {
        AppMethodBeat.i(17881, false);
        AppMethodBeat.o(17881);
    }

    private CompressManager() {
        AppMethodBeat.i(17857, false);
        AppMethodBeat.o(17857);
    }

    public static CompressManager getInstance() {
        return sInstance;
    }

    public void clearCompressTask() {
        AppMethodBeat.i(17862, false);
        a aVar = this.mDisposable;
        if (aVar != null) {
            aVar.a();
        }
        AppMethodBeat.o(17862);
    }

    public boolean isMfImage(String str) {
        boolean z = false;
        AppMethodBeat.i(17866, false);
        if (!TextUtils.isEmpty(str) && str.startsWith("http") && str.contains("missfresh") && str.contains("ucloud")) {
            z = true;
        }
        AppMethodBeat.o(17866);
        return z;
    }

    public void compressImage(Context context, List<String> list, a aVar) {
        AppMethodBeat.i(17869, false);
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(17869);
            return;
        }
        Log.d("[publish][compress]", "compress image start count: " + list.size());
        createNoMeidaFile(context);
        LinkedHashMap a = f.a();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                if (isMfImage(str)) {
                    arrayList2.add(str);
                } else {
                    String str2 = (String) a.get(str);
                    if (TextUtils.isEmpty(str2)) {
                        arrayList.add(str);
                    } else {
                        arrayList2.add(str2);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            if (aVar != null) {
                aVar.onResult(arrayList2);
            }
            Log.d("[publish][compress]", "compress image end count: " + arrayList2.size());
            AppMethodBeat.o(17869);
            return;
        }
        this.mDisposable.a(g.a(arrayList).a(io.reactivex.f.a.b()).b(new AnonymousClass3(context)).a(io.reactivex.a.b.a.a()).a((io.reactivex.c.g<? super Throwable>) new AnonymousClass2()).a((b) g.b()).b(new AnonymousClass1(arrayList2, aVar)));
        AppMethodBeat.o(17869);
    }

    /* renamed from: cn.missfresh.picture.luban.CompressManager$3  reason: invalid class name */
    class AnonymousClass3 implements h<List<String>, List<File>> {
        final /* synthetic */ Context a;

        AnonymousClass3(Context context) {
            this.a = context;
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(19212, false);
            List<File> a = a((List) obj);
            AppMethodBeat.o(19212);
            return a;
        }

        public List<File> a(List<String> list) throws Exception {
            AppMethodBeat.i(19208, false);
            List<File> a = f.a(this.a).a(list).a();
            AppMethodBeat.o(19208);
            return a;
        }
    }

    /* renamed from: cn.missfresh.picture.luban.CompressManager$2  reason: invalid class name */
    class AnonymousClass2 implements io.reactivex.c.g<Throwable> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(18672, false);
            a((Throwable) obj);
            AppMethodBeat.o(18672);
        }

        public void a(Throwable th) {
            AppMethodBeat.i(18670, false);
            Log.e(CompressManager.TAG, th.getMessage());
            AppMethodBeat.o(18670);
        }
    }

    /* renamed from: cn.missfresh.picture.luban.CompressManager$1  reason: invalid class name */
    class AnonymousClass1 implements io.reactivex.c.g<List<File>> {
        final /* synthetic */ List a;
        final /* synthetic */ a b;

        AnonymousClass1(List list, a aVar) {
            this.a = list;
            this.b = aVar;
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(18438, false);
            a((List) obj);
            AppMethodBeat.o(18438);
        }

        public void a(List<File> list) {
            AppMethodBeat.i(18434, false);
            CompressManager.access$000(CompressManager.this, list, this.a, this.b);
            AppMethodBeat.o(18434);
        }
    }

    private void showResult(List<File> list, List<String> list2, a aVar) {
        AppMethodBeat.i(17871, false);
        if (list == null || list.isEmpty()) {
            if (aVar != null) {
                Log.d("[publish][compress]", "compress image error, result empty");
                aVar.onResult(null);
            }
            AppMethodBeat.o(17871);
            return;
        }
        Log.d("[publish][compress]", "compress image success count: " + list.size());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list2);
        for (File file : list) {
            arrayList.add(file.getAbsolutePath());
        }
        if (aVar != null) {
            Log.d("[publish][compress]", "compress image end all compress count: " + arrayList.size());
            aVar.onResult(arrayList);
        }
        AppMethodBeat.o(17871);
    }

    private String obtianComressPath() {
        AppMethodBeat.i(17872, false);
        String str = Environment.getExternalStorageDirectory() + "/Pictures/mf/compress/";
        if (new File(str).mkdirs()) {
            AppMethodBeat.o(17872);
            return str;
        }
        AppMethodBeat.o(17872);
        return str;
    }

    private void createNoMeidaFile(Context context) {
        AppMethodBeat.i(17875, false);
        try {
            File a = f.a(context, "disk_cache");
            if (a == null) {
                AppMethodBeat.o(17875);
                return;
            }
            File file = new File(a, "/.nomedia");
            if (!file.exists()) {
                file.createNewFile();
            }
            AppMethodBeat.o(17875);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearCompressDir(Context context) {
        AppMethodBeat.i(17876, false);
        try {
            File a = f.a(context, "disk_cache");
            if (a == null) {
                AppMethodBeat.o(17876);
                return;
            }
            deleteDirWithFile(a);
            AppMethodBeat.o(17876);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteDirWithFile(File file) {
        AppMethodBeat.i(17878, false);
        if (file == null || !file.exists() || !file.isDirectory()) {
            AppMethodBeat.o(17878);
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            AppMethodBeat.o(17878);
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isFile() && !file2.getName().contains("nomedia")) {
                file2.delete();
            } else if (file2.isDirectory()) {
                deleteDirWithFile(file2);
            }
        }
        file.delete();
        AppMethodBeat.o(17878);
    }
}
