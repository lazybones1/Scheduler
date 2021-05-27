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
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter {

    private List<Memo> memoList;
    private Context context;

    public MemoAdapter(List<Memo> memoList, Context context) {
        this.memoList = memoList;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        if (memoList == null) return 0;
        return memoList.size();
    }

}
