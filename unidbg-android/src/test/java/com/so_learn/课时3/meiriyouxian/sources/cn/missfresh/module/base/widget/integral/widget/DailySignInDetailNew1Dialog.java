package cn.missfresh.module.base.widget.integral.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.telephony.PreciseDisconnectCause;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.common.providers.ISurveyService;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.r;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.support.dialog.ShareDialog;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.l;
import cn.missfresh.module.base.widget.integral.bean.DailySignIn;
import cn.missfresh.module.base.widget.integral.widget.SwitchView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.b;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class DailySignInDetailNew1Dialog extends DialogFragment implements View.OnTouchListener, ShareDialog.a, SwitchView.a {
    DailySignIn a;
    float b = 0.0f;
    float c = 0.0f;
    float d = 0.0f;
    float e = 0.0f;
    private String f = getClass().getSimpleName();
    private TextView g;
    private FrameLayout h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private RelativeLayout l;
    private View m;
    private SwitchView n;
    private ShareDialog o;
    private String p;
    private ImageView q;
    private boolean r = false;
    private Bitmap s;
    private Bitmap t;
    private final int u = PreciseDisconnectCause.LOCAL_ILLEGAL_ARGUMENT;
    private ImageView v;
    private ISurveyService w;
    private View.OnClickListener x = new AnonymousClass2();
    private View.OnClickListener y = new AnonymousClass3();

    @Override // cn.missfresh.module.base.support.dialog.ShareDialog.a
    public void onCancelClick() {
    }

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

    public DailySignInDetailNew1Dialog() {
        AppMethodBeat.i(24161, false);
        AppMethodBeat.o(24161);
    }

    static /* synthetic */ void a(DailySignInDetailNew1Dialog dailySignInDetailNew1Dialog, ShareInfo shareInfo, boolean z, int i) {
        AppMethodBeat.i(24188, false);
        dailySignInDetailNew1Dialog.a(shareInfo, z, i);
        AppMethodBeat.o(24188);
    }

    static /* synthetic */ void f(DailySignInDetailNew1Dialog dailySignInDetailNew1Dialog) {
        AppMethodBeat.i(24189, false);
        dailySignInDetailNew1Dialog.h();
        AppMethodBeat.o(24189);
    }

    public static DailySignInDetailNew1Dialog a(DailySignIn dailySignIn) {
        AppMethodBeat.i(24162, false);
        DailySignInDetailNew1Dialog dailySignInDetailNew1Dialog = new DailySignInDetailNew1Dialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable("chest_content", dailySignIn);
        dailySignInDetailNew1Dialog.setArguments(bundle);
        AppMethodBeat.o(24162);
        return dailySignInDetailNew1Dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(24163, false);
        super.onCreate(bundle);
        this.a = (DailySignIn) getArguments().getParcelable("chest_content");
        a();
        AppMethodBeat.o(24163);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(24164, false);
        getDialog().requestWindowFeature(1);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        View inflate = layoutInflater.inflate(R.layout.dialog_dailysingindetail, viewGroup, false);
        AppMethodBeat.o(24164);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(24165, false);
        super.onViewCreated(view, bundle);
        if (view == null || this.a == null) {
            try {
                dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.g = (TextView) view.findViewById(R.id.tv_dailySignIn_share);
            this.i = (ImageView) view.findViewById(R.id.iv_heart);
            this.k = (ImageView) view.findViewById(R.id.iv_heart_bottom_shadow);
            this.l = (RelativeLayout) view.findViewById(R.id.re_heart_bottom_menu);
            this.m = view.findViewById(R.id.v_heart_margin);
            this.n = (SwitchView) view.findViewById(R.id.switchview);
            this.h = (FrameLayout) view.findViewById(R.id.fl_heart);
            this.j = (ImageView) view.findViewById(R.id.iv_heart_bad);
            this.v = (ImageView) view.findViewById(R.id.iv_frist_see_daysign_notice);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(getContext().getResources().getColor(R.color.color_e6e6e6));
            gradientDrawable.setCornerRadius((float) aw.b(getContext(), 30));
            this.n.setBackgroundDrawable(gradientDrawable);
            this.q = (ImageView) view.findViewById(R.id.iv_close);
            this.q.setOnClickListener(new AnonymousClass1());
            if (this.a.getShare_invite_content() != null) {
                this.a.getShare_invite_content().friend_share_type = 1;
                this.a.getShare_invite_content().wx_share_type = 1;
            }
            this.g.setOnClickListener(this.x);
            this.n.setOnHeartChangeListener(this);
            this.o = new ShareDialog(getContext(), true);
            this.o.a(1);
            this.o.a(this);
            this.o.setCanceledOnTouchOutside(true);
            this.h.setOnTouchListener(this);
            this.v.setOnClickListener(this.y);
            if (this.a.getSign_in_tag() != 0) {
                String integral_amount = this.a.getIntegral_amount();
                if (!b.a(integral_amount)) {
                    a.b(integral_amount);
                }
            } else if (!TextUtils.isEmpty(this.a.getAlready_sign_in_text())) {
                a.b(this.a.getAlready_sign_in_text());
            }
            d();
        }
        AppMethodBeat.o(24165);
    }

    /* renamed from: cn.missfresh.module.base.widget.integral.widget.DailySignInDetailNew1Dialog$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(24155, false);
            DailySignInDetailNew1Dialog.this.dismiss();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(24155);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        AppMethodBeat.i(24167, false);
        super.onStart();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        AppMethodBeat.o(24167);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        AppMethodBeat.i(24168, false);
        super.onDestroyView();
        b();
        AppMethodBeat.o(24168);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        AppMethodBeat.i(24169, false);
        super.onDestroy();
        Bitmap bitmap = this.t;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.t.recycle();
            this.t = null;
        }
        Bitmap bitmap2 = this.s;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.s.recycle();
        }
        System.gc();
        AppMethodBeat.o(24169);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        AppMethodBeat.i(24170, false);
        super.onActivityCreated(bundle);
        c();
        ISurveyService iSurveyService = this.w;
        if (iSurveyService != null) {
            iSurveyService.a(2, 9);
        }
        AppMethodBeat.o(24170);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        AppMethodBeat.i(24171, false);
        super.onDismiss(dialogInterface);
        c();
        ISurveyService iSurveyService = this.w;
        if (iSurveyService != null) {
            iSurveyService.b(2, 9);
        }
        AppMethodBeat.o(24171);
    }

    private void c() {
        AppMethodBeat.i(24172, false);
        if (this.w == null) {
            this.w = (ISurveyService) com.alibaba.android.arouter.b.a.a().a("/order/survey_service").navigation();
        }
        AppMethodBeat.o(24172);
    }

    private void d() {
        AppMethodBeat.i(24173, false);
        if (!b.a(this.a.getShare_button_name())) {
            this.g.setText(this.a.getShare_button_name());
        }
        if (e.V()) {
            e.r(false);
            this.v.setVisibility(8);
        } else {
            this.v.setVisibility(8);
        }
        this.n.setDatas(this.a.getShare_img());
        a(this.j, this.i, 10);
        if (this.a.getShare_img() == null || this.a.getShare_img().size() <= 0) {
            this.r = true;
            e();
            this.i.setImageResource(R.drawable.ic_mood_main_good);
            this.j.setImageResource(R.drawable.ic_mood_main_bad);
        } else {
            this.r = false;
            if (this.a.getShare_img().size() < 2) {
                e();
            }
            DailySignIn.MoodImage moodImage = null;
            DailySignIn.MoodImage moodImage2 = this.a.getShare_img().size() > 0 ? this.a.getShare_img().get(0) : null;
            if (this.a.getShare_img().size() > 1) {
                moodImage = this.a.getShare_img().get(1);
            }
            String str = "";
            d.a(getContext(), moodImage2 == null ? str : moodImage2.share_main_img, R.drawable.ic_default_200, R.drawable.ic_mood_main_good, this.i);
            Context context = getContext();
            if (moodImage != null) {
                str = moodImage.share_main_img;
            }
            d.a(context, str, R.drawable.ic_default_200, R.drawable.ic_mood_main_bad, this.j);
        }
        AppMethodBeat.o(24173);
    }

    private void e() {
        AppMethodBeat.i(24174, false);
        SwitchView switchView = this.n;
        if (switchView != null) {
            switchView.setVisibility(8);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.g.getLayoutParams();
        layoutParams.addRule(11, 0);
        layoutParams.addRule(13, -1);
        this.g.setLayoutParams(layoutParams);
        this.h.setEnabled(false);
        AppMethodBeat.o(24174);
    }

    /* renamed from: cn.missfresh.module.base.widget.integral.widget.DailySignInDetailNew1Dialog$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(24156, false);
            if (DailySignInDetailNew1Dialog.this.getActivity() == null || DailySignInDetailNew1Dialog.this.getActivity().isFinishing() || DailySignInDetailNew1Dialog.this.o == null || DailySignInDetailNew1Dialog.this.a == null || DailySignInDetailNew1Dialog.this.a.getShare_invite_content() == null) {
                a.b(DailySignInDetailNew1Dialog.this.getString(R.string.net_work_error));
            } else {
                DailySignInDetailNew1Dialog.this.o.show();
                DailySignInDetailNew1Dialog.this.n.getCurrentHeart();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(24156);
        }
    }

    /* renamed from: cn.missfresh.module.base.widget.integral.widget.DailySignInDetailNew1Dialog$3  reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(24157, false);
            if (DailySignInDetailNew1Dialog.this.v != null) {
                DailySignInDetailNew1Dialog.this.v.setVisibility(8);
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(24157);
        }
    }

    @Override // cn.missfresh.module.base.support.dialog.ShareDialog.a
    public void onShareClick(int i) {
        AppMethodBeat.i(24175, false);
        if (i == 1) {
            this.p = "wechat01";
            c(1);
        } else if (i == 2) {
            this.p = "timeline01";
            c(2);
        } else if (i == 3) {
            this.p = "weibo01";
            if (this.r) {
                cn.missfresh.utils.a.d.d(this.f, "isNativeShared");
                this.s = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mood_main_good);
                ShareInfo share_invite_content = this.a.getShare_invite_content();
                r a = r.a(this.o.getContext());
                Bitmap bitmap = this.s;
                a.a(3, bitmap, bitmap, share_invite_content, true);
            } else {
                DailySignIn.MoodImage moodImage = null;
                if (this.a.getShare_img().size() > this.n.getCurrentHeart()) {
                    moodImage = this.a.getShare_img().get(this.n.getCurrentHeart());
                }
                this.a.getShare_invite_content().setOrigin_image_url(moodImage == null ? "" : moodImage.share_main_img);
                this.a.getShare_invite_content().bottom_image_url = this.a.getShare_bottom_img();
                if (b.a(this.a.getShare_invite_content().getOrigin_image_url())) {
                    a.b(getString(R.string.net_work_error));
                } else {
                    ShareInfo share_invite_content2 = this.a.getShare_invite_content();
                    g();
                    a(share_invite_content2, true, 3);
                }
            }
        } else if (i == 4) {
            this.p = "timeline01";
            b(4);
        }
        ShareDialog shareDialog = this.o;
        if (shareDialog != null) {
            shareDialog.dismiss();
        }
        AppMethodBeat.o(24175);
    }

    private void a(ShareInfo shareInfo, boolean z, int i) {
        AppMethodBeat.i(24176, false);
        String str = z ? shareInfo.bottom_image_url : shareInfo.origin_image_url;
        if (!b.a(str)) {
            d.a(getContext(), str, 0, 0, new AnonymousClass4(z, shareInfo, i));
            AppMethodBeat.o(24176);
        } else if (z) {
            a(shareInfo, false, i);
            AppMethodBeat.o(24176);
        } else {
            h();
            a.a(getString(R.string.net_work_error));
            AppMethodBeat.o(24176);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.integral.widget.DailySignInDetailNew1Dialog$4  reason: invalid class name */
    public class AnonymousClass4 implements d.b<Bitmap> {
        final /* synthetic */ boolean a;
        final /* synthetic */ ShareInfo b;
        final /* synthetic */ int c;

        AnonymousClass4(boolean z, ShareInfo shareInfo, int i) {
            this.a = z;
            this.b = shareInfo;
            this.c = i;
        }

        @Override // cn.missfresh.module.base.network.d.b
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(24160, false);
            a((Bitmap) obj);
            AppMethodBeat.o(24160);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(24158, false);
            if (bitmap != null) {
                if (this.a) {
                    DailySignInDetailNew1Dialog.this.t = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                    DailySignInDetailNew1Dialog.a(DailySignInDetailNew1Dialog.this, this.b, false, this.c);
                } else {
                    DailySignInDetailNew1Dialog.this.s = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                    if (DailySignInDetailNew1Dialog.this.t != null) {
                        DailySignInDetailNew1Dialog dailySignInDetailNew1Dialog = DailySignInDetailNew1Dialog.this;
                        dailySignInDetailNew1Dialog.s = l.a(dailySignInDetailNew1Dialog.s, DailySignInDetailNew1Dialog.this.t, false, 1146, 190);
                    } else {
                        DailySignInDetailNew1Dialog.this.s = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                    }
                    this.b.bottom_image_url = "";
                    DailySignInDetailNew1Dialog.f(DailySignInDetailNew1Dialog.this);
                    try {
                        r.a(DailySignInDetailNew1Dialog.this.o.getContext()).a(this.c, DailySignInDetailNew1Dialog.this.s, DailySignInDetailNew1Dialog.this.s, this.b, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        DailySignInDetailNew1Dialog.f(DailySignInDetailNew1Dialog.this);
                        a.a(DailySignInDetailNew1Dialog.this.getString(R.string.net_work_error));
                    }
                }
            } else if (this.a) {
                DailySignInDetailNew1Dialog.a(DailySignInDetailNew1Dialog.this, this.b, false, this.c);
            } else {
                DailySignInDetailNew1Dialog.f(DailySignInDetailNew1Dialog.this);
                a.a(DailySignInDetailNew1Dialog.this.getString(R.string.net_work_error));
            }
            AppMethodBeat.o(24158);
        }

        @Override // cn.missfresh.module.base.network.d.b
        public void a(Exception exc, Drawable drawable) {
            AppMethodBeat.i(24159, false);
            cn.missfresh.utils.a.d.d(DailySignInDetailNew1Dialog.this.f, "dowload bottomimage fail");
            if (this.a) {
                DailySignInDetailNew1Dialog.a(DailySignInDetailNew1Dialog.this, this.b, false, this.c);
            } else {
                DailySignInDetailNew1Dialog.f(DailySignInDetailNew1Dialog.this);
                if (DailySignInDetailNew1Dialog.this.getActivity() != null) {
                    a.a(DailySignInDetailNew1Dialog.this.getString(R.string.net_work_error));
                }
            }
            AppMethodBeat.o(24159);
        }
    }

    private void b(int i) {
        AppMethodBeat.i(24177, false);
        if (this.r) {
            this.s = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mood_main_good);
            r a = r.a(getContext());
            Bitmap bitmap = this.s;
            a.a(i, bitmap, bitmap, this.a.getShare_invite_content(), true);
        } else if (this.n.getCurrentHeart() >= this.a.getShare_img().size()) {
            a.a(getString(R.string.net_work_error));
            AppMethodBeat.o(24177);
            return;
        } else {
            this.a.getShare_invite_content().setOrigin_image_url(this.a.getShare_img().get(this.n.getCurrentHeart()).share_main_img);
            this.a.getShare_invite_content().bottom_image_url = this.a.getShare_bottom_img();
            r.a(getContext()).a(i, this.a.getShare_invite_content(), false);
        }
        AppMethodBeat.o(24177);
    }

    private void c(int i) {
        AppMethodBeat.i(24178, false);
        if (this.r) {
            this.s = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mood_main_good);
            r a = r.a(getContext());
            Bitmap bitmap = this.s;
            a.a(i, bitmap, bitmap, this.a.getShare_invite_content(), true);
        } else if (this.n.getCurrentHeart() >= this.a.getShare_img().size()) {
            a.a(getString(R.string.net_work_error));
            AppMethodBeat.o(24178);
            return;
        } else {
            this.a.getShare_invite_content().setOrigin_image_url(this.a.getShare_img().get(this.n.getCurrentHeart()).share_main_img);
            this.a.getShare_invite_content().bottom_image_url = this.a.getShare_bottom_img();
            g();
            a(this.a.getShare_invite_content(), true, i);
        }
        AppMethodBeat.o(24178);
    }

    public void a() {
        AppMethodBeat.i(24179, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        AppMethodBeat.o(24179);
    }

    public void b() {
        AppMethodBeat.i(24180, false);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        AppMethodBeat.o(24180);
    }

    @Subscribe
    public void onHandleEvent(BaseResp baseResp) {
        AppMethodBeat.i(24181, false);
        String simpleName = getClass().getSimpleName();
        int i = baseResp.errCode;
        if (i == -4) {
            cn.missfresh.utils.a.d.d(simpleName, "\u5206\u4eab\u88ab\u62d2\u7edd");
        } else if (i == -2) {
            cn.missfresh.utils.a.d.d(simpleName, "\u5206\u4eab\u53d6\u6d88");
        } else if (i != 0) {
            cn.missfresh.utils.a.d.d(simpleName, "\u5206\u4eab\u8fd4\u56de");
        } else {
            cn.missfresh.utils.a.d.d(simpleName, "\u5206\u4eab\u6210\u529f");
            a.b("\u5206\u4eab\u6210\u529f");
        }
        AppMethodBeat.o(24181);
    }

    @Override // cn.missfresh.module.base.widget.integral.widget.SwitchView.a
    public void a(int i) {
        AppMethodBeat.i(24182, false);
        this.a.getComeFrom().equals(DailySignIn.EXTRA_COME_FROM_HOME);
        if (i == 0) {
            a(this.j, this.i, PreciseDisconnectCause.LOCAL_ILLEGAL_ARGUMENT);
        } else if (i == 1) {
            a(this.i, this.j, PreciseDisconnectCause.LOCAL_ILLEGAL_ARGUMENT);
        }
        AppMethodBeat.o(24182);
    }

    private void a(ImageView imageView, ImageView imageView2, int i) {
        AppMethodBeat.i(24183, false);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView2, "alpha", 0.0f, 1.0f);
        long j = (long) i;
        ofFloat.setDuration(j);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.0f);
        ofFloat2.setDuration(j);
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.start();
        AppMethodBeat.o(24183);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        AppMethodBeat.i(24184, false);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.b = motionEvent.getX();
            this.d = motionEvent.getY();
        } else if (action == 1) {
            this.c = motionEvent.getX();
            this.e = motionEvent.getY();
            if (this.b - this.c > 50.0f && this.n.getCurrentHeart() != 1) {
                SwitchView switchView = this.n;
                switchView.a(1, switchView.getBadHeartDx());
            } else if (this.c - this.b > 50.0f && this.n.getCurrentHeart() != 0) {
                this.n.a(0, 0);
            }
        }
        AppMethodBeat.o(24184);
        return true;
    }

    private BaseFragmentActivity f() {
        AppMethodBeat.i(24185, false);
        Activity topActivity = ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity();
        if (topActivity == null || topActivity.isFinishing() || !(topActivity instanceof BaseFragmentActivity)) {
            AppMethodBeat.o(24185);
            return null;
        }
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) topActivity;
        AppMethodBeat.o(24185);
        return baseFragmentActivity;
    }

    private void g() {
        AppMethodBeat.i(24186, false);
        BaseFragmentActivity f = f();
        if (f != null) {
            f.b(true);
        }
        AppMethodBeat.o(24186);
    }

    private void h() {
        AppMethodBeat.i(24187, false);
        BaseFragmentActivity f = f();
        if (f != null) {
            f.c(true);
        }
        AppMethodBeat.o(24187);
    }
}
