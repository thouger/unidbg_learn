package cn.missfresh.player;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Surface;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Map;

public class PlayerEngine extends MediaApi implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener {
    private static final String TAG = "PlayerEngine";
    public MediaPlayer mediaPlayer;

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public PlayerEngine(VideoLayout videoLayout) {
        super(videoLayout);
    }

    @Override // cn.missfresh.player.MediaApi
    public void prepare() {
        AppMethodBeat.i(7711, false);
        release();
        this.mMediaHandlerThread = new HandlerThread("MF_MediaPlayer");
        this.mMediaHandlerThread.start();
        this.mMediaHandler = new Handler(this.mMediaHandlerThread.getLooper());
        this.handler = new Handler();
        this.mMediaHandler.post(new $$Lambda$PlayerEngine$T1CZyE4s4nEWdfpiR6qv5VEAVoI(this));
        AppMethodBeat.o(7711);
    }

    public /* synthetic */ void lambda$prepare$0$PlayerEngine() {
        AppMethodBeat.i(7827, false);
        try {
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setLooping(this.mVideoLayout.mMediaSource.looping);
            this.mediaPlayer.setOnPreparedListener(this);
            this.mediaPlayer.setOnCompletionListener(this);
            this.mediaPlayer.setOnBufferingUpdateListener(this);
            this.mediaPlayer.setScreenOnWhilePlaying(true);
            this.mediaPlayer.setOnSeekCompleteListener(this);
            this.mediaPlayer.setOnErrorListener(this);
            this.mediaPlayer.setOnInfoListener(this);
            this.mediaPlayer.setOnVideoSizeChangedListener(this);
            MediaPlayer.class.getDeclaredMethod("setDataSource", String.class, Map.class).invoke(this.mediaPlayer, this.mVideoLayout.mMediaSource.getCurrentUrl().toString(), this.mVideoLayout.mMediaSource.headerMap);
            this.mediaPlayer.prepareAsync();
            this.mediaPlayer.setSurface(new Surface(SAVED_SURFACE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(7827);
    }

    @Override // cn.missfresh.player.MediaApi
    public void start() {
        AppMethodBeat.i(7713, false);
        if (this.mediaPlayer == null) {
            AppMethodBeat.o(7713);
            return;
        }
        if (this.mMediaHandler != null) {
            this.mMediaHandler.post(new $$Lambda$PlayerEngine$mopcOlTVm6x4Bvvw9l8pm6ADxQI(this));
        } else if (this.mMediaHandlerThread != null && this.mMediaHandlerThread.isAlive()) {
            this.mMediaHandler = new Handler(this.mMediaHandlerThread.getLooper());
            this.mMediaHandler.post(new $$Lambda$PlayerEngine$Q4hwQHPN94ds9udKXdM0Wz1qMc(this));
        }
        AppMethodBeat.o(7713);
    }

    public /* synthetic */ void lambda$start$1$PlayerEngine() {
        AppMethodBeat.i(7823, false);
        this.mediaPlayer.start();
        AppMethodBeat.o(7823);
    }

    public /* synthetic */ void lambda$start$2$PlayerEngine() {
        AppMethodBeat.i(7820, false);
        this.mediaPlayer.start();
        AppMethodBeat.o(7820);
    }

    @Override // cn.missfresh.player.MediaApi
    public void pause() {
        AppMethodBeat.i(7716, false);
        if (this.mMediaHandler == null) {
            AppMethodBeat.o(7716);
            return;
        }
        this.mMediaHandler.post(new $$Lambda$PlayerEngine$WgHhHvySo0a3AkJj_Kf7m3LOzm0(this));
        AppMethodBeat.o(7716);
    }

    public /* synthetic */ void lambda$pause$3$PlayerEngine() {
        AppMethodBeat.i(7818, false);
        this.mediaPlayer.pause();
        AppMethodBeat.o(7818);
    }

    @Override // cn.missfresh.player.MediaApi
    public boolean isPlaying() {
        AppMethodBeat.i(7719, false);
        boolean isPlaying = this.mediaPlayer.isPlaying();
        AppMethodBeat.o(7719);
        return isPlaying;
    }

    @Override // cn.missfresh.player.MediaApi
    public void seekTo(long j) {
        AppMethodBeat.i(7724, false);
        if (this.mMediaHandler == null) {
            AppMethodBeat.o(7724);
            return;
        }
        this.mMediaHandler.post(new $$Lambda$PlayerEngine$opuCBw6dma8OlS5N_9vczl3evZ4(this, j));
        AppMethodBeat.o(7724);
    }

    public /* synthetic */ void lambda$seekTo$4$PlayerEngine(long j) {
        AppMethodBeat.i(7817, false);
        try {
            this.mediaPlayer.seekTo((int) j);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(7817);
    }

    @Override // cn.missfresh.player.MediaApi
    public void release() {
        AppMethodBeat.i(7728, false);
        if (!(this.mMediaHandler == null || this.mMediaHandlerThread == null || this.mediaPlayer == null)) {
            HandlerThread handlerThread = this.mMediaHandlerThread;
            MediaPlayer mediaPlayer = this.mediaPlayer;
            MediaApi.SAVED_SURFACE = null;
            this.mMediaHandler.post(new $$Lambda$PlayerEngine$h3JfGik2zKj5iDUhuGIe3iLkvW0(mediaPlayer, handlerThread));
            this.mediaPlayer = null;
        }
        AppMethodBeat.o(7728);
    }

    static /* synthetic */ void lambda$release$5(MediaPlayer mediaPlayer, HandlerThread handlerThread) {
        AppMethodBeat.i(7816, false);
        mediaPlayer.setSurface(null);
        mediaPlayer.release();
        handlerThread.quit();
        AppMethodBeat.o(7816);
    }

    @Override // cn.missfresh.player.MediaApi
    public long getCurrentPosition() {
        AppMethodBeat.i(7730, false);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            long currentPosition = (long) mediaPlayer.getCurrentPosition();
            AppMethodBeat.o(7730);
            return currentPosition;
        }
        AppMethodBeat.o(7730);
        return 0;
    }

    @Override // cn.missfresh.player.MediaApi
    public long getDuration() {
        AppMethodBeat.i(7735, false);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            long duration = (long) mediaPlayer.getDuration();
            AppMethodBeat.o(7735);
            return duration;
        }
        AppMethodBeat.o(7735);
        return 0;
    }

    @Override // cn.missfresh.player.MediaApi
    public void setVolume(float f, float f2) {
        AppMethodBeat.i(7739, false);
        if (this.mMediaHandler == null) {
            AppMethodBeat.o(7739);
            return;
        }
        this.mMediaHandler.post(new $$Lambda$PlayerEngine$wvqFjo_oTiJKE3XJdFgT6T0gYqw(this, f, f2));
        AppMethodBeat.o(7739);
    }

    public /* synthetic */ void lambda$setVolume$6$PlayerEngine(float f, float f2) {
        AppMethodBeat.i(7813, false);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f, f2);
        }
        AppMethodBeat.o(7813);
    }

