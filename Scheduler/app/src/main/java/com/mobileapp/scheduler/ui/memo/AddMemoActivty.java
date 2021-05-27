package com.mobileapp.scheduler.ui.memo;


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

public class
AddMemoActivty extends AppCompatActivity {
    EditText mName, mMemo;
    TextView mDate;
    Button addMemoBtn, startDayBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        mName = findViewById(R.id.addMemoName);
        mMemo = findViewById(R.id.addMemo);
        mDate = findViewById(R.id.addMemoDate);

        addMemoBtn = findViewById(R.id.addMemoBtn);
        startDayBtn = findViewById(R.id.startMemoDayBtn);

        addMemoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("mName", mName.getText().toString());
                intent.putExtra("mMemo", mMemo.getText().toString());
                intent.putExtra("mDate", mDate.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        startDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                new DatePickerDialog(AddMemoActivty.this, startDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
            }
        });
    }
    DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    mDate.setText(String.format("%d-%d-%d", yy,mm+1,dd));
                }
            };
}
