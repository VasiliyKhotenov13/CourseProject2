import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {

    private static final DailyPlaner PLANNER = new DailyPlaner();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH.mm");
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    scanner.nextLine();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTasks(scanner);
                            break;
                        case 3:
                            printTaskForDate(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        """
        );
    }

    private static void inputTask(Scanner scanner) {
        String title = readString("Введите название задачи:", scanner);
        String description = readString("Введите описание задачи:", scanner);
        LocalDateTime taskDate = readDateTime(scanner);
        TaskType taskType = readType(scanner);
        Repeatability repeatability = readRepeatable(scanner);
        Task task = switch (repeatability) {
            case ONCE -> new TaskOnce(title, description, taskType, repeatability, taskDate);
            case DAILY -> new TaskDaily(title, description, taskType, repeatability, taskDate);
            case WEEKLY -> new TaskWeekly(title, description, taskType, repeatability, taskDate);
            case MONTHLY -> new TaskMonthly(title, description, taskType, repeatability, taskDate);
            case YEARLY -> new TaskYearly(title, description, taskType, repeatability, taskDate);
        };
        PLANNER.addTask(task);

    }

    private static Repeatability readRepeatable(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите тип повторяемости: ");
                for (Repeatability repeatable : Repeatability.values()) {
                    System.out.println(repeatable.ordinal() + ". " + localizeRepeatability(repeatable));
                }
                System.out.print("Введите тип повторяемости задачи: ");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return Repeatability.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный тип повторяемости");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Тип повторяемости не найден");
            }
        }
    }


    private static TaskType readType(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите тип задачи: ");
                for (TaskType taskType : TaskType.values()) {
                    System.out.println(taskType.ordinal() + ". " + localizeType(taskType));
                }
                System.out.print("Введите тип задачи: ");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return TaskType.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный номер типа задачи");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Тип задачи не найден");
            }
        }
    }

    private static LocalDateTime readDateTime(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        LocalTime localTime = readTime(scanner);
        return localDate.atTime(localTime);
    }

    private static String readString(String message, Scanner scanner) {
        while (true) {
            System.out.printf(message);
            String readString = scanner.nextLine();
            if (readString == null || readString.isBlank()) {
                System.out.println("Введено пустое значение");
            } else {
                return readString;
            }
        }
    }

    //удалить задачу
    public static void removeTasks(Scanner scanner) {
        System.out.println("Все задачи:");
        for (Task task : PLANNER.getAllTask()) {
            System.out.printf("%d. %s [%s](%s)%n",
                    task.getId(),
                    task.getHeading(),
                    localizeType(task.getTaskType()),
                    localizeRepeatability(task.getRepeatabilityTask()));
        }
        while (true) {
            try {
                System.out.print("Выберите задачу для удаления: ");
                String idLine = scanner.nextLine();
                int id = Integer.parseInt(idLine);
                PLANNER.deleteTask(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный id задачи");
            } catch (ExceptionInMyApplication e) {
                System.out.println("Задача для удаления не найдена");
            }
        }
    }

    //получить задачу на указанный день
    public static void printTaskForDate(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        Collection<Task> taskForDate = PLANNER.getTaskOfTheDay(localDate);
        System.out.println("Задачи на " + localDate.format(DATE_FORMAT));
        for (Task task : taskForDate) {
            System.out.printf("[%s]%s: %s (%s)%n",
                    localizeType(task.getTaskType()),
                    task.getHeading(),
                    task.getDateOfCreation().format(TIME_FORMAT),
                    task.getContent());
        }

    }

    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {
                System.out.printf("Введите дату задачи в формате dd.mm.yyyy: ");
                String dateLine = scanner.nextLine();
                return LocalDate.parse(dateLine, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Введена дата в неверном формате");
            }

        }

    }
    private static LocalTime readTime(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите время задачи в формате hh.mm : ");
                String dateLine = scanner.nextLine();
                return LocalTime.parse(dateLine, TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Введено время в неверном формате");
            }

        }

    }

    private static String localizeType(TaskType taskType) {
        return switch (taskType) {
            case WORKING -> "Рабочая задача";
            case PERSONAL -> "Персональная задача";
            default -> "Неизвестная задача";
        };
    }

    private static String localizeRepeatability(Repeatability repeatability) {
        return switch (repeatability) {
            case ONCE -> "Однокраная задача";
            case DAILY -> "Ежедневная задача";
            case WEEKLY -> "Еженедельная задача";
            case MONTHLY -> "Ежемесячная задача";
            case YEARLY -> "Ежегодная задача";
            default -> "Неизвестно";
        };
    }
}