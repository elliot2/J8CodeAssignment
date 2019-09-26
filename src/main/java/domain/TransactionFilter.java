package domain;

import model.Transaction;
import model.TransactionType;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionFilter {

    public static List<Transaction> getByTransactionType(List<Transaction> transactions, TransactionType transactionType) {
        List<Transaction> result = transactions.stream().
                filter( transaction -> transaction.getTransactionType().
                        equals(transactionType
                        )).
                collect(Collectors.toList());
        return result;
    }
}
