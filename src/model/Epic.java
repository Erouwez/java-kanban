package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subTasks = new ArrayList<>(); // Список подзадач для данного эпика

    // Констр
    public Epic(String name, Status status, String description) {
        super(name, status, description); // Вызов конструктора суперкласса Task

    }

    // Метод для получения списка подзадач
    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    // Метод для добавления подзадачи в список
    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    // Метод для удаления подзадачи из списка
    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
    }

    // Метод для расчета статуса эпика
    private void calculateStatus() {
        boolean allDone = true;
        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() != Status.DONE) {
                allDone = false;
                break;
            }
        }
        if (allDone) {
            this.setStatus(Status.DONE);
        } else {
            this.setStatus(Status.IN_PROGRESS);
        }
    }

    // Метод для обновления эпика (обновл только два поля)
    public void updateEpic(String newName, String newDescription) {
        this.setName(newName); // Обновление названия
        this.setDescription(newDescription); // Обновление описания
    }

    // Метод для обновления статуса эпика
    public void updateStatus() {
        boolean allDone = subTasks.stream().allMatch(subTask -> subTask.getStatus() == Status.DONE);
        if (allDone) {
            this.setStatus(Status.DONE);
        } else {
            this.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTasks=" + subTasks +
                '}';
    }
}

