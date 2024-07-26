package com.iraianbu.adapter;

public record ResidentialProperty(
        String address,
        double price,
        int numberOfBedrooms) implements Property {
}
