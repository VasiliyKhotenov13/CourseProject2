import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDaily extends Task {

    public TaskDaily(String heading, String content, TaskType taskType, Repeatability repeat, LocalDateTime dateOfCreation) {
        super(heading, content, taskType, repeat, dateOfCreation);
    }

    @Override
    public boolean checkingDate(LocalDate date) {
        LocalDate taskDate = this.getDateOfCreation().toLocalDate();
        return date.equals(taskDate) || date.isAfter(taskDate);
    }

    @Override
    public Repeatability getRepeatabilityTask() {
        return Repeatability.DAILY;
    }
}
