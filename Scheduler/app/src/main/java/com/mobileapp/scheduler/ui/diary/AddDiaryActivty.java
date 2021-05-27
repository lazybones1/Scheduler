package com.mobileapp.scheduler.ui.diary;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobileapp.scheduler.R;

import java.util.Calendar;

public class AddDiaryActivty extends AppCompatActivity {
    EditText dName, mWeather, dMemo;
    TextView dDate;
    Button addDiaryBtn, diaryDayBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        dName = findViewById(R.id.addDiaryName);
        mWeather = findViewById(R.id.addDiaryWeather);
        dMemo = findViewById(R.id.addDiaryMemo);
        dDate = findViewById(R.id.addDiaryDate);

        addDiaryBtn = findViewById(R.id.addDiaryBtn);
        diaryDayBtn = findViewById(R.id.startDiaryDayBtn);


        addDiaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("dName", dName.getText().toString());
                intent.putExtra("mWeather", mWeather.getText().toString());
                intent.putExtra("dMemo", dMemo.getText().toString());
                intent.putExtra("dDate", dDate.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        diaryDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                new DatePickerDialog(AddDiaryActivty.this, startDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
            }
        });
    }
    DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    dDate.setText(String.format("%d-%d-%d", yy,mm+1,dd));
                }
            };
}
