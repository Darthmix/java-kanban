package com.yandex.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class EpicTask extends Task {
    private final List<SubTask> subTasks;

    public EpicTask(String name, String description) {
        super(name, description);
        this.subTasks = new ArrayList<>();
    }

    public void modifySubTask(SubTask subTask) { // Add if not exist, or modify existed object
        boolean findSubTask = false;
        for (int i = 0; i < this.subTasks.size(); i++) {
            if (this.subTasks.get(i).getId() == subTask.getId()) {
                this.subTasks.set(i, subTask);
                findSubTask = true;
            }
        }
        if (!findSubTask) {
            this.subTasks.add(subTask);
        }
        this.statusTask = this.calcStatus();
    }

    public List<SubTask> getSubTasks() {
        return this.subTasks;
    }

    public void removeSubTask(SubTask subTask) {
        this.subTasks.remove(subTask);
        this.statusTask = this.calcStatus();
    }

    public void setStatus(StatusTask statusTask) {
        this.statusTask = statusTask;
    }

    public StatusTask calcStatus() {
        if (this.subTasks.isEmpty() || isAllSubTasksByStatusTask(StatusTask.NEW)) {
            return StatusTask.NEW;
        } else if (isAllSubTasksByStatusTask(StatusTask.DONE)) {
            return StatusTask.DONE;
        } else {
            return StatusTask.IN_PROGRESS;
        }
    }

    @Override
    public StatusTask getStatus() {
        return this.statusTask;
    }

    private boolean isAllSubTasksByStatusTask(StatusTask statusTask) {
        for (SubTask subTask : this.subTasks) {
            if (!subTask.getStatus().equals(statusTask)) {
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
                ", statusTask=" + this.statusTask +
                ", subtasks=" + '\n' + this.subTasks +
                '}' + '\n';
    }

}
