package com.iraianbu.builder;

public class ParisVacationBuilder extends VacationBuilder{
    private final Vacation vacation;
    ParisVacationBuilder(){
        vacation = new Vacation();
    }
    @Override
    public void addFlight() {
        this.vacation.setFlight("Qatar Airways");
    }

    @Override
    public void addHotel() {
        this.vacation.setHotel("Ritz Paris");
    }

    @Override
    public void addAddress() {
        this.vacation.setAddress("15 Pl. Vendôme, 75001 Paris, France");
    }

    @Override
    public void addPrice() {
        this.vacation.setPrice("$1960");
    }

    @Override
    public void addTotalDays() {
        this.vacation.setTotalDays("5");
    }

    @Override
    public Vacation build() {
        return vacation;
    }
}
