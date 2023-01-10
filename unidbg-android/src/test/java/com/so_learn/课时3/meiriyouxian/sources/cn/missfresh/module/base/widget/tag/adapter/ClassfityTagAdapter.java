package cn.missfresh.module.base.widget.tag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.FitWidthImageView;
import cn.missfresh.module.base.widget.tag.a;
import cn.missfresh.module.base.widget.tag.bean.TagInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.f;
import java.util.List;

public class ClassfityTagAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LayoutInflater a;
    private List<TagInfo> b;
    private int c;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(24354, false);
        a((ViewHolder) viewHolder, i);
        AppMethodBeat.o(24354);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(24355, false);
        ViewHolder a = a(viewGroup, i);
        AppMethodBeat.o(24355);
        return a;
    }

    public ClassfityTagAdapter(Context context) {
        AppMethodBeat.i(24349, false);
        this.a = LayoutInflater.from(context);
        AppMethodBeat.o(24349);
    }

    public void a(List<TagInfo> list, int i) {
        AppMethodBeat.i(24350, false);
        this.b = list;
        this.c = i;
        notifyDataSetChanged();
        AppMethodBeat.o(24350);
    }

    public ViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(24351, false);
        ViewHolder viewHolder = new ViewHolder(this.a.inflate(R.layout.classify_item_promotion_tag, viewGroup, false));
        AppMethodBeat.o(24351);
        return viewHolder;
    }

    public void a(ViewHolder viewHolder, int i) {
        AppMethodBeat.i(24352, false);
        TagInfo tagInfo = this.b.get(i);
        if (tagInfo.getUseType() == 1) {
            viewHolder.b.setVisibility(8);
            viewHolder.a.setVisibility(0);
            if (((int) viewHolder.a.getPaint().measureText(tagInfo.getTagName())) + f.c(viewHolder.a.getContext(), 12) < f.c(viewHolder.a.getContext(), 70)) {
                a.a(tagInfo, viewHolder.a, false);
            } else {
                viewHolder.a.setVisibility(8);
            }
        } else {
            viewHolder.b.setVisibility(0);
            viewHolder.a.setVisibility(8);
            viewHolder.b.setFixedHeight(this.c);
            viewHolder.b.a(tagInfo.getTagIcon());
        }
        AppMethodBeat.o(24352);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(24353, false);
        List<TagInfo> list = this.b;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(24353);
        return i;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private TextView a;
        private FitWidthImageView b;
        private FrameLayout c;

        public ViewHolder(View view) {
            super(view);
            AppMethodBeat.i(24348, false);
            this.c = (FrameLayout) view.findViewById(R.id.tv_layout);
            this.a = (TextView) view.findViewById(R.id.tv_tag_name);
            this.b = (FitWidthImageView) view.findViewById(R.id.fwv_tag_image);
            AppMethodBeat.o(24348);
        }
    }
}
