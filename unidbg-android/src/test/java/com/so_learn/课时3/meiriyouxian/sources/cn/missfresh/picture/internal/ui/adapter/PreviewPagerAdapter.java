package cn.missfresh.picture.internal.ui.adapter;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.ui.PreviewItemFragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class PreviewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<LocalMedia> a = new ArrayList<>();
    private a b;

    /* access modifiers changed from: package-private */
    public interface a {
        void a(int i);
    }

    public PreviewPagerAdapter(FragmentManager fragmentManager, a aVar) {
        super(fragmentManager);
        AppMethodBeat.i(17931, false);
        this.b = aVar;
        AppMethodBeat.o(17931);
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        AppMethodBeat.i(17933, false);
        PreviewItemFragment a2 = PreviewItemFragment.a(this.a.get(i));
        AppMethodBeat.o(17933);
        return a2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        AppMethodBeat.i(17934, false);
        int size = this.a.size();
        AppMethodBeat.o(17934);
        return size;
    }

    public int a(LocalMedia localMedia) {
        AppMethodBeat.i(17937, false);
        int indexOf = this.a.indexOf(localMedia);
        AppMethodBeat.o(17937);
        return indexOf;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(17940, false);
        super.setPrimaryItem(viewGroup, i, obj);
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(i);
        }
        AppMethodBeat.o(17940);
    }

    public LocalMedia a(int i) {
        AppMethodBeat.i(17943, false);
        LocalMedia localMedia = this.a.get(i);
        AppMethodBeat.o(17943);
        return localMedia;
    }

    public void a(List<LocalMedia> list) {
        AppMethodBeat.i(17946, false);
        this.a.addAll(list);
        AppMethodBeat.o(17946);
    }
}
