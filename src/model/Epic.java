package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subTasks = new ArrayList<>(); // Список подзадач для данного эпика

    // Конструктор класса Epic
    public Epic(String name, Status status, String description) {
        super(name, status, description); // Вызов конструктора суперкласса Task
    }

    // Метод для получения списка подзадач
    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    // Метод для доб субстака в список
    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
        updateStatus(); // Пересчет статуса посе добавления подзадачи
    }

    // Метод для удаления подзадачи из списка
    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
        updateStatus(); // Пересчет статуса после удаления сабстаки
    }

    // Метод для обн статуса эпика
    public void updateStatus() {
        if (subTasks.isEmpty()) {
            this.setStatus(Status.NEW); // Если список пуст, статус уст NEW
            return;
        }
        boolean allDone = subTasks.stream().allMatch(subTask -> subTask.getStatus() == Status.DONE);
        if (allDone) {
            this.setStatus(Status.DONE);
        } else {
            this.setStatus(Status.IN_PROGRESS);
        }
    }

    // Метод для обновления эпика (обновляются только два поля)
    public void updateEpic(String newName, String newDescription) {
        this.setName(newName); // Обн названия
        this.setDescription(newDescription); // Обн описания
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTasks=" + subTasks +
                ", status=" + status +
                '}';
    }
}