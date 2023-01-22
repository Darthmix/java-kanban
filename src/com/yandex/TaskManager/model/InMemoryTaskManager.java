package com.yandex.TaskManager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager<HistoryManagerType extends  HistoryManager> implements TaskManager {

    private final HashMap<Integer, Task> taskById;  // Основной хеш список всех тасок

    private final TaskIdGenerator taskIdGenerator; // Объект генерации новых ID для тасок

    private final HistoryManagerType inMemoryHistoryManager;

    public InMemoryTaskManager(HistoryManagerType inMemoryHistoryManager) {
        this.taskIdGenerator = new TaskIdGenerator();
        this.taskById = new HashMap<>();
        this.inMemoryHistoryManager = inMemoryHistoryManager;
    }

    @Override
    public void  clearAll(){
        this.taskById.clear();
    }
    @Override
    public void clearSingleTasks(){
        this.clearByType(TypeTask.REG);
    }
    @Override
    public void clearEpicTasks(){
        this.clearByType(TypeTask.EPIC);
    }
    @Override
    public void clearSubTasks(){
        this.clearByType(TypeTask.SUB);
    }
    @Override
    public void clearByType(TypeTask typeTask){
        ArrayList<Task> tasks = this.getTasksByType(typeTask);
        for (Task task: tasks){
            this.removeTask(task.getId());
        }
    }

    @Override
    public Task getTaskById(int id) {
        this.inMemoryHistoryManager.add(taskById.get(id));
        return taskById.get(id);
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        for (Task task: this.taskById.values()){
            this.inMemoryHistoryManager.add(task);
        }

        return new ArrayList<>(this.taskById.values());
    }

    @Override
    public ArrayList<SingleTask> getSingleTasks(){
        ArrayList<SingleTask> singleTasks = new ArrayList<>();
        ArrayList<Task> tasks = this.getTasksByType(TypeTask.REG);
        for (Task task: tasks){
            singleTasks.add((SingleTask) task);
            this.inMemoryHistoryManager.add(task);
        }
        return singleTasks;
    }
    @Override
    public ArrayList<SubTask> getSubTasks(){
        ArrayList<SubTask> subTasks = new ArrayList<>();
        ArrayList<Task> tasks = this.getTasksByType(TypeTask.SUB);
        for (Task task: tasks){
            subTasks.add((SubTask) task);
            this.inMemoryHistoryManager.add(task);
        }
        return subTasks;
    }
    @Override
    public ArrayList<EpicTask> getEpicTasks() {
        ArrayList<EpicTask> epicTasks = new ArrayList<>();
        ArrayList<Task> tasks = this.getTasksByType(TypeTask.EPIC);
        for (Task task : tasks) {
            epicTasks.add((EpicTask) task);
            this.inMemoryHistoryManager.add(task);
        }
        return epicTasks;
    }
    @Override
    public ArrayList<Task> getTasksByType(TypeTask typeTask) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : this.taskById.values()) {
            if (task.getTypeTask().equals(typeTask)){
                tasks.add(task);
                this.inMemoryHistoryManager.add(task);
            }
        }
        return tasks;
    }

    @Override
    public List<SubTask> getAllSubTaskFromEpicById(int id){
        Task task = getTaskById(id);
        if (!task.getTypeTask().equals(TypeTask.EPIC)){
            return null;
        }
        EpicTask epicTask = (EpicTask) task;
        return epicTask.getSubTasks();
    }

    @Override
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

    @Override
    public void createTask(Task task){
        task.setId(this.taskIdGenerator.getNextFreeId());
        this.taskById.put(task.getId(), task);
        if (task.getTypeTask().equals(TypeTask.SUB)){
            SubTask subTask = (SubTask) task;
            EpicTask epicTask = (EpicTask) this.taskById.get(subTask.getEpicTaskId());
            epicTask.modifySubTask(subTask);
            this.taskById.put(epicTask.getId(), epicTask);
        }
    }

    @Override
    public void updateTask(Task task){
        this.taskById.put(task.getId(), task);
        if (task.getTypeTask().equals(TypeTask.SUB)){
            SubTask subTask = (SubTask) task;
            EpicTask epicTask = (EpicTask) this.taskById.get(subTask.getEpicTaskId());
            epicTask.modifySubTask(subTask);
            this.taskById.put(epicTask.getId(), epicTask);
        }
    }

    @Override
    public List<Task> getHistory() {
        return this.inMemoryHistoryManager.getHistory();
    }

    private static class TaskIdGenerator{
        private int nextFreeId ;
        public int getNextFreeId() {
            return nextFreeId++;
        }
    }
}
