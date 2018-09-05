package org.suggs.katas.bankaccount;

import java.util.Objects;

/**
 * Immutable class
 */
public final class Money {
    private final double amount;

    private Money(final double anAmount) {
        this.amount = anAmount;
    }

    public static Money anAmountOf(final double anAmount) {
        return new Money(anAmount);
    }

    public Money add(final Money anAmount) {
        return anAmountOf(this.amount + anAmount.amount);
    }

    public Money less(final Money anAmount) {
        return anAmountOf(this.amount - anAmount.amount);
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

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }
}
