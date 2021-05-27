package com.mobileapp.scheduler.Async;

import android.os.AsyncTask;

import com.mobileapp.scheduler.dao.DiaryDao;
import com.mobileapp.scheduler.dao.MemoDao;
import com.mobileapp.scheduler.entity.Diary;
import com.mobileapp.scheduler.entity.Memo;

import java.util.List;

public class DiaryShowAsync extends AsyncTask<Diary, Void, List<Diary>> {
    private DiaryDao mDiaryDao;

    public DiaryShowAsync(DiaryDao DiaryDao){
        this.mDiaryDao = DiaryDao;
    }

    @Override
    protected List<Diary> doInBackground(Diary... diarys) {
        return mDiaryDao.getAll();
    }
}
