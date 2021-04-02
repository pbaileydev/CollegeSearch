package com.pbaileyapps.android.collegesearch.RoomComponents;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "College")
public class College {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    @ColumnInfo(name = "Name")
    private String Name;
    @ColumnInfo(name = "Country")
    private String Country;
    public College(String Name, String Country){
        this.Country = Country;
        this.Name = Name;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getName() {
        return Name;
    }

    public String getCountry() {
        return Country;
    }
}

