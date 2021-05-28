package com.mobileapp.scheduler.ui.memo;


import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.mobileapp.scheduler.MainActivity;
import com.mobileapp.scheduler.R;

import java.util.Calendar;

public class AddMemoActivty extends AppCompatActivity {
    EditText mName, mMemo;
    TextView mDate;
    Button addMemoBtn, startDayBtn;
    NotificationManager manager;
    NotificationCompat.Builder builder;
    EditText et;


    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        mName = findViewById(R.id.addMemoName);
        mMemo = findViewById(R.id.addMemo);
        mDate = findViewById(R.id.addMemoDate);

        et = findViewById(R.id.et);

        addMemoBtn = findViewById(R.id.addMemoBtn);
        startDayBtn = findViewById(R.id.startMemoDayBtn);

        addMemoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("mName", mName.getText().toString());
                intent.putExtra("mMemo", mMemo.getText().toString());
                intent.putExtra("mDate", mDate.getText().toString());

                showNoti(et.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");


        startDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                new DatePickerDialog(AddMemoActivty.this, startDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
            }
        });
    }

    public void showNoti(String name){
        if(et != null){
            builder = null;
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                manager.createNotificationChannel(
                        new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
                );

                builder = new NotificationCompat.Builder(this,CHANNEL_ID);
            }



            // 알림창 제목
            builder.setContentTitle("오늘의 다짐");
            // 알림창 메시지
            builder.setContentText(et.getText().toString());
            // 알림창 아이콘
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
            // 알림창 터치시 상단 알림상태창에서 알림이 자동으로 삭제되게 합니다.
            builder.setAutoCancel(false);

            // pendingIntent를 builder에 설정해줍니다.
            // 알림창 터치시 인텐트가 전달할 수 있도록 해줍니다.
//            builder.setContentIntent(pendingIntent);

            Notification notification = builder.build();
            // 알림창 실행

            manager.notify(1,notification);


        }
    }

    DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
            mDate.setText(String.format("%d-%d-%d", yy,mm+1,dd));
        }
    };
}
