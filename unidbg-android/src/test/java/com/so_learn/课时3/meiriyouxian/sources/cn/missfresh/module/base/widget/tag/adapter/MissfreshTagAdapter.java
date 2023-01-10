package cn.missfresh.module.base.widget.tag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.FitWidthImageView;
import cn.missfresh.module.base.widget.tag.a;
import cn.missfresh.module.base.widget.tag.bean.TagInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class MissfreshTagAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LayoutInflater a;
    private List<TagInfo> b;
    private int c;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(24362, false);
        a((ViewHolder) viewHolder, i);
        AppMethodBeat.o(24362);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(24363, false);
        ViewHolder a = a(viewGroup, i);
        AppMethodBeat.o(24363);
        return a;
    }

    public MissfreshTagAdapter(Context context) {
        AppMethodBeat.i(24357, false);
        this.a = LayoutInflater.from(context);
        AppMethodBeat.o(24357);
    }

    public void a(List<TagInfo> list, int i) {
        AppMethodBeat.i(24358, false);
        this.b = list;
        this.c = i;
        notifyDataSetChanged();
        AppMethodBeat.o(24358);
    }

    public ViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(24359, false);
        ViewHolder viewHolder = new ViewHolder(this.a.inflate(R.layout.base_item_missfresh_tag, viewGroup, false));
        AppMethodBeat.o(24359);
        return viewHolder;
    }

    public void a(ViewHolder viewHolder, int i) {
        AppMethodBeat.i(24360, false);
        TagInfo tagInfo = this.b.get(i);
        if (tagInfo.getUseType() == 1) {
            viewHolder.b.setVisibility(8);
            viewHolder.c.setVisibility(8);
            viewHolder.a.setVisibility(0);
            a.a(tagInfo, viewHolder.a);
        } else if (tagInfo.getUseType() == 3) {
            viewHolder.b.setVisibility(8);
            viewHolder.c.setVisibility(0);
            viewHolder.a.setVisibility(8);
            a.a(tagInfo, viewHolder.c, viewHolder.d, viewHolder.e, false);
        } else {
            viewHolder.b.setVisibility(0);
            viewHolder.a.setVisibility(8);
            viewHolder.c.setVisibility(8);
            viewHolder.b.setFixedHeight(this.c);
            viewHolder.b.a(tagInfo.getTagIcon());
        }
        AppMethodBeat.o(24360);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(24361, false);
        List<TagInfo> list = this.b;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(24361);
        return i;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private TextView a;
        private FitWidthImageView b;
        private View c;
        private ImageView d;
        private TextView e;

        public ViewHolder(View view) {
            super(view);
            AppMethodBeat.i(24356, false);
            this.a = (TextView) view.findViewById(R.id.tv_tag_name);
            this.b = (FitWidthImageView) view.findViewById(R.id.fwv_tag_image);
            this.c = view.findViewById(R.id.ll_tag_text);
            this.e = (TextView) view.findViewById(R.id.iv_icon_text_text);
            this.d = (ImageView) view.findViewById(R.id.iv_icon_text_icon);
            AppMethodBeat.o(24356);
        }
    }
}
