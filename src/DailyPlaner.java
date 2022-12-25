import java.time.LocalDate;
import java.util.*;

public class DailyPlaner {

    private Map<Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        this.tasks.put(task.getId(), task);
    }

    public void deleteTask(Integer id) throws ExceptionInMyApplication {
        if (this.tasks.containsKey(id)) {
            this.tasks.remove(id);
        } else {
            throw new ExceptionInMyApplication("Такого ID не существует");
        }
    }

    public Collection<Task> getAllTask() {
        return this.tasks.values();
    }

    public Collection<Task> getTaskOfTheDay(LocalDate date) {
        TreeSet<Task> taskOfTheDay = new TreeSet<>();
        for (Task task : tasks.values()) {
            if (task.checkingDate(date)) {
                taskOfTheDay.add(task);
            }
        }
        return taskOfTheDay;
    }
}
