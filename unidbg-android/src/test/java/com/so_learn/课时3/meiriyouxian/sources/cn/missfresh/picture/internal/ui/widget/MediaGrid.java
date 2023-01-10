package cn.missfresh.picture.internal.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.picture.R;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.entity.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MediaGrid extends SquareFrameLayout implements View.OnClickListener {
    private ImageView a;
    private CheckView b;
    private ImageView c;
    private TextView d;
    private LocalMedia e;
    private b f;
    private a g;

    public interface a {
        void a(ImageView imageView, LocalMedia localMedia, RecyclerView.ViewHolder viewHolder);

        void a(CheckView checkView, LocalMedia localMedia, RecyclerView.ViewHolder viewHolder);
    }

    public MediaGrid(Context context) {
        super(context);
        AppMethodBeat.i(18916, false);
        a(context);
        AppMethodBeat.o(18916);
    }

    public MediaGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(18918, false);
        a(context);
        AppMethodBeat.o(18918);
    }

    private void a(Context context) {
        AppMethodBeat.i(18921, false);
        LayoutInflater.from(context).inflate(R.layout.media_grid_content, (ViewGroup) this, true);
        this.a = (ImageView) findViewById(R.id.media_thumbnail);
        this.b = (CheckView) findViewById(R.id.check_view);
        this.c = (ImageView) findViewById(R.id.gif);
        this.d = (TextView) findViewById(R.id.video_duration);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        AppMethodBeat.o(18921);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(18923, false);
        a aVar = this.g;
        if (aVar != null) {
            ImageView imageView = this.a;
            if (view == imageView) {
                aVar.a(imageView, this.e, this.f.d);
            } else {
                CheckView checkView = this.b;
                if (view == checkView) {
                    aVar.a(checkView, this.e, this.f.d);
                }
            }
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(18923);
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public void a(LocalMedia localMedia) {
        AppMethodBeat.i(18925, false);
        this.e = localMedia;
        a();
        b();
        c();
        d();
        AppMethodBeat.o(18925);
    }

    public LocalMedia getMedia() {
        return this.e;
    }

    private void a() {
        int i = 0;
        AppMethodBeat.i(18928, false);
        ImageView imageView = this.c;
        if (!this.e.d()) {
            i = 8;
        }
        imageView.setVisibility(i);
        AppMethodBeat.o(18928);
    }

    private void b() {
        AppMethodBeat.i(18930, false);
        this.b.setCountable(this.f.c);
        AppMethodBeat.o(18930);
    }

    public void setCheckEnabled(boolean z) {
        AppMethodBeat.i(18931, false);
        this.b.setEnabled(z);
        AppMethodBeat.o(18931);
    }

    public void setCheckedNum(int i) {
        AppMethodBeat.i(18933, false);
        this.b.setCheckedNum(i);
        AppMethodBeat.o(18933);
    }

    public void setChecked(boolean z) {
        AppMethodBeat.i(18934, false);
        this.b.setChecked(z);
        AppMethodBeat.o(18934);
    }

    private void c() {
        AppMethodBeat.i(18936, false);
        if (this.e.d()) {
            c.a().p.b(getContext(), this.f.a, this.f.b, this.a, this.e.a());
        } else {
            c.a().p.a(getContext(), this.f.a, this.f.b, this.a, this.e.a());
        }
        AppMethodBeat.o(18936);
    }

    private void d() {
        AppMethodBeat.i(18938, false);
        if (this.e.e()) {
            this.d.setVisibility(0);
            this.d.setText(DateUtils.formatElapsedTime(this.e.g / 1000));
        } else {
            this.d.setVisibility(8);
        }
        AppMethodBeat.o(18938);
    }

    public void setOnMediaGridClickListener(a aVar) {
        this.g = aVar;
    }

    public static class b {
        int a;
        Drawable b;
        boolean c;
        RecyclerView.ViewHolder d;

        public b(int i, Drawable drawable, boolean z, RecyclerView.ViewHolder viewHolder) {
            this.a = i;
            this.b = drawable;
            this.c = z;
            this.d = viewHolder;
        }
    }
}
