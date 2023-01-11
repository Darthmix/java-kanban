package com.yandex.TaskManager.model;

public class SingleTask extends Task {


    public SingleTask(int id, String name, String description,  StatusTask statusTask) {
        super(id, name, description, statusTask);
    }

    @Override
    public StatusTask getStatus() {
        return this.statusTask;
    }

    public SingleTask withStatus(StatusTask statusTask) {
        return new SingleTask(
                this.getId(),
                this.getName(),
                this.getDescription(),
                statusTask
        );
    }

    @Override
    public TypeTask getTypeTask() {
        return TypeTask.REG;
    }

    @Override
    public String toString() {
        return "com.yandex.TaskManager.model.SingleTask{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", statusTask=" + statusTask +
                '}' + '\n';
    }
}
