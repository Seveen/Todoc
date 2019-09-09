package com.cleanup.todoc.di;

import com.cleanup.todoc.repository.TaskRepository;

public class DI {

	private static TaskRepository repository = new TaskRepository();
	public static TaskRepository getTaskRepository() {
		return repository;
	}
}
