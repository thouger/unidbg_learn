package cn.missfresh.picture.a.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

/* compiled from: GlideEngine */
public class a implements cn.missfresh.picture.a.a {
    public void a(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        AppMethodBeat.i(19250, false);
        Glide.with(context).asBitmap().load(uri).apply((BaseRequestOptions<?>) new RequestOptions().override(i, i).placeholder(drawable).centerCrop()).into(imageView);
        AppMethodBeat.o(19250);
    }

    public void b(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        AppMethodBeat.i(19254, false);
        Glide.with(context).asBitmap().load(uri).apply((BaseRequestOptions<?>) new RequestOptions().override(i, i).placeholder(drawable).centerCrop()).into(imageView);
        AppMethodBeat.o(19254);
    }

    public void a(Context context, int i, int i2, ImageView imageView, Uri uri) {
        AppMethodBeat.i(19258, false);
        Glide.with(context).load(uri).apply((BaseRequestOptions<?>) new RequestOptions().override(i, i2).priority(Priority.HIGH).fitCenter()).into(imageView);
        AppMethodBeat.o(19258);
    }

    public void b(Context context, int i, int i2, ImageView imageView, Uri uri) {
        AppMethodBeat.i(19262, false);
        Glide.with(context).asGif().load(uri).apply((BaseRequestOptions<?>) new RequestOptions().override(i, i2).priority(Priority.HIGH).fitCenter()).into(imageView);
        AppMethodBeat.o(19262);
    }
}
