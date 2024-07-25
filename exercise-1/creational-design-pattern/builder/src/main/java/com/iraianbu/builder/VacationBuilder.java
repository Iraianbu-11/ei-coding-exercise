package com.iraianbu.builder;

import java.util.List;

public abstract class VacationBuilder {
    public abstract void addFlight();
    public abstract void addHotel();
    public abstract void addAddress();
    public abstract void addPrice();
    public abstract void addTotalDays();
    public abstract Vacation build();
}
