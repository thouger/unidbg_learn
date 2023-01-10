package cn.missfresh.unitepoplib.view.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.R;
import cn.missfresh.unitepoplib.UnitePopManager;
import cn.missfresh.unitepoplib.bean.PicDialogBean;
import cn.missfresh.unitepoplib.listener.b;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class AdPicAdapter extends PagerAdapter {
    private Context a;
    private List<PicDialogBean> b = new ArrayList();

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public AdPicAdapter(Context context) {
        AppMethodBeat.i(15656, false);
        this.a = context;
        AppMethodBeat.o(15656);
    }

    public void a(List<PicDialogBean> list) {
        AppMethodBeat.i(15658, false);
        if (list == null) {
            this.b = new ArrayList();
        } else {
            this.b = list;
        }
        AppMethodBeat.o(15658);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        AppMethodBeat.i(15661, false);
        int size = this.b.size();
        AppMethodBeat.o(15661);
        return size;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(15665, false);
        viewGroup.removeView((View) obj);
        AppMethodBeat.o(15665);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(15669, false);
        View inflate = LayoutInflater.from(this.a.getApplicationContext()).inflate(R.layout.pic_dialog_item, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
        if (i < this.b.size()) {
            PicDialogBean picDialogBean = this.b.get(i);
            Glide.with(this.a).load(picDialogBean.getImgUrl()).placeholder(R.drawable.ic_default_200).error(R.drawable.ic_default_200).into(imageView);
            imageView.setOnClickListener(new AnonymousClass1(picDialogBean));
        }
        viewGroup.addView(inflate);
        AppMethodBeat.o(15669);
        return inflate;
    }

    /* renamed from: cn.missfresh.unitepoplib.view.dialog.AdPicAdapter$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ PicDialogBean a;

        AnonymousClass1(PicDialogBean picDialogBean) {
            this.a = picDialogBean;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(15651, false);
            b.a().a(1003, UnitePopManager.a(), this.a);
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(15651);
        }
    }
}
