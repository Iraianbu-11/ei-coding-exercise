package com.iraianbu.decorator;

public class SprinklesDecorator extends IceCreamDecorator{
    public SprinklesDecorator(Icecream decoratedIceCream) {
        super(decoratedIceCream);
    }

    @Override
    public void makeIceCream() {
        super.makeIceCream();
        System.out.println("Sprinkles Added!!!");
    }
}
