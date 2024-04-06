import model.Status;
import model.Task;
import model.Epic;
import model.SubTask;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Создаем задачи
        Task task1 = new Task("Новая задача №1", Status.NEW,
                "Описание задачи №1");
        int taskId1 = taskManager.addNewTask(task1);
        Task task2 = new Task("Новая задача №2", Status.IN_PROGRESS,
                "Описание задачи №2");
        int taskId2 = taskManager.addNewTask(task2);

        // новы эпики и подзадачи
        Epic epic1 = new Epic("Новый эпик №1", "Описание эпика №1");
        int epicId1 = taskManager.addEpic(epic1);
        SubTask subTask1 = new SubTask("Новая подзадача №1", Status.NEW,
                "Описание подзадачи №1", epicId1);
        int subTaskId1 = taskManager.addSubTask(subTask1, epic1);
        SubTask subTask2 = new SubTask("Новая подзадача №2", Status.DONE,
                "Описание подзадачи №2", epicId1);
        int subTaskId2 = taskManager.addSubTask(subTask2, epic1);

        Epic epic2 = new Epic("Новый эпик №2", "Описание эпика №2");
        int epicId2 = taskManager.addEpic(epic2);
        SubTask subTask3 = new SubTask("Новая подзадача №3", Status.IN_PROGRESS,
                "Описание подзадачи №3", epicId2);
        int subTaskId3 = taskManager.addSubTask(subTask3, epic2);

        // принт списков эпиков, задач и подзадач
        System.out.println("List of epics:");
        for (Epic epic : taskManager.getAllEpics()) {
            System.out.println(epic);
        }
        System.out.println("List of tasks:");
        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("List of subtasks:");
        for (SubTask subTask : taskManager.getAllSubTasks()) {
            System.out.println(subTask);
        }

        // Изм статусов созд объектов
        task2.setStatus(Status.DONE);
        subTask3.setStatus(Status.DONE);
        epic2.updateStatus();

        // принт измененных статусов
        System.out.println("Updated status of task 2: " + task2.getStatus());
        System.out.println("Updated status of subtask 3: " + subTask3.getStatus());
        System.out.println("Updated status of epic 2: " + epic2.getStatus());

        // удаляемс задачу и эпик
        taskManager.deleteTaskById(taskId1);
        taskManager.deleteTaskById(epicId1);
    }
}