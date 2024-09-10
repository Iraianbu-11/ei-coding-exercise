package com.astronaut.scheduleorganizer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@ToString
public class Task{
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;
    private boolean isCompleted;

    private static final DateTimeFormatter MILITARY_TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    public Task(String description, String startTime, String endTime, String priority) {
        try {
            this.description = description;
            this.startTime = LocalTime.parse(startTime, MILITARY_TIME_FORMATTER);
            this.endTime = LocalTime.parse(endTime, MILITARY_TIME_FORMATTER);
            this.priority = priority;
            this.isCompleted = false;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Time should be in HHmm format");
        }
    }
}
