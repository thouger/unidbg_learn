package cn.missfresh.module.base.common.providers;

import androidx.fragment.app.DialogFragment;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface IRewardService extends IProvider {
    DialogFragment a(boolean z, String str, int i);
}
