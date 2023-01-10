package cn.missfresh.ui.tablayout.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.tablayout.MFTabLayoutAdapter;
import java.util.List;

public class MFSimpleTabLayoutAdapter extends MFTabLayoutAdapter<SimpleTabViewHolder> {
    private List<String> a;

    @Override // cn.missfresh.ui.tablayout.MFTabLayoutAdapter
    public /* synthetic */ RecyclerView.ViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(2559, false);
        SimpleTabViewHolder b = b(viewGroup, i);
        AppMethodBeat.o(2559);
        return b;
    }

    @Override // cn.missfresh.ui.tablayout.MFTabLayoutAdapter
    public /* synthetic */ void a(RecyclerView.ViewHolder viewHolder, int i, boolean z) {
        AppMethodBeat.i(2558, false);
        a((SimpleTabViewHolder) viewHolder, i, z);
        AppMethodBeat.o(2558);
    }

    public final void a(List<String> list) {
        AppMethodBeat.i(2542, false);
        this.a = list;
        notifyDataSetChanged();
        AppMethodBeat.o(2542);
    }

    public SimpleTabViewHolder b(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(2545, false);
        Context context = viewGroup.getContext();
        TextView textView = new TextView(context);
        if (1 == b().getOrientation()) {
            textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        }
        textView.setTextSize(1, 14.0f);
        int a = a(context, 14.0f);
        textView.setPadding(a, a, a, a(context, 12.0f));
        textView.setGravity(17);
        SimpleTabViewHolder simpleTabViewHolder = new SimpleTabViewHolder(textView);
        AppMethodBeat.o(2545);
        return simpleTabViewHolder;
    }

    public void a(SimpleTabViewHolder simpleTabViewHolder, int i, boolean z) {
        AppMethodBeat.i(2549, false);
        TextView textView = simpleTabViewHolder.a;
        textView.setText(this.a.get(i));
        textView.setTextColor(z ? -46959 : -12105139);
        textView.getPaint().setFakeBoldText(z);
        AppMethodBeat.o(2549);
    }

    @Override // cn.missfresh.ui.tablayout.MFTabLayoutAdapter
    public final int a() {
        int i = 0;
        AppMethodBeat.i(2553, false);
        List<String> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(2553);
        return i;
    }

    private static int a(Context context, float f) {
        AppMethodBeat.i(2556, false);
        int i = (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(2556);
        return i;
    }

    public static final class SimpleTabViewHolder extends RecyclerView.ViewHolder {
        public TextView a;

        public SimpleTabViewHolder(View view) {
            super(view);
            AppMethodBeat.i(2536, false);
            this.a = (TextView) view;
            AppMethodBeat.o(2536);
        }
    }
}