    @Override // cn.missfresh.player.MediaApi
    public void setSpeed(float f) {
        AppMethodBeat.i(7744, false);
        if (Build.VERSION.SDK_INT >= 23) {
            PlaybackParams playbackParams = this.mediaPlayer.getPlaybackParams();
            playbackParams.setSpeed(f);
            this.mediaPlayer.setPlaybackParams(playbackParams);
        }
        AppMethodBeat.o(7744);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        AppMethodBeat.i(7749, false);
        Log.d(TAG, "onPrepared " + mediaPlayer.toString());
        this.handler.post(new $$Lambda$PlayerEngine$Ry9mmsD3vMjizMN7u7gQEPB9xo(this));
        AppMethodBeat.o(7749);
    }

    public /* synthetic */ void lambda$onPrepared$7$PlayerEngine() {
        AppMethodBeat.i(7812, false);
        this.mVideoLayout.onPrepared();
        AppMethodBeat.o(7812);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        AppMethodBeat.i(7755, false);
        Log.d(TAG, "onCompletion " + mediaPlayer.toString());
        this.handler.post(new $$Lambda$PlayerEngine$tLR6fyol4G48uoaNNNBnCEtypk(this));
        AppMethodBeat.o(7755);
    }

    public /* synthetic */ void lambda$onCompletion$8$PlayerEngine() {
        AppMethodBeat.i(7809, false);
        this.mVideoLayout.onAutoCompletion();
        AppMethodBeat.o(7809);
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        AppMethodBeat.i(7757, false);
        Log.d(TAG, "onBufferingUpdate " + mediaPlayer.toString() + ",percent: " + i);
        this.handler.post(new $$Lambda$PlayerEngine$KqY44DKfinWMTj4UMS7NBXKe5o(this, i));
        AppMethodBeat.o(7757);
    }

