import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskMonthly extends Task {

    public TaskMonthly(String heading, String content, TaskType taskType,
                       Repeatability repeat, LocalDateTime dateOfCreation) {
        super(heading, content, taskType, repeat, dateOfCreation);
    }

    @Override
    public boolean checkingDate(LocalDate date) {
        LocalDate taskDate = this.getDateOfCreation().toLocalDate();
        return taskDate.equals(date) ||
                (date.isAfter(taskDate) && date.getDayOfMonth() == taskDate.getDayOfMonth());
    }

    @Override
    public Repeatability getRepeatabilityTask() {
        return Repeatability.MONTHLY;
    }
}
