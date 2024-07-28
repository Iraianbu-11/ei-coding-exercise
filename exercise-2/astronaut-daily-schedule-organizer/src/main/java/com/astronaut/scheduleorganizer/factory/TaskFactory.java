package com.astronaut.scheduleorganizer.factory;

import com.astronaut.scheduleorganizer.model.Task;

public class TaskFactory {
    public static Task createTask(TaskType taskType,String description , String startTime , String endTime,String priority){
        return switch (taskType) {
            case EXERCISE -> new ExerciseTask(description, startTime, endTime, priority);
            case MAINTENANCE -> new MaintenanceTask(description, startTime, endTime, priority);
            case MEALS -> new MealsTask(description,startTime,endTime,priority);
            case MEETING -> new MeetingTask(description, startTime, endTime, priority);
            default -> throw new IllegalArgumentException("Unknown task type: " + taskType);
        };
    }
}
