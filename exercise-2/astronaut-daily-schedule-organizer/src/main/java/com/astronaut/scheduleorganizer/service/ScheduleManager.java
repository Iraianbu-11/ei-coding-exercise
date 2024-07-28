package com.astronaut.scheduleorganizer.service;

import com.astronaut.scheduleorganizer.exception.InvalidTimeFormatException;
import com.astronaut.scheduleorganizer.exception.TaskConflictException;
import com.astronaut.scheduleorganizer.exception.TaskNotFoundException;
import com.astronaut.scheduleorganizer.model.Task;
import com.astronaut.scheduleorganizer.observer.Observer;
import com.astronaut.scheduleorganizer.observer.ObserverManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> taskList;
    private final ObserverManager observerManager;
    private ScheduleManager() {
        taskList = new ArrayList<>();
        observerManager = new ObserverManager();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            synchronized (ScheduleManager.class) {
                if (instance == null) {
                    instance = new ScheduleManager();
                }
            }
        }
        return instance;
    }

    public void addTask(Task task) throws TaskConflictException, InvalidTimeFormatException {
        if (task.getStartTime().isAfter(task.getEndTime())) {
            throw new InvalidTimeFormatException("Error: Invalid time format");
        }
        for (Task existingTask : taskList) {
            if (isTimeConflict(existingTask, task)) {
                observerManager.notifyObservers(existingTask, task);
                throw new TaskConflictException("Error: Task conflicts with existing task " + existingTask.getDescription());
            }
        }
        taskList.add(task);
        System.out.println("Task added successfully. No conflicts");
    }













    public void editTask(Task taskRequest) throws TaskNotFoundException, TaskConflictException {
        Task oldTask = taskList.stream()
                .filter(task -> task.getDescription().equals(taskRequest.getDescription()))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskList.remove(oldTask);
        oldTask.setStartTime(taskRequest.getStartTime());
        oldTask.setEndTime(taskRequest.getEndTime());
        oldTask.setPriority(taskRequest.getPriority());
        oldTask.setDescription(taskRequest.getDescription());
        for (Task existingTask : taskList) {
            if (isTimeConflict(existingTask,oldTask)) {
                observerManager.notifyObservers(existingTask, oldTask);
                throw new TaskConflictException("Error: Task conflicts with existing task " + existingTask.getDescription());
            }
        }

        taskList.add(oldTask);
    }
    public void markTaskAsCompleted(String description) throws TaskNotFoundException {
        Task oldTask = taskList.stream()
                .filter(task -> task.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        oldTask.setCompleted(true);
        System.out.println(description + " " + "completed");
    }

    public List<String> getTasksByPriority(String priority) {
        return taskList.stream()
                .filter(task -> task.getPriority().equalsIgnoreCase(priority))
                .map(Task::getDescription)
                .collect(Collectors.toList());
    }

    public List<String> getCompletedTasks(){
        return taskList.stream()
                .filter(Task::isCompleted)
                .map(Task::getDescription)
                .toList();
    }

    public void removeTask(String description) throws TaskNotFoundException {
        Task oldTask = taskList.stream()
                .filter(task -> task.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        System.out.println("Task removed successfully");
        taskList.remove(oldTask);
    }

    public List<String> getTasks() {
        taskList.sort(Comparator.comparing(Task::getStartTime));
        return taskList.stream()
                .map(task -> task.getStartTime() + " - " + task.getEndTime() + ": " + task.getDescription() + " [" + task.getPriority() + "]")
                .collect(Collectors.toList());
    }

    public void addObserver(Observer observer) {
        observerManager.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        observerManager.removeObserver(observer);
    }

    private boolean isTimeConflict(Task task1, Task task2) {
        return task1.getStartTime().isBefore(task2.getEndTime()) && task1.getEndTime().isAfter(task2.getStartTime());
    }

}
