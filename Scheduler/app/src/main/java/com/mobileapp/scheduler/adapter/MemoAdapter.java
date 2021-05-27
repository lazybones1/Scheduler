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
import com.mobileapp.scheduler.Async.MemoDeleteWantAsync;
import com.mobileapp.scheduler.Async.MemoShowWantNameAsync;
import com.mobileapp.scheduler.MainActivity;
import com.mobileapp.scheduler.R;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Memo;
import com.mobileapp.scheduler.room.CalendarRoomDatabase;
import com.mobileapp.scheduler.room.MemoRoomDatabase;

import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter {

    private List<Memo> memoList;
    private Context context;
    private String day;
    Activity activity;

    public MemoAdapter(List<Memo> memoList, Context context, Activity activity, String day) {
        this.memoList = memoList;
        this.context = context;
        this.activity = activity;
        this.day = day;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(context).
                inflate(R.layout.memo_item, parent, false);
        return new RecyclerView.ViewHolder(layoutInflater) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView memoName = holder.itemView.findViewById(R.id.memoName);
        TextView memoContent = holder.itemView.findViewById(R.id.memo);

        if(!memoList.isEmpty()){
            Memo memo = memoList.get(position);

            memoName.setText(memo.getMemoName());
            memoContent.setText(memo.getMemoContent());

            Log.e("리사이클러뷰 확인", ""+memoName.getText() + " " + memoContent.getText());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoRoomDatabase db = MemoRoomDatabase.getDatabase(activity);
                Memo dialogCal = null;

                try {
                    dialogCal = new MemoShowWantNameAsync(db.memoDao(), day, memoName.getText().toString()).execute().get();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(day);
                    builder.setMessage("제목: " + dialogCal.getMemoName() + "\n내용: " + dialogCal.getMemoContent());

                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

                    builder.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                new MemoDeleteWantAsync(db.memoDao(), memoName.getText().toString(), day).execute();
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
        if (memoList == null) return 0;
        return memoList.size();
    }

}
