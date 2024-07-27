package com.astronaut.scheduleorganizer;

import com.astronaut.scheduleorganizer.exception.InvalidTimeFormatException;
import com.astronaut.scheduleorganizer.exception.TaskConflictException;
import com.astronaut.scheduleorganizer.exception.TaskNotFoundException;
import com.astronaut.scheduleorganizer.handler.UserInputHandler;
import com.astronaut.scheduleorganizer.model.Task;
import com.astronaut.scheduleorganizer.model.TaskRequest;
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
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter start time: ");
            String startTime = scanner.nextLine();
            System.out.print("Enter end time: ");
            String endTime = scanner.nextLine();
            System.out.print("Enter priority: ");
            String priority = scanner.nextLine();
            if(isValidTime(startTime,endTime)) {
                Task task = new Task(description, startTime, endTime, priority);
                scheduleManager.addTask(task);
                log.info("Task added {}" , task.getDescription());
            }
        } catch (InvalidTimeFormatException | TaskConflictException e) {
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

    private static void editTask() throws TaskNotFoundException {

        System.out.print("Enter description of task to edit: ");
        String description = scanner.nextLine();
        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();
        System.out.print("Enter new start time: ");
        String newStartTime = scanner.nextLine();
        System.out.print("Enter new end time: ");
        String newEndTime = scanner.nextLine();
        System.out.print("Enter new priority level: ");
        String newPriority = scanner.nextLine();
        TaskRequest taskRequest = new TaskRequest(description,newDescription,newStartTime,newEndTime,newPriority);
        scheduleManager.editTask(taskRequest);
    }

    private static void removeTask() throws TaskNotFoundException {
        System.out.print("Enter description of task to remove: ");
        String description = scanner.nextLine();
        scheduleManager.removeTask(description);
    }

    private static void viewCompletedTasks() {
        scheduleManager.getCompletedTasks().forEach(System.out::println);
    }

    private static void markTaskAsCompleted() throws TaskNotFoundException {
        System.out.print("Enter description of task: ");
        String description = scanner.nextLine();
        scheduleManager.markTaskAsCompleted(description);
    }

    private static void viewTasksByPriority() {
        System.out.print("Enter priority level: ");
        String priority = scanner.nextLine();
        scheduleManager.getTasksByPriority(priority).forEach(System.out::println);
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
/***
 *   MDC.put("user","admin");
 *         Logger logger = LoggerFactory.getLogger(AstronautDailyScheduleOrganizer.class);
 *         logger.info("Hello World");
 *
 *        try{
 *            performTask();
 *        }
 *        catch (InvalidTimeFormatException e){
 *            logger.error(e.getMessage());
 *        }
 *     }
 *
 *     private static void performTask() throws InvalidTimeFormatException{
 *         if(true){
 *             throw new InvalidTimeFormatException("Error: Invalid time format");
 *         }
 *         ScheduleManager scheduleManager = ScheduleManager.getInstance();
 *             Task task1 = new Task("Team Meeting", "09:00", "10:00", "High");
 *             Task task2 = new Task("Overlap Task", "08:02", "09:00", "High");
 *             Task task3 = new Task("New Task","07:00","08:02","Low");
 *             scheduleManager.addTask(task1);
 *             scheduleManager.addTask(task2);
 *             scheduleManager.addTask(task3);
 *             scheduleManager.getTasks().forEach(System.out::println);
 *             System.out.println();
 *             scheduleManager.editTask(new TaskRequest(
 *                     "Team Meeting","New Meeting","11:00","13:00","High"
 *             ));
 *             //scheduleManager.getTasks().forEach(System.out::println);
 *             scheduleManager.markTaskAsCompleted("Overlap Task");
 *             //scheduleManager.getTasksByPriority("High").forEach(System.out::println);
 *             scheduleManager.getCompletedTasks().forEach(System.out::println);
 *             //scheduleManager.getTasks().forEach(System.out::println);
 */