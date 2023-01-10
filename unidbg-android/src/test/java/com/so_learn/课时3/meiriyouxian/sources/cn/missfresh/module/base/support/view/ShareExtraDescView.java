package cn.missfresh.module.base.support.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.ShareExtraDescBean;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

public class ShareExtraDescView extends LinearLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;

    public ShareExtraDescView(Context context) {
        this(context, null);
    }

    public ShareExtraDescView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(22770, false);
        a();
        AppMethodBeat.o(22770);
    }

    private void a() {
        AppMethodBeat.i(22771, false);
        LayoutInflater.from(getContext()).inflate(R.layout.dialog_extral_desc_view, (ViewGroup) this, true);
        this.a = (TextView) findViewById(R.id.tv_share_extra_first);
        this.b = (TextView) findViewById(R.id.tv_share_extra_second);
        this.c = (TextView) findViewById(R.id.tv_share_extra_third);
        this.d = (TextView) findViewById(R.id.tv_share_in_dialog_text);
        AppMethodBeat.o(22771);
    }

    public void setData(ShareExtraDescBean shareExtraDescBean) {
        AppMethodBeat.i(22773, false);
        if (shareExtraDescBean == null) {
            setVisibility(8);
        } else if ("bargain_page".equals(shareExtraDescBean.getPageType())) {
            if (!b.a(shareExtraDescBean.getCutText())) {
                this.a.setVisibility(0);
                this.a.setTextSize(2, 20.0f);
                aw.a(this.a, shareExtraDescBean.getCutText(), getContext().getResources().getColor(R.color.color_FFED19), new String[]{"#_$", "#_$"}, true);
            } else {
                this.a.setVisibility(8);
            }
            if (!b.a(shareExtraDescBean.getNewUserText())) {
                this.d.setVisibility(0);
                this.d.setText(shareExtraDescBean.getNewUserText());
            } else {
                this.d.setVisibility(8);
            }
            this.b.setVisibility(8);
            this.c.setVisibility(8);
        } else {
            if (!b.a(shareExtraDescBean.getNewUserText())) {
                this.a.setVisibility(0);
                this.a.setTextSize(2, 16.0f);
                this.a.setText(shareExtraDescBean.getNewUserText());
                aw.a(this.a, shareExtraDescBean.getNewUserText(), getContext().getResources().getColor(R.color.color_FFED19), new String[]{"#_$", "#_$"}, true);
            } else {
                this.a.setVisibility(8);
            }
            if (!b.a(shareExtraDescBean.getCutText())) {
                this.b.setVisibility(0);
                aw.a(this.b, shareExtraDescBean.getCutText(), getContext().getResources().getColor(R.color.color_FFED19), new String[]{"#_$", "#_$"}, true);
            } else {
                this.b.setVisibility(8);
            }
            if (!b.a(shareExtraDescBean.getShareHint())) {
                this.c.setVisibility(0);
                aw.a(this.c, shareExtraDescBean.getShareHint(), getContext().getResources().getColor(R.color.color_FFED19), new String[]{"#_$", "#_$"}, true);
            } else {
                setVisibility(8);
            }
        }
        AppMethodBeat.o(22773);
    }
}
