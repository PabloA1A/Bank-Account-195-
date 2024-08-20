package dev.pablo;

public class SavingsAccount extends Account {
    private boolean isActive;

    public SavingsAccount(float balance, float annualRate) {
        super(balance, annualRate);
        updateActiveStatus();
    }

    @Override
    public void deposit(float amount) {
        super.deposit(amount);
        updateActiveStatus();
    }

    @Override
    public void withdraw(float amount) {
        if (isActive) {
            super.withdraw(amount);
            updateActiveStatus();
        }
    }

    @Override
    public void monthlyStatement() {
        if (numWithdrawals > 4) {
            monthlyFee += (numWithdrawals - 4) * 1000;
        }
        super.monthlyStatement();
        updateActiveStatus();
    }

    public String print() {
        return "Balance: " + balance +
                "\nMonthly fee: " + monthlyFee +
                "\nNumber of transactions: " + (mumDeposits + numWithdrawals);
    }

    public boolean isActive() {
        return this.isActive;
    }

    private void updateActiveStatus() {
        isActive = (balance >= 10000);
    }
}
