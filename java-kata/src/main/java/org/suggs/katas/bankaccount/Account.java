package org.suggs.katas.bankaccount;

import lombok.EqualsAndHashCode;

import static org.suggs.katas.bankaccount.Money.anAmountOf;

@EqualsAndHashCode
public class Account {
    private Money balance;

    public static Account anAccountWith(final Money amount) {
        return new Account(amount);
    }

    public static Account anEmptyAccount() {
        return new Account(anAmountOf(0.0d));
    }

    private Account(final Money anAmount) {
        this.balance = anAmount;
    }

    public void deposit(final Money anAmount) {
        balance.add(anAmount);
    }

    public void withdraw(final Money anAmount) {
        if(balance.isLessThan(anAmount)){
            throw new IllegalStateException("You cannot withdraw more than the balance");
        }
        balance.less(anAmount);
    }

    public void transferTo(final Account destinationAccount, final Money money) {
        destinationAccount.deposit(money);
        this.withdraw(money);
    }
}
