package org.xpdojo.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.xpdojo.bank.Account.anAccountWith;
import static org.xpdojo.bank.Account.anEmptyAccount;
import static org.xpdojo.bank.AccountBalanceComparator.ofBalances;
import static org.xpdojo.bank.Money.anAmountOf;

public class WithAnAccountWeCan {

    @Mock StatementWriter statementWriter;

    @BeforeEach
    public void setUpMocks() {
        initMocks(this);
    }

    @Test
    public void compareTwoAccountsHaveTheSameBalance() {
        Account account = anAccountWith(anAmountOf(10.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(anAmountOf(10.0d)));
    }

    @Test
    public void depositAnAmountToIncreaseTheBalance() {
        Account account = anEmptyAccount();
        account.deposit(anAmountOf(10.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(anAmountOf(10.0d)));
    }

    @Test
    public void withdrawAnAmountToDecreaseTheBalance() {
        Account account = anAccountWith(anAmountOf(20.0d));
        account.withdraw(anAmountOf(10.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(anAmountOf(10.0d)));
    }

    @Test
    public void throwsExceptionIfYouTryToWithdrawMoreThanTheBalance() {
        Account account = anAccountWith(anAmountOf(20.0d));
        assertThrows(IllegalStateException.class, () -> account.withdraw(anAmountOf(30.0d)));
    }

    @Test
    public void transferMoneyFromOneAccountToAnother() {
        Account destinationAccount = anEmptyAccount();
        Account sourceAccount = anAccountWith(anAmountOf(50.0d));

        sourceAccount.transferTo(destinationAccount, anAmountOf(20.0d));

        assertThat(sourceAccount).usingComparator(ofBalances()).isEqualTo(anAccountWith(anAmountOf(30.0d)));
        assertThat(destinationAccount).usingComparator(ofBalances()).isEqualTo(anAccountWith(anAmountOf(20.0d)));
    }

    @Test
    public void throwsExceptionIfYouTryToTransferMoreThantheBalance() {
        Account sourceAccount = anAccountWith(anAmountOf(20.0d));
        assertThrows(IllegalStateException.class, () -> sourceAccount.transferTo(anEmptyAccount(), anAmountOf(30.0d)));
    }

    @Test
    public void hasTheRightBalanceAfterANumberOfTransactions() {
        Account account = anEmptyAccount();
        account.deposit(anAmountOf(10.0d));
        account.deposit(anAmountOf(80.0d));
        account.deposit(anAmountOf(5.0d));
        account.withdraw(anAmountOf(15.0d));
        account.withdraw(anAmountOf(10.0d));
        assertThat(account).usingComparator(ofBalances()).isEqualTo(anAccountWith(anAmountOf(70.0d)));
    }

    @Test
    public void printOutAnAccountBalance() {
        Account account = anAccountWith(anAmountOf(30.0d));
        account.setStatementWriter(statementWriter);
        account.printBalanceStatement();
        verify(statementWriter).printBalanceOf(any(Money.class));
    }

    @Test
    public void printOutAFullStatement() {
        Account account = anEmptyAccount();
        account.setStatementWriter(statementWriter);
        account.printFullStatement();
        verify(statementWriter).printFullStatementWith(anyListOf(Transaction.class));
    }

}
