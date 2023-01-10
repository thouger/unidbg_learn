package cn.missfresh.player;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.AudioManager;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.missfresh.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class VideoLayout extends FrameLayout implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {
    public static LinkedList<ViewGroup> CONTAINER_LIST = null;
    public static int FULLSCREEN_ORIENTATION = 0;
    public static int NORMAL_ORIENTATION = 0;
    public static int ON_PLAY_PAUSE_TMP_STATE = 0;
    public static boolean SAVE_PROGRESS = false;
    public static final int SCREEN_FULLSCREEN = 1;
    public static final int SCREEN_NORMAL = 0;
    public static final int SCREEN_TINY = 2;
    public static final int STATE_AUTO_COMPLETE = 7;
    public static final int STATE_ERROR = 8;
    public static final int STATE_IDLE = -1;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_PAUSE = 6;
    public static final int STATE_PLAYING = 5;
    public static final int STATE_PREPARED = 4;
    public static final int STATE_PREPARING = 1;
    public static final int STATE_PREPARING_CHANGE_URL = 2;
    public static final int STATE_PREPARING_PLAYING = 3;
    public static final String TAG = "BaseVideoLayout";
    public static final int THRESHOLD = 80;
    public static boolean TOOL_BAR_EXIST = false;
    public static int VIDEO_IMAGE_DISPLAY_TYPE = 0;
    public static final int VIDEO_IMAGE_DISPLAY_TYPE_ADAPTER = 0;
    public static final int VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT = 1;
    public static final int VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP = 2;
    public static final int VIDEO_IMAGE_DISPLAY_TYPE_ORIGINAL = 3;
    public static boolean WIFI_TIP_DIALOG_SHOWED = false;
    public static int backUpBufferState = -1;
    public static VideoLayout currentVideoLayout;
    public static long lastAutoFullscreenTime = 0;
    public static AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AnonymousClass2();
    protected Timer UPDATE_PROGRESS_TIMER;
    public ViewGroup bottomContainer;
    public TextView currentTimeTextView;
    public ImageView fullscreenButton;
    protected long gobakFullscreenTime = 0;
    protected long gotoFullscreenTime = 0;
    public int heightRatio = 0;
    protected AudioManager mAudioManager;
    protected boolean mChangeBrightness;
    protected boolean mChangePosition;
    protected boolean mChangeVolume;
    private Context mContext;
    protected float mDownX;
    protected float mDownY;
    protected float mGestureDownBrightness;
    protected long mGestureDownPosition;
    protected int mGestureDownVolume;
    public MediaSource mMediaSource;
    protected ProgressTimerTask mProgressTimerTask;
    protected int mScreenHeight;
    protected int mScreenWidth;
    protected long mSeekTimePosition;
    private boolean mTouchControlSwitch = false;
    protected boolean mTouchingProgressBar;
    public MediaApi mediaInterface;
    public Class mediaInterfaceClass;
    public int positionInList = -1;
    public boolean preloading = false;
    public SeekBar progressBar;
    public int screen = -1;
    public long seekToInAdvance = 0;
    public int seekToManulPosition = -1;
    public ImageView startButton;
    public int state = -1;
    public PlayerView textureView;
    public ViewGroup textureViewContainer;
    public ViewGroup topContainer;
    public TextView totalTimeTextView;
    public int videoRotation = 0;
    public int widthRatio = 0;

    public abstract boolean checkWifiAndUrl();

    public void dismissBrightnessDialog() {
    }

    public void dismissProgressDialog() {
    }

    public void dismissVolumeDialog() {
    }

    public abstract int getLayoutId();

    public void onSeekComplete() {
    }

    public void showBrightnessDialog(int i) {
    }

    public void showProgressDialog(float f, String str, long j, String str2, long j2) {
    }

    public void showVolumeDialog(float f, int i) {
    }

    static {
        CONTAINER_LIST = new LinkedList<>();
        TOOL_BAR_EXIST = true;
        FULLSCREEN_ORIENTATION = 6;
        NORMAL_ORIENTATION = 1;
        SAVE_PROGRESS = false;
        VIDEO_IMAGE_DISPLAY_TYPE = 0;
        ON_PLAY_PAUSE_TMP_STATE = 0;
    }

    public VideoLayout(Context context) {
        super(context);
        init(context);
    }

    public VideoLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public void init(Context context) {
        View.inflate(context, getLayoutId(), this);
        this.startButton = (ImageView) findViewById(R.id.start);
        this.fullscreenButton = (ImageView) findViewById(R.id.fullscreen);
        this.progressBar = (SeekBar) findViewById(R.id.bottom_seek_progress);
        this.currentTimeTextView = (TextView) findViewById(R.id.current);
        this.totalTimeTextView = (TextView) findViewById(R.id.total);
        this.bottomContainer = (ViewGroup) findViewById(R.id.layout_bottom);
        this.textureViewContainer = (ViewGroup) findViewById(R.id.surface_container);
        this.topContainer = (ViewGroup) findViewById(R.id.layout_top);
        this.startButton.setOnClickListener(this);
        this.fullscreenButton.setOnClickListener(this);
        this.progressBar.setOnSeekBarChangeListener(this);
        this.bottomContainer.setOnClickListener(this);
        this.textureViewContainer.setOnClickListener(this);
        this.textureViewContainer.setOnTouchListener(this);
        this.mScreenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        this.mScreenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
        this.state = -1;
    }

    public void setUp(String str, String str2) {
        setUp(new MediaSource(str, str2), 0);
    }

    public void setUp(String str, String str2, int i) {
        setUp(new MediaSource(str, str2), i);
    }

    public void setUp(MediaSource mediaSource, int i) {
        setUp(mediaSource, i, PlayerEngine.class);
    }

    public void setUp(String str, String str2, int i, Class cls) {
        setUp(new MediaSource(str, str2), i, cls);
    }

    public void setUp(MediaSource mediaSource, int i, Class cls) {
        this.mMediaSource = mediaSource;
        this.screen = i;
        onStateNormal();
        this.mediaInterfaceClass = cls;
    }

    public void setMediaInterface(Class cls) {
        reset();
        this.mediaInterfaceClass = cls;
    }

    public boolean checkSourceValid() {
        MediaSource mediaSource = this.mMediaSource;
        if (mediaSource != null && !mediaSource.urlsMap.isEmpty() && this.mMediaSource.getCurrentUrl() != null) {
            return true;
        }
        Toast.makeText(getContext(), getResources().getString(R.string.no_url), 0).show();
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        Log.i(TAG, "onClick start [" + hashCode() + "] ,view id: " + id);
        if (id == R.id.start) {
            if (checkSourceValid()) {
                int i = this.state;
                if (i == 0) {
                    if (!checkWifiAndUrl()) {
                        startVideo();
                    }
                } else if (i == 5) {
                    Log.d(TAG, "pauseVideo [" + hashCode() + "] ");
                    this.mediaInterface.pause();
                    onStatePause();
                } else if (i == 6) {
                    this.mediaInterface.start();
                    onStatePlaying();
                } else if (i == 7) {
                    startVideo();
                }
            }
        } else if (id == R.id.fullscreen) {
            Log.i(TAG, "onClick fullscreen [" + hashCode() + "] ");
            if (this.state != 7) {
                if (this.screen == 1) {
                    backPress();
                    return;
                }
                Log.d(TAG, "toFullscreenActivity [" + hashCode() + "] ");
                gotoScreenFullscreen();
            }
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int i;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (view.getId() == R.id.surface_container) {
            int action = motionEvent.getAction();
            if (action == 0) {
                Log.i(TAG, "onTouch surfaceContainer actionDown [" + hashCode() + "] ");
                this.mTouchingProgressBar = true;
                this.mDownX = x;
                this.mDownY = y;
                this.mChangeVolume = false;
                this.mChangePosition = false;
                this.mChangeBrightness = false;
            } else if (action == 1) {
                Log.i(TAG, "onTouch surfaceContainer actionUp [" + hashCode() + "] ");
                this.mTouchingProgressBar = false;
                dismissProgressDialog();
                dismissVolumeDialog();
                dismissBrightnessDialog();
                if (this.mChangePosition) {
                    this.mediaInterface.seekTo(this.mSeekTimePosition);
                    long duration = getDuration();
                    long j = this.mSeekTimePosition * 100;
                    if (duration == 0) {
                        duration = 1;
                    }
                    this.progressBar.setProgress((int) (j / duration));
                }
                boolean z = this.mChangeVolume;
                startProgressTimer();
            } else if (action == 2) {
                Log.i(TAG, "onTouch surfaceContainer actionMove [" + hashCode() + "] ");
                float f = x - this.mDownX;
                float f2 = y - this.mDownY;
                float abs = Math.abs(f);
                float abs2 = Math.abs(f2);
                if (this.screen == 1 && this.mTouchControlSwitch && !this.mChangePosition && !this.mChangeVolume && !this.mChangeBrightness && (abs > 80.0f || abs2 > 80.0f)) {
                    cancelProgressTimer();
                    if (i >= 0) {
                        if (this.state != 8) {
                            this.mChangePosition = true;
                            this.mGestureDownPosition = getCurrentPositionWhenPlaying();
                        }
                    } else if (this.mDownX < ((float) this.mScreenWidth) * 0.5f) {
                        this.mChangeBrightness = true;
                        WindowManager.LayoutParams attributes = MediaUtils.getWindow(getContext()).getAttributes();
                        if (attributes.screenBrightness < 0.0f) {
                            try {
                                this.mGestureDownBrightness = (float) Settings.System.getInt(getContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                                Log.i(TAG, "current system brightness: " + this.mGestureDownBrightness);
                            } catch (Settings.SettingNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            this.mGestureDownBrightness = attributes.screenBrightness * 255.0f;
                            Log.i(TAG, "current activity brightness: " + this.mGestureDownBrightness);
                        }
                    } else {
                        this.mChangeVolume = true;
                        this.mGestureDownVolume = this.mAudioManager.getStreamVolume(3);
                    }
                }
                if (this.mChangePosition) {
                    long duration2 = getDuration();
                    this.mSeekTimePosition = (long) ((int) (((float) this.mGestureDownPosition) + ((((float) duration2) * f) / ((float) this.mScreenWidth))));
                    if (this.mSeekTimePosition > duration2) {
                        this.mSeekTimePosition = duration2;
                    }
                    showProgressDialog(f, MediaUtils.stringForTime(this.mSeekTimePosition), this.mSeekTimePosition, MediaUtils.stringForTime(duration2), duration2);
                }
                if (this.mChangeVolume) {
                    f2 = -f2;
                    int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
                    this.mAudioManager.setStreamVolume(3, this.mGestureDownVolume + ((int) (((((float) streamMaxVolume) * f2) * 3.0f) / ((float) this.mScreenHeight))), 0);
                    showVolumeDialog(-f2, (int) (((float) ((this.mGestureDownVolume * 100) / streamMaxVolume)) + (((f2 * 3.0f) * 100.0f) / ((float) this.mScreenHeight))));
                }
                if (this.mChangeBrightness) {
                    float f3 = -f2;
                    WindowManager.LayoutParams attributes2 = MediaUtils.getWindow(getContext()).getAttributes();
                    float f4 = this.mGestureDownBrightness;
                    float f5 = (float) ((int) (((f3 * 255.0f) * 3.0f) / ((float) this.mScreenHeight)));
                    if ((f4 + f5) / 255.0f >= 1.0f) {
                        attributes2.screenBrightness = 1.0f;
                    } else if ((f4 + f5) / 255.0f <= 0.0f) {
                        attributes2.screenBrightness = 0.01f;
                    } else {
                        attributes2.screenBrightness = (f4 + f5) / 255.0f;
                    }
                    MediaUtils.getWindow(getContext()).setAttributes(attributes2);
                    showBrightnessDialog((int) (((this.mGestureDownBrightness * 100.0f) / 255.0f) + (((f3 * 3.0f) * 100.0f) / ((float) this.mScreenHeight))));
                }
            }
        }
        return false;
    }

    public void onStateNormal() {
        Log.i(TAG, "onStateNormal  [" + hashCode() + "] ");
        this.state = 0;
        cancelProgressTimer();
        MediaApi mediaApi = this.mediaInterface;
        if (mediaApi != null) {
            mediaApi.release();
        }
    }

    public void onStatePreparing() {
        Log.i(TAG, "onStatePreparing  [" + hashCode() + "] ");
        this.state = 1;
        resetProgressAndTime();
    }

    public void onStatePreparingPlaying() {
        Log.i(TAG, "onStatePreparingPlaying  [" + hashCode() + "] ");
        this.state = 3;
    }

    public void onStatePreparingChangeUrl() {
        Log.i(TAG, "onStatePreparingChangeUrl  [" + hashCode() + "] ");
        this.state = 2;
        releaseAllVideos();
        startVideo();
    }

    public void changeUrl(MediaSource mediaSource, long j) {
        this.mMediaSource = mediaSource;
        this.seekToInAdvance = j;
        onStatePreparingChangeUrl();
    }

    public void onPrepared() {
        Log.i(TAG, "onPrepared  [" + hashCode() + "] ");
        this.state = 4;
        if (!this.preloading) {
            this.mediaInterface.start();
            this.preloading = false;
        }
        if (this.mMediaSource.getCurrentUrl().toString().toLowerCase().contains("mp3") || this.mMediaSource.getCurrentUrl().toString().toLowerCase().contains("wma") || this.mMediaSource.getCurrentUrl().toString().toLowerCase().contains("aac") || this.mMediaSource.getCurrentUrl().toString().toLowerCase().contains("m4a") || this.mMediaSource.getCurrentUrl().toString().toLowerCase().contains("wav")) {
            onStatePlaying();
        }
    }

    public void startPreloading() {
        this.preloading = true;
        startVideo();
    }

    public void startVideoAfterPreloading() {
        if (this.state == 4) {
            this.mediaInterface.start();
            return;
        }
        this.preloading = false;
        startVideo();
    }

    public void onStatePlaying() {
        Log.i(TAG, "onStatePlaying  [" + hashCode() + "] ");
        if (this.state == 4) {
            long j = this.seekToInAdvance;
            if (j != 0) {
                this.mediaInterface.seekTo(j);
                this.seekToInAdvance = 0;
            } else {
                long savedProgress = MediaUtils.getSavedProgress(getContext(), this.mMediaSource.getCurrentUrl());
                if (savedProgress != 0) {
                    this.mediaInterface.seekTo(savedProgress);
                }
            }
        }
        this.state = 5;
        startProgressTimer();
    }

    public void onStatePause() {
        Log.i(TAG, "onStatePause  [" + hashCode() + "] ");
        this.state = 6;
        startProgressTimer();
    }

    public void onStateError() {
        Log.i(TAG, "onStateError  [" + hashCode() + "] ");
        this.state = 8;
        cancelProgressTimer();
    }

    public void onStateAutoComplete() {
        Log.i(TAG, "onStateAutoComplete  [" + hashCode() + "] ");
        this.state = 7;
        cancelProgressTimer();
        this.progressBar.setProgress(100);
        this.currentTimeTextView.setText(this.totalTimeTextView.getText());
    }

    public void onInfo(int i, int i2) {
        Log.d(TAG, "onInfo what - " + i + " extra - " + i2);
        if (i == 3) {
            Log.d(TAG, "MEDIA_INFO_VIDEO_RENDERING_START");
            int i3 = this.state;
            if (i3 == 4 || i3 == 2) {
                onStatePlaying();
            }
        } else if (i == 701) {
            Log.d(TAG, "MEDIA_INFO_BUFFERING_START");
            backUpBufferState = this.state;
            setState(3);
        } else if (i == 702) {
            Log.d(TAG, "MEDIA_INFO_BUFFERING_END");
            int i4 = backUpBufferState;
            if (i4 != -1) {
                setState(i4);
                backUpBufferState = -1;
            }
        }
    }

    public void onError(int i, int i2) {
        Log.e(TAG, "onError " + i + " - " + i2 + " [" + hashCode() + "] ");
        if (i != 38 && i2 != -38 && i != -38 && i2 != 38 && i2 != -19) {
            onStateError();
            this.mediaInterface.release();
        }
    }

    public void onAutoCompletion() {
        Runtime.getRuntime().gc();
        Log.i(TAG, "onAutoCompletion  [" + hashCode() + "] ");
        cancelProgressTimer();
        dismissBrightnessDialog();
        dismissProgressDialog();
        dismissVolumeDialog();
        onStateAutoComplete();
        this.mediaInterface.release();
        MediaUtils.scanForActivity(getContext()).getWindow().clearFlags(128);
        MediaUtils.saveProgress(getContext(), this.mMediaSource.getCurrentUrl(), 0);
        if (this.screen != 1) {
            return;
        }
        if (CONTAINER_LIST.size() == 0) {
            clearFloatScreen();
            return;
        }
        setCurrentVideoLayout(null);
        gotoScreenNormal();
    }

    public void reset() {
        Log.i(TAG, "reset  [" + hashCode() + "] ");
        int i = this.state;
        if (i == 5 || i == 6) {
            MediaUtils.saveProgress(getContext(), this.mMediaSource.getCurrentUrl(), getCurrentPositionWhenPlaying());
        }
        cancelProgressTimer();
        dismissBrightnessDialog();
        dismissProgressDialog();
        dismissVolumeDialog();
        onStateNormal();
        post(new AnonymousClass1());
        MediaApi mediaApi = this.mediaInterface;
        if (mediaApi != null) {
            mediaApi.release();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.player.VideoLayout$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(7577, false);
            VideoLayout.this.textureViewContainer.removeAllViews();
            ((AudioManager) VideoLayout.this.getApplicationContext().getSystemService("audio")).abandonAudioFocus(VideoLayout.onAudioFocusChangeListener);
            MediaUtils.scanForActivity(VideoLayout.this.getContext()).getWindow().clearFlags(128);
            AppMethodBeat.o(7577);
        }
    }

    public void setState(int i) {
        switch (i) {
            case 0:
                onStateNormal();
                return;
            case 1:
                onStatePreparing();
                return;
            case 2:
                onStatePreparingChangeUrl();
                return;
            case 3:
                onStatePreparingPlaying();
                return;
            case 4:
            default:
                return;
            case 5:
                onStatePlaying();
                return;
            case 6:
                onStatePause();
                return;
            case 7:
                onStateAutoComplete();
                return;
            case 8:
                onStateError();
                return;
        }
    }

    public void setScreen(int i) {
        if (i == 0) {
            setScreenNormal();
        } else if (i == 1) {
            setScreenFullscreen();
        } else if (i == 2) {
            setScreenTiny();
        }
    }

    public void startVideo() {
        Log.d(TAG, "startVideo [" + hashCode() + "] ");
        setCurrentVideoLayout(this);
        try {
            this.mediaInterface = (MediaApi) this.mediaInterfaceClass.getConstructor(VideoLayout.class).newInstance(this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
        addTextureView();
        this.mAudioManager = (AudioManager) getApplicationContext().getSystemService("audio");
        this.mAudioManager.requestAudioFocus(onAudioFocusChangeListener, 3, 2);
        MediaUtils.scanForActivity(getContext()).getWindow().addFlags(128);
        onStatePreparing();
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = this.screen;
        if (i3 == 1 || i3 == 2) {
            super.onMeasure(i, i2);
        } else if (this.widthRatio == 0 || this.heightRatio == 0) {
            super.onMeasure(i, i2);
        } else {
            int size = View.MeasureSpec.getSize(i);
            int i4 = (int) ((((float) size) * ((float) this.heightRatio)) / ((float) this.widthRatio));
            setMeasuredDimension(size, i4);
            getChildAt(0).measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
        }
    }

    public void addTextureView() {
        Log.d(TAG, "addTextureView [" + hashCode() + "] ");
        PlayerView playerView = this.textureView;
        if (playerView != null) {
            this.textureViewContainer.removeView(playerView);
        }
        this.textureView = new PlayerView(getContext().getApplicationContext());
        this.textureView.setSurfaceTextureListener(this.mediaInterface);
        this.textureViewContainer.addView(this.textureView, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    public void clearFloatScreen() {
        MediaUtils.showStatusBar(getContext());
        MediaUtils.setRequestedOrientation(getContext(), NORMAL_ORIENTATION);
        MediaUtils.showSystemUI(getContext());
        ((ViewGroup) MediaUtils.scanForActivity(getContext()).getWindow().getDecorView()).removeView(this);
        MediaApi mediaApi = this.mediaInterface;
        if (mediaApi != null) {
            mediaApi.release();
        }
        currentVideoLayout = null;
    }

    public void onVideoSizeChanged(int i, int i2) {
        Log.i(TAG, "onVideoSizeChanged  [" + hashCode() + "] ");
        PlayerView playerView = this.textureView;
        if (playerView != null) {
            int i3 = this.videoRotation;
            if (i3 != 0) {
                playerView.setRotation((float) i3);
            }
            this.textureView.setVideoSize(i, i2);
        }
    }

    public void startProgressTimer() {
        Log.i(TAG, "startProgressTimer:  [" + hashCode() + "] ");
        cancelProgressTimer();
        this.UPDATE_PROGRESS_TIMER = new Timer();
        this.mProgressTimerTask = new ProgressTimerTask();
        this.UPDATE_PROGRESS_TIMER.schedule(this.mProgressTimerTask, 0, 300);
    }

    public void cancelProgressTimer() {
        Timer timer = this.UPDATE_PROGRESS_TIMER;
        if (timer != null) {
            timer.cancel();
        }
        ProgressTimerTask progressTimerTask = this.mProgressTimerTask;
        if (progressTimerTask != null) {
            progressTimerTask.cancel();
        }
    }

    public void onProgress(int i, long j, long j2) {
        if (!this.mTouchingProgressBar) {
            int i2 = this.seekToManulPosition;
            if (i2 != -1) {
                if (i2 <= i) {
                    this.seekToManulPosition = -1;
                } else {
                    return;
                }
            } else if (i != 0) {
                this.progressBar.setProgress(i);
            }
        }
        if (j != 0) {
            this.currentTimeTextView.setText(MediaUtils.stringForTime(j));
        }
        this.totalTimeTextView.setText(MediaUtils.stringForTime(j2));
    }

    public void setBufferProgress(int i) {
        if (i != 0) {
            this.progressBar.setSecondaryProgress(i);
        }
    }

    public void resetProgressAndTime() {
        this.progressBar.setProgress(0);
        this.progressBar.setSecondaryProgress(0);
        this.currentTimeTextView.setText(MediaUtils.stringForTime(0));
        this.totalTimeTextView.setText(MediaUtils.stringForTime(0));
    }

    public long getCurrentPositionWhenPlaying() {
        int i = this.state;
        if (i != 5 && i != 6 && i != 3) {
            return 0;
        }
        try {
            return this.mediaInterface.getCurrentPosition();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public long getDuration() {
        try {
            return this.mediaInterface.getDuration();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.i(TAG, "bottomProgress onStartTrackingTouch [" + hashCode() + "] ");
        cancelProgressTimer();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.i(TAG, "bottomProgress onStopTrackingTouch [" + hashCode() + "] ");
        startProgressTimer();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        int i = this.state;
        if (i == 5 || i == 6) {
            long progress = (((long) seekBar.getProgress()) * getDuration()) / 100;
            this.seekToManulPosition = seekBar.getProgress();
            this.mediaInterface.seekTo(progress);
            Log.i(TAG, "seekTo " + progress + " [" + hashCode() + "] ");
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.currentTimeTextView.setText(MediaUtils.stringForTime((((long) i) * getDuration()) / 100));
        }
    }

    public void cloneVideoLayout(ViewGroup viewGroup) {
        try {
            VideoLayout videoLayout = (VideoLayout) getClass().getConstructor(Context.class).newInstance(getContext());
            videoLayout.setId(getId());
            viewGroup.addView(videoLayout);
            videoLayout.setUp(this.mMediaSource.cloneMe(), 0, this.mediaInterfaceClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        }
    }

    public void gotoScreenFullscreen() {
        this.gotoFullscreenTime = System.currentTimeMillis();
        this.mContext = ((ViewGroup) getParent()).getContext();
        ViewGroup viewGroup = (ViewGroup) getParent();
        viewGroup.removeView(this);
        cloneVideoLayout(viewGroup);
        CONTAINER_LIST.add(viewGroup);
        ((ViewGroup) MediaUtils.scanForActivity(this.mContext).getWindow().getDecorView()).addView(this, new FrameLayout.LayoutParams(-1, -1));
        setScreenFullscreen();
        MediaUtils.hideStatusBar(this.mContext);
        MediaUtils.setRequestedOrientation(this.mContext, FULLSCREEN_ORIENTATION);
        MediaUtils.hideSystemUI(this.mContext);
    }

    public void gotoScreenNormal() {
        this.gobakFullscreenTime = System.currentTimeMillis();
        ((ViewGroup) MediaUtils.scanForActivity(this.mContext).getWindow().getDecorView()).removeView(this);
        CONTAINER_LIST.getLast().removeAllViews();
        CONTAINER_LIST.getLast().addView(this, new FrameLayout.LayoutParams(-1, -1));
        CONTAINER_LIST.pop();
        setScreenNormal();
        MediaUtils.showStatusBar(this.mContext);
        MediaUtils.setRequestedOrientation(this.mContext, NORMAL_ORIENTATION);
        MediaUtils.showSystemUI(this.mContext);
    }

    public void setScreenNormal() {
        this.screen = 0;
    }

    public void setScreenFullscreen() {
        this.screen = 1;
    }

    public void setScreenTiny() {
        this.screen = 2;
    }

    public void autoFullscreen(float f) {
        int i;
        if (currentVideoLayout != null) {
            int i2 = this.state;
            if ((i2 == 5 || i2 == 6) && (i = this.screen) != 1 && i != 2) {
                if (f > 0.0f) {
                    MediaUtils.setRequestedOrientation(getContext(), 0);
                } else {
                    MediaUtils.setRequestedOrientation(getContext(), 8);
                }
                gotoScreenFullscreen();
            }
        }
    }

    public void autoQuitFullscreen() {
        if (System.currentTimeMillis() - lastAutoFullscreenTime > 2000 && this.state == 5 && this.screen == 1) {
            lastAutoFullscreenTime = System.currentTimeMillis();
            backPress();
        }
    }

    public Context getApplicationContext() {
        Context applicationContext;
        Context context = getContext();
        return (context == null || (applicationContext = context.getApplicationContext()) == null) ? context : applicationContext;
    }

    public static class JZAutoFullscreenListener implements SensorEventListener {
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            AppMethodBeat.i(7518, false);
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            float f3 = sensorEvent.values[2];
            if ((f < -12.0f || f > 12.0f) && System.currentTimeMillis() - VideoLayout.lastAutoFullscreenTime > 2000) {
                if (VideoLayout.currentVideoLayout != null) {
                    VideoLayout.currentVideoLayout.autoFullscreen(f);
                }
                VideoLayout.lastAutoFullscreenTime = System.currentTimeMillis();
            }
            AppMethodBeat.o(7518);
        }
    }

    public class ProgressTimerTask extends TimerTask {
        public ProgressTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            AppMethodBeat.i(7835, false);
            if (VideoLayout.this.state == 5 || VideoLayout.this.state == 6 || VideoLayout.this.state == 3) {
                VideoLayout.this.post(new $$Lambda$VideoLayout$ProgressTimerTask$PMmlxPwWH2SbdE0T_DmI94z9kbU(this));
            }
            AppMethodBeat.o(7835);
        }

        public /* synthetic */ void lambda$run$0$VideoLayout$ProgressTimerTask() {
            AppMethodBeat.i(7837, false);
            long currentPositionWhenPlaying = VideoLayout.this.getCurrentPositionWhenPlaying();
            long duration = VideoLayout.this.getDuration();
            VideoLayout.this.onProgress((int) ((100 * currentPositionWhenPlaying) / (duration == 0 ? 1 : duration)), currentPositionWhenPlaying, duration);
            AppMethodBeat.o(7837);
        }
    }

    /* renamed from: cn.missfresh.player.VideoLayout$2  reason: invalid class name */
    static class AnonymousClass2 implements AudioManager.OnAudioFocusChangeListener {
        AnonymousClass2() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            AppMethodBeat.i(7693, false);
            if (i == -2) {
                try {
                    VideoLayout videoLayout = VideoLayout.currentVideoLayout;
                    if (videoLayout != null && videoLayout.state == 5) {
                        videoLayout.post(new AnonymousClass1(videoLayout));
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                Log.d(VideoLayout.TAG, "AUDIOFOCUS_LOSS_TRANSIENT [" + hashCode() + "]");
            } else if (i == -1) {
                VideoLayout.releaseAllVideos();
                Log.d(VideoLayout.TAG, "AUDIOFOCUS_LOSS [" + hashCode() + "]");
            }
            AppMethodBeat.o(7693);
        }

        /* renamed from: cn.missfresh.player.VideoLayout$2$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ VideoLayout a;

            AnonymousClass1(VideoLayout videoLayout) {
                this.a = videoLayout;
            }

            @Override // java.lang.Runnable
            public void run() {
                AppMethodBeat.i(7560, false);
                this.a.startButton.performClick();
                AppMethodBeat.o(7560);
            }
        }
    }

    public static void goOnPlayOnResume() {
        VideoLayout videoLayout = currentVideoLayout;
        if (videoLayout != null) {
            int i = videoLayout.state;
            if (i == 6) {
                if (ON_PLAY_PAUSE_TMP_STATE == 6) {
                    videoLayout.onStatePause();
                    currentVideoLayout.mediaInterface.pause();
                } else {
                    videoLayout.onStatePlaying();
                    currentVideoLayout.mediaInterface.start();
                }
                ON_PLAY_PAUSE_TMP_STATE = 0;
            } else if (i == 1) {
                videoLayout.startVideo();
            }
        }
    }

    public static void goOnPlayOnPause() {
        VideoLayout videoLayout = currentVideoLayout;
        if (videoLayout != null) {
            int i = videoLayout.state;
            if (i == 7 || i == 0 || i == 8 || i == 1) {
                releaseAllVideos();
                return;
            }
            ON_PLAY_PAUSE_TMP_STATE = i;
            videoLayout.onStatePause();
            currentVideoLayout.mediaInterface.pause();
        }
    }

    public static void startFullscreenDirectly(Context context, Class cls, String str, String str2) {
        startFullscreenDirectly(context, cls, new MediaSource(str, str2));
    }

    public static void startFullscreenDirectly(Context context, Class cls, MediaSource mediaSource) {
        MediaUtils.hideStatusBar(context);
        MediaUtils.setRequestedOrientation(context, FULLSCREEN_ORIENTATION);
        MediaUtils.hideSystemUI(context);
        ViewGroup viewGroup = (ViewGroup) MediaUtils.scanForActivity(context).getWindow().getDecorView();
        try {
            VideoLayout videoLayout = (VideoLayout) cls.getConstructor(Context.class).newInstance(context);
            viewGroup.addView(videoLayout, new FrameLayout.LayoutParams(-1, -1));
            videoLayout.setUp(mediaSource, 1);
            videoLayout.startVideo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void releaseAllVideos() {
        Log.d(TAG, "releaseAllVideos");
        VideoLayout videoLayout = currentVideoLayout;
        if (videoLayout != null) {
            videoLayout.reset();
            currentVideoLayout = null;
        }
    }

    public static boolean backPress() {
        VideoLayout videoLayout;
        VideoLayout videoLayout2;
        Log.i(TAG, "backPress");
        if (CONTAINER_LIST.size() != 0 && (videoLayout2 = currentVideoLayout) != null) {
            videoLayout2.gotoScreenNormal();
            return true;
        } else if (CONTAINER_LIST.size() != 0 || (videoLayout = currentVideoLayout) == null || videoLayout.screen == 0) {
            return false;
        } else {
            videoLayout.clearFloatScreen();
            return true;
        }
    }

    public static void setCurrentVideoLayout(VideoLayout videoLayout) {
        VideoLayout videoLayout2 = currentVideoLayout;
        if (videoLayout2 != null) {
            videoLayout2.reset();
        }
        currentVideoLayout = videoLayout;
    }

    public static void setTextureViewRotation(int i) {
        PlayerView playerView;
        VideoLayout videoLayout = currentVideoLayout;
        if (videoLayout != null && (playerView = videoLayout.textureView) != null) {
            playerView.setRotation((float) i);
        }
    }

    public static void setVideoImageDisplayType(int i) {
        PlayerView playerView;
        VIDEO_IMAGE_DISPLAY_TYPE = i;
        VideoLayout videoLayout = currentVideoLayout;
        if (videoLayout != null && (playerView = videoLayout.textureView) != null) {
            playerView.requestLayout();
        }
    }

    public void setTouchControlSwitch(boolean z) {
        this.mTouchControlSwitch = z;
    }
}
