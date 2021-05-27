package com.mobileapp.scheduler.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.scheduler.Async.CalendarDeleteWantAsync;
import com.mobileapp.scheduler.Async.CalendarShowAsync;
import com.mobileapp.scheduler.Async.CalendarShowWantNameAsync;
import com.mobileapp.scheduler.MainActivity;
import com.mobileapp.scheduler.R;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.room.CalendarRoomDatabase;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter {

    private List<Calendar> calendarList;
    private Context context;
    private String day;
    Activity activity;

    public CalendarAdapter(List<Calendar> calendarList, Context context, Activity activity, String day) {
        this.calendarList = calendarList;
        this.context = context;
        this.activity = activity;
        this.day = day;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(context).
                inflate(R.layout.calendar_item, parent, false);
        return new RecyclerView.ViewHolder(layoutInflater) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView calendarName = holder.itemView.findViewById(R.id.calendarName);
        TextView time = holder.itemView.findViewById(R.id.calendarTime);

        if(!calendarList.isEmpty()){
            Calendar calendar = calendarList.get(position);

            calendarName.setText(calendar.getCalendarName());
            time.setText(calendar.getStartTime() + " ~ " + calendar.endTime);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarRoomDatabase db = CalendarRoomDatabase.getDatabase(activity);
                Calendar dialogCal = null;

                try {
                    List<Calendar> c = new CalendarShowAsync(db.calendarDao()).execute().get();

                    dialogCal = new CalendarShowWantNameAsync(db.calendarDao(), day, calendarName.getText().toString()).execute().get();
                    Log.e("dialogTest", "d "+ dialogCal.getCalendarName() + " " + dialogCal.getStartDay() + " "+dialogCal.getStartTime() + " " +dialogCal.getEndTime() + " " + dialogCal.getCalendar_memo());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(day);
                    builder.setMessage("일정: " + dialogCal.getCalendarName() + "\n시간: " + dialogCal.getStartTime() + " ~ " + dialogCal.getEndTime() + "\n메모:" + dialogCal.getCalendar_memo());

                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id){
                        }
                    });

                    builder.setNegativeButton("삭제", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id){
                            try {
                                new CalendarDeleteWantAsync(db.calendarDao(), calendarName.getText().toString(), day).execute();
                                Intent intent = new Intent(activity, MainActivity.class);
                                activity.startActivity(intent);
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (calendarList == null) return 0;
        return calendarList.size();
    }
}
