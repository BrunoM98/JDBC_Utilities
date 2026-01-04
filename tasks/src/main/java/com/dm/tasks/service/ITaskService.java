package com.dm.tasks.service;

import com.dm.tasks.model.Task;

import java.util.List;

public interface ITaskService {
    public List<Task> taskList();

    public Task searchTaskID(Integer idTask);

    public void saveTask(Task task);

    public void deleteTask(Task task);
}
