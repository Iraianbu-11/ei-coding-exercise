package com.iraianbu.chain_of_responsibilty;

public class AppraisalApprovalHandler extends LoanHandler {
    @Override
    public void handleRequest(LoanApplication loanApplication) {
        if(loanApplication.equals(LoanApplication.GOLD_LOAN)){
            System.out.println("Appraiser Approved: " + "Loan for " + loanApplication);
        }
        else {
            next.handleRequest(loanApplication);
        }
    }
}
