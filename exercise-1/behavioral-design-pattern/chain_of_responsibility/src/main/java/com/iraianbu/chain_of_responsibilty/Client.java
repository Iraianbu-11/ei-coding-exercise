package com.iraianbu.chain_of_responsibilty;
public class Client {
    public static void main(String[] args) {
        LoanHandler accountant = new AccountantApprovalHandler();
        LoanHandler appraiser = new AppraisalApprovalHandler();
        LoanHandler manager = new ManagerApprovalHandler();
        accountant.setNext(appraiser);
        appraiser.setNext(manager);
        accountant.handleRequest(LoanApplication.BUSINESS_LOAN);
        accountant.handleRequest(LoanApplication.GOLD_LOAN);
        accountant.handleRequest(LoanApplication.EDUCATION_LOAN);
    }
}