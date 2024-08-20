package dev.pablo;

public class CheckingAccount extends Account {
    private float overdraft;

    public CheckingAccount(float balance, float annualRate) {
        super(balance, annualRate);
        overdraft = 0;
    }

    @Override
    public void withdraw(float amount) {
        if (amount <= balance) {
            super.withdraw(amount);
        } else {
            overdraft = (amount - balance);
            balance = 0;
            numWithdrawals++;
        }
    }

    @Override
    public void deposit(float amount) {
        if (overdraft > 0) {
            if (amount <= overdraft) {
                overdraft -= amount;
            } else {
                balance = (amount - overdraft);
                overdraft = 0;
            }
        } else {
            super.deposit(amount);
        }
    }

    @Override
    public void monthlyStatement() {
        super.monthlyStatement();
    }

    public String print() {
        return "Balance: " + balance +
                "\nMonthly fee: " + monthlyFee +
                "\nNumber of transactions: " + (mumDeposits + numWithdrawals) +
                "\nOverdraft value: " + overdraft;
    }

    public float getOverdraft() {
        return this.overdraft;
    }
}
