package in.srain.cube.views.ptr.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import in.srain.cube.views.ptr.R;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.interfaces.PtrUIHandler;

public class PtrMissFreshHeader extends FrameLayout implements PtrUIHandler {
    private final int ANIM_DURATION_LOGO = 100;
    private final int ANIM_DURATION_RELEASE = 20;
    private ImageView mIvLogo;
    private int[] mLogoAnimFrameId = null;
    private int mLogoAnimFrameIdIndex = 0;
    private Runnable mLogoAnimRunable = null;
    private View mVHeaderContainer;

    private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout ptrFrameLayout) {
    }

    @Override // in.srain.cube.views.ptr.interfaces.PtrUIHandler
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
    }

    @Override // in.srain.cube.views.ptr.interfaces.PtrUIHandler
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
    }

    static /* synthetic */ int access$204(PtrMissFreshHeader ptrMissFreshHeader) {
        int i = ptrMissFreshHeader.mLogoAnimFrameIdIndex + 1;
        ptrMissFreshHeader.mLogoAnimFrameIdIndex = i;
        return i;
    }

    public PtrMissFreshHeader(Context context) {
        super(context);
        init();
    }

    public PtrMissFreshHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_pull_to_refresh_header, (ViewGroup) this, true);
        this.mVHeaderContainer = findViewById(R.id.ll_ptr_header_container);
        this.mIvLogo = (ImageView) findViewById(R.id.iv_ptr_header_logo);
        this.mVHeaderContainer.setVisibility(0);
        this.mLogoAnimFrameId = new int[]{R.drawable.ic_refresh_icon_0, R.drawable.ic_refresh_icon_1, R.drawable.ic_refresh_icon_2, R.drawable.ic_refresh_icon_3, R.drawable.ic_refresh_icon_4, R.drawable.ic_refresh_icon_5, R.drawable.ic_refresh_icon_6, R.drawable.ic_refresh_icon_7, R.drawable.ic_refresh_icon_8, R.drawable.ic_refresh_icon_9, R.drawable.ic_refresh_icon_10, R.drawable.ic_refresh_icon_11, R.drawable.ic_refresh_icon_12, R.drawable.ic_refresh_icon_13, R.drawable.ic_refresh_icon_14, R.drawable.ic_refresh_icon_15, R.drawable.ic_refresh_icon_16, R.drawable.ic_refresh_icon_17};
        this.mLogoAnimRunable = new AnonymousClass1();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: in.srain.cube.views.ptr.widget.PtrMissFreshHeader$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PtrMissFreshHeader ptrMissFreshHeader = PtrMissFreshHeader.this;
            ptrMissFreshHeader.postDelayed(ptrMissFreshHeader.mLogoAnimRunable, 100);
            PtrMissFreshHeader.this.mIvLogo.setImageResource(PtrMissFreshHeader.this.mLogoAnimFrameId[PtrMissFreshHeader.access$204(PtrMissFreshHeader.this) % PtrMissFreshHeader.this.mLogoAnimFrameId.length]);
        }
    }

    @Override // in.srain.cube.views.ptr.interfaces.PtrUIHandler
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        stopAllAnimations();
        this.mIvLogo.setImageResource(R.drawable.ic_refresh_icon_0);
        this.mIvLogo.setVisibility(0);
    }

    @Override // in.srain.cube.views.ptr.interfaces.PtrUIHandler
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        this.mVHeaderContainer.setVisibility(0);
        this.mIvLogo.setImageResource(this.mLogoAnimFrameId[0]);
        postDelayed(this.mLogoAnimRunable, 100);
    }

    @Override // in.srain.cube.views.ptr.interfaces.PtrUIHandler
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        this.mVHeaderContainer.setVisibility(0);
        stopAllAnimations();
    }

    private void stopAllAnimations() {
        this.mIvLogo.clearAnimation();
        this.mIvLogo.removeCallbacks(this.mLogoAnimRunable);
        this.mIvLogo.clearAnimation();
    }

    private void crossRotateLineFromTopUnderTouch(PtrFrameLayout ptrFrameLayout) {
        ptrFrameLayout.isPullToRefresh();
    }
}
