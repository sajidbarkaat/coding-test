package com.smallworld.data;

import java.util.List;

public class Transaction {
    // constructors
    public Transaction(int mtn, double amount, SenderInformation senderInformation,
            BeneficiaryInformation beneficiaryInformation, List<Issue> issues) {
        this.mtn = mtn;
        this.amount = amount;
        this.senderInformation = senderInformation;
        this.beneficiaryInformation = beneficiaryInformation;
        this.issues = issues;
    }

    public Transaction() {
    }

    private int mtn;
    private double amount;
    private SenderInformation senderInformation;
    private BeneficiaryInformation beneficiaryInformation;
    private List<Issue> issues;

    // getters
    public int getMtn() {
        return mtn;
    }

    public double getAmount() {
        return amount;
    }

    public SenderInformation getSenderInformation() {
        return senderInformation;
    }

    public BeneficiaryInformation getBeneficiaryInformation() {
        return beneficiaryInformation;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    // setters
    public void setMtn(int mtn) {
        this.mtn = mtn;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setSenderInformation(SenderInformation senderInformation) {
        this.senderInformation = senderInformation;
    }

    public void setBeneficiaryInformation(BeneficiaryInformation beneficiaryInformation) {
        this.beneficiaryInformation = beneficiaryInformation;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    // methods
    public void addIssue(Issue issue) {
        this.issues.add(issue);
    }

    public void removeIssue(int issueId) {
        for (Issue issue : this.issues) {
            if (issue.getIssueId() == issueId) {
                this.issues.remove(issue);
                break;
            }
        }
    }

    public void removeIssue(String issueMessage) {
        for (Issue issue : this.issues) {
            if (issue.getIssueMessage().equals(issueMessage)) {
                this.issues.remove(issue);
                break;
            }
        }
    }    
}