package domain;

import model.Transaction;
import model.TransactionType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionFilterTest {

    @Test
    public void getByTransactionType() {
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
        List<Transaction> filteredList = TransactionFilter.getByTransactionType(transactions, TransactionType.REVERSAL);
        assertEquals(1, filteredList.size());

        filteredList = TransactionFilter.getByTransactionType(transactions, TransactionType.PAYMENT);
        assertEquals(2, filteredList.size());
    }
}