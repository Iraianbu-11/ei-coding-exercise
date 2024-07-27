package com.astronaut.scheduleorganizer.service;

import com.astronaut.scheduleorganizer.exception.InvalidTimeFormatException;
import com.astronaut.scheduleorganizer.exception.TaskConflictException;
import com.astronaut.scheduleorganizer.exception.TaskNotFoundException;
import com.astronaut.scheduleorganizer.model.Task;
import com.astronaut.scheduleorganizer.model.TaskRequest;
import com.astronaut.scheduleorganizer.observer.Observer;
import com.astronaut.scheduleorganizer.observer.ObserverManager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> taskList;
    private final ObserverManager observerManager;

    private ScheduleManager(){
        taskList = new ArrayList<>();
        observerManager = new ObserverManager();
    }

    public static ScheduleManager getInstance(){
        if(instance == null){
            synchronized (ScheduleManager.class){
                if(instance == null){
                    instance = new ScheduleManager();
                }
            }
        }
        return instance;
    }

    public void addTask(Task task) throws TaskConflictException , InvalidTimeFormatException {
        if(task.getStartTime().isAfter(task.getEndTime())) {
            throw new InvalidTimeFormatException("Error: Invalid time format");
        }
        for (Task existingTask : taskList) {
            if (isTimeConflict(existingTask, task)) {
                observerManager.notifyObservers(existingTask,task);
                throw new TaskConflictException("Error: Task conflicts with existing task " + existingTask.getDescription());
            }
        }
    taskList.add(task);
    taskList.sort(Comparator.comparing(Task::getStartTime));
    }

    public void editTask(TaskRequest taskRequest) throws TaskNotFoundException {
        Task oldTask = taskList.stream()
                .filter(task -> task.getDescription().equals(taskRequest.getOldDescription()))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        oldTask.setDescription(taskRequest.getNewDescription());
        oldTask.setStartTime(LocalTime.parse(taskRequest.getNewStartTime()));
        oldTask.setEndTime(LocalTime.parse(taskRequest.getNewEndTime()));
        oldTask.setPriority(taskRequest.getNewPriority());
    }

    public void markTaskAsCompleted(String description) throws TaskNotFoundException{
        Task oldTask = taskList.stream()
                .filter(task -> task.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        oldTask.setCompleted(true);
        System.out.println(description + " " + "completed");
    }

    public List<String> getTasksByPriority(String priority){
        return taskList.stream()
                .filter(task -> task.getPriority().equalsIgnoreCase(priority))
                .map(Task::getDescription)
                .collect(Collectors.toList());
    }

    public List<String> getCompletedTasks(){
        return taskList.stream()
                .filter(Task::isCompleted)
                .map(Task::getDescription)
                .collect(Collectors.toList());
    }

    public void removeTask(String description) throws TaskNotFoundException {
       Task oldTask = taskList.stream()
                .filter(task -> task.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
       taskList.remove(oldTask);

    }

    public List<String> getTasks(){
        return taskList.stream()
                .map(Task::getDescription)
                .collect(Collectors.toList());
    }

    public void addObserver(Observer observer){
        observerManager.addObserver(observer);
    }

    public void removeObserver(Observer observer){
        observerManager.removeObserver(observer);
    }

    private boolean isTimeConflict(Task task1, Task task2) {
        return task1.getStartTime().isBefore(task2.getEndTime()) && task1.getEndTime().isAfter(task2.getStartTime());
    }

}
