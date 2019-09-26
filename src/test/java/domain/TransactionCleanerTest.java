package domain;

import model.Transaction;
import model.TransactionType;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionCleanerTest {

    @Test
    public void getClean() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(
                new Transaction("foo",
                        "bar",
                        "baz", new Date(), BigDecimal.valueOf(100),
                        TransactionType.PAYMENT, null)
                );
        transactions.add(
                new Transaction("qax",
                        "qux",
                        "wibble", new Date(), BigDecimal.valueOf(200),
                        TransactionType.PAYMENT, null)
                );
        transactions.add(
                new Transaction("wobble",
                        "daisy",
                        "bubble", new Date(), BigDecimal.valueOf(200),
                        TransactionType.REVERSAL, "qax")
        );
        List<Transaction> cleanTransactions = TransactionCleaner.getClean(transactions);
        assertEquals(1, cleanTransactions.size());
    }
}