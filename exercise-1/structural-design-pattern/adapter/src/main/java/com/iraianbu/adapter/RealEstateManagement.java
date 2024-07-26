package com.iraianbu.adapter;

import java.util.ArrayList;
import java.util.List;

public class RealEstateManagement {
    private final List<Property> propertyList;
    public RealEstateManagement(){
        propertyList = new ArrayList<>();
    }

    public void addProperty(Property property){
        propertyList.add(property);
    }

    public void displayProperties(){
        for(Property property : propertyList){
            System.out.println("Address: " + property.address());
            System.out.println("Price: $" + property.price());
            System.out.println("Bedrooms: " + property.numberOfBedrooms());
        }
    }
}
