package com.iraianbu.adapter;
public class CommercialPropertyAdapter implements Property{
    private final CommercialProperty commercialProperty;
    public CommercialPropertyAdapter(CommercialProperty commercialProperty) {
        this.commercialProperty = commercialProperty;
    }

    @Override
    public String address() {
        return commercialProperty.location();
    }

    @Override
    public double price() {
        return commercialProperty.cost();
    }

    @Override
    public int numberOfBedrooms() {
        return commercialProperty.squareFootage();
    }
}
