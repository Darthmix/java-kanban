package com.yandex.TaskManager.model;

public class SingleTask extends Task {

    public SingleTask( String name, String description) {
        super(name, description);
        this.statusTask = StatusTask.NEW;
    }

    @Override
    public StatusTask getStatus() {
        return this.statusTask;
    }

    public SingleTask withStatus(StatusTask statusTask) {
        SingleTask singleTask = new SingleTask(
                                    this.getName(),
                                    this.getDescription()
        );
        singleTask.setId(this.getId());
        singleTask.setStatus(statusTask);
        return singleTask;
    }

    private void setStatus(StatusTask statusTask){
        this.statusTask = statusTask;
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
