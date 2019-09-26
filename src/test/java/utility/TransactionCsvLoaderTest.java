package utility;

import model.Transaction;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TransactionCsvLoaderTest {

    @Test
    public void loadFromResource() throws Exception {
        TransactionCsvLoader transactionCsvLoader = new TransactionCsvLoader();
        List<Transaction> transactions = transactionCsvLoader.loadFromResource("/transactions.csv", true);
        assertEquals(5, transactions.size());
    }
}