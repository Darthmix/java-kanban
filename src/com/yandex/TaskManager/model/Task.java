package com.yandex.TaskManager.model;

public abstract class Task {
    private final int id;
    private final String name;

    private final String description;

    protected StatusTask statusTask;

    public Task(int id, String name, String description, StatusTask statusTask) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.statusTask = statusTask;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract StatusTask getStatus();
    public abstract TypeTask getTypeTask();

    @Override
    public abstract String toString();

}
