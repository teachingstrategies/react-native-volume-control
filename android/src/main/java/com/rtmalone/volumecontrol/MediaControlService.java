package com.rtmalone.volumecontrol;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;

public class MediaControlService extends Service {
  private MediaSessionCompat mMediaSession;
  private ReactApplicationContext rContext;
  private String TAG = RNVolumeControlModule.class.getSimpleName();

  public MediaControlService(ReactApplicationContext reactContext) {
    this.rContext = reactContext;
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    ComponentName componentName = new ComponentName(this.rContext, RNVolumeControlModule.class);
    mMediaSession = new MediaSessionCompat(this.rContext, TAG, componentName, null);
    mMediaSession.setCallback(new MediaSessionCompat.Callback() {
      @Override
      public boolean onMediaButtonEvent(Intent mediaButtonIntent) {
        Log.d(TAG, "MEDIA BUTTON FIRED");

        if (Intent.ACTION_MEDIA_BUTTON.equals((mediaButtonIntent.getAction()))) {
          Log.d(TAG, "Inside the media btn action equals");
          // float volume = getNormalizedVolume();
          // WritableMap params = Arguments.createMap();
          // params.putDouble("volume", volume);
          // sendEvent(rContext, "MediaBtnPressed", params);
        }
        return super.onMediaButtonEvent(mediaButtonIntent);
      }
    });
  }

  public int onStartCommand(Intent intent, int flags, int startId) {
    MediaButtonReceiver.handleIntent(mMediaSession, intent);
    return super.onStartCommand(intent, flags, startId);
  }

  // private void mediaKeyCallback() {
  //
  // }
}