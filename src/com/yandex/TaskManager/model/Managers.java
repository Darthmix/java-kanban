package com.yandex.TaskManager.model;

public abstract class Managers {

    private static TaskManager IN_MEMORY_TASK_MANAGER ;
    private static HistoryManager IN_MEMORY_HISTORY_MANAGER;

    public static  HistoryManager getDefaultHistory(){
        if (IN_MEMORY_HISTORY_MANAGER == null){
            IN_MEMORY_HISTORY_MANAGER = new InMemoryHistoryManager();
        }
        return IN_MEMORY_HISTORY_MANAGER;
    }

    public static TaskManager getDefaultTaskManager(){
        if ( IN_MEMORY_TASK_MANAGER == null ){
            // Может и не нужно, но поигрался с дженериком
            IN_MEMORY_TASK_MANAGER = new InMemoryTaskManager<InMemoryHistoryManager>((InMemoryHistoryManager) getDefaultHistory());
        }
        return IN_MEMORY_TASK_MANAGER;
    }

}
