package com.yandex.TaskManager.model;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    public static final byte HISTORY_LIST_MAX_SIZE = 4;
    private final List<Task> historyTasks; // Исптория изменений


    public InMemoryHistoryManager() {
        this.historyTasks = new ArrayList<>();
    }

    @Override
    public void add(Task task){
        if (this.historyTasks.size() >= HISTORY_LIST_MAX_SIZE){
            this.historyTasks.remove(0);
        }
        this.historyTasks.add(task);
    }

    @Override
    public List<Task> getHistory(){
        return historyTasks;
    }
}
