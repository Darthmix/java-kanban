package com.yandex.TaskManager.model;

import java.awt.desktop.ScreenSleepEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {

    private final HashMap<Integer, Task> taskById;  // Основной хеш список всех тасок
    private final TaskIdGenerator taskIdGenerator; // Объект генерации новых ID для тасок

    public TaskManager() {
        this.taskIdGenerator = new TaskIdGenerator();
        this.taskById = new HashMap<>();
    }

    public void  clearAll(){
        this.taskById.clear();
    }

    public void clearSingleTasks(){
        this.clearByType(TypeTask.REG);
    }

    public void clearEpicTasks(){
        this.clearByType(TypeTask.EPIC);
    }
    public void clearSubTasks(){
        this.clearByType(TypeTask.SUB);
    }
    private void clearByType(TypeTask typeTask){
        ArrayList<Task> tasks = this.getTasksByType(typeTask);
        for (Task task: tasks){
            this.removeTask(task.getId());
        }
    }

    public Task getTaskById(int id) {
        return taskById.get(id);
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.taskById.values());
    }

    public ArrayList<SingleTask> getSingleTasks(){
        ArrayList<SingleTask> singleTasks = new ArrayList<>();
        ArrayList<Task> tasks = this.getTasksByType(TypeTask.REG);
        for (Task task: tasks){
            singleTasks.add((SingleTask) task);
        }
        return singleTasks;
    }
    public ArrayList<SubTask> getSubTasks(){
        ArrayList<SubTask> subTasks = new ArrayList<>();
        ArrayList<Task> tasks = this.getTasksByType(TypeTask.SUB);
        for (Task task: tasks){
            subTasks.add((SubTask) task);
        }
        return subTasks;
    }
    public ArrayList<EpicTask> getEpicTasks() {
        ArrayList<EpicTask> epicTasks = new ArrayList<>();
        ArrayList<Task> tasks = this.getTasksByType(TypeTask.EPIC);
        for (Task task : tasks) {
            epicTasks.add((EpicTask) task);
        }
        return epicTasks;
    }
    private ArrayList<Task> getTasksByType(TypeTask typeTask) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : this.taskById.values()) {
            if (task.getTypeTask().equals(typeTask)){
                tasks.add(task);
            }
        }
        return tasks;
    }

    public List<SubTask> getAllSubTaskFromEpicById(int id){
        Task task = getTaskById(id);
        if (!task.getTypeTask().equals(TypeTask.EPIC)){
            return null;
        }
        EpicTask epicTask = (EpicTask) task;
        return epicTask.getSubTasks();
    }


    public void removeTask(int id){
        Task task = this.getTaskById(id);
        switch (task.getTypeTask()){
            case REG:
                this.taskById.remove(task.getId());
                break;
            case SUB:
                SubTask subTask = (SubTask) task;
                subTask.removeFromEpic( (EpicTask) this.taskById.get(subTask.getEpicId()) );
                this.taskById.remove(task.getId());
                break;
            case EPIC:
                EpicTask epicTask = (EpicTask) task;
                ArrayList<SubTask> subTasks = (ArrayList<SubTask>) epicTask.getSubTasks();
                for (SubTask tmpTask: subTasks){
                    this.taskById.remove(tmpTask.getId());
                }
                this.taskById.remove(task.getId());
                break;
        }
    }
    public void saveTask(Task task){
        taskById.put(task.getId(), task);
    }

    public SingleTask createSingleTask(String name, String description ){
        return new SingleTask(
                this.taskIdGenerator.getNextFreeId(),
                name,
                description,
                StatusTask.NEW );
    }
    public  EpicTask createEpicTask(String name, String description){
        return new EpicTask(
                this.taskIdGenerator.getNextFreeId(),
                name,
                description);
    }

    public SubTask createSubTask(String name, String description, EpicTask epicTask){
        return  new SubTask(
                this.taskIdGenerator.getNextFreeId(),
                name,
                description,
                StatusTask.NEW,
                epicTask
        );
    }

    private static class TaskIdGenerator{
        private int nextFreeId ;
        public int getNextFreeId() {
            return nextFreeId++;
        }
    }
}
