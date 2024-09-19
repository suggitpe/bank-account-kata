package org.xpdojo.bank;

import java.util.Objects;

/**
 * Immutable class to represent Money as a concept.
 * This class should have no accessor methods.
 */
public class Money {
    private final double value;

    public static Money amountOf(double amount) {
        return new Money(amount);
    }

    private Money(double value) {
        this.value = value;
    }

    public Money add(Money amount) {
        return new Money(value + amount.value);
    }

    public Money less(Money amount) {
        return new Money(value - amount.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(value, money.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String
    toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }

    public boolean isGreaterThan(Money balance) {
        return value > balance.value;
    }
}
