public enum TaskType {

    WORKING("Рабочая"),
    PERSONAL("Личная");

    private String taskType;

    TaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}
