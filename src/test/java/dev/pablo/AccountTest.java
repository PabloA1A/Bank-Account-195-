package dev.pablo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account(1000.0f, 0.12f);
    }

    @Test
    public void testInitialValues() {
        assertEquals(1000.0f, account.getBalance());
        assertEquals(0, account.getMumDeposits());
        assertEquals(0, account.getNumWithdrawals());
        assertEquals(0.12f, account.getAnnualRate());
        assertEquals(0.0f, account.getMonthlyFee());  
    }

    @Test
    public void testDeposit() {
        account.deposit(500.0f);
        assertEquals(1500.0f, account.getBalance());
        assertEquals(1, account.getMumDeposits());
    }

    @Test
    public void testWithdrawSuccess() {
        account.withdraw(300.0f);
        assertEquals(700.0f, account.getBalance());
        assertEquals(1, account.getNumWithdrawals());
    }

    @Test
    public void testWithdrawFailure() {
        account.withdraw(1100.0f);  
        assertEquals(1000.0f, account.getBalance()); 
        assertEquals(0, account.getNumWithdrawals());  
    }

    @Test
    public void testCalculateInterest() {
        account.calculateInterest();
        float expectedBalance = 1000.0f + (1000.0f * 0.12f / 12);
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void testMonthlyStatementWithNoMonthlyFee() {
        account.monthlyStatement();
        float expectedBalance = 1000.0f + (1000.0f * 0.12f / 12);
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void testMonthlyStatementWithMonthlyFee() {
        account.setMonthlyFee(50.0f);
        account.monthlyStatement();
        float expectedBalance = (1000.0f - 50.0f) + ((1000.0f - 50.0f) * 0.12f / 12);
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void testPrint() {
        account.deposit(500.0f);
        account.withdraw(200.0f);
        String expectedOutput = "Balance: 1300.0\nNumber of deposits: 1\nNumber of withdrawals: 1\nAnnual rate: 0.12\nMonthly fee: 0.0";
        assertEquals(expectedOutput, account.print());
    }

    @Test
    public void testAccount() {
        Account account = new Account(100000, 0.12f);
        account.deposit(50000);
        account.withdraw(20000);
        account.monthlyStatement();
        System.out.println("Final balance: " + account.balance);
        assertTrue(account.balance > 130000, "Balance should be greater than 130000");
    }

    @Test
    public void testSavingsAccount() {
        SavingsAccount account = new SavingsAccount(100000, 0.1f);
        account.deposit(50000);
        account.withdraw(20000);
        account.withdraw(15000);
        account.withdraw(10000);
        account.withdraw(5000);
        account.withdraw(5000);
        account.monthlyStatement();
        assertTrue(account.balance < 96000);
    }

    @Test
    public void testCheckingAccount() {
        CheckingAccount account = new CheckingAccount(100000, 0.1f);
        account.deposit(50000);
        account.withdraw(200000);
        account.deposit(100000);
        assertTrue(account.balance > 49000);
    }

    @Test
    public void testAccountConstructor() {
        Account account = new Account(5000f, 0.05f);
        assertEquals(5000f, account.balance, "Initial balance should be 5000");
        assertEquals(0.05f, account.annualRate, "Annual rate should be 0.05");
    }

    @Test
    public void testAccountDeposit() {
        Account account = new Account(1000f, 0.1f);
        float initialBalance = account.balance;
        account.deposit(500f);
        assertEquals(1500f, account.balance, "Balance should be 1500 after deposit");
        assertTrue(account.print().contains("Number of deposits: 1"), "Number of deposits should be 1");
    }

    @Test
    public void testAccountWithdrawSufficientFunds() {
        Account account = new Account(1000f, 0.1f);
        account.withdraw(500f);
        assertEquals(500f, account.balance, "Balance should be 500 after withdrawal");
        assertEquals(1, account.getNumWithdrawals(), "Number of withdrawals should be 1");
    }

    @Test
    public void testAccountWithdrawInsufficientFunds() {
        Account account = new Account(1000f, 0.1f);
        account.withdraw(1500f);
        assertEquals(1000f, account.balance, "Balance should remain 1000 when insufficient funds");
        assertEquals(0, account.getNumWithdrawals(), "Number of withdrawals should be 0");
    }

    @Test
    public void testSavingsAccountActivation() {
        SavingsAccount account = new SavingsAccount(9000f, 0.1f);
        assertFalse(account.isActive(), "Account should be inactive when balance is less than 10000");
        account.deposit(1000f);
        account.monthlyStatement();
        assertTrue(account.isActive(), "Account should be active when balance is 10000 or more");
        System.out.println("Balance after deposit and monthly statement: " + account.balance);
    }

    @Test
    public void testSavingsAccountExtraWithdrawalFee() {
        SavingsAccount account = new SavingsAccount(100000f, 0.1f);
        for (int i = 0; i < 5; i++) {
            account.withdraw(1000f);
        }
        account.monthlyStatement();
        assertTrue(account.monthlyFee > 0, "Monthly fee should be charged for extra withdrawals");
    }

    @Test
    public void testCheckingAccountOverdraft() {
        CheckingAccount account = new CheckingAccount(1000f, 0.1f);
        account.withdraw(1500f);
        assertEquals(0f, account.balance, "Balance should be 0 after overdraft");
        assertEquals(500f, account.getOverdraft(), "Overdraft should be 500");
        System.out.println("Balance: " + account.balance + ", Overdraft: " + account.getOverdraft());
    }

    @Test
    public void testCheckingAccountOverdraftRecovery() {
        CheckingAccount account = new CheckingAccount(1000f, 0.1f);
        account.withdraw(1500f);
        account.deposit(700f);
        assertEquals(200f, account.balance, "Balance should be 200 after recovering from overdraft");
        assertEquals(0f, account.getOverdraft(), "Overdraft should be 0 after recovery");
    }
}
