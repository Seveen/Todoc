package com.cleanup.todoc.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@androidx.room.Database(entities = {Task.class, Project.class}, version = 1)
public abstract class Database extends RoomDatabase {
	public abstract TaskDao taskDao();
	public abstract ProjectDao projectDao();

	public static volatile Database INSTANCE;

	public static Database getDatabase(final Context context) {
		if (INSTANCE == null) {
			synchronized (Database.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
							Database.class, "task_database")
							.build();
				}
			}
		}
		return INSTANCE;
	}
}
