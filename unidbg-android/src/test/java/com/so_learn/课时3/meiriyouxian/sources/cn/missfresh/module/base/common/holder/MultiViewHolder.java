package cn.missfresh.module.base.common.holder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.common.interfaces.t;

public abstract class MultiViewHolder extends RecyclerView.ViewHolder {
    public abstract void a(t tVar);

    public MultiViewHolder(View view) {
        super(view);
    }
}
