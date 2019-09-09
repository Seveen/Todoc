package com.cleanup.todoc.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
	public abstract TaskDao taskDao();

	public static volatile TaskDatabase INSTANCE;

	public static TaskDatabase getDatabase(final Context context) {
		if (INSTANCE == null) {
			synchronized (TaskDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
							TaskDatabase.class, "task_database")
							.build();
				}
			}
		}
		return INSTANCE;
	}
}
