package com.cleanup.todoc.di;

import android.app.Application;

import com.cleanup.todoc.repository.Repository;

public class DI {

	private static Repository repository = null;
	public static Repository getTaskRepository(Application application) {
		if (repository == null) {
			repository = new Repository(application);
		}
		return repository;
	}
}
