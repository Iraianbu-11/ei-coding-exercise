package com.iraianbu.chain_of_responsibilty;

public abstract class LoanHandler {
    protected LoanHandler next;
    public void setNext(LoanHandler next){
        this.next = next;
    }
    public abstract void handleRequest(LoanApplication loanApplication);
}
