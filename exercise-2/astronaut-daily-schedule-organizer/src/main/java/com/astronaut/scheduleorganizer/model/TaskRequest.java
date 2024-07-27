package com.astronaut.scheduleorganizer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {
    private String oldDescription;
    private String newDescription;
    private String newStartTime;
    private String newEndTime;
    private String newPriority;
}
