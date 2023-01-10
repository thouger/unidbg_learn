package cn.missfresh.ui.recyclerview.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> a = new SparseArray<>();
    private View b;
    private Context c;

    public ViewHolder(Context context, View view) {
        super(view);
        AppMethodBeat.i(1078, false);
        this.c = context;
        this.b = view;
        AppMethodBeat.o(1078);
    }

    public static ViewHolder a(Context context, ViewGroup viewGroup, int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SETTINGS_SEARCH_RESULT_ASYNC_RANKING_STATE, false);
        ViewHolder viewHolder = new ViewHolder(context, LayoutInflater.from(context).inflate(i, viewGroup, false));
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SETTINGS_SEARCH_RESULT_ASYNC_RANKING_STATE);
        return viewHolder;
    }

    public View a() {
        return this.b;
    }
}
