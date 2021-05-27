package com.mobileapp.scheduler.ui.calender;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.mobileapp.scheduler.R;

import java.util.Calendar;

public class AddCalendarActivty extends AppCompatActivity {
    EditText cname, cMemo;
    TextView sDate, sTime, eTime;
    Button addCalBtn, startDayBtn, endDayBtn, startTimeBtn, endTimeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar);

        cname = findViewById(R.id.addCalendarName);
        sDate = findViewById(R.id.addCalendarStartDate);
        sTime = findViewById(R.id.addCalendarStartTime);
        eTime = findViewById(R.id.addCalendarEndTime);
        cMemo = findViewById(R.id.addCalendarMemo);

        addCalBtn = findViewById(R.id.addCalendarBtn);
        startDayBtn = findViewById(R.id.startDayBtn);
        startTimeBtn = findViewById(R.id.startTimeBtn);
        endTimeBtn = findViewById(R.id.endTimeBtn);


        addCalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("c_cname", cname.getText().toString());
                intent.putExtra("c_sDate", sDate.getText().toString());
                intent.putExtra("c_sTime", sTime.getText().toString());
                intent.putExtra("c_eTime", eTime.getText().toString());
                intent.putExtra("c_cMemo", cMemo.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        startDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                new DatePickerDialog(AddCalendarActivty.this, startDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
            }
        });

        startTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                new TimePickerDialog(AddCalendarActivty.this, startTimeSetListener, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show();
            }
        });
        endTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                new TimePickerDialog(AddCalendarActivty.this, endTimeSetListener, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show();
            }
        });
    }
    DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    sDate.setText(String.format("%d-%d-%d", yy,mm+1,dd));
                }
            };

    TimePickerDialog.OnTimeSetListener startTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hh, int mm) {
            sTime.setText(String.format("%02d:%02d", hh, mm));
        }
    };
    TimePickerDialog.OnTimeSetListener endTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hh, int mm) {
            eTime.setText(String.format("%02d:%02d", hh, mm ));
        }
    };
}
