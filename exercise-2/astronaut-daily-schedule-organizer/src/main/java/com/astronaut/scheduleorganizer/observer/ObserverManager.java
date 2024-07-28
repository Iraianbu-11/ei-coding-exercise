package com.astronaut.scheduleorganizer.observer;

import com.astronaut.scheduleorganizer.model.Task;
import java.util.ArrayList;
import java.util.List;

public class ObserverManager {
    private List<Observer> observers;
    public ObserverManager(){
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(Task newTask, Task existingTask){
        for(Observer observer : observers){
            observer.update(newTask,existingTask);
        }
    }
}
