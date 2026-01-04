package com.dm.tasks.repository;

import com.dm.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTask extends JpaRepository<Task, Integer> {
}
