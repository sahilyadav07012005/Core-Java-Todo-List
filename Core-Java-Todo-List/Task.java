import java.io.*;
import java.util.*;

public class Task {

    static ArrayList<String> tasks = new ArrayList<>();
    static String fileName = "tasks.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadTasks();

        while (true) {

            System.out.println("\n========== TO-DO LIST ==========");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");

            try {

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        addTask(sc);
                        break;

                    case 2:
                        viewTasks();
                        break;

                    case 3:
                        completeTask(sc);
                        break;

                    case 4:
                        deleteTask(sc);
                        break;

                    case 5:
                        saveTasks();
                        System.out.println("Thank you for using To-Do List!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (InputMismatchException e) {

                System.out.println("Please enter a valid number!");
                sc.nextLine();
            }
        }
    }

    static void addTask(Scanner sc) {

        System.out.print("Enter task: ");
        String task = sc.nextLine();

        tasks.add(task + " | Pending");

        saveTasks();

        System.out.println("Task added successfully!");
    }

    static void viewTasks() {

        if (tasks.isEmpty()) {

            System.out.println("No tasks available.");

        } else {

            System.out.println("\n========== YOUR TASKS ==========");

            for (int i = 0; i < tasks.size(); i++) {

                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    static void completeTask(Scanner sc) {

        viewTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter task number: ");
        int number = sc.nextInt();
        sc.nextLine();

        if (number >= 1 && number <= tasks.size()) {

            String task = tasks.get(number - 1);

            task = task.replace("Pending", "Completed");

            tasks.set(number - 1, task);

            saveTasks();

            System.out.println("Task completed successfully!");

        } else {

            System.out.println("Invalid task number!");
        }
    }

    static void deleteTask(Scanner sc) {

        viewTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter task number: ");
        int number = sc.nextInt();
        sc.nextLine();

        if (number >= 1 && number <= tasks.size()) {

            tasks.remove(number - 1);

            saveTasks();

            System.out.println("Task deleted successfully!");

        } else {

            System.out.println("Invalid task number!");
        }
    }

    static void saveTasks() {

        try {

            FileWriter writer = new FileWriter(fileName);

            for (String task : tasks) {

                writer.write(task);
                writer.write("\n");
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Error while saving tasks.");
        }
    }

    static void loadTasks() {

        try {

            File file = new File(fileName);

            if (!file.exists()) {
                return;
            }

            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                tasks.add(line);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error while loading tasks.");
        }
    }
}