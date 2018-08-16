package org.suggs.katas.bankaccount;

import java.util.Objects;

public class Money {
    private double amount;

    private Money(final double anAmount) {
        this.amount = anAmount;
    }

    public static Money anAmountOf(final double anAmount) {
        return new Money(anAmount);
    }

    public void add(final Money anAmount) {
        amount = this.amount + anAmount.amount;
    }

    public void less(final Money anAmount) {
        amount = this.amount - anAmount.amount;
    }

    public boolean isLessThan(Money anAmount) {
        return amount < anAmount.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
