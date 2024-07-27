package com.astronaut.scheduleorganizer.exception;

public class TaskConflictException extends Exception{
    public TaskConflictException(String msg){
        super(msg);
    }
}
