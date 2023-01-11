package com.yandex.TaskManager.model;

import java.util.ArrayList;
import java.util.List;

public class EpicTask extends Task {
    private final List<SubTask> subTasks;

    public EpicTask(int id, String name, String description) {
        super(id, name, description, StatusTask.NEW);
        this.subTasks = new ArrayList<>();
    }

    protected void addSubTask(SubTask subTask) {
        boolean findSubTask = false;
        for (int i = 0; i<this.subTasks.size(); i++){
            if (this.subTasks.get(i).getId() == subTask.getId()){
                this.subTasks.set(i, subTask);
                findSubTask = true;
            }
        }
        if (!findSubTask){
            this.subTasks.add(subTask);
        }
        this.statusTask = this.calcStatus();
    }

    public List<SubTask> getSubTasks(){
        return this.subTasks;
    }

    public void removeSubTask(SubTask subTask){
        this.subTasks.remove(subTask);
        this.statusTask = this.calcStatus();
    }


    private StatusTask calcStatus(){
        if (this.subTasks.isEmpty() || isAllSubTasksByStatusTask(StatusTask.NEW)){
            return  StatusTask.NEW;
        } else if (isAllSubTasksByStatusTask(StatusTask.DONE)) {
            return StatusTask.DONE;
        }else {
            return StatusTask.IN_PROGRESS;
        }
    }
    @Override
    public StatusTask getStatus() {
        return this.statusTask;
    }

    private boolean isAllSubTasksByStatusTask(StatusTask statusTask){
        for (SubTask subTask: this.subTasks){
           if (!subTask.getStatus().equals(statusTask)){
               return false;
           }
        }
        return true;
    }

    @Override
    public TypeTask getTypeTask() {
        return TypeTask.EPIC;
    }

    @Override
    public String toString() {
        return "com.yandex.TaskManager.model.EpicTask{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", statusTask=" + this.statusTask + //this.getStatus() +
                ", subtasks=" + '\n' + this.subTasks +
                '}' + '\n' ;
    }

}
