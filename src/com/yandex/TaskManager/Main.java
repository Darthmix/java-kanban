package com.yandex.TaskManager;

import com.yandex.TaskManager.model.*;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        // Создаём 2 обычные задачи, сохраняем их
        SingleTask singleTask1 = new SingleTask( 0, "CommonTask1", "Common task 1", StatusTask.NEW );
        SingleTask singleTask2 = new SingleTask( 1, "CommonTask2", "Common task 2", StatusTask.NEW );

        taskManager.saveTask(singleTask1);
        taskManager.saveTask(singleTask2);

        // Создаём первый эпик с 2 подзадачами и сохраняем
        EpicTask epicTask1 = new EpicTask(2, "EpicTask1", "Epic task 1", StatusTask.NEW);
        taskManager.saveTask(epicTask1);

        SubTask subTask1 = new SubTask(3, "SubTask1", "Subtask 1", StatusTask.NEW, epicTask1);
        SubTask subTask2 = new SubTask(4, "SubTask2", "Subtask 2", StatusTask.NEW, epicTask1);
        taskManager.saveTask(subTask1);
        taskManager.saveTask(subTask2);

        // Создаём второй эпик с одной подзадачей и сохраняем
        EpicTask epicTask2 = new EpicTask(5, "EpicTask2", "Epic task 2", StatusTask.NEW);
        taskManager.saveTask(epicTask2);

        SubTask subTask3 = new SubTask(6, "SubTask3", "Subtask 3", StatusTask.NEW, epicTask2);
        taskManager.saveTask(subTask3);

        System.out.println("Созданные объекты");
        System.out.println(taskManager.getAllTasks());

        // Установил новые статусы
        SingleTask tmpSingleTask;

        tmpSingleTask = (SingleTask) taskManager.getTaskById(0);
        taskManager.saveTask(tmpSingleTask.withStatus(StatusTask.IN_PROGRESS));
        tmpSingleTask = (SingleTask) taskManager.getTaskById(1);
        taskManager.saveTask(tmpSingleTask.withStatus(StatusTask.DONE));

        SubTask tmpSubTask;
        tmpSubTask = (SubTask) taskManager.getTaskById(3);
        taskManager.saveTask(tmpSubTask.withStatus(StatusTask.IN_PROGRESS, epicTask1));
        tmpSubTask = (SubTask) taskManager.getTaskById(6);
        taskManager.saveTask(tmpSubTask.withStatus(StatusTask.DONE, epicTask2));

        System.out.println("После изменения статусов");
        System.out.println(taskManager.getAllTasks());

//        // Получение списка задач в зависимости от типа
//        System.out.println("Список обычных задач:");
//        System.out.println(taskManager.getSubTasks());
//        System.out.println("Список эпик задач:");
//        System.out.println(taskManager.getEpicTasks());
//        System.out.println("Список подзадач задач:");
//        System.out.println(taskManager.getSubTasks());

        // Проверка удаления
//        System.out.println("Удаляем первую(0) обычную задачу");
//        taskManager.remove(0);
//        System.out.println(taskManager.getAllTasks());
//
//        System.out.println("Удаляем подзадачу 1 эпика 1");
//        taskManager.remove(3);
//        System.out.println(taskManager.getAllTasks());
//
//        System.out.println("Удаляем эпик 2");
//        taskManager.remove(5);
//        System.out.println(taskManager.getAllTasks());

//        System.out.println("Удаляем обычные задачи");
//        taskManager.clearByType(com.yandex.TaskManager.model.TypeTask.REG);
//        System.out.println(taskManager.getAllTasks());

//        System.out.println("Удаляем эпики");
//        taskManager.clearByType(com.yandex.TaskManager.model.TypeTask.EPIC);
//        System.out.println(taskManager.getAllTasks());

//        System.out.println("Удаляем подзадачи");
//        taskManager.clearByType(com.yandex.TaskManager.model.TypeTask.SUB);
//        System.out.println(taskManager.getAllTasks());

//        System.out.println("Удаляем все");
//        taskManager.clearAll();
//        System.out.println(taskManager.getAllTasks());

    }
}
