package com.sobot.chat.widget;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import com.sobot.chat.activity.WebViewActivity;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.t;

/* access modifiers changed from: package-private */
public class SelectPicPopupWindow$3 implements View.OnClickListener {
    final /* synthetic */ b a;

    SelectPicPopupWindow$3(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.dismiss();
        if (view == this.a.a) {
            m.d("imgUrl:" + this.a.e);
            if (this.a.g.equals("gif")) {
                b bVar = this.a;
                bVar.a(bVar.f, this.a.e);
            } else {
                Bitmap a = t.a(this.a.e, this.a.f, true);
                b bVar2 = this.a;
                bVar2.a(bVar2.f, a);
            }
        }
        Button unused = this.a.b;
        if (view != this.a.c) {
            return;
        }
        if (this.a.i == null || this.a.i.length != 1) {
            this.a.c.setVisibility(8);
            return;
        }
        Intent intent = new Intent(this.a.f, WebViewActivity.class);
        intent.putExtra("url", this.a.i[0].a());
        intent.addFlags(268435456);
        this.a.f.startActivity(intent);
    }
}
