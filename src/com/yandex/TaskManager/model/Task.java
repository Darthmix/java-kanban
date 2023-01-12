package com.yandex.TaskManager.model;

public abstract class Task {
    private int id;
    private String name;

    private String description;

    protected StatusTask statusTask;

    public Task( String name, String description) {
        this.name        = name;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
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
