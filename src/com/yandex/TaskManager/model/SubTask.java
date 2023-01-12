package com.yandex.TaskManager.model;

public class SubTask extends Task{
    private  final  Integer epicTaskId;
    public SubTask(int id, String name, String description, int epicTaskId) {
        super(id, name, description, StatusTask.NEW);
        this.epicTaskId = epicTaskId;
//        epic.addSubTask(this);
    }
    private void setStatus(StatusTask statusTask){
        this.statusTask = statusTask;
    }
    public SubTask withStatus(StatusTask statusTask) {
        SubTask subTask =  new SubTask(
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.getEpicTaskId()
        );
        subTask.setStatus(statusTask);
        return subTask;
    }
    public int getEpicId(){
        return epicTaskId;
    }

    public Integer getEpicTaskId() {
        return epicTaskId;
    }

    public void removeFromEpic(EpicTask epicTask){
        epicTask.removeSubTask(this);
    }

    @Override
    public StatusTask getStatus() {
        return statusTask;
    }

    @Override
    public TypeTask getTypeTask() {
        return TypeTask.SUB;
    }

    @Override
    public String toString() {
        return "com.yandex.TaskManager.model.SubTask{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", statusTask=" + this.getStatus() +
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
