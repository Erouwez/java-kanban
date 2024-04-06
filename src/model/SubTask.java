package model;

import java.util.ArrayList;
import java.util.List;

public class SubTask extends Task {
    private Epic epic;
    private List<SubTask> subTasks; // Список подзадач

    // Констр
    public SubTask(String name, Status status, String description, Epic epic) {
        super(name, status, description);
        this.epic = epic;
        this.subTasks = new ArrayList<>();
    }

    // Пустой констр для создания объекта без параметров
    public SubTask() {
        super("", Status.NEW, "");
    }

    // Метод для получения списка всех сабтасков
    public List<SubTask> getAllSubTasks() {
        List<SubTask> allSubTasks = new ArrayList<>();
        allSubTasks.add(this);
        return allSubTasks;
    }

    // Метод для удаления всех сабтасков
    public void deleteAllSubTasks() {
        subTasks.clear();
    }

    // Получение SubTask по идентификатору
    public SubTask getSubTaskById(int id) {
        if (this.getId() == id) {
            return this;
        } else {
            return null;
        }
    }

    // Метод для создания SubTask
    // Параметр task не используется,тк создаем новый объект SubTask
    public SubTask createSubTask(SubTask subTask) {
        return new SubTask(); // Возвращаем новый SubTask
    }

    // Метод для обновления SubTask
    // обновление = изм полей тек объекта
    public void updateSubTask(SubTask updatedSubTask) {
        // Обн полей тек SubTask на основе переданного объекта updatedSubTask
        this.setName(updatedSubTask.getName());
        this.setStatus(updatedSubTask.getStatus());
        this.setDescription(updatedSubTask.getDescription());
    }

    public Epic getEpic() {
        return epic;
    }

    // Метод для удаления SubTask по ид
    // идентификаторм будет ID самого SubTask
    public void deleteSubTaskById(int id) {
        if (this.getId() == id) {
            Epic epic = this.getEpic(); // Получаем ссылку на эпик, к которому принадлежит тек подзадача
            if (epic != null) {
                epic.removeSubTask(this); // делит тек подзадачу из списка подзадач эпика
            }
        }
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epic=" + epic +
                '}';
    }
}