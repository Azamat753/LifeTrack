package com.example.lifetrack.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lifetrack.model.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(TaskModel model);

    @Delete
    void delete(    TaskModel model);


    @Query("SELECT * FROM taskmodel")
    LiveData<List<TaskModel>> getAll();
}
