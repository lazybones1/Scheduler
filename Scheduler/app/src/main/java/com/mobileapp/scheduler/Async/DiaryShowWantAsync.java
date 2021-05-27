package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.DiaryDao;
import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

public class DiaryShowWantAsync extends AsyncTask<Diary, Void, List<Diary>> {
    private DiaryDao mDiaryDao;
    private String day;

    public DiaryShowWantAsync(DiaryDao diaryDao, String day){
        this.mDiaryDao = diaryDao;
        this.day = day;
    }

    @Override
    protected List<Diary> doInBackground(Diary... diaries) {
        return mDiaryDao.getDayEvents(this.day);
    }
}
