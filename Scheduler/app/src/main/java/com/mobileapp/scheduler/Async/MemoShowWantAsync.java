package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

public class MemoShowWantAsync extends AsyncTask<Memo, Void, List<Memo>> {
    private MemoDao mMemoDao;
    private String day;

    public MemoShowWantAsync(MemoDao memoDao, String day){
        this.mMemoDao = memoDao;
        this.day = day;
    }

    @Override
    protected List<Memo> doInBackground(Memo... memos) {
        return mMemoDao.getDayEvents(this.day);
    }
}
