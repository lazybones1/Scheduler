package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Memo;

public class MemoInsertAsync extends AsyncTask<Memo, Void, Void> {
    private MemoDao mMemoDao;

    public MemoInsertAsync(MemoDao memoDao){
        this.mMemoDao = memoDao;
    }

    @Override
    protected Void doInBackground(Memo... memos) {
        mMemoDao.insert(memos[0]);
        return null;
    }
}
