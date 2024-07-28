package com.astronaut.scheduleorganizer.factory;

import com.astronaut.scheduleorganizer.model.Task;

public class ExerciseTask extends Task {
    public ExerciseTask(String description, String startTime, String endTime, String priority) {
        super(description, startTime, endTime, priority);
    }
}
