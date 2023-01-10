package cn.missfresh.picture.internal.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.picture.R;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.roundiv.MFRoundedImageView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class SmallPictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private a a;
    private List<LocalMedia> b = new ArrayList();
    private RecyclerView c;
    private int d = -1;

    public interface a {
        void b(LocalMedia localMedia);
    }

    public SmallPictureAdapter() {
        AppMethodBeat.i(18189, false);
        AppMethodBeat.o(18189);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(18192, false);
        b bVar = new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_small_picture_item, viewGroup, false));
        AppMethodBeat.o(18192);
        return bVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(18195, false);
        ((b) viewHolder).a(this.b.get(i));
        AppMethodBeat.o(18195);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(18199, false);
        if (!this.b.isEmpty()) {
            i = this.b.size();
        }
        AppMethodBeat.o(18199);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        AppMethodBeat.i(18203, false);
        super.onAttachedToRecyclerView(recyclerView);
        this.c = recyclerView;
        AppMethodBeat.o(18203);
    }

    public void a(LocalMedia localMedia) {
        int i = 0;
        AppMethodBeat.i(18206, false);
        this.d = -1;
        if (localMedia == null) {
            notifyDataSetChanged();
            a();
            AppMethodBeat.o(18206);
            return;
        }
        List<LocalMedia> list = this.b;
        if (list != null && !list.isEmpty()) {
            while (true) {
                if (i >= this.b.size()) {
                    break;
                } else if (this.b.get(i).equals(localMedia)) {
                    this.d = i;
                    break;
                } else {
                    i++;
                }
            }
        }
        notifyDataSetChanged();
        int i2 = this.d;
        if (i2 > -1 && i2 < this.b.size()) {
            this.c.smoothScrollToPosition(this.d);
        }
        AppMethodBeat.o(18206);
    }

    public void b(LocalMedia localMedia) {
        AppMethodBeat.i(18208, false);
        this.b.add(localMedia);
        a(localMedia);
        AppMethodBeat.o(18208);
    }

    public void a(List<LocalMedia> list) {
        AppMethodBeat.i(18211, false);
        this.b.addAll(list);
        notifyDataSetChanged();
        a();
        AppMethodBeat.o(18211);
    }

    public void c(LocalMedia localMedia) {
        AppMethodBeat.i(18213, false);
        this.b.remove(localMedia);
        a((LocalMedia) null);
        AppMethodBeat.o(18213);
    }

    private void a() {
        RecyclerView recyclerView;
        AppMethodBeat.i(18215, false);
        if (!this.b.isEmpty() && (recyclerView = this.c) != null) {
            recyclerView.smoothScrollToPosition(this.b.size() - 1);
        }
        AppMethodBeat.o(18215);
    }

    class b extends RecyclerView.ViewHolder {
        private LinearLayout b;
        private MFRoundedImageView c;

        public b(View view) {
            super(view);
            AppMethodBeat.i(18955, false);
            this.b = (LinearLayout) view.findViewById(R.id.ll_small_root);
            this.c = (MFRoundedImageView) view.findViewById(R.id.iv_small_picture);
            this.c.setOnClickListener(new SmallPictureAdapter$SmallPictureHolder$1(this, SmallPictureAdapter.this));
            AppMethodBeat.o(18955);
        }

        public void a(LocalMedia localMedia) {
            AppMethodBeat.i(18958, false);
            if (getAdapterPosition() == SmallPictureAdapter.this.d) {
                LinearLayout linearLayout = this.b;
                linearLayout.setBackground(linearLayout.getResources().getDrawable(R.drawable.small_picture_selected));
            } else {
                LinearLayout linearLayout2 = this.b;
                linearLayout2.setBackgroundColor(linearLayout2.getResources().getColor(R.color.transparent));
            }
            Glide.with(this.itemView.getContext()).load(localMedia.a()).into(this.c);
            AppMethodBeat.o(18958);
        }
    }

    public void a(a aVar) {
        this.a = aVar;
    }
}
