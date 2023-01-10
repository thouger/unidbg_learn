package cn.missfresh.buttomline.abtest.future;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.bean.Plan;
import cn.missfresh.buttomline.abtest.inf.AbtestRequestCallback;
import cn.missfresh.buttomline.abtest.inf.Future;
import cn.missfresh.buttomline.abtest.log.Logger;
import cn.missfresh.buttomline.abtest.net.AbtestRequestParam;
import cn.missfresh.buttomline.abtest.net.ApiManager;
import cn.missfresh.buttomline.abtest.net.BaseAbtestApiResult;
import cn.missfresh.buttomline.abtest.uitl.DataUtil;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiskFuture implements Future {
    private HashMap<String, AbtestRequestCallback> cbMap = new HashMap<>();
    private final ABTest mTest;

    @Override // cn.missfresh.buttomline.abtest.inf.Future
    public int immutableGet(String str) {
        return -1;
    }

    @Override // cn.missfresh.buttomline.abtest.inf.Future
    public void set(String str, String str2) {
    }

    DiskFuture(ABTest aBTest) {
        AppMethodBeat.i(7326, false);
        this.mTest = aBTest;
        AppMethodBeat.o(7326);
    }

    @Override // cn.missfresh.buttomline.abtest.inf.Future
    public int get(String str, AbtestRequestCallback abtestRequestCallback) {
        AppMethodBeat.i(7329, false);
        if (abtestRequestCallback != null) {
            this.cbMap.put(str, abtestRequestCallback);
        }
        int pick = pick(str, getAll());
        AppMethodBeat.o(7329);
        return pick;
    }

    /* access modifiers changed from: package-private */
    public List<Plan> getAll() {
        AppMethodBeat.i(7331, false);
        String readFileContent = FileOpt.readFileContent(this.mTest.getPath());
        Logger.i("DiskFuture json in File:" + readFileContent);
        if (readFileContent == null || readFileContent.length() <= 0 || !readFileContent.startsWith("[") || !readFileContent.endsWith("]")) {
            ArrayList arrayList = new ArrayList();
            AppMethodBeat.o(7331);
            return arrayList;
        }
        List<Plan> list = (List) DataUtil.getGsonInstance().fromJson(readFileContent, new AnonymousClass1().getType());
        AppMethodBeat.o(7331);
        return list;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.buttomline.abtest.future.DiskFuture$1  reason: invalid class name */
    public class AnonymousClass1 extends TypeToken<List<Plan>> {
        AnonymousClass1() {
        }
    }

    @Override // cn.missfresh.buttomline.abtest.inf.Future
    public void set(List<Plan> list) {
        AppMethodBeat.i(7333, false);
        FileOpt.writeStringToFile(DataUtil.getGsonInstance().toJson(list), this.mTest.getPath());
        AppMethodBeat.o(7333);
    }

    @Override // cn.missfresh.buttomline.abtest.inf.Future
    public void requestServer(String str) {
        AppMethodBeat.i(7336, false);
        AbtestRequestParam createAbtestRequest = ABTest.createAbtestRequest();
        createAbtestRequest.getExpIds().add(str);
        ApiManager.getAbtestResult(createAbtestRequest, new AnonymousClass2(str));
        AppMethodBeat.o(7336);
    }

    /* renamed from: cn.missfresh.buttomline.abtest.future.DiskFuture$2  reason: invalid class name */
    class AnonymousClass2 extends BaseAbtestApiResult {
        Plan current = null;
        final /* synthetic */ String val$id;

        AnonymousClass2(String str) {
            this.val$id = str;
        }

        @Override // cn.missfresh.buttomline.abtest.net.BaseAbtestApiResult, cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(7318, false);
            onSuccess((List) obj);
            AppMethodBeat.o(7318);
        }

        @Override // cn.missfresh.buttomline.abtest.net.BaseAbtestApiResult
        public void onSuccess(List<Plan> list) {
            AppMethodBeat.i(7312, false);
            if (list != null) {
                DiskFuture.this.set(list);
            }
            AppMethodBeat.o(7312);
        }

        @Override // cn.missfresh.buttomline.abtest.net.BaseAbtestApiResult, cn.missfresh.basiclib.net.a.a
        public void onComplete() {
            AppMethodBeat.i(7315, false);
            super.onComplete();
            AbtestRequestCallback abtestRequestCallback = (AbtestRequestCallback) DiskFuture.this.cbMap.get(this.val$id);
            if (abtestRequestCallback != null) {
                abtestRequestCallback.onComplite(DiskFuture.this.get(this.val$id, null));
                DiskFuture.this.cbMap.remove(this.val$id);
            }
            AppMethodBeat.o(7315);
        }
    }

    /* access modifiers changed from: package-private */
    public int pick(String str, List<Plan> list) {
        AppMethodBeat.i(7340, false);
        for (Plan plan : list) {
            if (plan.getExpId().equals(str)) {
                int value = plan.getValue();
                AppMethodBeat.o(7340);
                return value;
            }
        }
        AppMethodBeat.o(7340);
        return -1;
    }
}
