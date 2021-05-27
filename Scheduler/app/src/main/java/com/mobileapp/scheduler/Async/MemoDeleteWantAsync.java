package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Memo;

public class MemoDeleteWantAsync extends AsyncTask<Memo, Void, Void> {
    private MemoDao mMemoDao;
    private String calName;
    private String calDay;

    public MemoDeleteWantAsync(MemoDao memoDao, String calName, String calDay){
        this.mMemoDao = memoDao;
        this.calName = calName;
        this.calDay = calDay;
    }

    @Override
    protected Void doInBackground(Memo... memos) {
        mMemoDao.deleteMemoendar(this.calName, this.calDay);
        return null;
    }
}
