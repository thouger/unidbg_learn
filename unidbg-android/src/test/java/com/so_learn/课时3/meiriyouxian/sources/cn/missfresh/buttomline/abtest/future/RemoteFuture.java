package cn.missfresh.buttomline.abtest.future;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.bean.Plan;
import cn.missfresh.buttomline.abtest.inf.AbtestRequestCallback;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.concurrent.TimeUnit;

public class RemoteFuture extends MemoryFuture {
    public RemoteFuture(ABTest aBTest) {
        super(aBTest);
    }

    @Override // cn.missfresh.buttomline.abtest.future.MemoryFuture, cn.missfresh.buttomline.abtest.future.DiskFuture, cn.missfresh.buttomline.abtest.inf.Future
    public int immutableGet(String str) {
        AppMethodBeat.i(7376, false);
        int immutableGet = super.immutableGet(str);
        request(str, immutableGet);
        AppMethodBeat.o(7376);
        return immutableGet;
    }

    @Override // cn.missfresh.buttomline.abtest.future.MemoryFuture, cn.missfresh.buttomline.abtest.future.DiskFuture, cn.missfresh.buttomline.abtest.inf.Future
    public int get(String str, AbtestRequestCallback abtestRequestCallback) {
        AppMethodBeat.i(7378, false);
        int i = super.get(str, abtestRequestCallback);
        request(str, i);
        AppMethodBeat.o(7378);
        return i;
    }

    private void request(String str, int i) {
        AppMethodBeat.i(7382, false);
        Plan plan = getPlan(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (plan == null || currentTimeMillis - plan.getLastReqTime() > TimeUnit.SECONDS.toMillis((long) plan.getTickTime())) {
            requestServer(str);
            if (plan != null) {
                plan.setLastReqTime(currentTimeMillis);
            }
        }
        AppMethodBeat.o(7382);
    }
}
