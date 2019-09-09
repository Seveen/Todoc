package com.cleanup.todoc.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.di.DI;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.List;

public class TaskListViewModel extends ViewModel {
	private TaskRepository repository;
	private LiveData<List<Task>> tasksList;

	public TaskListViewModel() {
		repository = DI.getTaskRepository();

	}

	public LiveData<List<Task>> getTasksList() {
		return tasksList;
	}

	public void deleteTaskById(long id) {
		repository.deleteTaskById(id);
	}

	public void addNewTask(long projectId, String name, long creationTimestamp) {
		repository.addNewTask(projectId, name, creationTimestamp);
	}
}
