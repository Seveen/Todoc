package com.cleanup.todoc.dao;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
	@Query("SELECT * from project_table")
	public LiveData<List<Project>> loadAllProjects();
}
