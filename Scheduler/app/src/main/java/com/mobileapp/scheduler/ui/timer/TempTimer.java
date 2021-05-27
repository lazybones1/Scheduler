package com.mobileapp.scheduler.ui.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileapp.scheduler.MainActivity;
import com.mobileapp.scheduler.R;

public class TempTimer extends AppCompatActivity {

    private TextView timeH, timeM, timeS;
    private EditText editH, editM, editS;
    private boolean firstStart, timerStartPushed;
    private int timeHourCount, timeMinuteCount, timeSecondCount;
    private String h, m, s;
    private Double firstTime, currTime;
    private Handler handler = new Handler();
    private Button startStopBtn, resetBtn;
    private MediaPlayer mediaPlayer;

    private FrameLayout setting, timerView;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeH.setText(String.valueOf(timeHourCount));
            if (timeMinuteCount < 10) {
                timeM.setText(String.valueOf("0" + timeMinuteCount));
            } else {
                timeM.setText(String.valueOf(timeMinuteCount));
            }
            if (timeSecondCount < 10) {
                timeS.setText(String.valueOf("0" + timeSecondCount--));
            } else {
                timeS.setText(String.valueOf(timeSecondCount--));
            }


            if((timeHourCount-1)<0 && (timeMinuteCount-1)<0 && timeSecondCount<0){ //00:00:00
                handler.removeCallbacks(runnable);
            }
            else {
                if ((timeMinuteCount - 1) < 0 && timeSecondCount< 0) { //HH:00:00
                    timeMinuteCount = timeSecondCount = 59;
                    timeH.setText(String.valueOf(timeHourCount--));
                }
                else if (timeSecondCount < 0) { //HH:MM:00
                    timeSecondCount = 59;
                    timeM.setText(String.valueOf(timeMinuteCount--));
                }
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_timer);

        startStopBtn = findViewById(R.id.startStop); // 시작/정지 버튼.
        resetBtn = findViewById(R.id.reset); // 리셋 버튼.

        setting = findViewById(R.id.setting); //시간 설정창
        timerView = findViewById(R.id.countDown); //타이머 화면.
        timerView.setVisibility(View.INVISIBLE); //처음에는 타이머 화면이 보이지 않음.

        editH = findViewById(R.id.timeHourEditText);
        editM = findViewById(R.id.timeMinuteEditText);
        editS = findViewById(R.id.timeSecondEditText);

        timeH = findViewById(R.id.timeHourText);
        timeM = findViewById(R.id.timeMinuteText);
        timeS = findViewById(R.id.timeSecondText);

        firstStart = true;

        Intent i = getIntent();
        SharedPreferences sharedPreferences = getSharedPreferences("timerStartPushed", Context.MODE_PRIVATE);
        timerStartPushed = sharedPreferences.getBoolean("timerStartPushed", false);
        if(timerStartPushed) {
            CurrTimeSetting(i.getIntExtra("timeH", 0), i.getIntExtra("timeM", 0), i.getIntExtra("timeS", 0) );
            handler.post(runnable);
            setting.setVisibility(View.GONE);
            timerView.setVisibility(View.VISIBLE);
            firstStart = false;
            startStopBtn.setText("STOP");
            resetBtn.setText("RESET & BACK");
        }

        startStopBtn.setOnClickListener(new View.OnClickListener() { //시작/정지 버튼 push
            @Override
            public void onClick(View v) {
                String temp = startStopBtn.getText().toString();
                if(temp.equals("START")) { //타이머 멈춘 상태.
                    startStopBtn.setText("STOP");
                    if(firstStart){
                        FirstTimerSetting();
                    }
                    else{
                        CurrTimeSetting(timeHourCount, timeMinuteCount, timeSecondCount);
                    }
                    handler.post(runnable);
                    setting.setVisibility(View.GONE);
                    timerView.setVisibility(View.VISIBLE);
                }
                else{  //타이머 동작 중.
                    startStopBtn.setText("START");
                    firstStart = false;
                    handler.removeCallbacks(runnable);
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() { //리셋 버튼 push
            @Override
            public void onClick(View v) {
                if(timerStartPushed){
                    Intent i = new Intent(TempTimer.this, MainActivity.class);
                    firstStart=true;
                    startActivity(i);
                }
                else {
                    firstStart = true;
                    setting.setVisibility(View.VISIBLE);
                    timerView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void FirstTimerSetting(){
        h = editH.getText().toString();
        m = editM.getText().toString();
        s = editS.getText().toString();

        firstTime = (Double.parseDouble(h) * 3600000) + (Double.parseDouble(m) * 60000) + (Double.parseDouble(s) * 1000);

        timeHourCount = (int) (firstTime/3600000);
        timeMinuteCount = (int) (firstTime%3600000/60000);
        timeSecondCount = (int) (firstTime%3600000%60000/1000);
    }

    private  void CurrTimeSetting(int h, int m, int s) {
        currTime = (double) ((h *3600000 )+ (m*60000) + (s*1000));

        timeHourCount = (int) (currTime/3600000);
        timeMinuteCount = (int) (currTime%3600000/60000);
        timeSecondCount = (int) (currTime%3600000%60000/1000);

    }

}