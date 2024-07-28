package com.astronaut.scheduleorganizer.factory;

import com.astronaut.scheduleorganizer.model.Task;
public class MealsTask extends Task {
    public MealsTask(String description, String startTime, String endTime, String priority) {
        super(description,startTime,endTime,priority);
    }
}
