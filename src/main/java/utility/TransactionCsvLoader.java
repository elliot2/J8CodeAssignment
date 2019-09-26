package utility;

import model.Transaction;
import model.TransactionType;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionCsvLoader {

    public List<Transaction> loadFromResource(String resourceName, boolean hasHeader) throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        try {
            // open CSV file from resource file, ie. inside this jar file.
            InputStream is = TransactionCsvLoader.class.getResourceAsStream(resourceName);
            InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String csvLine = bufferedReader.readLine();

            // skip the CSV header line
            if (hasHeader) {
                csvLine = bufferedReader.readLine();
            }
            while (csvLine != null) {
                String[] csvField = csvLine.split(",");

                String transactionId = csvField[0].trim();
                String fromAccountId = csvField[1].trim();
                String toAccountId = csvField[2].trim();

                Date createAt = new SimpleDateFormat(TransactionConfig.DATE_FORMAT).parse(csvField[3].trim());
                BigDecimal amount = new BigDecimal(csvField[4].trim());
                TransactionType transactionType = TransactionType.valueOf(csvField[5].trim());

                // Related transactionId is optional, if not provided in CSV set null.
                String relatedTransactionId = null;
                if (csvField.length == 7 && !csvField[6].trim().isEmpty()) {
                    relatedTransactionId = csvField[6].trim();
                }

                transactions.add(new Transaction(transactionId, fromAccountId, toAccountId, createAt, amount, transactionType, relatedTransactionId));
                csvLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw(new Exception("TransactionCsvLoader failure : Could not loadFromResource '"+resourceName+"'"));
        }
        return transactions;
    }
}
