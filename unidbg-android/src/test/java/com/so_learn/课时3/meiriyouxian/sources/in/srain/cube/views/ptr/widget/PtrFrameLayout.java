package in.srain.cube.views.ptr.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;
import in.srain.cube.views.ptr.R;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.interfaces.PtrHandler;
import in.srain.cube.views.ptr.interfaces.PtrUIHandler;
import in.srain.cube.views.ptr.util.PtrCLog;
import in.srain.cube.views.ptr.util.PtrUIHandlerHook;

public class PtrFrameLayout extends ViewGroup {
    public static boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static byte FLAG_AUTO_REFRESH_AT_ONCE = 1;
    private static byte FLAG_AUTO_REFRESH_BUT_LATER = 2;
    private static byte FLAG_ENABLE_NEXT_PTR_AT_ONCE = 4;
    private static byte FLAG_PIN_CONTENT = 8;
    private static int ID = 1;
    private static byte MASK_AUTO_REFRESH = 3;
    public static final byte PTR_STATUS_COMPLETE = 4;
    public static final byte PTR_STATUS_INIT = 1;
    public static final byte PTR_STATUS_LOADING = 3;
    public static final byte PTR_STATUS_PREPARE = 2;
    protected final String LOG_TAG;
    private int mContainerId;
    protected View mContent;
    private boolean mDisableWhenHorizontalMove;
    private int mDurationToClose;
    private int mDurationToCloseHeader;
    private int mFlag;
    private boolean mHasSendCancelEvent;
    private int mHeaderHeight;
    private int mHeaderId;
    private View mHeaderView;
    private boolean mKeepHeaderWhenRefresh;
    private MotionEvent mLastMoveEvent;
    private int mLoadingMinTime;
    private long mLoadingStartTime;
    private int mPagingTouchSlop;
    private Runnable mPerformRefreshCompleteDelay;
    private boolean mPreventForHorizontal;
    private PtrHandler mPtrHandler;
    private PtrIndicator mPtrIndicator;
    private PtrUIHandlerHolder mPtrUIHandlerHolder;
    private boolean mPullToRefresh;
    private PtrUIHandlerHook mRefreshCompleteHook;
    private ScrollChecker mScrollChecker;
    private byte mStatus;

    /* access modifiers changed from: protected */
    public void onPositionChange(boolean z, byte b, PtrIndicator ptrIndicator) {
    }

    @Deprecated
    public void setInterceptEventWhileWorking(boolean z) {
    }

