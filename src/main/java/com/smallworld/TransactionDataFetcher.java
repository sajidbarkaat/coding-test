package com.smallworld;

import com.smallworld.data.Issue;
import com.smallworld.data.Transaction;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDataFetcher {

    private List<Transaction> transactions;

    // Constructor
    public TransactionDataFetcher(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        return transactions.stream()
                .filter(transaction -> transaction.getSenderInformation().getFullName().equals(senderFullName))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .max()
                .orElse(0.0);
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        return transactions.stream()
                .flatMap(transaction -> Stream.of(transaction.getSenderInformation().getFullName(), 
                                                  transaction.getBeneficiaryInformation().getFullName()))
                .distinct()
                .count();
    }
    

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.getSenderInformation().getFullName().equals(clientFullName)
                                || transaction.getBeneficiaryInformation().getFullName().equals(clientFullName))
                .anyMatch(transaction -> transaction.getIssues().stream()
                        .anyMatch(issue -> !issue.isIssueSolved()));
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<Transaction>> getTransactionsByBeneficiaryName() {
        return transactions.stream()
                .collect(Collectors.groupingBy(transaction ->
                        transaction.getBeneficiaryInformation().getFullName()));
    }

    // Following two methods can be combined into one method by passing a boolean parameter
    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Long> getUnsolvedIssueIds() {
        return transactions.stream()
                .flatMap(transaction -> transaction.getIssues().stream())
                .filter(issue -> !issue.isIssueSolved())
                .map(Issue::getIssueId)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        return transactions.stream()
                .flatMap(transaction -> transaction.getIssues().stream())
                .filter(Issue::isIssueSolved)
                .map(Issue::getIssueMessage)
                .collect(Collectors.toList());
    }

    //Following method can be improved by passing limit as a parameter
    //that makes it flexible to get top n transactions
    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        return transactions.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        Map<String, Double> senderAmountMap = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getSenderInformation().getFullName(),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        return senderAmountMap.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey);
    }
}