    public /* synthetic */ void lambda$onBufferingUpdate$9$PlayerEngine(int i) {
        AppMethodBeat.i(7806, false);
        this.mVideoLayout.setBufferProgress(i);
        AppMethodBeat.o(7806);
    }

    public /* synthetic */ void lambda$onSeekComplete$10$PlayerEngine() {
        AppMethodBeat.i(7803, false);
        this.mVideoLayout.onSeekComplete();
        AppMethodBeat.o(7803);
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        AppMethodBeat.i(7759, false);
        this.handler.post(new $$Lambda$PlayerEngine$tLNFIuXGSbtr8AyTrA7Ha7rZa2U(this));
        AppMethodBeat.o(7759);
    }

    public /* synthetic */ void lambda$onError$11$PlayerEngine(int i, int i2) {
        AppMethodBeat.i(7799, false);
        this.mVideoLayout.onError(i, i2);
        AppMethodBeat.o(7799);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        AppMethodBeat.i(7762, false);
        this.handler.post(new $$Lambda$PlayerEngine$yWByPHcUuCPW4wTZsssLnjpvvww(this, i, i2));
        AppMethodBeat.o(7762);
        return true;
    }

    public /* synthetic */ void lambda$onInfo$12$PlayerEngine(int i, int i2) {
        AppMethodBeat.i(7795, false);
        this.mVideoLayout.onInfo(i, i2);
        AppMethodBeat.o(7795);
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        AppMethodBeat.i(7767, false);
        this.handler.post(new $$Lambda$PlayerEngine$iPSEOwll0F8FokuIpjIuDKuR8w(this, i, i2));
        AppMethodBeat.o(7767);
        return false;
    }

    public /* synthetic */ void lambda$onVideoSizeChanged$13$PlayerEngine(int i, int i2) {
        AppMethodBeat.i(7791, false);
        this.mVideoLayout.onVideoSizeChanged(i, i2);
        AppMethodBeat.o(7791);
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        AppMethodBeat.i(7769, false);
        this.handler.post(new $$Lambda$PlayerEngine$Pn5RYFJAIlp5U2tjzgSLJ1H2kY(this, i, i2));
        AppMethodBeat.o(7769);
    }

    @Override // cn.missfresh.player.MediaApi
    public void setSurface(Surface surface) {
        AppMethodBeat.i(7773, false);
        this.mediaPlayer.setSurface(surface);
        AppMethodBeat.o(7773);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        AppMethodBeat.i(7776, false);
        if (SAVED_SURFACE == null) {
            SAVED_SURFACE = surfaceTexture;
            prepare();
        } else {
            this.mVideoLayout.textureView.setSurfaceTexture(SAVED_SURFACE);
        }
        AppMethodBeat.o(7776);
    }
}
