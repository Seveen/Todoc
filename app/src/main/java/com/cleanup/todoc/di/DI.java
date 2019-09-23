package com.cleanup.todoc.di;

import android.app.Application;

import com.cleanup.todoc.database.Database;
import com.cleanup.todoc.repository.Repository;

public class DI {
	private static boolean instantiateDbInMemory = false;

	private static Repository repository = null;
	public static Repository getTaskRepository(Application application) {
		if (repository == null) {
			if (instantiateDbInMemory) {
				Database db = Database.getNewDatabaseInMemory(application);
				repository = new Repository(db.taskDao(), db.projectDao());
			} else {
				Database db = Database.getDatabase(application);
				repository = new Repository(db.taskDao(), db.projectDao());
			}
		}
		return repository;
	}

	public static void setInstantiateDbInMemory(boolean val) {
		instantiateDbInMemory = val;
	}
}
