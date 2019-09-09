package com.cleanup.todoc.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	public void insertTasks(Task... tasks);

	@Update
	public void updateTasks(Task... tasks);

	@Delete
	public void deleteTasks(Task... tasks);

	@Query("SELECT * from task")
	public List<Task> loadAllTasks();


	@Query("SELECT * FROM task WHERE id = :id LIMIT 1")
	public Task loadTaskById(long id);
}
