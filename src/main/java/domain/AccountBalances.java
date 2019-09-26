package domain;

import model.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * This singleton contains the business logic for maintaining account balances
 */
public class AccountBalances {

    private List<Account> accountList = new ArrayList<>();
    private static final AccountBalances instance = new AccountBalances();

    public static AccountBalances getInstance() {
        return instance;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void clearAccountList() {
        accountList.clear();
    }

    public void processPayment(String fromAccountId, String toAccountId, BigDecimal amount) {

        Account fromAccount = accountList.stream().filter(
                account -> fromAccountId.equals(account.getAccountId())).findAny().
                orElse(null);

        Account toAccount = accountList.stream().filter(
                account -> toAccountId.equals(account.getAccountId())).findAny().
                orElse(null);

        // from or to account may not exist, if so add to list.
        if ( fromAccount == null) {
            fromAccount = new Account(fromAccountId, BigDecimal.valueOf(0));
            accountList.add(fromAccount);
        }

        if ( toAccount == null) {
            toAccount = new Account(toAccountId, BigDecimal.valueOf(0));
            accountList.add(toAccount);
        }

        // Take the payment 'from' account.
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));

        // Make payment 'to' account.
        toAccount.setBalance(toAccount.getBalance().add(amount));
    }

    public String getAccountBalanceFmt(String accountId) {
        Account resultAccount = accountList.stream().filter(
                account -> accountId.equals(account.getAccountId())).findAny().
                orElse(null);
        if (resultAccount != null) {
            // Format the balance as per acceptance criteria -$25.00
            return ( resultAccount.getBalance().floatValue() < 0 ? "-" : "" ) +
                    "$"+(resultAccount.getBalance().setScale(2, RoundingMode.HALF_UP).abs()).toString();
        }
        return null;
    };

    @Override
    public String toString() {
        return "AccountBalances{" +
                "accountList=" + accountList +
                '}';
    }
}
