package com.yandex.taskManager.model;

public abstract class Managers {
    private Managers(){

    }

    public static  HistoryManager getDefaultHistory(){
       return new InMemoryHistoryManager();
    }

    public static TaskManager getDefaultTaskManager(){
        return new InMemoryTaskManager(getDefaultHistory());
    }

}
