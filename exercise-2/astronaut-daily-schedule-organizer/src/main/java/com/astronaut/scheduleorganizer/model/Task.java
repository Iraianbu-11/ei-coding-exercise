package com.astronaut.scheduleorganizer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalTime;
@Getter
@Setter
@ToString
public class Task{
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;
    private boolean isCompleted;

    public Task(String description , String startTime,String endTime,String priority){
        this.description = description;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
        this.priority = priority;
        this.isCompleted=false;
    }
}
