package com.yandex.taskmanager;

import com.yandex.taskmanager.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private final Map<Integer, Task> taskById;  // Основной хеш список всех тасок

    private final TaskIdGenerator taskIdGenerator; // Объект генерации новых ID для тасок

    private final HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.taskIdGenerator = new TaskIdGenerator();
        this.taskById = new HashMap<>();
        this.historyManager = historyManager;
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
        ArrayList<Task> tasks = (ArrayList<Task>) this.getTasksByType(typeTask);
        for (Task task: tasks){
            this.removeTask(task.getId());
        }
    }

    @Override
    public Task getTaskById(int id) {
        Task task = taskById.get(id);
        this.historyManager.add(task);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.taskById.values());
    }

    @Override
    public List<SingleTask> getSingleTasks(){
        ArrayList<SingleTask> singleTasks = new ArrayList<>();
        ArrayList<Task> tasks = (ArrayList<Task>) this.getTasksByType(TypeTask.REG);
        for (Task task: tasks){
            singleTasks.add((SingleTask) task);
        }
        return singleTasks;
    }
    @Override
    public List<SubTask> getSubTasks(){
        ArrayList<SubTask> subTasks = new ArrayList<>();
        ArrayList<Task> tasks = (ArrayList<Task>) this.getTasksByType(TypeTask.SUB);
        for (Task task: tasks){
            subTasks.add((SubTask) task);
        }
        return subTasks;
    }
    @Override
    public List<EpicTask> getEpicTasks() {
        ArrayList<EpicTask> epicTasks = new ArrayList<>();
        ArrayList<Task> tasks = (ArrayList<Task>) this.getTasksByType(TypeTask.EPIC);
        for (Task task : tasks) {
            epicTasks.add((EpicTask) task);
        }
        return epicTasks;
    }

    private List<Task> getTasksByType(TypeTask typeTask) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : this.taskById.values()) {
            if (task.getTypeTask().equals(typeTask)){
                tasks.add(task);
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
        return this.historyManager.getHistory();
    }

    private static class TaskIdGenerator{
        private int nextFreeId ;
        public int getNextFreeId() {
            return nextFreeId++;
        }
    }
}
