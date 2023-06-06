# Improvement Areas

* Static Code analysis should run on the project, I have experience in SonarQube, would have run it had I had more time

* Following methods can be generalized 
    * public Set<Long> getUnsolvedIssueIds()
    * public List<String> getAllSolvedIssueMessages() 
* A generalized method would look something like following
    * public Set<Long> getIssueIds(bool isSolved)

* Following method can be improved by passing limit as a parameter that makes it flexible to get top N transactions    
    * public List<Transaction> getTop3TransactionsByAmount() 

* Lombok should be used for boilerplate code

* Custom exception classes should be used. E.g. when throwing exception for negative age

* Person class is sufficient for both Beneficiary and Sender but keeping both classes separate to keep the implementation flexible. Should have used KISS principle and used only person class.

* Imports can be improved. Instead of using *. Specific classes should be imported

* https://www.baeldung.com/jackson-vs-gson

* JSON File Path should be in a configuration file

* Have kept TransactionRepository simple. Should have mapper and json reader in separate classes. With right design patterns this area can be improved a lot

# Assumptions
    * First and last name will always be present for beneficiary and sender
    * Sender and beneficiary are always present for a transaction
    * Sender and beneficiary are always different people, I am not checking for this

# TODO

* The part to read JSON from file then map it to transaction POJOs can be easily done, leaving it due to lack to time
 
# Methods Implementations

getTotalTransactionAmount():
Retrieve the list of transactions from the data source.
Iterate over the transactions and accumulate the transaction amounts.
Return the total transaction amount.

getTotalTransactionAmountSentBy(String senderFullName):
Retrieve the list of transactions from the data source.
Iterate over the transactions and accumulate the transaction amounts for the specified sender.
Return the total transaction amount sent by the sender.

getMaxTransactionAmount():
Retrieve the list of transactions from the data source.
Find the transaction with the maximum amount.
Return the maximum transaction amount.

countUniqueClients():
Retrieve the list of transactions from the data source.
Create a set to store unique client names.
Iterate over the transactions and add the sender and beneficiary names to the set.
Return the size of the set.

hasOpenComplianceIssues(String clientFullName):
Retrieve the list of transactions from the data source.
Check if any transaction for the specified client has an open compliance issue (issueSolved = false).
Return true if there is at least one transaction with an open compliance issue, otherwise false.

getTransactionsByBeneficiaryName():
Retrieve the list of transactions from the data source.
Create a map to store transactions indexed by beneficiary name.
Iterate over the transactions and add each transaction to the map using the beneficiary name as the key.
Return the map of transactions.

getUnsolvedIssueIds():
Retrieve the list of transactions from the data source.
Create a set to store unsolved issue IDs.
Iterate over the transactions and add the issue ID to the set if the issue is not solved (issueSolved = false).
Return the set of unsolved issue IDs.

getAllSolvedIssueMessages():
Retrieve the list of transactions from the data source.
Create a list to store solved issue messages.
Iterate over the transactions and add the issue message to the list if the issue is solved (issueSolved = true).
Return the list of solved issue messages.

getTop3TransactionsByAmount():
Retrieve the list of transactions from the data source.
Sort the transactions by amount in descending order.
Return the first three transactions from the sorted list.

getTopSender():
Retrieve the list of transactions from the data source.
Create a map to store the total sent amounts for each sender.
Iterate over the transactions and accumulate the sent amounts for each sender.
Find the sender with the maximum total sent amount.
Return the sender's full name if found, otherwise return an empty optional.
Now that we have a plan, let's start implementing the methods and writing unit tests to ensure the correctness of our solution.

Unit Tests:
setUp() method to initialize the TransactionDataFetcher instance and the list of transactions before each test case. Then, we write individual test methods for each method in the TransactionDataFetcher class. Each test method prepares the necessary test data, calls the corresponding method under test, and verifies the expected results using assertions.

Note that in the helper methods createTransactionWithSender and createTransactionWithBeneficiary, we create mock objects of SenderInformation and BeneficiaryInformation respectively, and set the full name for testing purposes.