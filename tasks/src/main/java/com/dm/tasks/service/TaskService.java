package com.dm.tasks.service;

import com.dm.tasks.model.Task;
import com.dm.tasks.repository.RepositoryTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TaskService implements ITaskService{

    @Autowired
    private RepositoryTask repositoryTask;

    @Override
    public List<Task> taskList() {
        return repositoryTask.findAll();
    }

    @Override
    public Task searchTaskID(Integer idTask) {

        Task task = repositoryTask.findById(idTask).orElse(null);
        return task;
    }

    @Override
    public void saveTask(Task task) {
        repositoryTask.save(task);

    }

    @Override
    public void deleteTask(Task task) {
        repositoryTask.delete(task);

    }
}
