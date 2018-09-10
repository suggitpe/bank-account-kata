package org.suggs.katas.bankaccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.suggs.katas.bankaccount.Money.anAmountOf;
import static org.suggs.katas.bankaccount.Transaction.*;

@RunWith(MockitoJUnitRunner.class)
public class WithAStatementWeCan {

    @Mock
    PrintStream printStream;

    @Test
    public void printAStatementBalance() {
        StatementWriter statement = new StatementWriter();
        statement.setPrintStream(printStream);
        statement.printBalanceOf(anAmountOf(10.d));
        verify(printStream, times(5)).println(anyString());
    }

    @Test
    public void printAFullStatement() {
        StatementWriter statement = new StatementWriter();
        statement.setPrintStream(printStream);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(anOpeningBalanceOf(anAmountOf(10.d), LocalDateTime.now()));
        transactions.add(aDepositOf(anAmountOf(100.d), LocalDateTime.now()));
        transactions.add(aWithDrawlOf(anAmountOf(10.d), LocalDateTime.now()));
        transactions.add(aWithDrawlOf(anAmountOf(10.d), LocalDateTime.now()));
        statement.printFullStatementWith(transactions);
        verify(printStream, times(8)).println(anyString());
    }
}
