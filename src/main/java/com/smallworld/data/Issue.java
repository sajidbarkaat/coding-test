package com.smallworld.data;

public class Issue {

    public Issue() {
    }

    public Issue(long issueId, boolean issueSolved, String issueMessage) {
        this.issueId = issueId;
        this.issueSolved = issueSolved;
        this.issueMessage = issueMessage;
    }

    private long issueId;
    private boolean issueSolved;
    private String issueMessage;
    
    //getters, and setters

    public long getIssueId() {
        return issueId;
    }

    public boolean isIssueSolved() {
        return issueSolved;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public void setIssueSolved(boolean issueSolved) {
        this.issueSolved = issueSolved;
    }
     
    public void setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
    }
}

