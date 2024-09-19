package org.xpdojo.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.xpdojo.bank.Account.accountWithOpeningBalance;
import static org.xpdojo.bank.Money.amountOf;

@DisplayName("With an account we can ...")
public class AccountTest {

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        var account = accountWithOpeningBalance(amountOf(25.0));
        account.deposit(amountOf(10.0));
        assertThat(account.balance()).isEqualTo(amountOf(35.0));
    }

    @Test
    public void withdrawAnAmountToDecreaseTheBalance() {
        var account = accountWithOpeningBalance(amountOf(125.0));
        account.withdraw(amountOf(75.0));
        assertThat(account.balance()).isEqualTo(amountOf(50.0));
    }

    @Test
    public void throwsExceptionIfWithdrawMoreThanTheBalance() {
        var account = accountWithOpeningBalance(amountOf(25.0));
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> {
            account.withdraw(amountOf(100.0));
        });
    }

    @Test
    public void transferAnAmountToAnotherAccount() {
        var senderAccount = accountWithOpeningBalance(amountOf(100.0));
        var receiverAccount = accountWithOpeningBalance(amountOf(25.0));

        senderAccount.transfer(amountOf(50.0)).to(receiverAccount);

        assertThat(senderAccount.balance()).isEqualTo(amountOf(50.0));
        assertThat(receiverAccount.balance()).isEqualTo(amountOf(75.0));
    }

    @Test
    public void throwsExceptionIfTransferMoreThanTheBalance() {
        var senderAccount = accountWithOpeningBalance(amountOf(40.0));
        assertThatException().isThrownBy(() -> {
            senderAccount.transfer(amountOf(50.0)).to(accountWithOpeningBalance(amountOf(0.0)));
        });
    }
}
