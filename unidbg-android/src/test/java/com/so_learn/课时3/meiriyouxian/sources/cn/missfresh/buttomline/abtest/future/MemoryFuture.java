package cn.missfresh.buttomline.abtest.future;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.bean.Plan;
import cn.missfresh.buttomline.abtest.inf.AbtestRequestCallback;
import cn.missfresh.buttomline.abtest.log.Logger;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryFuture extends DiskFuture {
    private final List<Plan> mImmutablePlans = getAll();
    private CopyOnWriteArrayList<Plan> mPlans = new CopyOnWriteArrayList<>();

    public MemoryFuture(ABTest aBTest) {
        super(aBTest);
        AppMethodBeat.i(7350, false);
        List<Plan> list = this.mImmutablePlans;
        if (list != null) {
            for (Plan plan : list) {
                Plan plan2 = new Plan();
                plan2.setExpId(plan.getExpId());
                plan2.setTickTime(plan.getTickTime());
                plan2.setValue(String.valueOf(plan.getValue()));
                this.mPlans.add(plan2);
            }
        }
        AppMethodBeat.o(7350);
    }

    @Override // cn.missfresh.buttomline.abtest.future.DiskFuture, cn.missfresh.buttomline.abtest.inf.Future
    public int get(String str, AbtestRequestCallback abtestRequestCallback) {
        AppMethodBeat.i(7354, false);
        CopyOnWriteArrayList<Plan> copyOnWriteArrayList = this.mPlans;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            int i = super.get(str, abtestRequestCallback);
            AppMethodBeat.o(7354);
            return i;
        }
        int pick = pick(str, this.mPlans);
        if (abtestRequestCallback != null) {
            abtestRequestCallback.onComplite(pick);
        }
        Logger.i("MemoryFuture get:case_id=" + str + " case_value=" + pick);
        AppMethodBeat.o(7354);
        return pick;
    }

    @Override // cn.missfresh.buttomline.abtest.future.DiskFuture, cn.missfresh.buttomline.abtest.inf.Future
    public void set(List<Plan> list) {
        boolean z = false;
        AppMethodBeat.i(7357, false);
        if (this.mPlans.isEmpty()) {
            this.mPlans.addAll(list);
            if (this.mImmutablePlans.isEmpty()) {
                this.mImmutablePlans.addAll(list);
            }
            super.set(this.mPlans);
        } else {
            for (Plan plan : list) {
                Plan plan2 = getPlan(plan.getExpId());
                if (plan2 == null) {
                    this.mPlans.add(plan);
                } else if (!isPlanEquals(plan2, plan)) {
                    Logger.i("MemoryFuture set new plan:" + plan.toString());
                    plan2.setExpId(plan.getExpId());
                    plan2.setTickTime(plan.getTickTime());
                    plan2.setValue(String.valueOf(plan.getValue()));
                }
                z = true;
            }
            if (z) {
                super.set(this.mPlans);
            }
        }
        AppMethodBeat.o(7357);
    }

    @Override // cn.missfresh.buttomline.abtest.future.DiskFuture, cn.missfresh.buttomline.abtest.inf.Future
    public void set(String str, String str2) {
        AppMethodBeat.i(7360, false);
        Plan plan = getPlan(str);
        if (plan == null) {
            Plan plan2 = new Plan();
            plan2.setExpId(str);
            plan2.setValue(str2);
            this.mPlans.add(plan2);
        } else {
            plan.setValue(str2);
        }
        super.set(this.mPlans);
        AppMethodBeat.o(7360);
    }

    private boolean isPlanEquals(Plan plan, Plan plan2) {
        boolean z = false;
        AppMethodBeat.i(7362, false);
        if (plan.getValue() == plan2.getValue() && plan.getTickTime() == plan2.getTickTime()) {
            z = true;
        }
        AppMethodBeat.o(7362);
        return z;
    }

    @Override // cn.missfresh.buttomline.abtest.future.DiskFuture, cn.missfresh.buttomline.abtest.inf.Future
    public int immutableGet(String str) {
        AppMethodBeat.i(7365, false);
        int pick = pick(str, this.mImmutablePlans);
        Logger.i("MemoryFuture immutableGet:case_id=" + str + " case_value=" + pick);
        AppMethodBeat.o(7365);
        return pick;
    }

    /* access modifiers changed from: package-private */
    public Plan getPlan(String str) {
        Plan plan;
        AppMethodBeat.i(7369, false);
        Iterator<Plan> it2 = this.mPlans.iterator();
        while (true) {
            if (!it2.hasNext()) {
                plan = null;
                break;
            }
            plan = it2.next();
            if (plan.getExpId().equals(str)) {
                break;
            }
        }
        AppMethodBeat.o(7369);
        return plan;
    }
}
