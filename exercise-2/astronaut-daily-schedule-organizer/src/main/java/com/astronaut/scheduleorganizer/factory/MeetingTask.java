package com.astronaut.scheduleorganizer.factory;

import com.astronaut.scheduleorganizer.model.Task;

public class MeetingTask extends Task{
    public MeetingTask(String description, String startTime, String endTime, String priority) {
        super(description, startTime, endTime, priority);
    }
}
