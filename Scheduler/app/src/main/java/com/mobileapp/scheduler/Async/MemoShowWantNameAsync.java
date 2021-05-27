package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Memo;


public class MemoShowWantNameAsync extends AsyncTask<Void, Void, Memo> {
    private MemoDao mMemoDao;
    private String day;
    private String name;

    public MemoShowWantNameAsync(MemoDao memoDao, String day, String name){
        this.mMemoDao = memoDao;
        this.day = day;
        this.name = name;
    }

    @Override
    protected Memo doInBackground(Void... voids) {
        return mMemoDao.getMemoEvents(this.name, this.day);
    }
}
