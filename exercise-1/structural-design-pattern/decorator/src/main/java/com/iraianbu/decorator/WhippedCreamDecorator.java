package com.iraianbu.decorator;

public class WhippedCreamDecorator extends IceCreamDecorator{
    public WhippedCreamDecorator(Icecream decoratedIceCream) {
        super(decoratedIceCream);
    }
    @Override
    public void makeIceCream(){
        super.makeIceCream();
        System.out.println("Whipped Cream Added!!!");
    }
}
