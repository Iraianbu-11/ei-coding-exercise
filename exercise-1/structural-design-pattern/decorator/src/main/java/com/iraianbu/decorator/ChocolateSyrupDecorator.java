package com.iraianbu.decorator;

public class ChocolateSyrupDecorator extends IceCreamDecorator{
    public ChocolateSyrupDecorator(Icecream decoratedIceCream) {
        super(decoratedIceCream);
    }

    @Override
    public void makeIceCream() {
        super.makeIceCream();
        System.out.println("Chocolate Syrup Added!!!");
    }
}
