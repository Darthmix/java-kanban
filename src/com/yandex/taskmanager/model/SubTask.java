package com.yandex.taskmanager.model;

public class SubTask extends Task{
    private  final  Integer epicTaskId;
    public SubTask( String name, String description, int epicTaskId) {
        super( name, description);
        this.statusTask = StatusTask.NEW;
        this.epicTaskId = epicTaskId;
    }
    private void setStatus(StatusTask statusTask){
        this.statusTask = statusTask;
    }
    public SubTask withStatus(StatusTask statusTask) {
        SubTask subTask =  new SubTask(
                this.getName(),
                this.getDescription(),
                this.getEpicTaskId()
        );
        subTask.setId(this.getId());
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

}
