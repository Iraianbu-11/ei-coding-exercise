package com.astronaut.scheduleorganizer;

import com.astronaut.scheduleorganizer.exception.InvalidTimeFormatException;
import com.astronaut.scheduleorganizer.exception.TaskConflictException;
import com.astronaut.scheduleorganizer.exception.TaskNotFoundException;
import com.astronaut.scheduleorganizer.factory.TaskFactory;
import com.astronaut.scheduleorganizer.factory.TaskType;
import com.astronaut.scheduleorganizer.handler.UserInputHandler;
import com.astronaut.scheduleorganizer.model.Task;
import com.astronaut.scheduleorganizer.service.ScheduleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;

public class AstronautDailyScheduleOrganizer {
    private static final ScheduleManager scheduleManager = ScheduleManager.getInstance();
    private static final UserInputHandler inputHandler = UserInputHandler.getInstance();
    private static final Scanner scanner = inputHandler.getScanner();
    private static final Logger log = LoggerFactory.getLogger(AstronautDailyScheduleOrganizer.class);
    public static void main(String[] args) throws TaskNotFoundException {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Edit Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Get Completed Tasks");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
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
                    flag = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Operation. Please try again!!!");
            }
        }
    }
    private static void addTask(){
        try{
            System.out.print("Enter the task type: ");
            String taskType = scanner.nextLine().toUpperCase();
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter start time: ");
            String startTime = scanner.nextLine();
            System.out.print("Enter end time: ");
            String endTime = scanner.nextLine();
            System.out.print("Enter priority: ");
            String priority = scanner.nextLine();
            if(isValidTime(startTime,endTime)) {
                Task task = TaskFactory.createTask(TaskType.valueOf(taskType),description,startTime,endTime,priority);
                scheduleManager.addTask(task);
                log.info("Task added {}" , task.getDescription());
            }
        } catch (InvalidTimeFormatException | TaskConflictException e) {
            log.error("Task not added {}", e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static boolean isValidTime(String startTime, String endTime) throws InvalidTimeFormatException {
        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);
            return true;
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("Error: Invalid time format");
        }
    }

    private static void editTask() {
        System.out.print("Enter the task type: ");
        String taskType = scanner.nextLine().toUpperCase();
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new start time: ");
        String newStartTime = scanner.nextLine();
        System.out.print("Enter new end time: ");
        String newEndTime = scanner.nextLine();
        System.out.print("Enter new priority level: ");
        String newPriority = scanner.nextLine();
        try{
            Task task = TaskFactory.createTask(TaskType.valueOf(taskType),description,newStartTime,newEndTime,newPriority);
            scheduleManager.editTask(task);
            log.warn("Task edited successfully for {}" ,description);
        }
        catch (TaskNotFoundException | TaskConflictException e){
            System.out.println(e.getMessage());
            log.error("Task not edited {}" , description);
        }
    }

    private static void removeTask() throws TaskNotFoundException {
        System.out.print("Enter description of task to remove: ");
        String description = scanner.nextLine();
        try{
            scheduleManager.removeTask(description);
            log.warn("Task removed {}", description);
        }
        catch (TaskNotFoundException e){
            log.error(e.getMessage());
        }
    }

    private static void viewCompletedTasks() {
        List<String> completedTasks = scheduleManager.getCompletedTasks();
        if(completedTasks.isEmpty()){
            System.out.println("No task completed yet");
            return;
        }
        completedTasks.forEach(System.out::println);
    }

    private static void markTaskAsCompleted() throws TaskNotFoundException {
        System.out.print("Enter description of task: ");
        String description = scanner.nextLine();
        scheduleManager.markTaskAsCompleted(description);
        log.info("Task completed {}" , description);
    }

    private static void viewTasksByPriority() {
        System.out.print("Enter priority level: ");
        String priority = scanner.nextLine();
        List<String> tasksByPriority = scheduleManager.getTasksByPriority(priority);
       if(tasksByPriority.isEmpty()){
           System.out.println("No tasks found");
           return;
       }
       tasksByPriority.forEach(System.out::println);
    }

    private static void viewTasks() {
        List<String> tasks = scheduleManager.getTasks();
        if(tasks.isEmpty()){
            System.out.println("No tasks scheduled for the day");
            return;
        }
        tasks.forEach(System.out::println);
    }
}