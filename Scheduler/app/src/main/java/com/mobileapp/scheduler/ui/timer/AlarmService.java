package com.mobileapp.scheduler.ui.timer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.mobileapp.scheduler.R;

public class AlarmService extends Service {

    MediaPlayer mediaplayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaplayer = MediaPlayer.create(this, R.raw.alarm);
        Toast.makeText(this, "타이머가 종료되었습니다", Toast.LENGTH_SHORT).show();
        mediaplayer.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaplayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        mediaplayer.stop();

        super.onDestroy();
    }

}
