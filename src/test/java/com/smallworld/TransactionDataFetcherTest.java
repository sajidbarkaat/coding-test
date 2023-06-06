package com.smallworld;
import com.smallworld.data.*;
import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionDataFetcherTest {

    private TransactionDataFetcher transactionDataFetcher;
    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        transactions = new ArrayList<>();        
        transactionDataFetcher = new TransactionDataFetcher(transactions);
    }

    @Test
    void testGetTotalTransactionAmount() {
        // Prepare
        transactions.add(createTransaction(1, 100.0));
        transactions.add(createTransaction(2, 200.0));
        transactions.add(createTransaction(3, 300.0));

        // Execute
        double totalAmount = transactionDataFetcher.getTotalTransactionAmount();

        // Verify
        assertEquals(600.0, totalAmount);
    }

    @Test
    void testGetTotalTransactionAmountSentBy() {
        // Prepare
        transactions.add(createTransactionWithSender("John", "Doe", 100.0));
        transactions.add(createTransactionWithSender("Jane", "Smith", 200.0));
        transactions.add(createTransactionWithSender("John", "Doe", 300.0));

        // Execute
        double totalAmount = transactionDataFetcher.getTotalTransactionAmountSentBy("John Doe");

        // Verify
        assertEquals(400.0, totalAmount);
    }

    @Test
    void testGetMaxTransactionAmount() {
        // Prepare
        transactions.add(createTransaction(1, 100.0));
        transactions.add(createTransaction(2, 200.0));
        transactions.add(createTransaction(3, 300.0));

        // Execute
        double maxAmount = transactionDataFetcher.getMaxTransactionAmount();

        // Verify
        assertEquals(300.0, maxAmount);
    }

    @Test
    void testCountUniqueClients() {
        // Prepare
        transactions.add(createTransactionWithBeneficiaryAndSender("John", "Doe", "Jane", "Smith"));
        transactions.add(createTransactionWithBeneficiaryAndSender("John", "Doe", "David", "Johnson"));

        // Execute
        long uniqueClientsCount = transactionDataFetcher.countUniqueClients();

        // Verify
        assertEquals(3, uniqueClientsCount);
    }

    @Test
    void testHasOpenComplianceIssues() {
        // Prepare
        Transaction transaction1 = createTransactionWithSender("John", "Doe", 100.0);
        Transaction transaction2 = createTransactionWithSender("Jane", "Smith", 200.0);
        Transaction transaction3 = createTransactionWithSender("John", "Doe", 300.0);

        transaction1.getIssues().add(new Issue(1, false, "Issue 1"));
        transaction2.getIssues().add(new Issue(2, true, "Issue 2"));
        transaction3.getIssues().add(new Issue(3, false, "Issue 3"));

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);

        // Execute
        boolean hasOpenIssues = transactionDataFetcher.hasOpenComplianceIssues("John Doe");

        // Verify
        assertTrue(hasOpenIssues);
    }

    @Test
    void testGetTransactionsByBeneficiaryName() {
        // Prepare
        transactions.add(createTransactionWithBeneficiary("John", "Doe", 100.0));
        transactions.add(createTransactionWithBeneficiary("Jane", "Smith", 200.0));
        transactions.add(createTransactionWithBeneficiary("John", "Doe", 300.0));

        // Execute
        Map<String, List<Transaction>> transactionsByBeneficiary = transactionDataFetcher.getTransactionsByBeneficiaryName();

        // Verify
        assertEquals(2, transactionsByBeneficiary.size());
        assertEquals(2, transactionsByBeneficiary.get("John Doe").size());
        assertEquals(1, transactionsByBeneficiary.get("Jane Smith").size());
    }

    @Test
    void testGetUnsolvedIssueIds() {
        // Prepare
        Transaction transaction1 = createTransaction(1, 100.0);
        Transaction transaction2 = createTransaction(2, 200.0);
        Transaction transaction3 = createTransaction(3, 300.0);

        transaction1.getIssues().add(new Issue(1, false, "Issue 1"));
        transaction2.getIssues().add(new Issue(2, true, "Issue 2"));
        transaction3.getIssues().add(new Issue(3, false, "Issue 3"));

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);

        // Execute
        Set<Long> unsolvedIssueIds = transactionDataFetcher.getUnsolvedIssueIds();

        // Verify
        assertEquals(2, unsolvedIssueIds.size());
        assertTrue(unsolvedIssueIds.contains(1L));
        assertTrue(unsolvedIssueIds.contains(3L));
    }

    @Test
    void testGetAllSolvedIssueMessages() {
        // Prepare
        Transaction transaction1 = createTransaction(1, 100.0);
        Transaction transaction2 = createTransaction(2, 200.0);
        Transaction transaction3 = createTransaction(3, 300.0);

        transaction1.getIssues().add(new Issue(1, false, "Issue 1"));
        transaction2.getIssues().add(new Issue(2, true, "Issue 2"));
        transaction3.getIssues().add(new Issue(3, true, "Issue 3"));

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);

        // Execute
        List<String> solvedIssueMessages = transactionDataFetcher.getAllSolvedIssueMessages();

        // Verify
        assertEquals(2, solvedIssueMessages.size());
        assertTrue(solvedIssueMessages.contains("Issue 2"));
        assertTrue(solvedIssueMessages.contains("Issue 3"));
    }

    @Test
    void testGetTop3TransactionsByAmount() {
        // Prepare
        Transaction transaction1 = createTransaction(1, 100.0);
        Transaction transaction2 = createTransaction(2, 200.0);
        Transaction transaction3 = createTransaction(3, 300.0);
        Transaction transaction4 = createTransaction(4, 400.0);
        Transaction transaction5 = createTransaction(5, 500.0);

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);

        // Execute
        List<Transaction> top3Transactions = transactionDataFetcher.getTop3TransactionsByAmount();

        // Verify
        assertEquals(3, top3Transactions.size());
        assertEquals(500.0, top3Transactions.get(0).getAmount());
        assertEquals(400.0, top3Transactions.get(1).getAmount());
        assertEquals(300.0, top3Transactions.get(2).getAmount());
    }

    @Test
    void testGetTopSender() {
        // Prepare
        Transaction transaction1 = createTransactionWithSender("John", "Doe", 100.0);
        Transaction transaction2 = createTransactionWithSender("Jane", "Smith", 200.0);
        Transaction transaction3 = createTransactionWithSender("John", "Doe", 300.0);
        Transaction transaction4 = createTransactionWithSender("Jane", "Smith", 400.0);
        Transaction transaction5 = createTransactionWithSender("John", "Doe", 500.0);

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);

        // Execute
        Optional<String> topSender = transactionDataFetcher.getTopSender();

        // Verify
        assertTrue(topSender.isPresent());
        assertEquals("John Doe", topSender.get());
    }

    // Helper method to create a transaction with a given MTN and amount
    private Transaction createTransaction(int mtn, double amount) {
        return new Transaction(mtn, amount, null, null, new ArrayList<>());
    }

    // Helper method to create a transaction with a sender and amount
    private Transaction createTransactionWithSender(String firstName, String lastName, double amount) {
        SenderInformation sender = new SenderInformation();
        sender.setFirstName(firstName);
        sender.setLastName(lastName);
        return new Transaction(0, amount, sender, null, new ArrayList<>());
    }

    // Helper method to create a transaction with a beneficiary and amount
    private Transaction createTransactionWithBeneficiary(String firstName, String lastName, double amount) {
        BeneficiaryInformation beneficiary = new BeneficiaryInformation();
        beneficiary.setFirstName(firstName); 
        beneficiary.setLastName(lastName);
        return new Transaction(0, amount, null, beneficiary, new ArrayList<>());
    }
    
    // Helper method to create a transaction with a sender and beneficiary
    private Transaction createTransactionWithBeneficiaryAndSender(String senderFirstName, String senderLastName, String beneficiaryFirstName, String beneficiaryLastName) {
        SenderInformation sender = new SenderInformation();
        sender.setFirstName(senderFirstName);
        sender.setLastName(senderLastName);

        BeneficiaryInformation beneficiary = new BeneficiaryInformation();
        beneficiary.setFirstName(beneficiaryFirstName);
        beneficiary.setLastName(beneficiaryLastName);

        return new Transaction(0, 0.0, sender, beneficiary, new ArrayList<>());
    }
}