package com.cleanup.todoc.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
							.addCallback(databaseCallback)
							.build();
				}
			}
		}
		return INSTANCE;
	}

	private static Database.Callback databaseCallback =
			new Database.Callback(){

				@Override
				public void onCreate (@NonNull SupportSQLiteDatabase db){
					super.onCreate(db);
					new PopulateDbAsync(INSTANCE).execute();
				}
			};

	private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

		private final ProjectDao mDao;

		PopulateDbAsync(Database db) {
			mDao = db.projectDao();
		}

		@Override
		protected Void doInBackground(final Void... params) {
			mDao.deleteAll();
			Project[] projects = Project.getAllProjects();
			for (Project project : projects) {
				mDao.insert(project);
			}
			return null;
		}
	}
}
