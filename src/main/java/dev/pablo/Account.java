package dev.pablo;

public class Account {
    protected float balance;
    protected int mumDeposits;
    protected int numWithdrawals;
    protected float annualRate;
    protected float monthlyFee;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getMumDeposits() {
        return mumDeposits;
    }

    public void setMumDeposits(int mumDeposits) {
        this.mumDeposits = mumDeposits;
    }

    public void setNumWithdrawals(int numWithdrawals) {
        this.numWithdrawals = numWithdrawals;
    }

    public float getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(float annualRate) {
        this.annualRate = annualRate;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Account(float balance, float annualRate) {
        this.balance = balance;
        this.annualRate = annualRate;
    }

    public void deposit(float amount) {
        balance += amount;
        mumDeposits++;
    }

    public int getNumWithdrawals() {
        return this.numWithdrawals;
    }

    public void withdraw(float amount) {
        if (amount <= balance) {
            balance -= amount;
            numWithdrawals++;
        }
    }

    public void calculateInterest() {
        float monthlyRate = annualRate / 12;
        float monthlyInterest = balance * monthlyRate;
        balance += monthlyInterest;
    }

    public void monthlyStatement() {
        balance -= monthlyFee;
        calculateInterest();
    }

    public String print() {
        return "Balance: " + balance +
                "\nNumber of deposits: " + mumDeposits +
                "\nNumber of withdrawals: " + numWithdrawals +
                "\nAnnual rate: " + annualRate +
                "\nMonthly fee: " + monthlyFee;
    }
}