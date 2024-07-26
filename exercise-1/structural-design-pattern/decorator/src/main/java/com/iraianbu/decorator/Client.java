package com.iraianbu.decorator;

public class Client {
    public static void main(String[] args) {
        // Icecream made of chocolate,sprinkles and Whipped cream
        Icecream icecream = new BaseIceCream();
        Icecream chocolateIceCream = new ChocolateSyrupDecorator(icecream);
        Icecream sprinklesWithChocolateIceCream = new SprinklesDecorator(chocolateIceCream);
        Icecream whippedCreamWithsprinklesWithChocolateIceCream = new WhippedCreamDecorator(sprinklesWithChocolateIceCream);
        whippedCreamWithsprinklesWithChocolateIceCream.makeIceCream();
        System.out.println();

        // Icecream made of whipped cream
        Icecream newIceCream = new BaseIceCream();
        Icecream icecreamWithWhippedCream = new WhippedCreamDecorator(newIceCream);
        icecreamWithWhippedCream.makeIceCream();

    }
}