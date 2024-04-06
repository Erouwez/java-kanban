package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subTasks = new ArrayList<>(); // Список подзадач для данного эпика

    // Конструктор класса Epic
    public Epic(String name, String description) {
        super(name, Status.NEW, description); // Вызов конструктора суперкласса Task со статусом NEW
    }

    // Метод для получения списка подзадач
    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    // Метод для добавления подзадачи в список
    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
        updateStatus(); // Пересчет статуса после добавления подзадачи
    }

    // Метод для удаления подзадачи из списка
    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
        updateStatus(); // Пересчет статуса после удаления подзадачи
    }

    // Метод для обн статуса эпика
    public void updateStatus() {
        if (subTasks.isEmpty()) {
            this.setStatus(Status.NEW);
            return;
        }
        boolean allDone = subTasks.stream().allMatch(subTask -> subTask.getStatus() == Status.DONE);
        if (allDone) {
            this.setStatus(Status.DONE);
            return;
        }
        this.setStatus(Status.IN_PROGRESS);
    }

    // Метод для обновления эпика (обновляются только два поля)
    public void updateEpic(String newName, String newDescription) {
        this.setName(newName); // Обновление названия
        this.setDescription(newDescription); // Обновление описания
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTasks=" + subTasks +
                ", status=" + status +
                '}';
    }
}