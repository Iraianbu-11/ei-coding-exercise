package com.iraianbu.builder;

public class Client {
    public static void main(String[] args) {
        Vacation hawaiiVacation = new VacationDirector(new HawaiiVacationBuilder()).addPackage();
        Vacation parisVacation = new VacationDirector(new ParisVacationBuilder()).addPackage();
        System.out.println(hawaiiVacation);
        System.out.println(parisVacation);
    }
}