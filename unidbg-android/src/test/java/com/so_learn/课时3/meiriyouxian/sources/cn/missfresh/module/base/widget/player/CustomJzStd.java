package cn.missfresh.module.base.widget.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.base.support.dialog.TipsDialog;
import cn.missfresh.player.MediaUtils;
import cn.missfresh.player.StdVideoLayout;
import cn.missfresh.player.VideoLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;

public class CustomJzStd extends StdVideoLayout {
    private Context a;
    private ImageView b;
    private int c;
    private MediaActionListener d;

    public interface MediaActionListener {
        void onEvent(int i);

        void onEvent(int i, Object obj, int i2, Object... objArr);
    }

    static /* synthetic */ void a(CustomJzStd customJzStd, int i) {
        AppMethodBeat.i(24237, false);
        customJzStd.setVoiceControllVisibility(i);
        AppMethodBeat.o(24237);
    }

    public CustomJzStd(Context context) {
        this(context, null);
    }

    public CustomJzStd(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24220, false);
        this.c = 1;
        this.a = context;
        a();
        AppMethodBeat.o(24220);
    }

    public void a() {
        AppMethodBeat.i(24221, false);
        VideoLayout.SAVE_PROGRESS = false;
        this.b = (ImageView) findViewById(R.id.iv_voice_controller);
        this.b.setOnClickListener(this);
        AppMethodBeat.o(24221);
    }

    @Override // cn.missfresh.player.StdVideoLayout, cn.missfresh.player.VideoLayout, android.view.View.OnClickListener
    public void onClick(View view) {
        MediaActionListener mediaActionListener;
        AppMethodBeat.i(24222, false);
        int id = view.getId();
        if (id == R.id.iv_voice_controller) {
            if (this.c == 1) {
                a(0, 0.0f, 1017);
            } else {
                a(1, 1.0f, 1016);
            }
            AppMethodBeat.o(24222);
            return;
        }
        if (id == R.id.start) {
            if (this.mMediaSource == null || this.mMediaSource.urlsMap.isEmpty() || this.mMediaSource.getCurrentUrl() == null) {
                a.a(getResources().getString(R.string.no_url));
                AppMethodBeat.o(24222);
                return;
            } else if (!(this.state == 5 || (mediaActionListener = this.d) == null)) {
                mediaActionListener.onEvent(1001);
            }
        }
        super.onClick(view);
        AppMethodBeat.o(24222);
    }

    public void a(int i, float f, int i2) {
        AppMethodBeat.i(24223, false);
        this.c = i;
        this.mediaInterface.setVolume(f, f);
        if (i == 1) {
            this.b.setImageResource(R.mipmap.player_voice_on);
        } else {
            this.b.setImageResource(R.mipmap.player_voice_off);
        }
        MediaActionListener mediaActionListener = this.d;
        if (mediaActionListener != null) {
            mediaActionListener.onEvent(i2);
        }
        AppMethodBeat.o(24223);
    }

    @Override // cn.missfresh.player.StdVideoLayout
    public void updateStartImage() {
        AppMethodBeat.i(24224, false);
        if (this.state == 5) {
            this.startButton.setVisibility(0);
            this.startButton.setImageResource(R.drawable.jz_click_pause_selector);
            this.replayTextView.setVisibility(8);
        } else if (this.state == 8) {
            this.startButton.setVisibility(4);
            this.replayTextView.setVisibility(8);
        } else if (this.state == 7) {
            this.startButton.setVisibility(0);
            this.startButton.setImageResource(R.drawable.jz_click_play_selector);
            this.replayTextView.setVisibility(8);
        } else {
            this.startButton.setImageResource(R.drawable.jz_click_play_selector);
            this.replayTextView.setVisibility(8);
        }
        AppMethodBeat.o(24224);
    }

    @Override // cn.missfresh.player.StdVideoLayout, cn.missfresh.player.VideoLayout
    public void onStateAutoComplete() {
        AppMethodBeat.i(24225, false);
        super.onStateAutoComplete();
        if (this.screen == 1) {
            gotoScreenNormal();
            releaseAllVideos();
        }
        MediaActionListener mediaActionListener = this.d;
        if (mediaActionListener != null) {
            mediaActionListener.onEvent(1003);
        }
        AppMethodBeat.o(24225);
    }

    @Override // cn.missfresh.player.StdVideoLayout, cn.missfresh.player.VideoLayout
    public void onStatePause() {
        AppMethodBeat.i(24226, false);
        super.onStatePause();
        MediaActionListener mediaActionListener = this.d;
        if (mediaActionListener != null) {
            mediaActionListener.onEvent(1002);
        }
        if (this.screen == 0) {
            setAllControlsVisiblity(4, 4, 0, 4, 4, 0, 4);
        }
        AppMethodBeat.o(24226);
    }

    @Override // cn.missfresh.player.StdVideoLayout
    public void changeUiToPauseClear() {
        AppMethodBeat.i(24227, false);
        int i = this.screen;
        if (i == 0) {
            setAllControlsVisiblity(4, 4, 0, 4, 4, 0, 4);
        } else if (i == 1) {
            setAllControlsVisiblity(4, 0, 0, 4, 4, 4, 4);
        }
        AppMethodBeat.o(24227);
    }

    @Override // cn.missfresh.player.StdVideoLayout, cn.missfresh.player.VideoLayout
    public void setScreenNormal() {
        AppMethodBeat.i(24228, false);
        this.screen = 0;
        this.fullscreenButton.setImageResource(R.mipmap.player_ic_screen_full);
        this.backButton.setVisibility(8);
        this.tinyBackImageView.setVisibility(4);
        this.batteryTimeLayout.setVisibility(8);
        this.clarity.setVisibility(8);
        MediaActionListener mediaActionListener = this.d;
        if (mediaActionListener != null) {
            mediaActionListener.onEvent(1005);
        }
        AppMethodBeat.o(24228);
    }

    @Override // cn.missfresh.player.StdVideoLayout, cn.missfresh.player.VideoLayout
    public void setScreenFullscreen() {
        AppMethodBeat.i(24229, false);
        this.screen = 1;
        this.fullscreenButton.setImageResource(R.mipmap.player_ic_screen_normal);
        this.backButton.setVisibility(0);
        this.tinyBackImageView.setVisibility(4);
        this.batteryTimeLayout.setVisibility(8);
        if (this.mMediaSource.urlsMap.size() == 1) {
            this.clarity.setVisibility(8);
        } else {
            this.clarity.setText(this.mMediaSource.getCurrentKey().toString());
            this.clarity.setVisibility(0);
        }
        MediaActionListener mediaActionListener = this.d;
        if (mediaActionListener != null) {
            mediaActionListener.onEvent(1004);
        }
        AppMethodBeat.o(24229);
    }

    @Override // cn.missfresh.player.StdVideoLayout
    public void setAllControlsVisiblity(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        AppMethodBeat.i(24230, false);
        super.setAllControlsVisiblity(i, i2, i3, i4, i5, i6, i7);
        setVoiceControllVisibility(i2);
        this.topContainer.setVisibility(0);
        AppMethodBeat.o(24230);
    }

    @Override // cn.missfresh.player.StdVideoLayout
    public void dissmissControlView() {
        AppMethodBeat.i(24231, false);
        if (!((this.state == 0 || this.state == 8 || this.state == 7) && this.state == 6 && this.screen == 1)) {
            post(new AnonymousClass1());
        }
        AppMethodBeat.o(24231);
    }

    /* renamed from: cn.missfresh.module.base.widget.player.CustomJzStd$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(24218, false);
            if (!(CustomJzStd.this.state == 6 && CustomJzStd.this.screen == 1)) {
                CustomJzStd.this.bottomContainer.setVisibility(4);
            }
            if (CustomJzStd.this.state != 6) {
                CustomJzStd.this.startButton.setVisibility(4);
            }
            CustomJzStd.a(CustomJzStd.this, 4);
            if (CustomJzStd.this.clarityPopWindow != null) {
                CustomJzStd.this.clarityPopWindow.dismiss();
            }
            if (!(CustomJzStd.this.screen == 2 || CustomJzStd.this.bottomContainer.getVisibility() == 0)) {
                CustomJzStd.this.bottomProgressBar.setVisibility(0);
            }
            AppMethodBeat.o(24218);
        }
    }

    private void setVoiceControllVisibility(int i) {
        AppMethodBeat.i(24232, false);
        if (this.screen == 0) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(i);
        }
        AppMethodBeat.o(24232);
    }

    @Override // cn.missfresh.player.VideoLayout
    public boolean checkWifiAndUrl() {
        AppMethodBeat.i(24234, false);
        if (!checkWifiPlay() || !a.a) {
            if (!MediaUtils.isWifiConnected(getContext())) {
                a.a("\u5f53\u524d\u6b63\u5728\u4f7f\u7528\u79fb\u52a8\u7f51\u7edc");
            }
            AppMethodBeat.o(24234);
            return false;
        }
        showWifiDialog();
        AppMethodBeat.o(24234);
        return true;
    }

    @Override // cn.missfresh.player.StdVideoLayout, cn.missfresh.player.VideoLayout
    public int getLayoutId() {
        return R.layout.base_player_view;
    }

    public MediaActionListener getmJzUserActionListener() {
        return this.d;
    }

    public void setmJzUserActionListener(MediaActionListener mediaActionListener) {
        this.d = mediaActionListener;
    }

    @Override // cn.missfresh.player.StdVideoLayout
    public void showWifiDialog() {
        AppMethodBeat.i(24236, false);
        super.showWifiDialog();
        TipsDialog.l().a("\u5f53\u524d\u4e3a\u975eWiFi\u73af\u5883\n\u5df2\u4e3a\u60a8\u6682\u505c\u64ad\u653e").b("\u6682\u505c\u64ad\u653e").c("\u7ee7\u7eed\u64ad\u653e").a(new AnonymousClass2()).a((FragmentActivity) this.a).k();
        AppMethodBeat.o(24236);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.player.CustomJzStd$2  reason: invalid class name */
    public class AnonymousClass2 implements BaseTipDialog.a {
        AnonymousClass2() {
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            AppMethodBeat.i(24219, false);
            if (i == 101) {
                if (CustomJzStd.this.state == 6) {
                    CustomJzStd.this.startButton.performClick();
                } else {
                    CustomJzStd.this.startVideo();
                }
            } else if (i == 100) {
                VideoLayout.releaseAllVideos();
                CustomJzStd.this.clearFloatScreen();
            }
            a.a = false;
            AppMethodBeat.o(24219);
        }
    }
}
