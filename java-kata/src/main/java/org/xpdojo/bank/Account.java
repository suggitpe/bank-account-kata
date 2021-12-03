package org.xpdojo.bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.xpdojo.bank.Money.anAmountOf;
import static org.xpdojo.bank.Transaction.*;

public class Account {

    private StatementWriter statementWriter;
    private final List<Transaction> transactions = new ArrayList<>();

    public static Account anEmptyAccount() {
        return new Account(anAmountOf(0.0d));
    }

    public static Account anAccountWith(final Money amount) {
        return new Account(amount);
    }

    private Account(final Money anAmount) {
        transactions.add(anOpeningBalanceOf(anAmount, LocalDateTime.now()));
    }

    protected Money balance() {
        return transactions.stream().map(transaction -> transaction.balanceImpact()).reduce(Money::add).get();
    }

    public void deposit(final Money anAmount) {
        transactions.add(aDepositOf(anAmount, LocalDateTime.now()));
    }

    public void withdraw(final Money anAmount) {
        if (balance().isLessThan(anAmount)) {
            throw new IllegalStateException("You cannot withdraw more than the balance");
        }
        transactions.add(aWithDrawlOf(anAmount, LocalDateTime.now()));
    }

    public void transferTo(final Account destinationAccount, final Money money) {
        destinationAccount.deposit(money);
        this.withdraw(money);
    }

    public void printBalanceStatement() {
        statementWriter.printBalanceOf(balance());
    }

    public void printFullStatement() {
        statementWriter.printFullStatementWith(transactions);
    }

    public void setStatementWriter(StatementWriter statementWriter) {
        this.statementWriter = statementWriter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactions);
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance() +
                '}';
    }
}
