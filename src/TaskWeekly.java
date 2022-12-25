import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskWeekly extends Task{
    public TaskWeekly(String heading, String content, TaskType taskType,
                      Repeatability repeat, LocalDateTime dateOfCreation) {
        super(heading, content, taskType, repeat, dateOfCreation);
    }

    @Override
    public boolean checkingDate(LocalDate date) {
        LocalDate taskDate = this.getDateOfCreation().toLocalDate();
        return date.equals(taskDate) ||
                (date.isAfter(taskDate) && date.getDayOfWeek().equals(taskDate.getDayOfWeek()));
    }

    @Override
    public Repeatability getRepeatabilityTask() {
        return Repeatability.WEEKLY;
    }
}
