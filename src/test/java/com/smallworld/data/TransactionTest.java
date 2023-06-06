package com.smallworld.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Collections;

public class TransactionTest {

    @Test
    public void testTransaction() {
        // Create mock objects
        SenderInformation sender = mock(SenderInformation.class);
        BeneficiaryInformation beneficiary = mock(BeneficiaryInformation.class);
        Issue issue = mock(Issue.class);

        // Create a Transaction object
        Transaction transaction = new Transaction();
        transaction.setMtn(1284564);
        transaction.setAmount(150.2);
        transaction.setSenderInformation(sender);
        transaction.setBeneficiaryInformation(beneficiary);
        transaction.setIssues(Collections.singletonList(issue));

        // Verify the Transaction object
        assertEquals(1284564, transaction.getMtn());
        assertEquals(150.2, transaction.getAmount());
        assertEquals(sender, transaction.getSenderInformation());
        assertEquals(beneficiary, transaction.getBeneficiaryInformation());
        assertEquals(Collections.singletonList(issue), transaction.getIssues());
    }

    @Test
    public void testIssue() {
        // Create an Issue object
        Issue issue = new Issue();
        issue.setIssueId(2);
        issue.setIssueSolved(true);
        issue.setIssueMessage("Never gonna give you up");

        // Verify the Issue object
        assertEquals(2, issue.getIssueId());
        assertTrue(issue.isIssueSolved());
        assertEquals("Never gonna give you up", issue.getIssueMessage());
    }

    @Test
    public void testSenderInformation() {
        // Create a SenderInformation object
        SenderInformation sender = new SenderInformation();
        sender.setFirstName("John");
        sender.setLastName("Doe");
        sender.setAge(30);

        // Verify the SenderInformation object
        assertEquals("John", sender.getFirstName());
        assertEquals("Doe", sender.getLastName());
        assertEquals(30, sender.getAge());
    }

    @Test
    public void testBeneficiaryInformation() {
        // Create a BeneficiaryInformation object
        BeneficiaryInformation beneficiary = new BeneficiaryInformation();
        beneficiary.setFirstName("Jane");
        beneficiary.setLastName("Smith");
        beneficiary.setAge(25);

        // Verify the BeneficiaryInformation object
        assertEquals("Jane", beneficiary.getFirstName());
        assertEquals("Smith", beneficiary.getLastName());
        assertEquals(25, beneficiary.getAge());
    }
}

