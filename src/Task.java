import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Comparable <Task> {

    private String heading;
    private String content;
    private TaskType taskType;
    private Repeatability repeat;
    private LocalDateTime dateOfCreation;
    private final Integer id;
    private static Integer counter = 0;

    public Task(String heading, String content, TaskType taskType,
                Repeatability repeat, LocalDateTime dateOfCreation) {
        this.id = ++counter;
        this.heading = heading;
        this.content = content;
        this.taskType = taskType;
        this.repeat = repeat;
        this.dateOfCreation = dateOfCreation;
    }

    public String getHeading() {
        return heading;
    }

    public String getContent() {
        return content;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Repeatability getRepeat() {
        return repeat;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public Integer getId() {
        return id;
    }

    public abstract boolean checkingDate(LocalDate date);

    public abstract Repeatability getRepeatabilityTask();

    @Override
    public int compareTo(Task otherTask) {
        if (otherTask == null) {
            return 1;
        }
        return this.dateOfCreation.toLocalTime().compareTo(otherTask.dateOfCreation.toLocalTime());
    }

    @Override
    public String toString() {
        return "Task{" +
                "heading='" + heading + '\'' +
                ", content='" + content + '\'' +
                ", taskType=" + taskType +
                ", repeat=" + repeat +
                ", dateOfCreation=" + dateOfCreation +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(heading, task.heading) && Objects.equals(content, task.content)
                && taskType == task.taskType && repeat == task.repeat
                && Objects.equals(dateOfCreation, task.dateOfCreation)
                && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heading, content, taskType, repeat, dateOfCreation, id);
    }
}
