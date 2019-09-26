package ui;

import utility.TransactionConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Simple console User Interface, to collect input data.
 */
public class TransactionUi {

    private String selectedAccountId;
    private Date selectedFromDate;
    private Date selectedToDate;

    /**
     * Get selected accountId, from, to from console.
     */
    public void doTransactionInput() throws ParseException {
        System.out.println("accountId: ");
        Scanner input = new Scanner(System.in);
        selectedAccountId = input.nextLine().trim();
        System.out.println("from: ");
        String selectedFromDateStr = input.nextLine().trim();
        selectedFromDate = new SimpleDateFormat(TransactionConfig.DATE_FORMAT).parse(selectedFromDateStr);
        System.out.println("to: ");
        String selectedToDateStr = input.nextLine().trim();
        selectedToDate = new SimpleDateFormat(TransactionConfig.DATE_FORMAT).parse(selectedToDateStr);
    }

    public String getSelectedAccountId() {
        return selectedAccountId;
    }

    public Date getSelectedFromDate() {
        return selectedFromDate;
    }

    public Date getSelectedToDate() {
        return selectedToDate;
    }


}
