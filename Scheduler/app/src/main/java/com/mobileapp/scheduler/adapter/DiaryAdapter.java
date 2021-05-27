package com.mobileapp.scheduler.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.scheduler.R;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter {

    private List<Diary> diaryList;
    private Context context;

    public DiaryAdapter(List<Diary> diaryList, Context context) {
        this.diaryList = diaryList;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        if (diaryList == null) return 0;
        return diaryList.size();
    }

}
