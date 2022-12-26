import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskYearly extends Task{

    public TaskYearly(String heading, String content, TaskType taskType,
                      Repeatability repeat, LocalDateTime dateOfCreation) {
        super(heading, content, taskType, repeat, dateOfCreation);
    }

    @Override
    public boolean checkingDate(LocalDate date) {
        LocalDate taskDate = this.getDateOfCreation().toLocalDate();
        return date.equals(taskDate) ||
                (date.isAfter(taskDate) && date.getDayOfMonth() == taskDate.getDayOfMonth()
                        && date.getMonth().equals(taskDate.getMonth()));
    }

    @Override
    public Repeatability getRepeatabilityTask() {
        return Repeatability.YEARLY;
    }
}
