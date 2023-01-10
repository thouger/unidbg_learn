package cn.missfresh.player;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import cn.missfresh.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public abstract class StdVideoLayout extends VideoLayout {
    protected static Timer DISMISS_CONTROL_VIEW_TIMER = null;
    public static int LAST_GET_BATTERYLEVEL_PERCENT = 70;
    public static long LAST_GET_BATTERYLEVEL_TIME;
    public ImageView backButton;
    private BroadcastReceiver battertReceiver = new AnonymousClass1();
    public ImageView batteryLevel;
    public LinearLayout batteryTimeLayout;
    public ProgressBar bottomProgressBar;
    public TextView clarity;
    public PopupWindow clarityPopWindow;
    private ArrayDeque<Runnable> delayTask = new ArrayDeque<>();
    private long doubleTime = 200;
    private long lastClickTime = 0;
    public ProgressBar loadingProgressBar;
    protected Dialog mBrightnessDialog;
    protected ProgressBar mDialogBrightnessProgressBar;
    protected TextView mDialogBrightnessTextView;
    protected ImageView mDialogIcon;
    protected ProgressBar mDialogProgressBar;
    protected TextView mDialogSeekTime;
    protected TextView mDialogTotalTime;
    protected ImageView mDialogVolumeImageView;
    protected ProgressBar mDialogVolumeProgressBar;
    protected TextView mDialogVolumeTextView;
    protected DismissControlViewTimerTask mDismissControlViewTimerTask;
    private boolean mIsWifi;
    protected Dialog mProgressDialog;
    public TextView mRetryBtn;
    public LinearLayout mRetryLayout;
    protected Dialog mVolumeDialog;
    public ImageView posterImageView;
    public TextView replayTextView;
    public ImageView tinyBackImageView;
    public TextView titleTextView;
    public TextView videoCurrentTime;
    private BroadcastReceiver wifiReceiver = new AnonymousClass2();

    public void showWifiDialog() {
    }

    public StdVideoLayout(Context context) {
        super(context);
    }

    public StdVideoLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // cn.missfresh.player.VideoLayout
    public void init(Context context) {
        super.init(context);
        this.batteryTimeLayout = (LinearLayout) findViewById(R.id.battery_time_layout);
        this.bottomProgressBar = (ProgressBar) findViewById(R.id.bottom_progress);
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.backButton = (ImageView) findViewById(R.id.back);
        this.posterImageView = (ImageView) findViewById(R.id.poster);
        this.loadingProgressBar = (ProgressBar) findViewById(R.id.loading);
        this.tinyBackImageView = (ImageView) findViewById(R.id.back_tiny);
        this.batteryLevel = (ImageView) findViewById(R.id.battery_level);
        this.videoCurrentTime = (TextView) findViewById(R.id.video_current_time);
        this.replayTextView = (TextView) findViewById(R.id.replay_text);
        this.clarity = (TextView) findViewById(R.id.clarity);
        this.mRetryBtn = (TextView) findViewById(R.id.retry_btn);
        this.mRetryLayout = (LinearLayout) findViewById(R.id.retry_layout);
        this.posterImageView.setOnClickListener(this);
        this.backButton.setOnClickListener(this);
        this.tinyBackImageView.setOnClickListener(this);
        this.clarity.setOnClickListener(this);
        this.mRetryBtn.setOnClickListener(this);
    }

    @Override // cn.missfresh.player.VideoLayout
    public void setUp(MediaSource mediaSource, int i, Class cls) {
        if (System.currentTimeMillis() - this.gobakFullscreenTime >= 200 && System.currentTimeMillis() - this.gotoFullscreenTime >= 200) {
            super.setUp(mediaSource, i, cls);
            this.titleTextView.setText(mediaSource.title);
            setScreen(i);
        }
    }

    public void changeStartButtonSize(int i) {
        ViewGroup.LayoutParams layoutParams = this.startButton.getLayoutParams();
        layoutParams.height = i;
        layoutParams.width = i;
        ViewGroup.LayoutParams layoutParams2 = this.loadingProgressBar.getLayoutParams();
        layoutParams2.height = i;
        layoutParams2.width = i;
    }

    @Override // cn.missfresh.player.VideoLayout
    public int getLayoutId() {
        return R.layout.layout_std;
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onStateNormal() {
        super.onStateNormal();
        changeUiToNormal();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onStatePreparing() {
        super.onStatePreparing();
        changeUiToPreparing();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onStatePreparingPlaying() {
        super.onStatePreparingPlaying();
        changeUIToPreparingPlaying();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onStatePreparingChangeUrl() {
        super.onStatePreparingChangeUrl();
        changeUIToPreparingChangeUrl();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onStatePlaying() {
        super.onStatePlaying();
        changeUiToPlayingClear();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onStatePause() {
        super.onStatePause();
        changeUiToPauseShow();
        cancelDismissControlViewTimer();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onStateError() {
        super.onStateError();
        changeUiToError();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        changeUiToComplete();
        cancelDismissControlViewTimer();
        this.bottomProgressBar.setProgress(100);
    }

    public boolean checkWifiPlay() {
        String obj = this.mMediaSource.getCurrentUrl().toString();
        return !obj.startsWith(ContentResolver.SCHEME_FILE) && !obj.startsWith(NotificationIconUtil.SPLIT_CHAR) && !MediaUtils.isWifiConnected(getContext());
    }

    @Override // cn.missfresh.player.VideoLayout, android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if (id == R.id.surface_container) {
            int action = motionEvent.getAction();
            if (action != 0 && action == 1) {
                startDismissControlViewTimer();
                if (this.mChangePosition) {
                    long duration = getDuration();
                    long j = this.mSeekTimePosition * 100;
                    if (duration == 0) {
                        duration = 1;
                    }
                    this.bottomProgressBar.setProgress((int) (j / duration));
                }
                $$Lambda$StdVideoLayout$IA6drom1ooMDZjkEdvBd5NDOmxw r0 = new $$Lambda$StdVideoLayout$IA6drom1ooMDZjkEdvBd5NDOmxw(this);
                view.postDelayed(r0, this.doubleTime + 20);
                this.delayTask.add(r0);
                while (this.delayTask.size() > 2) {
                    this.delayTask.pollFirst();
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.lastClickTime < this.doubleTime) {
                    Iterator<Runnable> it2 = this.delayTask.iterator();
                    while (it2.hasNext()) {
                        view.removeCallbacks(it2.next());
                    }
                    if (this.state == 5 || this.state == 6) {
                        Log.d(VideoLayout.TAG, "doublClick [" + hashCode() + "] ");
                        this.startButton.performClick();
                    }
                }
                this.lastClickTime = currentTimeMillis;
            }
        } else if (id == R.id.bottom_seek_progress) {
            int action2 = motionEvent.getAction();
            if (action2 == 0) {
                cancelDismissControlViewTimer();
            } else if (action2 == 1) {
                startDismissControlViewTimer();
            }
        }
        return super.onTouch(view, motionEvent);
    }

    public /* synthetic */ void lambda$onTouch$0$StdVideoLayout() {
        if (!this.mChangePosition && !this.mChangeVolume) {
            onClickUiToggle();
        }
    }

    @Override // cn.missfresh.player.VideoLayout, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.poster) {
            if (checkSourceValid()) {
                if (this.state == 0) {
                    if (!checkWifiAndUrl()) {
                        startVideo();
                    }
                } else if (this.state == 7) {
                    onClickUiToggle();
                }
            }
        } else if (id == R.id.surface_container) {
            startDismissControlViewTimer();
        } else if (id == R.id.back) {
            backPress();
        } else if (id == R.id.back_tiny) {
            clearFloatScreen();
        } else if (id == R.id.clarity) {
            LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_clarity, (ViewGroup) null);
            $$Lambda$StdVideoLayout$__rMtPt9vEe9MzWhPemTIhEafaI r0 = new $$Lambda$StdVideoLayout$__rMtPt9vEe9MzWhPemTIhEafaI(this, linearLayout);
            for (int i = 0; i < this.mMediaSource.urlsMap.size(); i++) {
                String keyFromDataSource = this.mMediaSource.getKeyFromDataSource(i);
                TextView textView = (TextView) View.inflate(getContext(), R.layout.layout_clarity_item, null);
                textView.setText(keyFromDataSource);
                textView.setTag(Integer.valueOf(i));
                linearLayout.addView(textView, i);
                textView.setOnClickListener(r0);
                if (i == this.mMediaSource.currentUrlIndex) {
                    textView.setTextColor(Color.parseColor("#fff85959"));
                }
            }
            this.clarityPopWindow = new PopupWindow((View) linearLayout, -2, -2, true);
            this.clarityPopWindow.setContentView(linearLayout);
            this.clarityPopWindow.showAsDropDown(this.clarity);
            linearLayout.measure(0, 0);
            this.clarityPopWindow.update(this.clarity, -(this.clarity.getMeasuredWidth() / 3), -(this.clarity.getMeasuredHeight() / 3), Math.round((float) (linearLayout.getMeasuredWidth() * 2)), linearLayout.getMeasuredHeight());
        } else if (id == R.id.retry_btn && checkSourceValid() && !checkWifiAndUrl()) {
            addTextureView();
            onStatePreparing();
        }
    }

    public /* synthetic */ void lambda$onClick$1$StdVideoLayout(LinearLayout linearLayout, View view) {
        this.mMediaSource.currentUrlIndex = ((Integer) view.getTag()).intValue();
        changeUrl(this.mMediaSource, getCurrentPositionWhenPlaying());
        this.clarity.setText(this.mMediaSource.getCurrentKey().toString());
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            if (i == this.mMediaSource.currentUrlIndex) {
                ((TextView) linearLayout.getChildAt(i)).setTextColor(Color.parseColor("#fff85959"));
            } else {
                ((TextView) linearLayout.getChildAt(i)).setTextColor(Color.parseColor("#ffffff"));
            }
        }
        PopupWindow popupWindow = this.clarityPopWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    @Override // cn.missfresh.player.VideoLayout
    public void setScreenNormal() {
        super.setScreenNormal();
        this.fullscreenButton.setImageResource(R.drawable.enlarge);
        this.backButton.setVisibility(8);
        this.tinyBackImageView.setVisibility(4);
        changeStartButtonSize((int) getResources().getDimension(R.dimen.start_button_w_h_normal));
        this.batteryTimeLayout.setVisibility(8);
        this.clarity.setVisibility(8);
    }

    @Override // cn.missfresh.player.VideoLayout
    public void setScreenFullscreen() {
        super.setScreenFullscreen();
        this.fullscreenButton.setImageResource(R.drawable.shrink);
        this.backButton.setVisibility(0);
        this.tinyBackImageView.setVisibility(4);
        this.batteryTimeLayout.setVisibility(0);
        if (this.mMediaSource.urlsMap.size() == 1) {
            this.clarity.setVisibility(8);
        } else {
            this.clarity.setText(this.mMediaSource.getCurrentKey().toString());
            this.clarity.setVisibility(0);
        }
        changeStartButtonSize((int) getResources().getDimension(R.dimen.start_button_w_h_fullscreen));
        setSystemTimeAndBattery();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void setScreenTiny() {
        super.setScreenTiny();
        this.tinyBackImageView.setVisibility(0);
        setAllControlsVisiblity(4, 4, 4, 4, 4, 4, 4);
        this.batteryTimeLayout.setVisibility(8);
        this.clarity.setVisibility(8);
    }

    @Override // cn.missfresh.player.VideoLayout, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        cancelDismissControlViewTimer();
    }

    @Override // cn.missfresh.player.VideoLayout, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        startDismissControlViewTimer();
    }

    public void onClickUiToggle() {
        if (this.bottomContainer.getVisibility() != 0) {
            setSystemTimeAndBattery();
            this.clarity.setText(this.mMediaSource.getCurrentKey().toString());
        }
        if (this.state == 1) {
            changeUiToPreparing();
            if (this.bottomContainer.getVisibility() != 0) {
                setSystemTimeAndBattery();
            }
        } else if (this.state == 5) {
            if (this.bottomContainer.getVisibility() == 0) {
                changeUiToPlayingClear();
            } else {
                changeUiToPlayingShow();
            }
        } else if (this.state != 6) {
        } else {
            if (this.bottomContainer.getVisibility() == 0) {
                changeUiToPauseClear();
            } else {
                changeUiToPauseShow();
            }
        }
    }

    public void setSystemTimeAndBattery() {
        this.videoCurrentTime.setText(new SimpleDateFormat("HH:mm").format(new Date()));
        if (System.currentTimeMillis() - LAST_GET_BATTERYLEVEL_TIME > 30000) {
            LAST_GET_BATTERYLEVEL_TIME = System.currentTimeMillis();
            getContext().registerReceiver(this.battertReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            return;
        }
        setBatteryLevel();
    }

    public void setBatteryLevel() {
        int i = LAST_GET_BATTERYLEVEL_PERCENT;
        if (i < 15) {
            this.batteryLevel.setBackgroundResource(R.drawable.battery_level_10);
        } else if (i >= 15 && i < 40) {
            this.batteryLevel.setBackgroundResource(R.drawable.battery_level_30);
        } else if (i >= 40 && i < 60) {
            this.batteryLevel.setBackgroundResource(R.drawable.battery_level_50);
        } else if (i >= 60 && i < 80) {
            this.batteryLevel.setBackgroundResource(R.drawable.battery_level_70);
        } else if (i >= 80 && i < 95) {
            this.batteryLevel.setBackgroundResource(R.drawable.battery_level_90);
        } else if (i >= 95 && i <= 100) {
            this.batteryLevel.setBackgroundResource(R.drawable.battery_level_100);
        }
    }

    public void onCLickUiToggleToClear() {
        if (this.state == 1) {
            if (this.bottomContainer.getVisibility() == 0) {
                changeUiToPreparing();
            }
        } else if (this.state == 5) {
            if (this.bottomContainer.getVisibility() == 0) {
                changeUiToPlayingClear();
            }
        } else if (this.state == 6) {
            if (this.bottomContainer.getVisibility() == 0) {
                changeUiToPauseClear();
            }
        } else if (this.state == 7 && this.bottomContainer.getVisibility() == 0) {
            changeUiToComplete();
        }
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onProgress(int i, long j, long j2) {
        super.onProgress(i, j, j2);
        if (i != 0) {
            this.bottomProgressBar.setProgress(i);
        }
    }

    @Override // cn.missfresh.player.VideoLayout
    public void setBufferProgress(int i) {
        super.setBufferProgress(i);
        if (i != 0) {
            this.bottomProgressBar.setSecondaryProgress(i);
        }
    }

    @Override // cn.missfresh.player.VideoLayout
    public void resetProgressAndTime() {
        super.resetProgressAndTime();
        this.bottomProgressBar.setProgress(0);
        this.bottomProgressBar.setSecondaryProgress(0);
    }

    public void changeUiToNormal() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 4, 0, 4, 0, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToPreparing() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(4, 4, 4, 0, 0, 4, 4);
            updateStartImage();
        }
    }

    public void changeUIToPreparingPlaying() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 0, 4, 0, 4, 4, 4);
            updateStartImage();
        }
    }

    public void changeUIToPreparingChangeUrl() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(4, 4, 4, 0, 4, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToPlayingShow() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 0, 0, 4, 4, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToPlayingClear() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(4, 4, 4, 4, 4, 0, 4);
        }
    }

    public void changeUiToPauseShow() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 0, 0, 4, 4, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToPauseClear() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(4, 4, 4, 4, 4, 0, 4);
        }
    }

    public void changeUiToComplete() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 4, 0, 4, 0, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToError() {
        int i = this.screen;
        if (i == 0) {
            setAllControlsVisiblity(4, 4, 0, 4, 4, 4, 0);
            updateStartImage();
        } else if (i == 1) {
            setAllControlsVisiblity(0, 4, 0, 4, 4, 4, 0);
            updateStartImage();
        }
    }

    public void setAllControlsVisiblity(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.topContainer.setVisibility(i);
        this.bottomContainer.setVisibility(i2);
        this.startButton.setVisibility(i3);
        this.loadingProgressBar.setVisibility(i4);
        this.posterImageView.setVisibility(i5);
        this.bottomProgressBar.setVisibility(i6);
        this.mRetryLayout.setVisibility(i7);
    }

    public void updateStartImage() {
        if (this.state == 5) {
            this.startButton.setVisibility(0);
            this.startButton.setImageResource(R.drawable.click_pause_selector);
            this.replayTextView.setVisibility(8);
        } else if (this.state == 8) {
            this.startButton.setVisibility(4);
            this.replayTextView.setVisibility(8);
        } else if (this.state == 7) {
            this.startButton.setVisibility(0);
            this.startButton.setImageResource(R.drawable.click_replay_selector);
            this.replayTextView.setVisibility(0);
        } else {
            this.startButton.setImageResource(R.drawable.click_play_selector);
            this.replayTextView.setVisibility(8);
        }
    }

    @Override // cn.missfresh.player.VideoLayout
    public void showProgressDialog(float f, String str, long j, String str2, long j2) {
        super.showProgressDialog(f, str, j, str2, j2);
        if (this.mProgressDialog == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_progress, (ViewGroup) null);
            this.mDialogProgressBar = (ProgressBar) inflate.findViewById(R.id.duration_progressbar);
            this.mDialogSeekTime = (TextView) inflate.findViewById(R.id.tv_current);
            this.mDialogTotalTime = (TextView) inflate.findViewById(R.id.tv_duration);
            this.mDialogIcon = (ImageView) inflate.findViewById(R.id.duration_image_tip);
            this.mProgressDialog = createDialogWithView(inflate);
        }
        if (!this.mProgressDialog.isShowing()) {
            this.mProgressDialog.show();
        }
        this.mDialogSeekTime.setText(str);
        TextView textView = this.mDialogTotalTime;
        textView.setText(" / " + str2);
        this.mDialogProgressBar.setProgress(j2 <= 0 ? 0 : (int) ((j * 100) / j2));
        if (f > 0.0f) {
            this.mDialogIcon.setBackgroundResource(R.drawable.forward_icon);
        } else {
            this.mDialogIcon.setBackgroundResource(R.drawable.backward_icon);
        }
        onCLickUiToggleToClear();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
        Dialog dialog = this.mProgressDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override // cn.missfresh.player.VideoLayout
    public void showVolumeDialog(float f, int i) {
        super.showVolumeDialog(f, i);
        if (this.mVolumeDialog == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_volume, (ViewGroup) null);
            this.mDialogVolumeImageView = (ImageView) inflate.findViewById(R.id.volume_image_tip);
            this.mDialogVolumeTextView = (TextView) inflate.findViewById(R.id.tv_volume);
            this.mDialogVolumeProgressBar = (ProgressBar) inflate.findViewById(R.id.volume_progressbar);
            this.mVolumeDialog = createDialogWithView(inflate);
        }
        if (!this.mVolumeDialog.isShowing()) {
            this.mVolumeDialog.show();
        }
        if (i <= 0) {
            this.mDialogVolumeImageView.setBackgroundResource(R.drawable.close_volume);
        } else {
            this.mDialogVolumeImageView.setBackgroundResource(R.drawable.add_volume);
        }
        if (i > 100) {
            i = 100;
        } else if (i < 0) {
            i = 0;
        }
        this.mDialogVolumeTextView.setText(i + "%");
        this.mDialogVolumeProgressBar.setProgress(i);
        onCLickUiToggleToClear();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void dismissVolumeDialog() {
        super.dismissVolumeDialog();
        Dialog dialog = this.mVolumeDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override // cn.missfresh.player.VideoLayout
    public void showBrightnessDialog(int i) {
        super.showBrightnessDialog(i);
        if (this.mBrightnessDialog == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_brightness, (ViewGroup) null);
            this.mDialogBrightnessTextView = (TextView) inflate.findViewById(R.id.tv_brightness);
            this.mDialogBrightnessProgressBar = (ProgressBar) inflate.findViewById(R.id.brightness_progressbar);
            this.mBrightnessDialog = createDialogWithView(inflate);
        }
        if (!this.mBrightnessDialog.isShowing()) {
            this.mBrightnessDialog.show();
        }
        if (i > 100) {
            i = 100;
        } else if (i < 0) {
            i = 0;
        }
        this.mDialogBrightnessTextView.setText(i + "%");
        this.mDialogBrightnessProgressBar.setProgress(i);
        onCLickUiToggleToClear();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void dismissBrightnessDialog() {
        super.dismissBrightnessDialog();
        Dialog dialog = this.mBrightnessDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public Dialog createDialogWithView(View view) {
        Dialog dialog = new Dialog(getContext(), R.style.style_dialog_progress);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.addFlags(8);
        window.addFlags(32);
        window.addFlags(16);
        window.setLayout(-2, -2);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        window.setAttributes(attributes);
        return dialog;
    }

    public void startDismissControlViewTimer() {
        cancelDismissControlViewTimer();
        DISMISS_CONTROL_VIEW_TIMER = new Timer();
        this.mDismissControlViewTimerTask = new DismissControlViewTimerTask();
        DISMISS_CONTROL_VIEW_TIMER.schedule(this.mDismissControlViewTimerTask, 2500);
    }

    public void cancelDismissControlViewTimer() {
        Timer timer = DISMISS_CONTROL_VIEW_TIMER;
        if (timer != null) {
            timer.cancel();
        }
        DismissControlViewTimerTask dismissControlViewTimerTask = this.mDismissControlViewTimerTask;
        if (dismissControlViewTimerTask != null) {
            dismissControlViewTimerTask.cancel();
        }
    }

    @Override // cn.missfresh.player.VideoLayout
    public void onAutoCompletion() {
        super.onAutoCompletion();
        cancelDismissControlViewTimer();
    }

    @Override // cn.missfresh.player.VideoLayout
    public void reset() {
        super.reset();
        cancelDismissControlViewTimer();
        PopupWindow popupWindow = this.clarityPopWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void dissmissControlView() {
        if (this.state != 0 && this.state != 8 && this.state != 7) {
            post(new $$Lambda$StdVideoLayout$wrqTLWJ29LDJd8Rr95rAAeYX2b8(this));
        }
    }

    public /* synthetic */ void lambda$dissmissControlView$2$StdVideoLayout() {
        this.bottomContainer.setVisibility(4);
        this.topContainer.setVisibility(4);
        this.startButton.setVisibility(4);
        PopupWindow popupWindow = this.clarityPopWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        if (this.screen != 2) {
            this.bottomProgressBar.setVisibility(0);
        }
    }

    public class DismissControlViewTimerTask extends TimerTask {
        public DismissControlViewTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            AppMethodBeat.i(7572, false);
            StdVideoLayout.this.dissmissControlView();
            AppMethodBeat.o(7572);
        }
    }

    /* renamed from: cn.missfresh.player.StdVideoLayout$1  reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {
        AnonymousClass1() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppMethodBeat.i(7700, false);
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                StdVideoLayout.LAST_GET_BATTERYLEVEL_PERCENT = (intent.getIntExtra("level", 0) * 100) / intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
                StdVideoLayout.this.setBatteryLevel();
                try {
                    StdVideoLayout.this.getContext().unregisterReceiver(StdVideoLayout.this.battertReceiver);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            AppMethodBeat.o(7700);
        }
    }

    private void registerWifiListener(Context context) {
        if (context != null) {
            this.mIsWifi = MediaUtils.isWifiConnected(context);
            context.registerReceiver(this.wifiReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    private void unregisterWifiListener(Context context) {
        if (context != null) {
            context.unregisterReceiver(this.wifiReceiver);
        }
    }

    /* renamed from: cn.missfresh.player.StdVideoLayout$2  reason: invalid class name */
    class AnonymousClass2 extends BroadcastReceiver {
        AnonymousClass2() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppMethodBeat.i(7568, false);
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                boolean isWifiConnected = MediaUtils.isWifiConnected(context);
                if (StdVideoLayout.this.mIsWifi == isWifiConnected) {
                    AppMethodBeat.o(7568);
                    return;
                }
                StdVideoLayout.this.mIsWifi = isWifiConnected;
                if (!StdVideoLayout.this.mIsWifi && !VideoLayout.WIFI_TIP_DIALOG_SHOWED && StdVideoLayout.this.state == 5) {
                    StdVideoLayout.this.startButton.performClick();
                    StdVideoLayout.this.showWifiDialog();
                }
            }
            AppMethodBeat.o(7568);
        }
    }
}
