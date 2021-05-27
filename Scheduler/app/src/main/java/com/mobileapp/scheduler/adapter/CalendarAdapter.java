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
import com.mobileapp.scheduler.entity.Calendar;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter {

    private List<Calendar> calendarList;
    private Context context;

    public CalendarAdapter(List<Calendar> calendarList, Context context) {
        this.calendarList = calendarList;
        this.context = context;
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

            Log.e("리사이클러뷰 확인", ""+calendarName.getText() + " " + time.getText());
        }
    }

    @Override
    public int getItemCount() {
        if (calendarList == null) return 0;
        return calendarList.size();
    }

}
