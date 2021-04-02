package com.pbaileyapps.android.collegesearch.RoomComponents;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CollegeDao {
    @Query("Select * From College")
    public LiveData<List<College>> getAllColleges();
    @Insert()
    public void insertCollege(College college);
    @Delete()
    public void deleteCollege(College college);
    @Query("Delete From College")
    public void deleteAllColleges();
}
