package cn.missfresh.picture.internal.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import cn.missfresh.picture.R;
import cn.missfresh.picture.b.b;
import cn.missfresh.picture.internal.a.d;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.entity.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class PreviewItemFragment extends Fragment {
    private b a;

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public static PreviewItemFragment a(LocalMedia localMedia) {
        AppMethodBeat.i(18168, false);
        PreviewItemFragment previewItemFragment = new PreviewItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("args_item", localMedia);
        previewItemFragment.setArguments(bundle);
        AppMethodBeat.o(18168);
        return previewItemFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(18171, false);
        View inflate = layoutInflater.inflate(R.layout.picture_fragment_preview_item, viewGroup, false);
        AppMethodBeat.o(18171);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(18174, false);
        super.onViewCreated(view, bundle);
        LocalMedia localMedia = (LocalMedia) getArguments().getParcelable("args_item");
        if (localMedia == null) {
            AppMethodBeat.o(18174);
            return;
        }
        View findViewById = view.findViewById(R.id.picture_video_play_btn);
        if (localMedia.e()) {
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new AnonymousClass1(localMedia));
        } else {
            findViewById.setVisibility(8);
        }
        ImageViewTouch imageViewTouch = (ImageViewTouch) view.findViewById(R.id.image_view);
        imageViewTouch.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        imageViewTouch.setSingleTapListener(new AnonymousClass2());
        Point a = d.a(localMedia.a(), getActivity());
        if (localMedia.d()) {
            c.a().p.b(getContext(), a.x, a.y, imageViewTouch, localMedia.a());
        } else {
            c.a().p.a(getContext(), a.x, a.y, imageViewTouch, localMedia.a());
        }
        AppMethodBeat.o(18174);
    }

    /* renamed from: cn.missfresh.picture.internal.ui.PreviewItemFragment$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ LocalMedia a;

        AnonymousClass1(LocalMedia localMedia) {
            this.a = localMedia;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(18861, true);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(this.a.c, "video/*");
            try {
                PreviewItemFragment.this.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(PreviewItemFragment.this.getContext(), R.string.error_no_video_activity, 0).show();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(18861);
        }
    }

    /* renamed from: cn.missfresh.picture.internal.ui.PreviewItemFragment$2  reason: invalid class name */
    class AnonymousClass2 implements ImageViewTouch.c {
        AnonymousClass2() {
        }

        @Override // it.sephiroth.android.library.imagezoom.ImageViewTouch.c
        public void a() {
            AppMethodBeat.i(18021, false);
            if (PreviewItemFragment.this.a != null) {
                PreviewItemFragment.this.a.b();
            }
            AppMethodBeat.o(18021);
        }
    }

    public void a() {
        AppMethodBeat.i(18177, false);
        if (getView() != null) {
            ((ImageViewTouch) getView().findViewById(R.id.image_view)).a();
        }
        AppMethodBeat.o(18177);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        AppMethodBeat.i(18180, false);
        super.onAttach(context);
        if (context instanceof b) {
            this.a = (b) context;
            AppMethodBeat.o(18180);
            return;
        }
        RuntimeException runtimeException = new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        AppMethodBeat.o(18180);
        throw runtimeException;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        AppMethodBeat.i(18181, false);
        super.onDetach();
        this.a = null;
        AppMethodBeat.o(18181);
    }
}
