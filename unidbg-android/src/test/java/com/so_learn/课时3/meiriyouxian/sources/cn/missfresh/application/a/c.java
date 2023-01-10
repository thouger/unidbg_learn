package cn.missfresh.application.a;

import android.app.Application;
import cn.missfresh.next.a;
import cn.missfresh.next.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;

/* compiled from: TaskManager */
public class c {
    public void a(Application application, a aVar) {
        AppMethodBeat.i(54, false);
        a aVar2 = new a();
        aVar2.a(aVar.a("TASK_BASE"));
        aVar2.a(aVar.a("TASK_ABTEST"));
        aVar2.a(aVar.a("TASK_LOGTRACE"));
        aVar2.a(aVar.a("TASK_SHERLOCK"));
        aVar2.a(aVar.a("TASK_LINK"));
        aVar2.a(aVar.a("TASK_TOAST"));
        aVar2.a(aVar.a("TASK_QUEST_MOBILE"));
        aVar2.a(aVar.a("TASK_MAP"));
        a aVar3 = new a("PROJECT_ASYNC_A", false);
        aVar3.a(5);
        aVar3.a(aVar.a("TASK_MIID"));
        aVar3.a(aVar.a("TASK_TENCENT"));
        aVar3.a(aVar.a("TASK_PUSH"));
        aVar3.a(aVar.a("TASK_AD"));
        aVar3.a(aVar.a("TASK_X5"));
        a aVar4 = new a("PROJECT_ASYNC_B", false);
        aVar4.a(4);
        aVar4.a(aVar.a("TASK_SCM"));
        a aVar5 = new a("PROJECT_ASYNC_C", false);
        aVar5.a(3);
        aVar5.a(aVar.a("TASK_HOME_CACHE"));
        aVar5.a(aVar.a("TASK_INIT_CONFIG"));
        aVar5.a(aVar.a("TASK_STATISTICS"));
        a aVar6 = new a("PROJECT_ASYNC_D", false);
        aVar6.a(aVar.a("TASK_LOG"));
        aVar6.a(aVar.a("TASK_HUAWEI"));
        aVar6.a(aVar.a("TASK_CONFIG"));
        a aVar7 = new a("PROJECT_ASYNC_E", false);
        aVar7.a(aVar.a("TASK_SOBOT"));
        aVar7.a(aVar.a("TASK_DNS"));
        ArrayList arrayList = new ArrayList();
        arrayList.add(aVar2);
        arrayList.add(aVar3);
        arrayList.add(aVar4);
        arrayList.add(aVar5);
        arrayList.add(aVar6);
        arrayList.add(aVar7);
        d.a().a(arrayList);
        d.a().a(true, application);
        d.a().b();
        AppMethodBeat.o(54);
    }
}
