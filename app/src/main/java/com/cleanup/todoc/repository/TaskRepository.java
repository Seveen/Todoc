package com.cleanup.todoc.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.TaskDatabase;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {
	private TaskDao taskDao;
	private LiveData<List<Task>> allTasks;

	TaskRepository(Application app) {
		TaskDatabase db = TaskDatabase.getDatabase(app);
		taskDao = db.taskDao();
		allTasks = taskDao.loadAllTasks();
	}

	public LiveData<List<Task>> getAllTasks() {
		return allTasks;
	}

	public void deleteTaskById(long id) {
		Task taskToDelete = taskDao.loadTaskById(id).getValue();
		taskDao.deleteTasks(taskToDelete);
	}

	public void addNewTask(long projectId, String name, long creationTimestamp) {
		//TODO: A implementer
		taskDao.insertTasks();
	}
}
