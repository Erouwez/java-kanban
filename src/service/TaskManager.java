package service;

import model.SubTask;
import model.Task;
import model.Epic;

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
    public List<Epic> getAllEpics() {
        List<Epic> allEpicsList = new ArrayList<>(epics.values());
        return allEpicsList;
    }
    public List<SubTask> getAllSubTasks() {
        List<SubTask> allSubTasksList = new ArrayList<>(allSubTasks.values());
        return allSubTasksList;
    }


    // b. Удаление всех задач.
    public boolean deleteAllTasks() {
        tasks.clear(); // Очищаем список всех задач
        return true; // Возврат true для обозначения удаления
    }
    public void deleteSubtasks() {
        allSubTasks.clear(); // Очищаем список всех подзадач
        // Удаляем подзадачи из всех эпиков и пересчитываем статусы
        for (Epic epic : epics.values()) {
            List<SubTask> subTasks = epic.getSubTasks();
            for (SubTask subTask : subTasks) {
                allSubTasks.remove(subTask.getId());
            }
            epic.getSubTasks().clear(); // Удаляем подзадачи из эпика
            epic.updateStatus(); // Обновляем статус эпика
        }
    }
    public void deleteEpics() {
        // Удаляем подзадачи из всех эпиков и из общего списка
        for (Epic epic : epics.values()) {
            List<SubTask> subTasks = epic.getSubTasks();
            for (SubTask subTask : subTasks) {
                allSubTasks.remove(subTask.getId());
            }
            epic.getSubTasks().clear(); // Удаляем подзадачи из эпика
        }
        epics.clear(); // Очищаем список всех эпиков
    }


    // c. Получение по ид.. таска
    public Task getTaskById(int id) {
        return tasks.get(id); // Возвращаем задачу по заданному идентификатору
    }
    // ..эпика
    public Epic getEpicById(int id) {
        return epics.get(id); // Возвращаем эпик по заданному идентификатору
    }
    // ..подзадачи
    public SubTask getSubTaskById(int id) {
        return allSubTasks.get(id); // Возвращаем подзадачу по заданному идентификатору
    }

    // e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
    public void updateTask(Task task) {
        tasks.put(task.getId(), task); // Обновляем задачу в мапе
    }

    // f. Удаление по ид
    public void deleteTaskById(int id) {
        tasks.remove(id); // Удаляем задачу из мапы по ID
    }
    public void deleteEpicById(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            epics.remove(id);
            deleteSubOfEpic(epic);
        }
    }
    public void deleteSubTaskById(int id) {
        SubTask subTask = allSubTasks.get(id);
        if (subTask != null) {
            allSubTasks.remove(id);
            Epic epic = getEpicSubTask(subTask);
            if (epic != null) {
                epic.removeSubTask(subTask);
                epic.updateStatus();
            }
        }
    }
    // получение эпика, содержащего подзадачу
    private Epic getEpicSubTask(SubTask subTask) {
        for (Epic epic : epics.values()) {
            if (epic.getSubTasks().contains(subTask)) {
                return epic;
            }
        }
        return null;
    }
    // Метод для удаления всех подзадач эпика из общего списка подзадач
    private void deleteSubOfEpic(Epic epic) {
        for (SubTask subTask : epic.getSubTasks()) {
            allSubTasks.remove(subTask.getId());
        }
    }


    // Методы для работы с эпиками
    // Получ списка всех сабтасков опр эпика
    public List<SubTask> getAllSubTasksOfEpic(int epicId) {
        Epic epic = epics.get(epicId); // Получаем экземпляр эпика по ид
        if (epic != null) {
            return epic.getSubTasks(); // Возвращаем список всех подзадач данного эпика
        } else {
            return new ArrayList<>(); // Если эпика неть, возвращаем пустой список
        }
    }


    private int generateId() {
        return ++generatorId;
    }
    // Метод для добавления новой задачи
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
