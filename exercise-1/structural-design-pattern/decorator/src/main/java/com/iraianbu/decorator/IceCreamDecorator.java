package com.iraianbu.decorator;

public abstract class IceCreamDecorator implements Icecream {
    protected Icecream decoratedIceCream;

    public IceCreamDecorator(Icecream decoratedIceCream) {
        this.decoratedIceCream = decoratedIceCream;
    }
    public void makeIceCream(){
       this.decoratedIceCream.makeIceCream();
    }
}
