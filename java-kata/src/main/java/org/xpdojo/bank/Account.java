package org.xpdojo.bank;

public class Account {
    private Money balance;

    private Account(Money amount) {
        this.balance = amount;
    }

    public static Account accountWithOpeningBalance(Money money) {
        return new Account(money);
    }

    public void deposit(Money amount) {
        balance = balance.add(amount);
    }

    public void withdraw(Money amount) {
        if (amount.isGreaterThan(balance))
            throw new IllegalStateException("Insufficient balance");
        balance = balance.less(amount);
    }

    public TransferHelper transfer(Money money) {
        return new TransferHelper(money);
    }

    class TransferHelper {
        private final Money money;

        private TransferHelper(Money money) {
            this.money = money;
        }

        public void to(Account receiverAccount) {
            receiverAccount.deposit(money);
            Account.this.withdraw(money);
        }
    }

    public Money balance() {
        return balance;
    }
}
