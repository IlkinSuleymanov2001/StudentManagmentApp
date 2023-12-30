package org.example.service;

import org.example.entity.Task;

public interface TaskService {
    Task  createTask(Task task);
    Task  updateTask(Long id,Task task);
    Task  getTask(Long id);
    Task deleteTask(Long id);
    Task[] getAllTask();
}