    /* renamed from: in.srain.cube.views.ptr.widget.PtrFrameLayout$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            PtrFrameLayout.this.performRefreshComplete();
        }
    }

    public PtrFrameLayout(Context context) {
        this(context, null);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PtrFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStatus = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("ptr-frame-");
        int i2 = ID + 1;
        ID = i2;
        sb.append(i2);
        this.LOG_TAG = sb.toString();
        this.mHeaderId = 0;
        this.mContainerId = 0;
        this.mDurationToClose = 200;
        this.mDurationToCloseHeader = 1000;
        this.mKeepHeaderWhenRefresh = true;
        this.mPullToRefresh = false;
        this.mPtrUIHandlerHolder = PtrUIHandlerHolder.create();
        this.mDisableWhenHorizontalMove = false;
        this.mFlag = 0;
        this.mPreventForHorizontal = false;
        this.mLoadingMinTime = 500;
        this.mLoadingStartTime = 0;
        this.mHasSendCancelEvent = false;
        this.mPerformRefreshCompleteDelay = new AnonymousClass1();
        this.mPtrIndicator = new PtrIndicator();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PtrFrameLayout, 0, 0);
        if (obtainStyledAttributes != null) {
            this.mHeaderId = obtainStyledAttributes.getResourceId(R.styleable.PtrFrameLayout_ptr_header, this.mHeaderId);
            this.mContainerId = obtainStyledAttributes.getResourceId(R.styleable.PtrFrameLayout_ptr_content, this.mContainerId);
            this.mPtrIndicator.setResistance(obtainStyledAttributes.getFloat(R.styleable.PtrFrameLayout_ptr_resistance, this.mPtrIndicator.getResistance()));
            this.mDurationToClose = obtainStyledAttributes.getInt(R.styleable.PtrFrameLayout_ptr_duration_to_close, this.mDurationToClose);
            this.mDurationToCloseHeader = obtainStyledAttributes.getInt(R.styleable.PtrFrameLayout_ptr_duration_to_close_header, this.mDurationToCloseHeader);
            this.mPtrIndicator.setRatioOfHeaderHeightToRefresh(obtainStyledAttributes.getFloat(R.styleable.PtrFrameLayout_ptr_ratio_of_header_height_to_refresh, this.mPtrIndicator.getRatioOfHeaderToHeightRefresh()));
            this.mKeepHeaderWhenRefresh = obtainStyledAttributes.getBoolean(R.styleable.PtrFrameLayout_ptr_keep_header_when_refresh, this.mKeepHeaderWhenRefresh);
            this.mPullToRefresh = obtainStyledAttributes.getBoolean(R.styleable.PtrFrameLayout_ptr_pull_to_fresh, this.mPullToRefresh);
            obtainStyledAttributes.recycle();
        }
        this.mScrollChecker = new ScrollChecker();
        this.mPagingTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount <= 2) {
            if (childCount == 2) {
                int i = this.mHeaderId;
                if (i != 0 && this.mHeaderView == null) {
                    this.mHeaderView = findViewById(i);
                }
                int i2 = this.mContainerId;
                if (i2 != 0 && this.mContent == null) {
                    this.mContent = findViewById(i2);
                }
                if (this.mContent == null || this.mHeaderView == null) {
                    View childAt = getChildAt(0);
                    View childAt2 = getChildAt(1);
                    if (childAt instanceof PtrUIHandler) {
                        this.mHeaderView = childAt;
                        this.mContent = childAt2;
                    } else if (childAt2 instanceof PtrUIHandler) {
                        this.mHeaderView = childAt2;
                        this.mContent = childAt;
                    } else if (this.mContent == null && this.mHeaderView == null) {
                        this.mHeaderView = childAt;
                        this.mContent = childAt2;
                    } else {
                        View view = this.mHeaderView;
                        if (view == null) {
                            if (this.mContent == childAt) {
                                childAt = childAt2;
                            }
                            this.mHeaderView = childAt;
                        } else {
                            if (view == childAt) {
                                childAt = childAt2;
                            }
                            this.mContent = childAt;
                        }
                    }
                }
            } else if (childCount == 1) {
                this.mContent = getChildAt(0);
            } else {
                TextView textView = new TextView(getContext());
                textView.setClickable(true);
                textView.setTextColor(-39424);
                textView.setGravity(17);
                textView.setTextSize(20.0f);
                textView.setText("The content view in PtrFrameLayout is empty. Do you forget to specify its id in xml layout file?");
                this.mContent = textView;
                addView(this.mContent);
            }
            View view2 = this.mHeaderView;
            if (view2 != null) {
                view2.bringToFront();
            }
            super.onFinishInflate();
            return;
        }
        throw new IllegalStateException("PtrFrameLayout only can host 2 elements");
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ScrollChecker scrollChecker = this.mScrollChecker;
        if (scrollChecker != null) {
            scrollChecker.destroy();
        }
        Runnable runnable = this.mPerformRefreshCompleteDelay;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        boolean z = DEBUG;
        View view = this.mHeaderView;
        if (view != null) {
            measureChildWithMargins(view, i, 0, i2, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderView.getLayoutParams();
            this.mHeaderHeight = this.mHeaderView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            this.mPtrIndicator.setHeaderHeight(this.mHeaderHeight);
        }
        View view2 = this.mContent;
        if (view2 != null) {
            measureContentView(view2, i, i2);
            boolean z2 = DEBUG;
        }
    }

    private void measureContentView(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width), getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin, marginLayoutParams.height));
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layoutChildren();
    }

    private void layoutChildren() {
        int currentPosY = this.mPtrIndicator.getCurrentPosY();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        View view = this.mHeaderView;
        if (view != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int i = marginLayoutParams.leftMargin + paddingLeft;
            int i2 = ((marginLayoutParams.topMargin + paddingTop) + currentPosY) - this.mHeaderHeight;
            this.mHeaderView.layout(i, i2, this.mHeaderView.getMeasuredWidth() + i, this.mHeaderView.getMeasuredHeight() + i2);
            boolean z = DEBUG;
        }
        if (this.mContent != null) {
            if (isPinContent()) {
                currentPosY = 0;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mContent.getLayoutParams();
            int i3 = paddingLeft + marginLayoutParams2.leftMargin;
            int i4 = paddingTop + marginLayoutParams2.topMargin + currentPosY;
            boolean z2 = DEBUG;
            this.mContent.layout(i3, i4, this.mContent.getMeasuredWidth() + i3, this.mContent.getMeasuredHeight() + i4);
        }
    }

    public boolean dispatchTouchEventSupper(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r0 != 3) goto L_0x00ed;
     */
    @Override // android.view.ViewGroup, android.view.View
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r13) {
        /*
        // Method dump skipped, instructions count: 323
        */
        throw new UnsupportedOperationException("Method not decompiled: in.srain.cube.views.ptr.widget.PtrFrameLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void movePos(float f) {
        if (f >= 0.0f || !this.mPtrIndicator.isInStartPosition()) {
            int currentPosY = ((int) f) + this.mPtrIndicator.getCurrentPosY();
            if (this.mPtrIndicator.willOverTop(currentPosY)) {
                if (DEBUG) {
                    PtrCLog.e(this.LOG_TAG, String.format("over top", new Object[0]));
                }
                currentPosY = 0;
            }
            this.mPtrIndicator.setCurrentPos(currentPosY);
            updatePos(currentPosY - this.mPtrIndicator.getLastPosY());
        } else if (DEBUG) {
            PtrCLog.e(this.LOG_TAG, String.format("has reached the top", new Object[0]));
        }
    }

    private void updatePos(int i) {
        if (i != 0) {
            boolean isUnderTouch = this.mPtrIndicator.isUnderTouch();
            if (isUnderTouch && !this.mHasSendCancelEvent && this.mPtrIndicator.hasMovedAfterPressedDown()) {
                this.mHasSendCancelEvent = true;
                sendCancelEvent();
            }
            if ((this.mPtrIndicator.hasJustLeftStartPosition() && this.mStatus == 1) || (this.mPtrIndicator.goDownCrossFinishPosition() && this.mStatus == 4 && isEnabledNextPtrAtOnce())) {
                this.mStatus = 2;
                this.mPtrUIHandlerHolder.onUIRefreshPrepare(this);
                if (DEBUG) {
                    PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", Integer.valueOf(this.mFlag));
                }
            }
            if (this.mPtrIndicator.hasJustBackToStartPosition()) {
                tryToNotifyReset();
                if (isUnderTouch) {
                    sendDownEvent();
                }
            }
            if (this.mStatus == 2) {
                if (isUnderTouch && !isAutoRefresh() && this.mPullToRefresh && this.mPtrIndicator.crossRefreshLineFromTopToBottom()) {
                    tryToPerformRefresh();
                }
                if (performAutoRefreshButLater() && this.mPtrIndicator.hasJustReachedHeaderHeightFromTopToBottom()) {
                    tryToPerformRefresh();
                }
            }
            if (DEBUG) {
                PtrCLog.v(this.LOG_TAG, "updatePos: change: %s, current: %s last: %s, top: %s, headerHeight: %s", Integer.valueOf(i), Integer.valueOf(this.mPtrIndicator.getCurrentPosY()), Integer.valueOf(this.mPtrIndicator.getLastPosY()), Integer.valueOf(this.mContent.getTop()), Integer.valueOf(this.mHeaderHeight));
            }
            this.mHeaderView.offsetTopAndBottom(i);
            if (!isPinContent()) {
                this.mContent.offsetTopAndBottom(i);
            }
            invalidate();
            if (this.mPtrUIHandlerHolder.hasHandler()) {
                this.mPtrUIHandlerHolder.onUIPositionChange(this, isUnderTouch, this.mStatus, this.mPtrIndicator);
            }
            onPositionChange(isUnderTouch, this.mStatus, this.mPtrIndicator);
        }
    }

    public int getHeaderHeight() {
        return this.mHeaderHeight;
    }

    private void onRelease(boolean z) {
        tryToPerformRefresh();
        byte b = this.mStatus;
        if (b == 3) {
            if (!this.mKeepHeaderWhenRefresh) {
                tryScrollBackToTopWhileLoading();
            } else if (this.mPtrIndicator.isOverOffsetToKeepHeaderWhileLoading() && !z) {
                this.mScrollChecker.tryToScrollTo(this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading(), this.mDurationToClose);
            }
        } else if (b == 4) {
            notifyUIRefreshComplete(false);
        } else {
            tryScrollBackToTopAbortRefresh();
        }
    }

    /* renamed from: in.srain.cube.views.ptr.widget.PtrFrameLayout$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PtrFrameLayout.DEBUG) {
                PtrCLog.d(PtrFrameLayout.this.LOG_TAG, "mRefreshCompleteHook resume.");
            }
            PtrFrameLayout.this.notifyUIRefreshComplete(true);
        }
    }

    public void setRefreshCompleteHook(PtrUIHandlerHook ptrUIHandlerHook) {
        this.mRefreshCompleteHook = ptrUIHandlerHook;
        ptrUIHandlerHook.setResumeAction(new AnonymousClass2());
    }

    private void tryScrollBackToTop() {
        if (!this.mPtrIndicator.isUnderTouch()) {
            this.mScrollChecker.tryToScrollTo(0, this.mDurationToCloseHeader);
        }
    }

    private void tryScrollBackToTopWhileLoading() {
        tryScrollBackToTop();
    }

    private void tryScrollBackToTopAfterComplete() {
        tryScrollBackToTop();
    }

    private void tryScrollBackToTopAbortRefresh() {
        tryScrollBackToTop();
    }

    private boolean tryToPerformRefresh() {
        if (this.mStatus != 2) {
            return false;
        }
        if ((this.mPtrIndicator.isOverOffsetToKeepHeaderWhileLoading() && isAutoRefresh()) || this.mPtrIndicator.isOverOffsetToRefresh()) {
            this.mStatus = 3;
            performRefresh();
        }
        return false;
    }

    private void performRefresh() {
        this.mLoadingStartTime = System.currentTimeMillis();
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            this.mPtrUIHandlerHolder.onUIRefreshBegin(this);
            if (DEBUG) {
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshBegin");
            }
        }
        PtrHandler ptrHandler = this.mPtrHandler;
        if (ptrHandler != null) {
            ptrHandler.onRefreshBegin(this);
        }
    }

    private boolean tryToNotifyReset() {
        byte b = this.mStatus;
        if ((b != 4 && b != 2) || !this.mPtrIndicator.isInStartPosition()) {
            return false;
        }
        if (this.mPtrUIHandlerHolder.hasHandler()) {
            this.mPtrUIHandlerHolder.onUIReset(this);
            if (DEBUG) {
                PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIReset");
            }
        }
        this.mStatus = 1;
        clearFlag();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPtrScrollAbort() {
        if (this.mPtrIndicator.hasLeftStartPosition() && isAutoRefresh()) {
            if (DEBUG) {
                PtrCLog.d(this.LOG_TAG, "call onRelease after scroll abort");
            }
            onRelease(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onPtrScrollFinish() {
        if (this.mPtrIndicator.hasLeftStartPosition() && isAutoRefresh()) {
            if (DEBUG) {
                PtrCLog.d(this.LOG_TAG, "call onRelease after scroll finish");
            }
            onRelease(true);
        }
    }

    public boolean isRefreshing() {
        return this.mStatus == 3;
    }

    public final void refreshComplete() {
        if (DEBUG) {
            PtrCLog.i(this.LOG_TAG, "refreshComplete");
        }
        PtrUIHandlerHook ptrUIHandlerHook = this.mRefreshCompleteHook;
        if (ptrUIHandlerHook != null) {
            ptrUIHandlerHook.reset();
        }
        int currentTimeMillis = (int) (((long) this.mLoadingMinTime) - (System.currentTimeMillis() - this.mLoadingStartTime));
        if (currentTimeMillis <= 0) {
            if (DEBUG) {
                PtrCLog.d(this.LOG_TAG, "performRefreshComplete at once");
            }
            performRefreshComplete();
            return;
        }
        postDelayed(this.mPerformRefreshCompleteDelay, (long) currentTimeMillis);
        if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "performRefreshComplete after delay: %s", Integer.valueOf(currentTimeMillis));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void performRefreshComplete() {
        this.mStatus = 4;
        if (!this.mScrollChecker.mIsRunning || !isAutoRefresh()) {
            notifyUIRefreshComplete(false);
        } else if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "performRefreshComplete do nothing, scrolling: %s, auto refresh: %s", Boolean.valueOf(this.mScrollChecker.mIsRunning), Integer.valueOf(this.mFlag));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyUIRefreshComplete(boolean z) {
        if (!this.mPtrIndicator.hasLeftStartPosition() || z || this.mRefreshCompleteHook == null) {
            if (this.mPtrUIHandlerHolder.hasHandler()) {
                if (DEBUG) {
                    PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshComplete");
                }
                this.mPtrUIHandlerHolder.onUIRefreshComplete(this);
            }
            this.mPtrIndicator.onUIRefreshComplete();
            tryScrollBackToTopAfterComplete();
            tryToNotifyReset();
            return;
        }
        if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "notifyUIRefreshComplete mRefreshCompleteHook run.");
        }
        this.mRefreshCompleteHook.takeOver();
    }

    public void autoRefresh() {
        autoRefresh(true, this.mDurationToCloseHeader);
    }

    public void autoRefresh(boolean z) {
        autoRefresh(z, this.mDurationToCloseHeader);
    }

    private void clearFlag() {
        this.mFlag &= ~MASK_AUTO_REFRESH;
    }

    public void autoRefresh(boolean z, int i) {
        if (this.mStatus == 1) {
            this.mFlag |= z ? FLAG_AUTO_REFRESH_AT_ONCE : FLAG_AUTO_REFRESH_BUT_LATER;
            this.mStatus = 2;
            if (this.mPtrUIHandlerHolder.hasHandler()) {
                this.mPtrUIHandlerHolder.onUIRefreshPrepare(this);
                if (DEBUG) {
                    PtrCLog.i(this.LOG_TAG, "PtrUIHandler: onUIRefreshPrepare, mFlag %s", Integer.valueOf(this.mFlag));
                }
            }
            this.mScrollChecker.tryToScrollTo(this.mPtrIndicator.getOffsetToRefresh(), i);
            if (z) {
                this.mStatus = 3;
                performRefresh();
            }
        }
    }

    public boolean isAutoRefresh() {
        return (this.mFlag & MASK_AUTO_REFRESH) > 0;
    }

    private boolean performAutoRefreshButLater() {
        return (this.mFlag & MASK_AUTO_REFRESH) == FLAG_AUTO_REFRESH_BUT_LATER;
    }

    public boolean isEnabledNextPtrAtOnce() {
        return (this.mFlag & FLAG_ENABLE_NEXT_PTR_AT_ONCE) > 0;
    }

    public void setEnabledNextPtrAtOnce(boolean z) {
        if (z) {
            this.mFlag |= FLAG_ENABLE_NEXT_PTR_AT_ONCE;
        } else {
            this.mFlag &= ~FLAG_ENABLE_NEXT_PTR_AT_ONCE;
        }
    }

    public boolean isPinContent() {
        return (this.mFlag & FLAG_PIN_CONTENT) > 0;
    }

    public void setPinContent(boolean z) {
        if (z) {
            this.mFlag |= FLAG_PIN_CONTENT;
        } else {
            this.mFlag &= ~FLAG_PIN_CONTENT;
        }
    }

    public void disableWhenHorizontalMove(boolean z) {
        this.mDisableWhenHorizontalMove = z;
    }

    public void setLoadingMinTime(int i) {
        this.mLoadingMinTime = i;
    }

    public View getContentView() {
        return this.mContent;
    }

    public void setPtrHandler(PtrHandler ptrHandler) {
        this.mPtrHandler = ptrHandler;
    }

    public void addPtrUIHandler(PtrUIHandler ptrUIHandler) {
        PtrUIHandlerHolder.addHandler(this.mPtrUIHandlerHolder, ptrUIHandler);
    }

    public void removePtrUIHandler(PtrUIHandler ptrUIHandler) {
        this.mPtrUIHandlerHolder = PtrUIHandlerHolder.removeHandler(this.mPtrUIHandlerHolder, ptrUIHandler);
    }

    public void setPtrIndicator(PtrIndicator ptrIndicator) {
        PtrIndicator ptrIndicator2 = this.mPtrIndicator;
        if (!(ptrIndicator2 == null || ptrIndicator2 == ptrIndicator)) {
            ptrIndicator.convertFrom(ptrIndicator2);
        }
        this.mPtrIndicator = ptrIndicator;
    }

    public float getResistance() {
        return this.mPtrIndicator.getResistance();
    }

    public void setResistance(float f) {
        this.mPtrIndicator.setResistance(f);
    }

    public float getDurationToClose() {
        return (float) this.mDurationToClose;
    }

    public void setDurationToClose(int i) {
        this.mDurationToClose = i;
    }

    public long getDurationToCloseHeader() {
        return (long) this.mDurationToCloseHeader;
    }

    public void setDurationToCloseHeader(int i) {
        this.mDurationToCloseHeader = i;
    }

    public void setRatioOfHeaderHeightToRefresh(float f) {
        this.mPtrIndicator.setRatioOfHeaderHeightToRefresh(f);
    }

    public int getOffsetToRefresh() {
        return this.mPtrIndicator.getOffsetToRefresh();
    }

    public void setOffsetToRefresh(int i) {
        this.mPtrIndicator.setOffsetToRefresh(i);
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.mPtrIndicator.getRatioOfHeaderToHeightRefresh();
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        return this.mPtrIndicator.getOffsetToKeepHeaderWhileLoading();
    }

    public void setOffsetToKeepHeaderWhileLoading(int i) {
        this.mPtrIndicator.setOffsetToKeepHeaderWhileLoading(i);
    }

    public boolean isKeepHeaderWhenRefresh() {
        return this.mKeepHeaderWhenRefresh;
    }

    public void setKeepHeaderWhenRefresh(boolean z) {
        this.mKeepHeaderWhenRefresh = z;
    }

    public boolean isPullToRefresh() {
        return this.mPullToRefresh;
    }

    public void setPullToRefresh(boolean z) {
        this.mPullToRefresh = z;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public void setHeaderView(View view) {
        View view2 = this.mHeaderView;
        if (!(view2 == null || view == null || view2 == view)) {
            removeView(view2);
        }
        if (view != null) {
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new LayoutParams(-1, -2));
            }
            this.mHeaderView = view;
            addView(view);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    private void sendCancelEvent() {
        if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "send cancel event");
        }
        MotionEvent motionEvent = this.mLastMoveEvent;
        if (motionEvent != null) {
            dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime() + ((long) ViewConfiguration.getLongPressTimeout()), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
        }
    }

    private void sendDownEvent() {
        if (DEBUG) {
            PtrCLog.d(this.LOG_TAG, "send down event");
        }
        MotionEvent motionEvent = this.mLastMoveEvent;
        dispatchTouchEventSupper(MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState()));
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public class ScrollChecker implements Runnable {
        private boolean mIsRunning = false;
        private int mLastFlingY;
        private Scroller mScroller;
        private int mStart;
        private int mTo;

        public ScrollChecker() {
            this.mScroller = new Scroller(PtrFrameLayout.this.getContext());
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = !this.mScroller.computeScrollOffset() || this.mScroller.isFinished();
            int currY = this.mScroller.getCurrY();
            int i = currY - this.mLastFlingY;
            if (PtrFrameLayout.DEBUG && i != 0) {
                PtrCLog.v(PtrFrameLayout.this.LOG_TAG, "scroll: %s, start: %s, to: %s, currentPos: %s, current :%s, last: %s, delta: %s", Boolean.valueOf(z), Integer.valueOf(this.mStart), Integer.valueOf(this.mTo), Integer.valueOf(PtrFrameLayout.this.mPtrIndicator.getCurrentPosY()), Integer.valueOf(currY), Integer.valueOf(this.mLastFlingY), Integer.valueOf(i));
            }
            if (!z) {
                this.mLastFlingY = currY;
                PtrFrameLayout.this.movePos((float) i);
                PtrFrameLayout.this.post(this);
                return;
            }
            finish();
        }

        private void finish() {
            if (PtrFrameLayout.DEBUG) {
                PtrCLog.v(PtrFrameLayout.this.LOG_TAG, "finish, currentPos:%s", Integer.valueOf(PtrFrameLayout.this.mPtrIndicator.getCurrentPosY()));
            }
            reset();
            PtrFrameLayout.this.onPtrScrollFinish();
        }

        private void reset() {
            this.mIsRunning = false;
            this.mLastFlingY = 0;
            PtrFrameLayout.this.removeCallbacks(this);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void destroy() {
            reset();
            if (!this.mScroller.isFinished()) {
                this.mScroller.forceFinished(true);
            }
        }

        public void abortIfWorking() {
            if (this.mIsRunning) {
                if (!this.mScroller.isFinished()) {
                    this.mScroller.forceFinished(true);
                }
                PtrFrameLayout.this.onPtrScrollAbort();
                reset();
            }
        }

        public void tryToScrollTo(int i, int i2) {
            if (!PtrFrameLayout.this.mPtrIndicator.isAlreadyHere(i)) {
                this.mStart = PtrFrameLayout.this.mPtrIndicator.getCurrentPosY();
                this.mTo = i;
                int i3 = i - this.mStart;
                if (PtrFrameLayout.DEBUG) {
                    PtrCLog.d(PtrFrameLayout.this.LOG_TAG, "tryToScrollTo: start: %s, distance:%s, to:%s", Integer.valueOf(this.mStart), Integer.valueOf(i3), Integer.valueOf(i));
                }
                PtrFrameLayout.this.removeCallbacks(this);
                this.mLastFlingY = 0;
                if (!this.mScroller.isFinished()) {
                    this.mScroller.forceFinished(true);
                }
                this.mScroller.startScroll(0, 0, 0, i3, i2);
                PtrFrameLayout.this.post(this);
                this.mIsRunning = true;
            }
        }
    }
}
