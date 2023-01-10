package cn.missfresh.module.user.mine.qucikpay.viewmodel;

import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.module.order.orderdetails_v2.provider.IQuickPayService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.android.internal.telephony.SmsHeader;
import java.util.Map;

public class QuickPayViewModel extends BaseViewModel {
    private IQuickPayService a = ((IQuickPayService) a.a().a("/order/quick_service").navigation());
    private MutableLiveData<Map<String, String>> b = new MutableLiveData<>();
    private MutableLiveData<Boolean> c = new MutableLiveData<>();
    private MutableLiveData<Integer> d = new MutableLiveData<>();
    private String e;

    public QuickPayViewModel() {
        AppMethodBeat.i(9190, false);
        AppMethodBeat.o(9190);
    }

    public IQuickPayService a() {
        return this.a;
    }

    public MutableLiveData<Boolean> b() {
        return this.c;
    }

    public MutableLiveData<Integer> c() {
        return this.d;
    }

    public void a(String str) {
        AppMethodBeat.i(9196, false);
        this.a.a(str);
        StatisticsManager.ah("click_open_no_pwd", new Object[0]);
        AppMethodBeat.o(9196);
    }

    public void b(String str) {
        AppMethodBeat.i(SmsHeader.PORT_WAP_WSP, false);
        this.e = str;
        this.d.setValue(1);
        AppMethodBeat.o(SmsHeader.PORT_WAP_WSP);
    }

    public void d() {
        AppMethodBeat.i(9202, false);
        this.a.b(this.e);
        AppMethodBeat.o(9202);
    }
}
