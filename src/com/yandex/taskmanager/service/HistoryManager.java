package com.yandex.taskmanager.service;

import com.yandex.taskmanager.model.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);
    void remove(int id);
    List<Task> getHistory();
}
