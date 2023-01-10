package cn.missfresh.module.base.common.providers;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.android.arouter.facade.template.IProvider;
import java.util.List;

public interface IOrderDetailsService extends IProvider {
    void a(Activity activity, List<String> list);

    void a(FragmentActivity fragmentActivity, String str);

    void a(String str);
}
