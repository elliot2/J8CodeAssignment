package domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class AccountBalancesTest {

    private AccountBalances accountBalances = null;

    @Before
    public void setUp() throws Exception {
        accountBalances = AccountBalances.getInstance();
        accountBalances.clearAccountList();
    }

    @Test
    public void getInstance() {
        assertNotNull(accountBalances);
    }

    @Test
    public void getAccountList() {
        assertNotNull(accountBalances.getAccountList());
    }

    @Test
    public void processPayment() {
        accountBalances.processPayment("foo", "bar", BigDecimal.valueOf(25));
        assertEquals(2, accountBalances.getAccountList().size());
        assertEquals("-$25.00", accountBalances.getAccountBalanceFmt("foo") );
        assertEquals("$25.00", accountBalances.getAccountBalanceFmt("bar") );
    }

    @Test
    public void getAccountBalanceFmt() {
        accountBalances.processPayment("foo", "bar", BigDecimal.valueOf(55));
        assertEquals("-$55.00", accountBalances.getAccountBalanceFmt("foo") );
    }
}