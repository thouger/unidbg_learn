package cn.missfresh.module.base.common.providers;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import cn.missfresh.module.base.bean.AddResult;
import com.alibaba.android.arouter.facade.template.IProvider;
import io.reactivex.q;
import java.util.List;
import java.util.Map;

public interface IShoppingCartService2 extends IProvider {
    int a();

    int a(List<String> list);

    q<AddResult> a(String str);

    q<AddResult> a(String str, String str2, int i);

    q<AddResult> a(String str, String str2, int i, String[] strArr);

    q<AddResult> a(String str, String str2, int i, String[] strArr, String str3, Map<String, Object> map);

    q<AddResult> a(String str, Map<String, Integer> map, Map<String, List<Integer>> map2);

    q<AddResult> a(String str, Map<String, Integer> map, Map<String, List<Integer>> map2, String str2, Map<String, Object> map3);

    q<AddResult> a(String str, Map<String, Integer> map, Map<String, List<Integer>> map2, Map<String, Object> map3);

    void a(FragmentActivity fragmentActivity);

    void a(FragmentManager fragmentManager);

    void a(FragmentManager fragmentManager, String str, String str2);

    void a(String str, String str2, int i, String str3);

    void a(String str, String str2, int i, String[] strArr, String str3);

    void a(String str, String str2, int i, String[] strArr, Map<String, Object> map);

    int b(String str);

    LiveData<Integer> b();

    void b(String str, String str2, int i);

    void b(String str, String str2, int i, String[] strArr);

    void c();

    void c(String str);

    void d();

    Map<String, Integer> e();
}
