package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.DiaryDao;
import com.mobileapp.scheduler.entity.Diary;


public class DiaryDeleteWantAsync extends AsyncTask<Diary, Void, Void> {
    private DiaryDao mDiaryDao;
    private String calName;
    private String calDay;

    public DiaryDeleteWantAsync(DiaryDao diaryDao, String calName, String calDay){
        this.mDiaryDao = diaryDao;
        this.calName = calName;
        this.calDay = calDay;
    }

    @Override
    protected Void doInBackground(Diary... diaries) {
        mDiaryDao.deleteDiaryendar(this.calName, this.calDay);
        return null;
    }
}
