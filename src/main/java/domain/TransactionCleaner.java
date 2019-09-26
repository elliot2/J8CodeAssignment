package domain;

import model.Transaction;
import model.TransactionType;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionCleaner {

    /**
     * Get transactions of REVERSAL type
     * @param transactions
     * @return filtered transactions of REVERSAL type
     */
    private static List<Transaction> getReversals(List<Transaction> transactions) {
        return TransactionFilter.getByTransactionType(transactions, TransactionType.REVERSAL);
    }

    /**
     * Simply clean any reversals
     *
     * @param transactions
     * @return filtered transactions
     */
    public static List<Transaction> getClean(List<Transaction> transactions) {
        // List of reversals
        List<Transaction> reversals = getReversals(transactions);

        // Clean transactions
        List<Transaction> result = transactions.stream().
                // If this is a PAYMENT, include it in the stream
                filter( transaction -> transaction.getTransactionType().
                        equals(TransactionType.PAYMENT
                        )).
                // If found matching reversal, do not include.
                filter( transaction -> {
                    Transaction matchReversal =
                            reversals.stream().
                                    filter( revTransaction -> revTransaction.getRelatedTransactionId().equals(transaction.getTransactionId())).findAny().orElse(null);
                    return matchReversal == null;
                }).
                collect(Collectors.toList());
        return result;
    }

}
