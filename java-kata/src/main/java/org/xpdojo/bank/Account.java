package org.xpdojo.bank;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

public class Account {

    private final List<Transaction> transactions = new ArrayList<>();

    public static Account accountWithEmptyBalance() {
        return new Account(Money.amountOf(0.0));
    }

    public static Account accountWithOpeningBalance(final Money money) {
        return new Account(money);
    }

    private Account(final Money amount) {
        transactions.add(Transaction.aCreditOf(amount, now()));
    }

    public void deposit(final Money amount) {
        transactions.add(Transaction.aCreditOf(amount, now()));
    }

    protected List<Transaction> getTransactions(){
        return transactions;
    }

    protected Money balance() {
        return transactions.stream().map(Transaction::balanceImpact).reduce(Money::add).get();
    }

    public void withdraw(final Money amount) {
        if (amount.isGreaterThan(balance()))
            throw new IllegalStateException("Insufficient balance");
        transactions.add(Transaction.aDebitOf(amount, now()));
    }

    public TransferHelper transfer(final Money money) {
        return new TransferHelper(money);
    }

    public void printBalanceSlipTo(final StatementWriter statementWriter) {
        statementWriter.printBalanceSlipFrom(this);
    }

    public void printActivityStatementTo(final StatementWriter statementWriter) {
        statementWriter.printActivityStatementFor(this);
    }

    class TransferHelper {
        private final Money money;

        private TransferHelper(final Money money) {
            this.money = money;
        }

        public void to(final Account receiverAccount) {
            receiverAccount.deposit(money);
            Account.this.withdraw(money);
        }
    }
}
