import model.Status;
import model.Task;
import model.Epic;
import model.SubTask;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Создаем задачу
        Task task1 = new Task("Новая задача", Status.NEW, "Описание задачи");
        int taskId = taskManager.addNewTask(task1);
        System.out.println("Created task: " + task1);

        // Создаем эпик
        Epic epic = new Epic("Новый эпик", Status.NEW, "Описание эпика");
        int epicId = taskManager.addEpic(epic);
        System.out.println("Created epic: " + epic);

        // Создаем подзадачу и добавляем ее к эпику
        SubTask subTask1 = new SubTask("Новая подзадача", Status.NEW,
                "Описание подзадачи", epicId);
        int subTaskId = taskManager.addSubTask(subTask1, epic);
        System.out.println("Created subtask: " + subTask1);


        System.out.println("Epic status: " + epic.getStatus());
        System.out.println("Subtask ID: " + subTaskId);

        taskManager.deleteTaskById(taskId);
        System.out.println("Deleted task: " + task1);


        taskManager.deleteTaskById(epicId);
        System.out.println("Deleted epic: " + epic);
    }
}
