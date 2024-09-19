package org.xpdojo.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.xpdojo.bank.Money.amountOf;

@DisplayName("With Money we can ... ")
public class MoneyTest {

    @Test
    public void addTwoAmountsTogether() {
        assertThat(amountOf(10.0).add(amountOf(15.0))).isEqualTo(amountOf(25.0));
    }

    @Test
    public void reductTwoAmounts() {
        assertThat(amountOf(90.0).less(amountOf(20.0))).isEqualTo(amountOf(70.0));
    }
}
