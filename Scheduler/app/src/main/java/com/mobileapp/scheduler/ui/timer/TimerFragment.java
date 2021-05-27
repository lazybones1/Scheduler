package com.mobileapp.scheduler.ui.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mobileapp.scheduler.MainActivity;
import com.mobileapp.scheduler.R;
import com.mobileapp.scheduler.adapter.CalendarAdapter;

public class TimerFragment extends Fragment {

    private TextView timeH, timeM, timeS;
    private EditText editH, editM, editS;
    private boolean firstStart;
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
            //10보다 작은 경우 01, 02, 03,,,,
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_timer, container, false);

        startStopBtn = root.findViewById(R.id.startStop); // 시작/정지 버튼.
        resetBtn = root.findViewById(R.id.reset); // 리셋 버튼.

        setting = root.findViewById(R.id.setting); //시간 설정창
        timerView = root.findViewById(R.id.countDown); //타이머 화면.
        timerView.setVisibility(View.INVISIBLE); //처음에는 타이머 화면이 보이지 않음.

        editH = root.findViewById(R.id.timeHourEditText);
        editM = root.findViewById(R.id.timeMinuteEditText);
        editS = root.findViewById(R.id.timeSecondEditText);

        timeH = root.findViewById(R.id.timeHourText);
        timeM = root.findViewById(R.id.timeMinuteText);
        timeS = root.findViewById(R.id.timeSecondText);

        firstStart = true;

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
                    firstStart = true;
                    setting.setVisibility(View.VISIBLE);
                    timerView.setVisibility(View.INVISIBLE);
            }
        });

        return root;

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