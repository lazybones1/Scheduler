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
import com.mobileapp.scheduler.Async.DiaryDeleteWantAsync;
import com.mobileapp.scheduler.Async.DiaryShowWantNameAsync;
import com.mobileapp.scheduler.Async.MemoShowAsync;
import com.mobileapp.scheduler.Async.MemoShowWantNameAsync;
import com.mobileapp.scheduler.MainActivity;
import com.mobileapp.scheduler.R;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;
import com.mobileapp.scheduler.room.CalendarRoomDatabase;
import com.mobileapp.scheduler.room.DiaryRoomDatabase;
import com.mobileapp.scheduler.room.MemoRoomDatabase;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter {

    private List<Diary> diaryList;
    private Context context;
    private String day;
    Activity activity;

    public DiaryAdapter(List<Diary> diaryList, Context context, Activity activity, String day) {
        this.diaryList = diaryList;
        this.context = context;
        this.activity = activity;
        this.day = day;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(context).
                inflate(R.layout.diary_itme, parent, false);
        return new RecyclerView.ViewHolder(layoutInflater) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView diaryName = holder.itemView.findViewById(R.id.diaryName);
        TextView diaryContent = holder.itemView.findViewById(R.id.diaryContent);
        TextView diaryWeather = holder.itemView.findViewById(R.id.diaryWeather);

        if(!diaryList.isEmpty()){
            Diary diary = diaryList.get(position);

            diaryName.setText(diary.getDiaryName());
            diaryContent.setText(diary.getDiary_memo());
            diaryWeather.setText(diary.getDiary_weather());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryRoomDatabase db = DiaryRoomDatabase.getDatabase(activity);
                Diary dialogCal = null;

                try {
                    dialogCal = new DiaryShowWantNameAsync(db.diaryDao(), day, diaryName.getText().toString()).execute().get();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(day);
                    builder.setMessage("제목: " + dialogCal.getDiaryName() + "\n날씨: " + dialogCal.getDiary_weather() + "\n내용:" + dialogCal.getDiary_memo());

                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

                    builder.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                new DiaryDeleteWantAsync(db.diaryDao(), diaryName.getText().toString(), day).execute();
                                Intent intent = new Intent(activity, MainActivity.class);
                                activity.startActivity(intent);
                            } catch (Exception e) {
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
        if (diaryList == null) return 0;
        return diaryList.size();
    }

}
