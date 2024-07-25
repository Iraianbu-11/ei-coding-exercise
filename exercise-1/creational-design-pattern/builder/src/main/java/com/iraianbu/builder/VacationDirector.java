package com.iraianbu.builder;

import java.util.List;

public class VacationDirector {

    private final VacationBuilder vacationBuilder;
    public VacationDirector(VacationBuilder vacationBuilder){
        this.vacationBuilder = vacationBuilder;
    }
    public Vacation addPackage(){
        vacationBuilder.addFlight();
        vacationBuilder.addHotel();
        vacationBuilder.addAddress();
        vacationBuilder.addPrice();
        vacationBuilder.addTotalDays();
        return vacationBuilder.build();
    }
}
