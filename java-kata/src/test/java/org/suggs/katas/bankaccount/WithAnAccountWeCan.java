package org.suggs.katas.bankaccount;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.suggs.katas.bankaccount.Account.anAccountWith;
import static org.suggs.katas.bankaccount.Account.anEmptyAccount;
import static org.suggs.katas.bankaccount.Money.anAmountOf;

/*
 - Account statement (date, amount, balance)
 - Statement printing
 - Statement filters (just deposits, withdrawal, date)
 */
public class WithAnAccountWeCan {

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        Account account = anEmptyAccount();
        account.deposit(anAmountOf(10.0d));
        assertThat(account).isEqualTo(anAccountWith(anAmountOf(10.0d)));
    }

    @Test
    public void withdrawAnAmountToDecreaseTheBalance() {
        Account account = anAccountWith(anAmountOf(20.0d));
        account.withdraw(anAmountOf(10.0d));
        assertThat(account).isEqualTo(anAccountWith(anAmountOf(10.0d)));
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionIfYouTryToWithdrawMoreThanTheBalance(){
        Account account = anAccountWith(anAmountOf(20.0d));
        account.withdraw(anAmountOf(30.0d));
    }

    @Test
    public void transferMoneyFromOneAccountToAnother() {
        Account destinationAccount = anEmptyAccount();
        Account sourceAccount = anAccountWith(anAmountOf(50.0d));

        sourceAccount.transferTo(destinationAccount, anAmountOf(20.0d));

        assertThat(sourceAccount).isEqualTo(anAccountWith(anAmountOf(30.0d)));
        assertThat(destinationAccount).isEqualTo(anAccountWith(anAmountOf(20.0d)));
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionIfYouTryToTransferMoreThantheBalance(){
        Account sourceAccount = anAccountWith(anAmountOf(20.0d));
        sourceAccount.transferTo(anEmptyAccount(), anAmountOf(30.0d));
    }




}
