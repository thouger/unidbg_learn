package com.sobot.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.TtmlUtils;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sobot.chat.activity.SobotFileDetailActivity;
import com.sobot.chat.activity.SobotPhotoActivity;
import com.sobot.chat.activity.SobotVideoActivity;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.api.model.SobotFileModel;
import com.sobot.chat.api.model.SobotUserTicketEvaluate;
import com.sobot.chat.api.model.SobotUserTicketInfo;
import com.sobot.chat.api.model.StUserDealTicketInfo;
import com.sobot.chat.api.model.StUserDealTicketReply;
import com.sobot.chat.b.a;
import com.sobot.chat.utils.ac;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.widget.StExpandableTextView;
import com.sobot.chat.widget.attachment.AttachmentView;
import com.sobot.chat.widget.attachment.FileAttachmentAdapter;
import com.sobot.chat.widget.attachment.SpaceItemDecoration;
import java.util.List;

/* compiled from: SobotTicketDetailAdapter */
public class m extends com.sobot.chat.adapter.base.a<Object> {
    private static final String[] g = {"sobot_ticket_detail_head_item", "sobot_ticket_detail_created_item", "sobot_ticket_detail_processing_item", "sobot_ticket_detail_completed_item", "sobot_ticket_detail_foot_item"};
    AttachmentView.a a;
    private Context d;
    private Activity e;
    private int f;

    /* compiled from: SobotTicketDetailAdapter */
    /* renamed from: com.sobot.chat.adapter.m$1  reason: invalid class name */
    class AnonymousClass1 implements AttachmentView.a {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.widget.attachment.AttachmentView.a
        public void a(SobotFileModel sobotFileModel, int i) {
            Intent intent = new Intent(m.this.d, SobotFileDetailActivity.class);
            SobotCacheFile sobotCacheFile = new SobotCacheFile();
            sobotCacheFile.setFileName(sobotFileModel.getFileName());
            sobotCacheFile.setUrl(sobotFileModel.getFileUrl());
            sobotCacheFile.setFileType(com.sobot.chat.widget.attachment.a.a(sobotFileModel.getFileType()));
            sobotCacheFile.setMsgId(sobotFileModel.getFileId());
            intent.putExtra("sobot_intent_data_selected_file", sobotCacheFile);
            intent.setFlags(268435456);
            m.this.d.startActivity(intent);
        }

        @Override // com.sobot.chat.widget.attachment.AttachmentView.a
        public void b(SobotFileModel sobotFileModel, int i) {
            SobotCacheFile sobotCacheFile = new SobotCacheFile();
            sobotCacheFile.setFileName(sobotFileModel.getFileName());
            sobotCacheFile.setUrl(sobotFileModel.getFileUrl());
            sobotCacheFile.setFileType(com.sobot.chat.widget.attachment.a.a(sobotFileModel.getFileType()));
            sobotCacheFile.setMsgId(sobotFileModel.getFileId());
            m.this.d.startActivity(SobotVideoActivity.a(m.this.d, sobotCacheFile));
        }

        @Override // com.sobot.chat.widget.attachment.AttachmentView.a
        public void a(String str, String str2, int i) {
            Intent intent = new Intent(m.this.c, SobotPhotoActivity.class);
            intent.putExtra("imageUrL", str);
            m.this.c.startActivity(intent);
        }
    }

    public m(Activity activity, Context context, List list) {
        this(activity, context, list, 2);
    }

    public m(Activity activity, Context context, List list, int i) {
        super(context, list);
        this.a = new AnonymousClass1();
        this.d = context;
        this.e = activity;
        this.f = i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Object obj = this.b.get(i);
        if (obj == null) {
            return view;
        }
        View a2 = a(view, getItemViewType(i), i, obj);
        ((a) a2.getTag()).a(obj, i);
        return a2;
    }

