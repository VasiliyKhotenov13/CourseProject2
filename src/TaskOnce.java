import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskOnce extends Task {

    public TaskOnce(String heading, String content, TaskType taskType, Repeatability repeat, LocalDateTime dateOfCreation) {
        super(heading, content, taskType, repeat, dateOfCreation);
    }

    @Override
    public boolean checkingDate(LocalDate date) {
        return date.equals(this.getDateOfCreation().toLocalDate());
    }

    @Override
    public Repeatability getRepeatabilityTask() {
        return Repeatability.ONCE;
    }
}
