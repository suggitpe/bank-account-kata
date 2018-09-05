package org.suggs.katas.bankaccount;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.suggs.katas.bankaccount.Money.anAmountOf;

public class WithMoneyWeCan {

    @Test
    public void addTwoAmountsOfMoneyTogether() {
        assertThat(anAmountOf(7.0d).add(anAmountOf(4.0d))).isEqualTo(anAmountOf(11.0d));
    }

    @Test
    public void subtractAnAmountOfMoneyFromAnother() {
        assertThat(anAmountOf(23.0d).less(anAmountOf(12.0d))).isEqualTo(anAmountOf(11.0d));
    }

    @Test
    public void compareIfOneAmountIsLessThanAnotherAmount() {
        assertThat(anAmountOf(12.0d).isLessThan(anAmountOf(13.0d))).isTrue();
        assertThat(anAmountOf(12.0d).isLessThan(anAmountOf(12.0d))).isFalse();
        assertThat(anAmountOf(12.0d).isLessThan(anAmountOf(11.0d))).isFalse();
    }

    @Test
    public void compareIfTwoAmountsAreTheSame() {
        assertThat(anAmountOf(12.0d).isTheSameAs(anAmountOf(13.0d))).isFalse();
        assertThat(anAmountOf(12.0d).isTheSameAs(anAmountOf(12.0d))).isTrue();
        assertThat(anAmountOf(12.0d).isTheSameAs(anAmountOf(11.0d))).isFalse();
    }
}
