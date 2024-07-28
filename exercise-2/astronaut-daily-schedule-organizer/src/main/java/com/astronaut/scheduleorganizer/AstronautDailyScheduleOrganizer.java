package com.astronaut.scheduleorganizer;

import com.astronaut.scheduleorganizer.exception.InvalidTimeFormatException;
import com.astronaut.scheduleorganizer.exception.TaskConflictException;
import com.astronaut.scheduleorganizer.exception.TaskNotFoundException;
import com.astronaut.scheduleorganizer.factory.TaskFactory;
import com.astronaut.scheduleorganizer.factory.TaskType;
import com.astronaut.scheduleorganizer.handler.UserInputHandler;
import com.astronaut.scheduleorganizer.model.Task;
import com.astronaut.scheduleorganizer.observer.TaskConflictObserver;
import com.astronaut.scheduleorganizer.service.ScheduleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AstronautDailyScheduleOrganizer {
    private static final ScheduleManager scheduleManager = ScheduleManager.getInstance();
    private static final UserInputHandler inputHandler = UserInputHandler.getInstance();
    private static final Scanner scanner = inputHandler.getScanner();
    private static final Logger log = LoggerFactory.getLogger(AstronautDailyScheduleOrganizer.class);

    public static void main(String[] args) {
        TaskConflictObserver observer = new TaskConflictObserver();
        scheduleManager.addObserver(observer);

        while(true) {
            try {
                showMenu();
                int choice = getUserChoice();
                handleUserChoice(choice);
            } catch (InputMismatchException e) {
                log.error("Invalid input type.", e);
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. View Tasks");
        System.out.println("4. Edit Task");
        System.out.println("5. Mark Task as Completed");
        System.out.println("6. View Tasks by Priority");
        System.out.println("7. Get Completed Tasks");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice() {
        return scanner.nextInt();
    }

    private static void handleUserChoice(int choice) {
        scanner.nextLine();
        switch (choice) {
            case 1:
                addTask();
                break;
            case 2:
                removeTask();
                break;
            case 3:
                viewTasks();
                break;
            case 4:
                editTask();
                break;
            case 5:
                markTaskAsCompleted();
                break;
            case 6:
                viewTasksByPriority();
                break;
            case 7:
                viewCompletedTasks();
                break;
            case 8:
                log.info("Exiting application.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private static void addTask() {
        try {
            String taskType = getUserInput("Enter the task type: ").toUpperCase();
            String description = getUserInput("Enter description: ");
            String startTime = getUserInput("Enter start time: ");
            String endTime = getUserInput("Enter end time: ");
            String priority = getUserInput("Enter priority: ");

            if (isValidTime(startTime, endTime)) {
                Task task = TaskFactory.createTask(TaskType.valueOf(taskType), description, startTime, endTime, priority);
                scheduleManager.addTask(task);
                log.info("Task added: {}", task.getDescription());
            }
        } catch (InvalidTimeFormatException | TaskConflictException | IllegalArgumentException e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private static String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static boolean isValidTime(String startTime, String endTime) throws InvalidTimeFormatException {
        try {
            LocalTime.parse(startTime);
            LocalTime.parse(endTime);
            return true;
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("Error: Invalid time format");
        }
    }

    private static void editTask() {
        try {
            String taskType = getUserInput("Enter the task type: ").toUpperCase();
            String description = getUserInput("Enter new description: ");
            String newStartTime = getUserInput("Enter new start time: ");
            String newEndTime = getUserInput("Enter new end time: ");
            String newPriority = getUserInput("Enter new priority level: ");

            Task task = TaskFactory.createTask(TaskType.valueOf(taskType), description, newStartTime, newEndTime, newPriority);
            scheduleManager.editTask(task);
            log.info("Task edited successfully: {}", description);
        } catch (TaskNotFoundException | TaskConflictException | IllegalArgumentException e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private static void removeTask() {
        try {
            String description = getUserInput("Enter description of task to remove: ");
            scheduleManager.removeTask(description);
            log.info("Task removed: {}", description);
        } catch (TaskNotFoundException e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private static void viewCompletedTasks() {
        List<String> completedTasks = scheduleManager.getCompletedTasks();
        if (completedTasks.isEmpty()) {
            System.out.println("No tasks completed yet.");
        } else {
            completedTasks.forEach(System.out::println);
        }
    }

    private static void markTaskAsCompleted() {
        try {
            String description = getUserInput("Enter description of task: ");
            scheduleManager.markTaskAsCompleted(description);
            log.info("Task marked as completed: {}", description);
        } catch (TaskNotFoundException e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private static void viewTasksByPriority() {
        String priority = getUserInput("Enter priority level: ");
        List<String> tasksByPriority = scheduleManager.getTasksByPriority(priority);
        if (tasksByPriority.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            tasksByPriority.forEach(System.out::println);
        }
    }

    private static void viewTasks() {
        List<String> tasks = scheduleManager.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            tasks.forEach(System.out::println);
        }
    }
}
