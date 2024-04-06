package service;

import model.SubTask;
import model.Task;
import model.Epic;
import model.Status;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private Map<Integer, Task> tasks; // Хр задач
    private Map<Integer, Epic> epics; // Хр эпиковов
    private Map<Integer, SubTask> allSubTasks; // список всех подзадач и эпиков
    private int generatorId;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        allSubTasks = new HashMap<>();
        generatorId = 0;
    }

    // Методы для работы с задачами
    // a. Получение списка всех задач.
    public List<Task> getAllTasks() {
        List<Task> allTasksList = new ArrayList<>(tasks.values());
        return allTasksList;
    }

    // b. Удаление всех задач.
    public boolean deleteAllTasks() {
        tasks.clear(); // Очищаем список всех задач
        return true; // Возврат true для обозн удаления
    }

    // c. Получение по идентификатору.
    public Task getTaskById(int id) {
        return tasks.get(id); // Возвращаем задачу по заданному идентификатору
    }

    // d. Создание. Сам объект должен передаваться в качестве параметра.
    public Task createTask(Task task) {
        int taskId = generateId(); // Ген ID
        task.setId(taskId); // Уст ID для задачи
        tasks.put(taskId, task); // Добавляем задачу в хмап
        return task;
    }

    // Метод для создания подзадачи
    public SubTask createSubTask(SubTask subTask, Epic epic) {
        epic.addSubTask(subTask); // Добавляем подзадачу к эпику
        epic.updateStatus(); // Обновляем статус эпика
        return subTask;
    }

    // e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    public void updateTask(Task task) {
        tasks.put(task.getId(), task); // Обновляем задачу в HashMap
    }

    // f. Удаление по идентификатору.
    public void deleteTaskById(int id) {
        tasks.remove(id); // Удаляем задачу из мапы по ID
    }

    // Методы для работы с эпиками
    // Получ списка всех сабтасков опр эпика
    public List<SubTask> getAllSubTasksOfEpic(int epicId) {
        Epic epic = epics.get(epicId); // Получаем экземпляр эпика по идентификатору
        if (epic != null) {
            return epic.getSubTasks(); // Возвращаем список всех подзадач этого эпика
        } else {
            return new ArrayList<>(); // Если эпик не найден, возвращаем пустой список
        }
    }


    private int generateId() {
        return ++generatorId;
    }

    // Метод для доб новой задачи
    public int addNewTask(Task task) {
        final int id = generateId(); //ид+
        task.setId(id); // Уст идентификатор
        tasks.put(id, task); // Добавляем задачу
        return id;
    }

    // Метод для добавления нового эпика
    public int addEpic(Epic epic) {
        final int id = generateId(); // Генерируем ид
        epic.setId(id); // Уст идентификатор
        epics.put(id, epic); // Добавляем эпик
        return id;
    }

    // Метод для добавления новой подзадачи к эпику
    public int addSubTask(SubTask subTask, Epic epic) {
        final int id = generateId(); // Генерируем ид
        subTask.setId(id);
        allSubTasks.put(id, subTask); // Доб подзадачу в общий список подзадач
        epic.addSubTask(subTask); // Добавляем подзадачу к эпику
        epic.updateStatus(); // Обновляем статус эпика
        return id;
    }

    @Override
    public String toString() {
        return "TaskManager{" + "tasks=" + tasks + ", epics=" + epics + '}';
    }
}