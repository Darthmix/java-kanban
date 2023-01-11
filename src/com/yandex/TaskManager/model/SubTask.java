package com.yandex.TaskManager.model;

public class SubTask extends Task{
    //private final EpicTask epicTask;
    private  final  Integer epicTaskId;
    public SubTask(int id, String name, String description, StatusTask statusTask, EpicTask epic) {
        super(id, name, description, statusTask);
        //this.epicTask   = epic;
        this.epicTaskId = epic.getId();
        epic.addSubTask(this);
    }

    public SubTask withStatus(StatusTask statusTask, EpicTask epicTask) {
        return new SubTask(
                this.getId(),
                this.getName(),
                this.getDescription(),
                statusTask,
                epicTask
        );
    }
    public int getEpicId(){
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
}
