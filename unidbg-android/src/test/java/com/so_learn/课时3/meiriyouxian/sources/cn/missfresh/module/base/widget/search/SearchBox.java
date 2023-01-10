package cn.missfresh.module.base.widget.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class SearchBox extends FrameLayout {
    private BannerView a;
    private View b;
    private boolean c;
    private a d;
    private Context e;

    public SearchBox(Context context) {
        this(context, null);
    }

    public SearchBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SearchBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(24286, false);
        this.c = true;
        this.e = context;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SearchBox, i, 0);
            this.c = obtainStyledAttributes.getBoolean(R.styleable.SearchBox_hasStroke, true);
            obtainStyledAttributes.recycle();
        }
        a(context);
        AppMethodBeat.o(24286);
    }

    private void a(Context context) {
        AppMethodBeat.i(24287, false);
        View inflate = LayoutInflater.from(context).inflate(R.layout.search_box_layout, (ViewGroup) null);
        this.b = inflate.findViewById(R.id.search_layout);
        this.a = (BannerView) inflate.findViewById(R.id.banner_search_text);
        if (this.c) {
            this.b.setBackground(ContextCompat.getDrawable(context, R.drawable.search_bg_stroke));
        } else {
            this.b.setBackground(ContextCompat.getDrawable(context, R.drawable.search_bg_no_stroke));
        }
        addView(inflate);
        AppMethodBeat.o(24287);
    }

    public void a(boolean z) {
        AppMethodBeat.i(24288, false);
        if (z) {
            this.b.setBackground(ContextCompat.getDrawable(this.e, R.drawable.search_bg_stroke));
        } else {
            this.b.setBackground(ContextCompat.getDrawable(this.e, R.drawable.search_bg_no_stroke));
        }
        AppMethodBeat.o(24288);
    }

    public void a(Context context, List<String> list) {
        AppMethodBeat.i(24289, false);
        this.d = new a(context, R.layout.search_box_text, list);
        this.a.a(this.d);
        AppMethodBeat.o(24289);
    }

    public String getKeyword() {
        AppMethodBeat.i(24290, false);
        a aVar = this.d;
        if (aVar != null) {
            String a = aVar.a(this.a.c());
            AppMethodBeat.o(24290);
            return a;
        }
        AppMethodBeat.o(24290);
        return "";
    }

    public void a() {
        AppMethodBeat.i(24291, false);
        BannerView bannerView = this.a;
        if (bannerView != null) {
            bannerView.a();
        }
        AppMethodBeat.o(24291);
    }

    public void b() {
        AppMethodBeat.i(24292, false);
        BannerView bannerView = this.a;
        if (bannerView != null) {
            bannerView.b();
        }
        AppMethodBeat.o(24292);
    }
}
