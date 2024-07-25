package com.iraianbu.chain_of_responsibilty;

public class ManagerApprovalHandler extends LoanHandler {
    @Override
    public void handleRequest(LoanApplication loanApplication) {
        if(loanApplication.equals(LoanApplication.BUSINESS_LOAN)){
            System.out.println("Manager Approved: " + "Loan for " + loanApplication);
        }
        else {
            next.handleRequest(loanApplication);
        }
    }
}
