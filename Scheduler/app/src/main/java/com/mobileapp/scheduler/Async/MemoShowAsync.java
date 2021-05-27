package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.CalendarDao;
import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Calendar;
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

public class MemoShowAsync extends AsyncTask<Memo, Void, List<Memo>> {
    private MemoDao mMemoDao;

    public MemoShowAsync(MemoDao MemoDao){
        this.mMemoDao = MemoDao;
    }

    @Override
    protected List<Memo> doInBackground(Memo... memos) {
        return mMemoDao.getAll();
    }
}
