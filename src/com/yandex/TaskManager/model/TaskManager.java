package com.yandex.TaskManager.model;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    void  clearAll();
    void clearSingleTasks();
    void clearEpicTasks();
    void clearSubTasks();
    void clearByType(TypeTask typeTask);
    Task getTaskById(int id);
    ArrayList<Task> getAllTasks();
    ArrayList<SingleTask> getSingleTasks();
    ArrayList<SubTask> getSubTasks();
    ArrayList<EpicTask> getEpicTasks();
    ArrayList<Task> getTasksByType(TypeTask typeTask);
    List<SubTask> getAllSubTaskFromEpicById(int id);
    void removeTask(int id);
    void createTask(Task task);
    void updateTask(Task task);
    List<Task> getHistory();

}
