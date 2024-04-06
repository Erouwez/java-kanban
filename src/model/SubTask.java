package model;

public class SubTask extends Task {
    private int epicId; // Поле для хранения ид эпика, к которому относится сабстак

    public SubTask(String name, Status status, String description, int epicId) {
        super(name, status, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epicId=" + epicId +
                ", status=" + status +
                '}';
    }
}