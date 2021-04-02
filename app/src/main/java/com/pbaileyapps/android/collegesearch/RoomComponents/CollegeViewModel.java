package com.pbaileyapps.android.collegesearch.RoomComponents;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class CollegeViewModel extends AndroidViewModel {
    private LiveData<List<College>> mCollegeArrayList;
    private CollegeDao collegeDao;
    CollegeDatabase database;
    public CollegeViewModel(Application application){
        super(application);
        database = CollegeDatabase.BuildCollegeDB(application.getApplicationContext());
        collegeDao = database.collegeDao();
    }
    public void insertCollege(College college){
        if(database != null){
            new insertAsyncTask(collegeDao).execute(college);
        }
    }
    public LiveData<List<College>> getAllColleges(){
        return collegeDao.getAllColleges();
    }
    class insertAsyncTask extends AsyncTask<College,Void,Void>{
        private CollegeDao mDao;
        public insertAsyncTask(CollegeDao dao) {
            super();
            mDao = dao;
        }

        @Override
        protected Void doInBackground(College... colleges) {
            mDao.insertCollege(colleges[0]);
            return null;
        }
    }

}
