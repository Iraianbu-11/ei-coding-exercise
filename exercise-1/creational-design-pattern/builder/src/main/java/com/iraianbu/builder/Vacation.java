package com.iraianbu.builder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Vacation {
    private String flight;
    private String hotel;
    private String address;
    private String totalDays;
    private String price;
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
    public void setFlight(String flight) {
        this.flight = flight;
    }
    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }
    @Override
    public String toString() {
        return "Vacation = [" +
                "flight='" + flight + '\'' +
                ", hotel='" + hotel + '\'' +
                ", address='" + address + '\'' +
                ", totalDays='" + totalDays + '\'' +
                ", price='" + price + '\'' +
                ']';
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
