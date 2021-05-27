package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.DiaryDao;
import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;

public class DiaryInsertAsync extends AsyncTask<Diary, Void, Void> {
    private DiaryDao mdiaryDao;

    public DiaryInsertAsync(DiaryDao diaryDao){
        this.mdiaryDao = diaryDao;
    }

    @Override
    protected Void doInBackground(Diary... diarys) {
        mdiaryDao.insert(diarys[0]);
        return null;
    }
}
