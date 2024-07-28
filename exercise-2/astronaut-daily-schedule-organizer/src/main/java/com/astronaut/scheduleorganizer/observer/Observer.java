package com.astronaut.scheduleorganizer.observer;

import com.astronaut.scheduleorganizer.model.Task;

public interface Observer {
    void update(Task newTask, Task existingTask);
}
