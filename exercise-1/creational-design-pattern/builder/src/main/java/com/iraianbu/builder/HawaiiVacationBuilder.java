package com.iraianbu.builder;

public class HawaiiVacationBuilder extends VacationBuilder{
    private final Vacation vacation;
    public HawaiiVacationBuilder(){
        vacation = new Vacation();
    }
    @Override
    public void addFlight() {
        this.vacation.setFlight("Air Asia");
    }

    @Override
    public void addHotel() {
        this.vacation.setHotel("Hilton Hawaiian");
    }

    @Override
    public void addAddress() {
        this.vacation.setAddress("2005 Kalia Road Honolulu, Hawaii 96815 USA");
    }

    @Override
    public void addPrice() {
        this.vacation.setPrice("$491");
    }

    @Override
    public void addTotalDays() {
        this.vacation.setTotalDays("7");
    }

    @Override
    public Vacation build() {
        return vacation;
    }
}
