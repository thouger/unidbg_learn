package cn.missfresh.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class PlayerView extends TextureView {
    protected static final String TAG = "PlayerView";
    public int currentVideoHeight;
    public int currentVideoWidth;

    public PlayerView(Context context) {
        super(context);
        this.currentVideoWidth = 0;
        this.currentVideoHeight = 0;
        this.currentVideoWidth = 0;
        this.currentVideoHeight = 0;
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.currentVideoWidth = 0;
        this.currentVideoHeight = 0;
        this.currentVideoWidth = 0;
        this.currentVideoHeight = 0;
    }

    public void setVideoSize(int i, int i2) {
        AppMethodBeat.i(7548, false);
        if (!(this.currentVideoWidth == i && this.currentVideoHeight == i2)) {
            this.currentVideoWidth = i;
            this.currentVideoHeight = i2;
            requestLayout();
        }
        AppMethodBeat.o(7548);
    }

    @Override // android.view.View
    public void setRotation(float f) {
        AppMethodBeat.i(7549, false);
        if (f != getRotation()) {
            super.setRotation(f);
            requestLayout();
        }
        AppMethodBeat.o(7549);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        AppMethodBeat.i(7554, false);
        Log.i(TAG, "onMeasure  [" + hashCode() + "] ");
        int rotation = (int) getRotation();
        int i9 = this.currentVideoWidth;
        int i10 = this.currentVideoHeight;
        int measuredHeight = ((View) getParent()).getMeasuredHeight();
        int measuredWidth = ((View) getParent()).getMeasuredWidth();
        if (!(measuredWidth == 0 || measuredHeight == 0 || i9 == 0 || i10 == 0 || VideoLayout.VIDEO_IMAGE_DISPLAY_TYPE != 1)) {
            if (rotation == 90 || rotation == 270) {
                measuredWidth = measuredHeight;
                measuredHeight = measuredWidth;
            }
            i10 = (i9 * measuredHeight) / measuredWidth;
        }
        if (rotation == 90 || rotation == 270) {
            i3 = i;
            i4 = i2;
        } else {
            i4 = i;
            i3 = i2;
        }
        int defaultSize = getDefaultSize(i9, i4);
        int defaultSize2 = getDefaultSize(i10, i3);
        if (i9 <= 0 || i10 <= 0) {
            i5 = defaultSize2;
            i6 = defaultSize;
        } else {
            int mode = View.MeasureSpec.getMode(i4);
            i6 = View.MeasureSpec.getSize(i4);
            int mode2 = View.MeasureSpec.getMode(i3);
            i5 = View.MeasureSpec.getSize(i3);
            Log.i(TAG, "widthMeasureSpec  [" + View.MeasureSpec.toString(i4) + "]");
            Log.i(TAG, "heightMeasureSpec [" + View.MeasureSpec.toString(i3) + "]");
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i11 = i9 * i5;
                int i12 = i6 * i10;
                if (i11 < i12) {
                    i7 = i11 / i10;
                } else if (i11 > i12) {
                    i5 = i12 / i9;
                }
            } else {
                if (mode == 1073741824) {
                    i8 = (i6 * i10) / i9;
                    if (mode2 == Integer.MIN_VALUE && i8 > i5) {
                        i7 = (i5 * i9) / i10;
                    }
                } else if (mode2 == 1073741824) {
                    i7 = (i5 * i9) / i10;
                    if (mode == Integer.MIN_VALUE && i7 > i6) {
                        i8 = (i6 * i10) / i9;
                    }
                } else {
                    if (mode2 != Integer.MIN_VALUE || i10 <= i5) {
                        i7 = i9;
                        i5 = i10;
                    } else {
                        i7 = (i5 * i9) / i10;
                    }
                    if (mode == Integer.MIN_VALUE && i7 > i6) {
                        i8 = (i6 * i10) / i9;
                    }
                }
                i5 = i8;
            }
            i6 = i7;
        }
        if (!(measuredWidth == 0 || measuredHeight == 0 || i9 == 0 || i10 == 0)) {
            if (VideoLayout.VIDEO_IMAGE_DISPLAY_TYPE != 3) {
                if (VideoLayout.VIDEO_IMAGE_DISPLAY_TYPE == 2) {
                    if (rotation == 90 || rotation == 270) {
                        measuredWidth = measuredHeight;
                        measuredHeight = measuredWidth;
                    }
                    double d = ((double) i10) / ((double) i9);
                    double d2 = (double) measuredHeight;
                    double d3 = (double) measuredWidth;
                    double d4 = d2 / d3;
                    if (d > d4) {
                        i10 = (int) ((d3 / ((double) i6)) * ((double) i5));
                        i9 = measuredWidth;
                    } else if (d < d4) {
                        i9 = (int) ((d2 / ((double) i5)) * ((double) i6));
                        i10 = measuredHeight;
                    }
                }
            }
            setMeasuredDimension(i9, i10);
            AppMethodBeat.o(7554);
        }
        i9 = i6;
        i10 = i5;
        setMeasuredDimension(i9, i10);
        AppMethodBeat.o(7554);
    }
}
