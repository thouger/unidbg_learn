package com.sobot.chat.widget.kpswitch.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.c;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView;
import com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView;
import com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsIndicatorView;
import com.sobot.chat.widget.kpswitch.view.plus.SobotPlusPageView;
import com.sobot.chat.widget.kpswitch.widget.a.c;
import com.sobot.chat.widget.kpswitch.widget.a.d;
import com.sobot.chat.widget.kpswitch.widget.adpater.PageSetAdapter;
import com.sobot.chat.widget.kpswitch.widget.adpater.b;
import com.sobot.chat.widget.kpswitch.widget.data.PageSetEntity;
import com.sobot.chat.widget.kpswitch.widget.data.PlusPageSetEntity;
import java.util.ArrayList;
import java.util.List;

public class ChattingPanelUploadView extends BaseChattingPanelView implements View.OnClickListener, EmoticonsFuncView.a {
    private List<b> b = new ArrayList();
    private List<b> c = new ArrayList();
    private int d = -1;
    private EmoticonsFuncView e;
    private EmoticonsIndicatorView f;
    private PageSetAdapter g;
    private a h;

    public interface a extends BaseChattingPanelView.a {
        void A();

        void B();

        void C();

        void D();

        void E();

        void a(boolean z);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView.a
    public void a(PageSetEntity pageSetEntity) {
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public String d() {
        return "ChattingPanelUploadView";
    }

    public ChattingPanelUploadView(Context context) {
        super(context);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public View a() {
        return View.inflate(this.a, b("sobot_upload_layout"), null);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public void b() {
        Information information = (Information) s.d(this.a, "sobot_last_current_info");
        int b2 = s.b(this.a, "sobot_msg_flag", 0);
        boolean b3 = s.b(this.a, "sobot_leave_msg_flag", false);
        this.e = (EmoticonsFuncView) c().findViewById(a("view_epv"));
        this.f = (EmoticonsIndicatorView) c().findViewById(a("view_eiv"));
        this.e.setOnIndicatorListener(this);
        b bVar = new b(c("sobot_tack_picture_button_selector"), f("sobot_upload"), "sobot_action_pic");
        b bVar2 = new b(c("sobot_tack_video_button_selector"), f("sobot_upload_video"), "sobot_action_video");
        b bVar3 = new b(c("sobot_camera_picture_button_selector"), f("sobot_attach_take_pic"), "sobot_action_camera");
        b bVar4 = new b(c("sobot_choose_file_btn_selector"), f("sobot_choose_file"), "sobot_action_choose_file");
        b bVar5 = new b(c("sobot_leavemsg_selector"), f("sobot_str_bottom_message"), "sobot_action_leavemsg");
        b bVar6 = new b(c("sobot_picture_satisfaction_selector"), f("sobot_str_bottom_satisfaction"), "sobot_action_satisfaction");
        this.b.clear();
        this.c.clear();
        if (information != null) {
            if (!information.isHideMenuLeave() && b2 == 0) {
                this.b.add(bVar5);
            }
            if (!information.isHideMenuSatisfaction()) {
                this.b.add(bVar6);
            }
            if (!information.isHideMenuPicture()) {
                this.c.add(bVar);
            }
            if (!information.isHideMenuVedio()) {
                this.c.add(bVar2);
            }
            if (!information.isHideMenuCamera()) {
                this.c.add(bVar3);
            }
            if (!information.isHideMenuFile()) {
                this.c.add(bVar4);
            }
            if (!information.isHideMenuLeave() && b2 == 0 && !b3) {
                this.c.add(bVar5);
            }
            if (!information.isHideMenuSatisfaction()) {
                this.c.add(bVar6);
                return;
            }
            return;
        }
        if (b2 == 0) {
            this.b.add(bVar5);
        }
        this.b.add(bVar6);
        this.c.add(bVar);
        this.c.add(bVar2);
        this.c.add(bVar3);
        this.c.add(bVar4);
        if (b2 == 0 && !b3) {
            this.c.add(bVar5);
        }
        this.c.add(bVar6);
    }

    public static class b {
        public int a;
        public String b;
        public String c;

        public b(int i, String str, String str2) {
            this.a = i;
            this.b = str;
            this.c = str2;
        }
    }

    private void a(List<b> list) {
        PageSetAdapter pageSetAdapter = this.g;
        if (pageSetAdapter == null) {
            this.g = new PageSetAdapter();
        } else {
            pageSetAdapter.a().clear();
        }
        this.g.b(new PlusPageSetEntity.a().a(e("sobot_plus_menu_line")).b(e("sobot_plus_menu_row")).a(list).a(new AnonymousClass1()).a());
        this.e.setAdapter(this.g);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView$1  reason: invalid class name */
    public class AnonymousClass1 implements c<com.sobot.chat.widget.kpswitch.widget.data.b> {
        AnonymousClass1() {
        }

        public View a(ViewGroup viewGroup, int i, com.sobot.chat.widget.kpswitch.widget.data.b bVar) {
            if (bVar.e() == null) {
                SobotPlusPageView sobotPlusPageView = new SobotPlusPageView(viewGroup.getContext());
                sobotPlusPageView.setNumColumns(bVar.c());
                bVar.a(sobotPlusPageView);
                try {
                    com.sobot.chat.widget.kpswitch.widget.adpater.b bVar2 = new com.sobot.chat.widget.kpswitch.widget.adpater.b(viewGroup.getContext(), bVar, ChattingPanelUploadView.this.h);
                    bVar2.a(ChattingPanelUploadView.this.a(ChattingPanelUploadView.this.h));
                    sobotPlusPageView.getGridView().setAdapter((ListAdapter) bVar2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return bVar.e();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView$2  reason: invalid class name */
    public class AnonymousClass2 implements d<Object> {
        AnonymousClass2() {
        }

        @Override // com.sobot.chat.widget.kpswitch.widget.a.d
        public void a(int i, ViewGroup viewGroup, b.a aVar, Object obj) {
            b bVar = (b) obj;
            if (bVar != null) {
                aVar.c.setText(bVar.b);
                Drawable drawable = ChattingPanelUploadView.this.a.getResources().getDrawable(bVar.a);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                aVar.c.setCompoundDrawables(null, drawable, null, null);
                aVar.c.setTag(bVar.c);
                aVar.a.setOnClickListener(ChattingPanelUploadView.this);
            }
        }
    }

    public d<Object> a(a aVar) {
        return new AnonymousClass2();
    }

    @Override // com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView.a
    public void a(int i, PageSetEntity pageSetEntity) {
        this.f.a(i, pageSetEntity);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView.a
    public void a(int i, int i2, PageSetEntity pageSetEntity) {
        this.f.a(i, i2, pageSetEntity);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public void a(BaseChattingPanelView.a aVar) {
        if (aVar != null && (aVar instanceof a)) {
            this.h = (a) aVar;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.h != null) {
            String str = (String) view.findViewById(a("sobot_plus_menu")).getTag();
            if ("sobot_action_satisfaction".equals(str)) {
                this.h.D();
            } else if ("sobot_action_leavemsg".equals(str)) {
                this.h.a(false);
            } else if ("sobot_action_pic".equals(str)) {
                this.h.A();
            } else if ("sobot_action_video".equals(str)) {
                this.h.B();
            } else if ("sobot_action_camera".equals(str)) {
                this.h.C();
            } else if ("sobot_action_choose_file".equals(str)) {
                this.h.E();
            } else if (c.a.c != null) {
                c.a.c.a(view, str);
            }
        }
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public void a(Bundle bundle) {
        int i = bundle.getInt("current_client_model");
        int i2 = this.d;
        if (i2 == -1 || i2 != i) {
            ArrayList arrayList = new ArrayList();
            if (bundle.getInt("current_client_model") == 301) {
                arrayList.addAll(this.b);
            } else {
                arrayList.addAll(this.c);
                if (c.a.b != null) {
                    arrayList.addAll(c.a.b);
                }
            }
            if (c.a.a != null) {
                arrayList.addAll(c.a.a);
            }
            a(arrayList);
        }
        this.d = i;
    }
}
