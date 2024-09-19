package org.xpdojo.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.xpdojo.bank.Account.accountWithOpeningBalance;
import static org.xpdojo.bank.Money.amountOf;

@DisplayName("With a statement writer we can ... ")
public class StdOutStatementWriterTest {

    @Test
    public void printOutBalanceSlip(){
        new StdOutStatementWriter().printBalanceSlipFrom(accountWithOpeningBalance(amountOf(100.00)));
    }

    @Test
    public void printActivityStatement(){
        var account = accountWithOpeningBalance(amountOf(10.0));
        account.deposit(amountOf(12.0));
        account.deposit(amountOf(17.0));
        account.withdraw(amountOf(25.0));
        account.deposit(amountOf(35.0));
        account.deposit(amountOf(30.0));
        account.withdraw(amountOf(34.0));
        account.withdraw(amountOf(23.0));
        account.deposit(amountOf(3.0));
        account.withdraw(amountOf(22.0));
        new StdOutStatementWriter().printActivityStatementFor(account);
    }

}
