package com.iraianbu.chain_of_responsibilty;

public class AccountantApprovalHandler extends LoanHandler{
    @Override
    public void handleRequest(LoanApplication loanApplication) {
        if(loanApplication.equals(LoanApplication.EDUCATION_LOAN)){
            System.out.println("Accountant Approved: " + "Loan for " + loanApplication);
        }
        else {
            next.handleRequest(loanApplication);
        }
    }
}
