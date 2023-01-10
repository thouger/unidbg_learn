package cn.missfresh.ui.rclayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;

public class MFRcRelativeLayout extends RelativeLayout {
    private a a;

    public MFRcRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MFRcRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(MetricsProto.MetricsEvent.APPLICATIONS_STORAGE_GAMES, false);
        this.a = new a();
        this.a.a(context, attributeSet);
        AppMethodBeat.o(MetricsProto.MetricsEvent.APPLICATIONS_STORAGE_GAMES);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.STORAGE_FILES, false);
        super.onSizeChanged(i, i2, i3, i4);
        this.a.a(this, i, i2);
        AppMethodBeat.o(MetricsProto.MetricsEvent.STORAGE_FILES);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ENCRYPTION_AND_CREDENTIAL, false);
        canvas.saveLayer(this.a.k, null, 31);
        super.dispatchDraw(canvas);
        this.a.a(canvas);
        canvas.restore();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ENCRYPTION_AND_CREDENTIAL);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.WIFI_NETWORK_DETAILS, false);
        if (this.a.i) {
            canvas.save();
            canvas.clipPath(this.a.b);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.WIFI_NETWORK_DETAILS);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_PREFERENCE_CHANGE, false);
        int action = motionEvent.getAction();
        if (action != 0 || this.a.j.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (action == 0 || action == 1) {
                refreshDrawableState();
            } else if (action == 3) {
                setPressed(false);
                refreshDrawableState();
            }
            boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_PREFERENCE_CHANGE);
            return dispatchTouchEvent;
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_PREFERENCE_CHANGE);
        return false;
    }

    public void setClipBackground(boolean z) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_NOTIFICATION_CHANNEL_ID, false);
        this.a.i = z;
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_NOTIFICATION_CHANNEL_ID);
    }

    public void setRoundAsCircle(boolean z) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_NETWORK_SCORER, false);
        this.a.d = z;
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_NETWORK_SCORER);
    }

    public void setRadius(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_GET_CONTACT, false);
        for (int i2 = 0; i2 < this.a.a.length; i2++) {
            this.a.a[i2] = (float) i;
        }
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_GET_CONTACT);
    }

    public void setTopLeftRadius(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_BLUETOOTH_DISCONNECT, false);
        float f = (float) i;
        this.a.a[0] = f;
        this.a.a[1] = f;
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_BLUETOOTH_DISCONNECT);
    }

    public void setTopRightRadius(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_UNINSTALL_APP, false);
        float f = (float) i;
        this.a.a[2] = f;
        this.a.a[3] = f;
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_UNINSTALL_APP);
    }

    public void setBottomLeftRadius(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_CLEAR_APP_DATA, false);
        float f = (float) i;
        this.a.a[6] = f;
        this.a.a[7] = f;
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_CLEAR_APP_DATA);
    }

    public void setBottomRightRadius(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SETTINGS_SEARCH_INLINE_RESULT_NAME, false);
        float f = (float) i;
        this.a.a[4] = f;
        this.a.a[5] = f;
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SETTINGS_SEARCH_INLINE_RESULT_NAME);
    }

    public void setStrokeWidth(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_LOCK_SCREEN_PREFERENCES, false);
        this.a.h = i;
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LOCK_SCREEN_PREFERENCES);
    }

    public void setStrokeColor(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APPOP_DENIED_ACCESS_NOTIFICATIONS, false);
        this.a.f = i;
        invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_DENIED_ACCESS_NOTIFICATIONS);
    }

    @Override // android.view.View
    public void invalidate() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APPOP_GRANT_SYSTEM_ALERT_WINDOW, false);
        a aVar = this.a;
        if (aVar != null) {
            aVar.a(this);
        }
        super.invalidate();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_GRANT_SYSTEM_ALERT_WINDOW);
    }

    public float getTopLeftRadius() {
        return this.a.a[0];
    }

    public float getTopRightRadius() {
        return this.a.a[2];
    }

    public float getBottomLeftRadius() {
        return this.a.a[4];
    }

    public float getBottomRightRadius() {
        return this.a.a[6];
    }

    public int getStrokeWidth() {
        return this.a.h;
    }

    public int getStrokeColor() {
        return this.a.f;
    }
}
