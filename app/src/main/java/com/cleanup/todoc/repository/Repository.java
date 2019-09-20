package com.cleanup.todoc.repository;

import android.app.Application;
import android.os.AsyncTask;

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

	public Repository(TaskDao taskDao, ProjectDao projectDao) {
		this.taskDao = taskDao;
		this.projectDao = projectDao;
		allTasks = this.taskDao.loadAllTasks();
		allProjects = this.projectDao.loadAllProjects();
	}

	public LiveData<List<Task>> getAllTasks() {
		return allTasks;
	}

	public void deleteTask(Task task) {
		new deleteAsyncTask(taskDao).execute(task);
	}

	public void addNewTask(long projectId, String name, long creationTimestamp) {
		Task task = new Task(0, projectId, name, creationTimestamp);
		new insertAsyncTask(taskDao).execute(task);
	}

	public LiveData<List<Project>> getAllProjects() {
		return allProjects;
	}

	private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {

		private TaskDao asyncTaskDao;

		insertAsyncTask(TaskDao dao) {
			asyncTaskDao = dao;
		}

		@Override
		protected Void doInBackground(final Task... params) {
			asyncTaskDao.insertTasks(params[0]);
			return null;
		}
	}

	private static class deleteAsyncTask extends AsyncTask<Task, Void, Void> {
		private TaskDao asyncTaskDao;

		deleteAsyncTask(TaskDao dao) {
			asyncTaskDao = dao;
		}

		@Override
		protected Void doInBackground(final Task... params) {
			asyncTaskDao.deleteTasks(params[0]);
			return null;
		}
	}
}
