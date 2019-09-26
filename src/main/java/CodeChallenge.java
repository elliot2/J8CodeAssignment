import domain.AccountBalances;
import model.Transaction;
import ui.TransactionUi;
import domain.TransactionCleaner;
import utility.TransactionCsvLoader;

import java.util.List;
import java.util.stream.Collectors;

public class CodeChallenge {

    public static void main(String[] args) {

        TransactionCsvLoader transactionCsvLoader = new TransactionCsvLoader();
        AccountBalances accountBalances = AccountBalances.getInstance();

        TransactionUi transactionUi = new TransactionUi();

        try {
            List<Transaction> transactions = TransactionCleaner.getClean(transactionCsvLoader.loadFromResource("/transactions.csv", true));
            transactionUi.doTransactionInput();

            List<Transaction> dateFilteredTransactions = transactions.stream().
                    filter(transaction -> transaction.getCreateAt().after(transactionUi.getSelectedFromDate()) &&
                            transaction.getCreateAt().before(transactionUi.getSelectedToDate())).
                    collect(Collectors.toList());

            // Process payments for time frame.
            processPayments(accountBalances, dateFilteredTransactions);

            long impactedAccountResults = dateFilteredTransactions.stream().
                    filter(transaction -> transaction.getFromAccountId().
                            equals(transactionUi.getSelectedAccountId())
                            || transaction.getToAccountId().
                            equals(transactionUi.getSelectedAccountId())
                    ).count();

            System.out.println("Relative balance for the period is: " + accountBalances.getAccountBalanceFmt(transactionUi.getSelectedAccountId()));
            System.out.println("Number of transactions included is: " + impactedAccountResults);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void processPayments(AccountBalances accountBalances, List<Transaction> transactions) {
        transactions.forEach(transaction -> accountBalances.processPayment(transaction.getFromAccountId(), transaction.getToAccountId(), transaction.getAmount()));
    }
}
