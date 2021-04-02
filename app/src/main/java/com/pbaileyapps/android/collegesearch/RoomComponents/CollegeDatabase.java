package com.pbaileyapps.android.collegesearch.RoomComponents;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {College.class},version =2 ,exportSchema = false)
public abstract class CollegeDatabase extends RoomDatabase {
    static CollegeDatabase DATABASE_INSTANCE;

    public abstract CollegeDao collegeDao();

    public static CollegeDatabase BuildCollegeDB(Context context){
        if (DATABASE_INSTANCE == null) {
            synchronized (CollegeDatabase.class) {
                if (DATABASE_INSTANCE == null) {
                    DATABASE_INSTANCE = Room.databaseBuilder(context, CollegeDatabase.class, "CollegesDB")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    College college = new College("Test", "Unknown");
                                }
                            })
                            .fallbackToDestructiveMigration().build();
                }

            }
        }
        return DATABASE_INSTANCE;
    }
}
