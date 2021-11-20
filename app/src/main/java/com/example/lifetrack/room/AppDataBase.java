package com.example.lifetrack.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lifetrack.model.TaskModel;

//import com.example.lifetrack.model.TaskModel;
//
@Database(entities = {TaskModel.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
