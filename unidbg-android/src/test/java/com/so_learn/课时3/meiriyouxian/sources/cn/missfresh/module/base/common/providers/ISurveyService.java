package cn.missfresh.module.base.common.providers;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface ISurveyService extends LifecycleObserver, IProvider {
    LiveData<DialogFragment> a(int i, Fragment fragment);

    LiveData<DialogFragment> a(int i, LifecycleOwner lifecycleOwner, String str);

    void a(int i, int i2);

    void a(int i, LifecycleOwner lifecycleOwner);

    @Deprecated
    void b(int i, int i2);
}
