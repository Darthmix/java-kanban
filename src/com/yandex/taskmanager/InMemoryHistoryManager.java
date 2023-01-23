package com.yandex.taskmanager;

import com.yandex.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final byte HISTORY_LIST_MAX_SIZE = 10;
    private final List<Task> historyTasks; // Исптория изменений


    public InMemoryHistoryManager() {
        this.historyTasks = new ArrayList<>();
    }

    @Override
    public void add(Task task){
        if (task == null){
            return;
        }
        if (this.historyTasks.size() >= HISTORY_LIST_MAX_SIZE){
            this.historyTasks.remove(0);
        }
        this.historyTasks.add(task);
    }

    @Override
    public List<Task> getHistory(){
        return List.copyOf(historyTasks);
    }
}