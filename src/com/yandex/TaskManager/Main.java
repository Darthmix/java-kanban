package com.yandex.TaskManager;

import com.yandex.TaskManager.model.*;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        // Создаём 2 обычные задачи, сохраняем их
        SingleTask singleTask1 = taskManager.createSingleTask(new SingleTask.ToCreate("CommonTask1", "Common task 1"));
        SingleTask singleTask2 = taskManager.createSingleTask(new SingleTask.ToCreate("CommonTask2", "Common task 2"));

        // Создаём первый эпик с 2 подзадачами и сохраняем
        EpicTask epicTask1 = taskManager.createEpicTask(new EpicTask.ToCreate("EpicTask1", "Epic task 1"));

        SubTask subTask1 = taskManager.createSubTask(new SubTask.ToCreate("SubTask1", "Subtask 1"), epicTask1.getId());
        SubTask subTask2 = taskManager.createSubTask(new SubTask.ToCreate("SubTask2", "Subtask 2"), epicTask1.getId());

        // Создаём второй эпик с одной подзадачей и сохраняем
        EpicTask epicTask2 = taskManager.createEpicTask(new EpicTask.ToCreate("EpicTask2", "Epic task 2"));

        SubTask subTask3 = taskManager.createSubTask(new SubTask.ToCreate("SubTask3", "Subtask 3"), epicTask2.getId());

        System.out.println("Созданные объекты");
        System.out.println(taskManager.getAllTasks());

        // Установил новые статусы
        SingleTask tmpSingleTask;

        tmpSingleTask = (SingleTask) taskManager.getTaskById(0);
        taskManager.setSingleTaskStatus(tmpSingleTask, StatusTask.IN_PROGRESS);
        tmpSingleTask = (SingleTask) taskManager.getTaskById(1);
        taskManager.setSingleTaskStatus(tmpSingleTask, StatusTask.DONE);

        SubTask tmpSubTask;
        tmpSubTask = (SubTask) taskManager.getTaskById(3);
        taskManager.setSubTaskStatus(tmpSubTask, StatusTask.IN_PROGRESS);
        tmpSubTask = (SubTask) taskManager.getTaskById(6);
        taskManager.setSubTaskStatus(tmpSubTask, StatusTask.DONE);

        System.out.println("После изменения статусов");
        System.out.println(taskManager.getAllTasks());

        // Получение списка задач в зависимости от типа
        System.out.println("Список обычных задач:");
        System.out.println(taskManager.getSubTasks());
        System.out.println("Список эпик задач:");
        System.out.println(taskManager.getEpicTasks());
        System.out.println("Список подзадач задач:");
        System.out.println(taskManager.getSubTasks());

        // Проверка удаления
//        System.out.println("Удаляем первую(0) обычную задачу");
//        taskManager.removeTask(0);
//        System.out.println(taskManager.getAllTasks());
//
//        System.out.println("Удаляем подзадачу 1 эпика 1");
//        taskManager.removeTask(3);
//        System.out.println(taskManager.getAllTasks());
//
//        System.out.println("Удаляем эпик 2");
//        taskManager.removeTask(5);
//        System.out.println(taskManager.getAllTasks());

        System.out.println("Удаляем обычные задачи");
        taskManager.clearSingleTasks();
        System.out.println(taskManager.getAllTasks());

        System.out.println("Удаляем эпики");
        taskManager.clearEpicTasks();
        System.out.println(taskManager.getAllTasks());

        System.out.println("Удаляем подзадачи");
        taskManager.clearSubTasks();
        System.out.println(taskManager.getAllTasks());

//        System.out.println("Удаляем все");
//        taskManager.clearAll();
//        System.out.println(taskManager.getAllTasks());

    }
}