    private View a(View view, int i, int i2, Object obj) {
        Object obj2;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, g[i]), (ViewGroup) null);
            if (i == 0) {
                obj2 = new b(this.c, view);
            } else if (i == 1) {
                obj2 = new c(this.c, view);
            } else if (i == 2) {
                obj2 = new d(this.c, view);
            } else if (i == 3) {
                obj2 = new e(this.c, view);
            } else if (i != 4) {
                obj2 = new b(this.c, view);
            } else {
                obj2 = new f(this.c, view);
            }
            view.setTag(obj2);
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        String[] strArr = g;
        if (strArr.length > 0) {
            return strArr.length;
        }
        return super.getViewTypeCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        Object obj = this.b.get(i);
        if (obj instanceof SobotUserTicketInfo) {
            return 0;
        }
        if (obj instanceof StUserDealTicketInfo) {
            StUserDealTicketInfo stUserDealTicketInfo = (StUserDealTicketInfo) obj;
            if (stUserDealTicketInfo.getFlag() == 1) {
                return 1;
            }
            if (stUserDealTicketInfo.getFlag() == 2) {
                return 2;
            }
            if (stUserDealTicketInfo.getFlag() == 3) {
                return 3;
            }
        } else if (obj instanceof SobotUserTicketEvaluate) {
            return 4;
        }
        return 0;
    }

    /* compiled from: SobotTicketDetailAdapter */
    static abstract class a {
        Context a;

        /* access modifiers changed from: package-private */
        public abstract void a(Object obj, int i);

        a(Context context, View view) {
            this.a = context;
        }
    }

    /* compiled from: SobotTicketDetailAdapter */
    /* access modifiers changed from: package-private */
    public class b extends a {
        private TextView c;
        private StExpandableTextView d;
        private ImageView e = this.d.getImageView();
        private TextView f = this.d.getTextBtn();
        private TextView g;
        private TextView h;
        private RecyclerView i;
        private Context j;
        private int k;
        private int l;
        private int m;
        private String n;
        private String o;
        private String p;

        b(Context context, View view) {
            super(context, view);
            this.j = context;
            this.c = (TextView) view.findViewById(q.g(context, "sobot_tv_title"));
            this.d = (StExpandableTextView) view.findViewById(q.g(context, "sobot_content_fl"));
            this.d.setOnExpandStateChangeListener(new AnonymousClass1(m.this));
            this.f.setText(q.f(this.j, "sobot_notice_expand"));
            this.e.setImageResource(q.e(this.j, "sobot_icon_arrow_down"));
            this.g = (TextView) view.findViewById(q.g(context, "sobot_tv_time"));
            ViewGroup viewGroup = this.d.getmOtherView();
            if (viewGroup != null) {
                this.i = (RecyclerView) viewGroup.findViewById(q.g(context, "sobot_attachment_file_layout"));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                this.i.addItemDecoration(new SpaceItemDecoration(r.a(this.j, 10.0f), r.a(this.j, 10.0f), 0, 1));
                this.i.setLayoutManager(gridLayoutManager);
            }
            this.h = (TextView) view.findViewById(q.g(context, "sobot_tv_ticket_status"));
            this.k = q.e(context, "sobot_ticket_status_bg3");
            this.l = q.e(context, "sobot_ticket_status_bg2");
            this.m = q.e(context, "sobot_ticket_status_bg1");
            this.n = q.f(context, "sobot_created_1");
            this.o = q.f(context, "sobot_processing");
            this.p = q.f(context, "sobot_completed");
        }

        /* compiled from: SobotTicketDetailAdapter */
        /* renamed from: com.sobot.chat.adapter.m$b$1  reason: invalid class name */
        class AnonymousClass1 implements StExpandableTextView.b {
            final /* synthetic */ m a;

            AnonymousClass1(m mVar) {
                this.a = mVar;
            }

            @Override // com.sobot.chat.widget.StExpandableTextView.b
            public void a(TextView textView, boolean z) {
                if (z) {
                    b.this.f.setText(q.f(b.this.j, "sobot_notice_collapse"));
                } else {
                    b.this.f.setText(q.f(b.this.j, "sobot_notice_expand"));
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.adapter.m.a
        public void a(Object obj, int i) {
            m mVar = m.this;
            boolean z = false;
            mVar.a(mVar.e, this.g, 0);
            m mVar2 = m.this;
            mVar2.a(mVar2.e, this.d, 0);
            SobotUserTicketInfo sobotUserTicketInfo = (SobotUserTicketInfo) obj;
            if (sobotUserTicketInfo != null && !TextUtils.isEmpty(sobotUserTicketInfo.getContent())) {
                String str = "";
                String replaceAll = sobotUserTicketInfo.getContent().replaceAll("<br/>", str).replace("<p></p>", str).replaceAll("<p>", str).replaceAll("</p>", "<br/>").replaceAll("\n", "<br/>");
                StExpandableTextView stExpandableTextView = this.d;
                CharSequence charSequence = str;
                if (!TextUtils.isEmpty(sobotUserTicketInfo.getContent())) {
                    charSequence = Html.fromHtml(replaceAll);
                }
                stExpandableTextView.setText(charSequence);
            }
            int d = q.d(m.this.c, "sobot_common_text_gray");
            if (2 == sobotUserTicketInfo.getFlag()) {
                this.h.setText(this.o);
                this.h.setBackgroundResource(this.l);
            } else if (3 == sobotUserTicketInfo.getFlag()) {
                this.h.setText(this.p);
                this.h.setBackgroundResource(this.m);
            } else {
                this.h.setText(this.n);
                this.h.setBackgroundResource(this.k);
            }
            this.g.setText(com.sobot.chat.utils.f.a(sobotUserTicketInfo.getTimeStr(), com.sobot.chat.utils.f.i, Boolean.valueOf(com.sobot.chat.d.a(8))));
            StExpandableTextView stExpandableTextView2 = this.d;
            if (sobotUserTicketInfo.getFileList() != null && sobotUserTicketInfo.getFileList().size() > 0) {
                z = true;
            }
            stExpandableTextView2.setHaveFile(z);
            this.i.setAdapter(new FileAttachmentAdapter(m.this.c, sobotUserTicketInfo.getFileList(), d, m.this.a));
        }
    }

    /* compiled from: SobotTicketDetailAdapter */
    /* access modifiers changed from: package-private */
    public class c extends a {
        private TextView c;
        private LinearLayout d;
        private TextView e;
        private TextView f;
        private View g;
        private View h;
        private TextView i;

        c(Context context, View view) {
            super(context, view);
            this.d = (LinearLayout) view.findViewById(q.g(context, "sobot_ll_root"));
            this.i = (TextView) view.findViewById(q.g(context, "sobot_tv_icon2"));
            this.e = (TextView) view.findViewById(q.g(context, "sobot_tv_status"));
            this.e.setText(q.f(context, "sobot_created_1"));
            this.c = (TextView) view.findViewById(q.g(context, "sobot_tv_time"));
            this.f = (TextView) view.findViewById(q.g(context, "sobot_tv_secod"));
            this.g = view.findViewById(q.g(context, "sobot_line_view"));
            this.h = view.findViewById(q.g(context, "sobot_line_split"));
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.adapter.m.a
        public void a(Object obj, int i) {
            LinearLayout.LayoutParams layoutParams;
            m mVar = m.this;
            mVar.a(mVar.e, this.d, r.a(m.this.c, 20.0f));
            if (i == 1) {
                layoutParams = new LinearLayout.LayoutParams(r.a(m.this.c, 19.0f), r.a(m.this.c, 19.0f));
                this.c.setSelected(true);
                this.e.setSelected(true);
                this.f.setSelected(true);
                this.i.setSelected(true);
                this.h.setVisibility(0);
                this.g.setBackgroundColor(Color.parseColor("#00000000"));
                this.d.setPadding(r.a(m.this.c, 20.0f), r.a(m.this.c, 30.0f), r.a(m.this.c, 20.0f), r.a(m.this.c, 30.0f));
            } else {
                this.c.setSelected(false);
                this.e.setSelected(false);
                this.f.setSelected(false);
                this.i.setSelected(false);
                layoutParams = new LinearLayout.LayoutParams(r.a(m.this.c, 14.0f), r.a(m.this.c, 14.0f));
                this.h.setVisibility(8);
                this.g.setBackgroundColor(ContextCompat.getColor(m.this.c, q.c(m.this.c, "sobot_ticket_deal_line_grey")));
                this.d.setPadding(r.a(m.this.c, 20.0f), 0, r.a(m.this.c, 20.0f), r.a(m.this.c, 30.0f));
            }
            this.i.setLayoutParams(layoutParams);
            StUserDealTicketInfo stUserDealTicketInfo = (StUserDealTicketInfo) obj;
            this.c.setText(com.sobot.chat.utils.f.a(stUserDealTicketInfo.getTimeStr(), "MM-dd", Boolean.valueOf(com.sobot.chat.d.a(8))));
            this.f.setText(com.sobot.chat.utils.f.a(stUserDealTicketInfo.getTimeStr(), "HH:mm", Boolean.valueOf(com.sobot.chat.d.a(8))));
        }
    }

    /* compiled from: SobotTicketDetailAdapter */
    /* access modifiers changed from: package-private */
    public class d extends a {
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private TextView h;
        private View i;
        private LinearLayout j;
        private LinearLayout k;
        private View l;
        private View m;
        private LinearLayout n;
        private RecyclerView o;

        d(Context context, View view) {
            super(context, view);
            this.n = (LinearLayout) view.findViewById(q.g(context, "sobot_ll_root"));
            this.e = (TextView) view.findViewById(q.g(context, "sobot_tv_icon2"));
            this.f = (TextView) view.findViewById(q.g(context, "sobot_tv_status"));
            this.c = (TextView) view.findViewById(q.g(context, "sobot_tv_time"));
            this.d = (TextView) view.findViewById(q.g(context, "sobot_tv_secod"));
            this.k = (LinearLayout) view.findViewById(q.g(context, "sobot_tv_content_ll"));
            this.g = (TextView) view.findViewById(q.g(context, "sobot_tv_content"));
            this.i = view.findViewById(q.g(context, "sobot_tv_content_detail_split"));
            this.h = (TextView) view.findViewById(q.g(context, "sobot_tv_content_detail"));
            this.h.setText(q.f(context, "sobot_see_detail"));
            this.j = (LinearLayout) view.findViewById(q.g(context, "sobot_ll_container"));
            this.m = view.findViewById(q.g(context, "sobot_top_line_view"));
            this.l = view.findViewById(q.g(context, "sobot_line_split"));
            this.o = (RecyclerView) view.findViewById(q.g(context, "sobot_attachment_file_layout"));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            this.o.addItemDecoration(new SpaceItemDecoration(r.a(this.a, 10.0f), r.a(this.a, 10.0f), 0, 1));
            this.o.setLayoutManager(gridLayoutManager);
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.adapter.m.a
        public void a(Object obj, int i) {
            LinearLayout.LayoutParams layoutParams;
            int i2;
            CharSequence charSequence;
            m mVar = m.this;
            mVar.a(mVar.e, this.n, r.a(m.this.c, 20.0f));
            if (i == 1) {
                layoutParams = new LinearLayout.LayoutParams(r.a(m.this.c, 19.0f), r.a(m.this.c, 19.0f));
                this.c.setSelected(true);
                this.d.setSelected(true);
                this.e.setSelected(true);
                this.f.setSelected(true);
                this.j.setSelected(true);
                i2 = q.d(m.this.c, "sobot_common_gray1");
                this.m.setBackgroundColor(Color.parseColor("#00000000"));
                this.l.setVisibility(0);
                this.e.setBackgroundResource(q.e(m.this.c, "sobot_icon_processing_point_selector_2"));
                this.n.setPadding(r.a(m.this.c, 20.0f), r.a(m.this.c, 30.0f), r.a(m.this.c, 20.0f), 0);
            } else {
                this.c.setSelected(false);
                this.d.setSelected(false);
                this.e.setSelected(false);
                this.f.setSelected(false);
                this.j.setSelected(false);
                i2 = q.d(m.this.c, "sobot_common_text_gray");
                layoutParams = new LinearLayout.LayoutParams(r.a(m.this.c, 14.0f), r.a(m.this.c, 14.0f));
                this.e.setBackgroundResource(q.e(m.this.c, "sobot_icon_processing_point_selector"));
                this.m.setBackgroundColor(ContextCompat.getColor(m.this.c, q.c(m.this.c, "sobot_ticket_deal_line_grey")));
                this.l.setVisibility(8);
                this.n.setPadding(r.a(m.this.c, 20.0f), 0, r.a(m.this.c, 20.0f), 0);
            }
            this.e.setLayoutParams(layoutParams);
            StUserDealTicketInfo stUserDealTicketInfo = (StUserDealTicketInfo) obj;
            StUserDealTicketReply reply = stUserDealTicketInfo.getReply();
            String str = "";
            if (reply != null) {
                if (reply.getStartType() == 0) {
                    this.f.setVisibility(0);
                    this.f.setText(q.f(m.this.c, "sobot_processing"));
                    if (TextUtils.isEmpty(reply.getReplyContent())) {
                        this.k.setBackgroundDrawable(null);
                        this.h.setVisibility(8);
                        this.h.setOnClickListener(null);
                        this.i.setVisibility(8);
                        this.g.setPadding(0, 0, 0, 0);
                    } else {
                        if (ac.b(reply.getReplyContent()).size() > 0) {
                            this.k.setBackgroundDrawable(m.this.c.getResources().getDrawable(q.e(m.this.c, "sobot_round_ticket")));
                            this.h.setVisibility(0);
                            this.i.setVisibility(0);
                            this.g.setPadding(r.a(m.this.c, 15.0f), r.a(m.this.c, 10.0f), r.a(m.this.c, 15.0f), r.a(m.this.c, 10.0f));
                            this.h.setPadding(r.a(m.this.c, 15.0f), r.a(m.this.c, 11.0f), r.a(m.this.c, 15.0f), r.a(m.this.c, 11.0f));
                            this.h.setOnClickListener(new SobotTicketDetailAdapter$Type2ViewHolder$1(this, reply));
                        } else {
                            this.k.setBackgroundDrawable(null);
                            this.h.setVisibility(8);
                            this.h.setOnClickListener(null);
                            this.i.setVisibility(8);
                            this.g.setPadding(0, 0, 0, 0);
                        }
                        j a = j.a(m.this.c);
                        TextView textView = this.g;
                        String replaceAll = reply.getReplyContent().replaceAll("<br/>", str).replaceAll("\n", "<br/>");
                        a.a(textView, replaceAll.replaceAll("<img.*?/>", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + q.f(m.this.c, "sobot_upload") + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER), m.this.a());
                    }
                } else {
                    this.k.setBackgroundDrawable(null);
                    this.h.setVisibility(8);
                    this.h.setOnClickListener(null);
                    this.i.setVisibility(8);
                    this.g.setPadding(0, 0, 0, 0);
                    this.f.setVisibility(0);
                    this.f.setText(q.f(m.this.c, "sobot_my_reply"));
                    TextView textView2 = this.g;
                    if (TextUtils.isEmpty(reply.getReplyContent())) {
                        charSequence = q.f(m.this.c, "sobot_nothing");
                    } else {
                        String replyContent = reply.getReplyContent();
                        charSequence = Html.fromHtml(replyContent.replaceAll("<img.*?/>", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + q.f(m.this.c, "sobot_upload") + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER));
                    }
                    textView2.setText(charSequence);
                }
                this.c.setText(com.sobot.chat.utils.f.a(reply.getReplyTime() * 1000, com.sobot.chat.utils.f.g));
                this.d.setText(com.sobot.chat.utils.f.a(reply.getReplyTime() * 1000, com.sobot.chat.utils.f.d));
                this.o.setAdapter(new FileAttachmentAdapter(m.this.c, stUserDealTicketInfo.getFileList(), i2, m.this.a));
                return;
            }
            this.f.setVisibility(8);
            TextView textView3 = this.g;
            CharSequence charSequence2 = str;
            if (!TextUtils.isEmpty(stUserDealTicketInfo.getContent())) {
                charSequence2 = Html.fromHtml(stUserDealTicketInfo.getContent().replaceAll("<p>", str).replaceAll("</p>", str));
            }
            textView3.setText(charSequence2);
            this.c.setText(com.sobot.chat.utils.f.a(stUserDealTicketInfo.getTimeStr(), "MM-dd", Boolean.valueOf(com.sobot.chat.d.a(8))));
            this.d.setText(com.sobot.chat.utils.f.a(stUserDealTicketInfo.getTimeStr(), "HH:mm", Boolean.valueOf(com.sobot.chat.d.a(8))));
        }
    }

    /* compiled from: SobotTicketDetailAdapter */
    /* access modifiers changed from: package-private */
    public class e extends a {
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private View h;
        private LinearLayout i;
        private RecyclerView j;
        private TextView k;
        private View l;
        private View m;
        private LinearLayout n;

        e(Context context, View view) {
            super(context, view);
            this.i = (LinearLayout) view.findViewById(q.g(context, "sobot_ll_root"));
            this.e = (TextView) view.findViewById(q.g(context, "sobot_tv_icon2"));
            this.f = (TextView) view.findViewById(q.g(context, "sobot_tv_status"));
            this.c = (TextView) view.findViewById(q.g(context, "sobot_tv_time"));
            this.d = (TextView) view.findViewById(q.g(context, "sobot_tv_secod"));
            this.g = (TextView) view.findViewById(q.g(context, "sobot_tv_content"));
            this.n = (LinearLayout) view.findViewById(q.g(context, "sobot_tv_content_ll"));
            this.m = view.findViewById(q.g(context, "sobot_tv_content_detail_split"));
            this.k = (TextView) view.findViewById(q.g(context, "sobot_tv_content_detail"));
            this.k.setText(q.f(context, "sobot_see_detail"));
            this.l = view.findViewById(q.g(context, "sobot_top_line_view_slip"));
            this.h = view.findViewById(q.g(context, "sobot_top_line_view"));
            this.j = (RecyclerView) view.findViewById(q.g(context, "sobot_attachment_file_layout"));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            this.j.addItemDecoration(new SpaceItemDecoration(r.a(this.a, 10.0f), r.a(this.a, 10.0f), 0, 1));
            this.j.setLayoutManager(gridLayoutManager);
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.adapter.m.a
        public void a(Object obj, int i) {
            LinearLayout.LayoutParams layoutParams;
            int i2;
            m mVar = m.this;
            mVar.a(mVar.e, this.i, r.a(m.this.c, 20.0f));
            if (i == 1) {
                layoutParams = new LinearLayout.LayoutParams(r.a(m.this.c, 19.0f), r.a(m.this.c, 19.0f));
                this.c.setSelected(true);
                this.d.setSelected(true);
                this.e.setSelected(true);
                this.f.setSelected(true);
                this.g.setSelected(true);
                this.l.setVisibility(0);
                i2 = q.d(m.this.c, "sobot_common_gray1");
                this.h.setBackgroundColor(Color.parseColor("#00000000"));
                this.i.setPadding(r.a(m.this.c, 20.0f), r.a(m.this.c, 30.0f), r.a(m.this.c, 20.0f), 0);
            } else {
                this.c.setSelected(false);
                this.d.setSelected(false);
                this.e.setSelected(false);
                this.f.setSelected(false);
                this.g.setSelected(false);
                this.l.setVisibility(8);
                i2 = q.d(m.this.c, "sobot_common_text_gray");
                this.h.setBackgroundColor(ContextCompat.getColor(m.this.c, q.c(m.this.c, "sobot_ticket_deal_line_grey")));
                this.i.setPadding(r.a(m.this.c, 20.0f), 0, r.a(m.this.c, 20.0f), 0);
                layoutParams = new LinearLayout.LayoutParams(r.a(m.this.c, 14.0f), r.a(m.this.c, 14.0f));
            }
            this.e.setLayoutParams(layoutParams);
            StUserDealTicketInfo stUserDealTicketInfo = (StUserDealTicketInfo) obj;
            this.c.setText(com.sobot.chat.utils.f.a(stUserDealTicketInfo.getTimeStr(), "MM-dd", Boolean.valueOf(com.sobot.chat.d.a(8))));
            this.d.setText(com.sobot.chat.utils.f.a(stUserDealTicketInfo.getTimeStr(), "HH:mm", Boolean.valueOf(com.sobot.chat.d.a(8))));
            if (TextUtils.isEmpty(stUserDealTicketInfo.getContent())) {
                this.n.setBackgroundDrawable(null);
                this.k.setVisibility(8);
                this.k.setOnClickListener(null);
                this.m.setVisibility(8);
                this.g.setPadding(0, 0, 0, 0);
            } else {
                if (ac.b(stUserDealTicketInfo.getContent()).size() > 0) {
                    this.n.setBackgroundDrawable(m.this.c.getResources().getDrawable(q.e(m.this.c, "sobot_round_ticket")));
                    this.k.setVisibility(0);
                    this.m.setVisibility(0);
                    this.g.setPadding(r.a(m.this.c, 15.0f), r.a(m.this.c, 11.0f), r.a(m.this.c, 15.0f), r.a(m.this.c, 11.0f));
                    this.k.setPadding(r.a(m.this.c, 15.0f), r.a(m.this.c, 11.0f), r.a(m.this.c, 15.0f), r.a(m.this.c, 11.0f));
                    this.k.setOnClickListener(new SobotTicketDetailAdapter$Type3ViewHolder$1(this, stUserDealTicketInfo));
                } else {
                    this.n.setBackgroundDrawable(null);
                    this.k.setVisibility(8);
                    this.k.setOnClickListener(null);
                    this.m.setVisibility(8);
                    this.g.setPadding(0, 0, 0, 0);
                }
                j a = j.a(m.this.c);
                TextView textView = this.g;
                String replaceAll = stUserDealTicketInfo.getContent().replaceAll("<br/>", "").replaceAll("\n", "<br/>");
                a.a(textView, replaceAll.replaceAll("<img.*?/>", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + q.f(m.this.c, "sobot_upload") + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER), m.this.a());
            }
            this.j.setAdapter(new FileAttachmentAdapter(m.this.c, stUserDealTicketInfo.getFileList(), i2, m.this.a));
            if (stUserDealTicketInfo.getStartType() == 0) {
                this.f.setText(q.f(m.this.c, "sobot_completed"));
            } else {
                this.f.setText(q.f(m.this.c, "sobot_my_reply"));
            }
        }
    }

    /* compiled from: SobotTicketDetailAdapter */
    /* access modifiers changed from: package-private */
    public class f extends a {
        private LinearLayout c;
        private TextView d;
        private LinearLayout e;
        private RatingBar f;
        private TextView g;
        private TextView h;
        private TextView i;
        private LinearLayout j;

        f(Context context, View view) {
            super(context, view);
            this.c = (LinearLayout) view.findViewById(q.g(context, "sobot_ll_score"));
            this.d = (TextView) view.findViewById(q.g(context, "sobot_tv_remark"));
            this.e = (LinearLayout) view.findViewById(q.g(context, "sobot_ll_remark"));
            this.f = (RatingBar) view.findViewById(q.g(context, "sobot_ratingBar"));
            this.g = (TextView) view.findViewById(q.g(context, "sobot_my_evaluate_tv"));
            this.g.setText(q.f(context, "sobot_my_service_comment"));
            this.h = (TextView) view.findViewById(q.g(context, "sobot_tv_my_evaluate_score"));
            TextView textView = this.h;
            textView.setText(q.f(context, "sobot_rating_score") + "\uff1a");
            this.i = (TextView) view.findViewById(q.g(context, "sobot_tv_my_evaluate_remark"));
            TextView textView2 = this.i;
            textView2.setText(q.f(context, "sobot_rating_dec") + "\uff1a");
            this.j = (LinearLayout) view.findViewById(q.g(context, "sobot_my_evaluate_ll"));
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.adapter.m.a
        public void a(Object obj, int i) {
            m mVar = m.this;
            mVar.a(mVar.e, this.j, 0);
            SobotUserTicketEvaluate sobotUserTicketEvaluate = (SobotUserTicketEvaluate) obj;
            if (!sobotUserTicketEvaluate.isOpen()) {
                this.g.setVisibility(8);
                this.j.setVisibility(8);
                this.c.setVisibility(8);
                this.e.setVisibility(8);
            } else if (sobotUserTicketEvaluate.isEvalution()) {
                this.f.setIsIndicator(true);
                this.g.setVisibility(0);
                this.j.setVisibility(0);
                List<SobotUserTicketEvaluate.TicketScoreInfooListBean> ticketScoreInfooList = sobotUserTicketEvaluate.getTicketScoreInfooList();
                if (ticketScoreInfooList == null || ticketScoreInfooList.size() < sobotUserTicketEvaluate.getScore()) {
                    this.c.setVisibility(8);
                } else {
                    this.c.setVisibility(0);
                    this.f.setRating((float) sobotUserTicketEvaluate.getScore());
                }
                if (TextUtils.isEmpty(sobotUserTicketEvaluate.getRemark())) {
                    this.e.setVisibility(8);
                    return;
                }
                this.e.setVisibility(0);
                this.d.setText(sobotUserTicketEvaluate.getRemark());
            } else {
                this.g.setVisibility(8);
                this.j.setVisibility(8);
                this.c.setVisibility(8);
                this.e.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public int a() {
        if (-1 != com.sobot.chat.c.o) {
            return com.sobot.chat.c.o;
        }
        return q.a(this.d, "color", "sobot_color_link");
    }

    public void a(Activity activity, View view, int i) {
        if (com.sobot.chat.b.a(1) && com.sobot.chat.b.a(4) && view != null) {
            com.sobot.chat.b.b.a().a(activity);
            activity.getWindow().setFlags(1024, 1024);
            com.sobot.chat.b.b.a().a(activity, new AnonymousClass2(view, i));
        }
    }

    /* compiled from: SobotTicketDetailAdapter */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.adapter.m$2  reason: invalid class name */
    public class AnonymousClass2 implements a.AbstractC0139a {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        AnonymousClass2(View view, int i) {
            this.a = view;
            this.b = i;
        }

        @Override // com.sobot.chat.b.a.AbstractC0139a
        public void a(a.b bVar) {
            if (bVar.a) {
                for (Rect rect : bVar.b) {
                    this.a.setPadding(rect.right + this.b, this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                }
            }
        }
    }
}
