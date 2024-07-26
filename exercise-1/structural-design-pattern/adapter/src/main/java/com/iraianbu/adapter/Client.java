package com.iraianbu.adapter;
public class Client {
    public static void main(String[] args) {
        RealEstateManagement realEstate = new RealEstateManagement();
        realEstate.addProperty(new ResidentialProperty(
                "123 New Street",250000.00,4
        ));
        realEstate.addProperty(new ResidentialProperty(
                "321 Old Street",1000000.00,6
        ));
        realEstate.addProperty(
                new CommercialPropertyAdapter(
                        new CommercialProperty(
                                "456 God Street",300000.00,15000
                        ))
        );
        realEstate.displayProperties();
    }
}