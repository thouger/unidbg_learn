package com.sobot.chat.viewHolder;

import android.content.Intent;
import android.view.View;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.utils.d;
import com.sobot.chat.viewHolder.e;

/* access modifiers changed from: package-private */
public class RobotKeyWordMessageHolder$1 implements View.OnClickListener {
    final /* synthetic */ e a;

    RobotKeyWordMessageHolder$1(e eVar) {
        this.a = eVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(ZhiChiConstants.SOBOT_BROCAST_KEYWORD_CLICK);
        e.a aVar = (e.a) view.getTag();
        intent.putExtra("tempGroupId", aVar.a());
        intent.putExtra("keyword", aVar.b());
        intent.putExtra("keywordId", aVar.c());
        d.a(this.a.n, intent);
    }
}
