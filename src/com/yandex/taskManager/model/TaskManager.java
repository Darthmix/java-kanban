package com.yandex.taskManager.model;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    void  clearAll();
    void clearSingleTasks();
    void clearEpicTasks();
    void clearSubTasks();
    void clearByType(TypeTask typeTask);
    Task getTaskById(int id);
    List<Task> getAllTasks();
    List<SingleTask> getSingleTasks();
    List<SubTask> getSubTasks();
    List<EpicTask> getEpicTasks();
    //List<Task> getTasksByType(TypeTask typeTask);
    List<SubTask> getAllSubTaskFromEpicById(int id);
    void removeTask(int id);
    void createTask(Task task);
    void updateTask(Task task);
    List<Task> getHistory();

}
