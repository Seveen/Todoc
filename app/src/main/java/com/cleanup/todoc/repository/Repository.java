package com.cleanup.todoc.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.Database;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class Repository {
	private TaskDao taskDao;
	private ProjectDao projectDao;
	private LiveData<List<Task>> allTasks;
	private LiveData<List<Project>> allProjects;

	public Repository(Application app) {

		Database db = Database.getDatabase(app);
		taskDao = db.taskDao();
		projectDao = db.projectDao();
		allTasks = taskDao.loadAllTasks();
		allProjects = projectDao.loadAllProjects();
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

	public LiveData<List<Project>> getAllProjects() {
		return allProjects;
	}
}
