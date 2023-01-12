package com.yandex.TaskManager.model;

public class SingleTask extends Task {

    public SingleTask(int id, String name, String description) {
        super(id, name, description, StatusTask.NEW);
    }

    @Override
    public StatusTask getStatus() {
        return this.statusTask;
    }

    public SingleTask withStatus(StatusTask statusTask) {
        SingleTask singleTask = new SingleTask(
                                    this.getId(),
                                    this.getName(),
                                    this.getDescription()
        );
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

    public static class ToCreate{
        private String name;
        private String description;

        public ToCreate(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}
