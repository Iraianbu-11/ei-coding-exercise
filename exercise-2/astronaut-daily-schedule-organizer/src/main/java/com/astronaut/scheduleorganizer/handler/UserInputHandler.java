package com.astronaut.scheduleorganizer.handler;
import java.util.Scanner;

public class UserInputHandler {
    private static UserInputHandler instance;
    private static Scanner scanner;
    private UserInputHandler(){
        scanner = new Scanner(System.in);
    }

    public static UserInputHandler getInstance(){
        if(instance == null){
            synchronized (UserInputHandler.class){
                if(instance == null){
                    instance = new UserInputHandler();
                }
            }
        }
        return instance;
    }

    public Scanner getScanner(){
        return scanner;
    }
}
