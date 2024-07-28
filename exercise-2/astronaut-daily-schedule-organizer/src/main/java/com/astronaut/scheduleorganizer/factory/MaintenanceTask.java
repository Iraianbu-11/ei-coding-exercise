package com.astronaut.scheduleorganizer.factory;

import com.astronaut.scheduleorganizer.model.Task;

public class MaintenanceTask extends Task {
    public MaintenanceTask(String description, String startTime, String endTime, String priority) {
        super(description, startTime, endTime, priority);
    }
}
