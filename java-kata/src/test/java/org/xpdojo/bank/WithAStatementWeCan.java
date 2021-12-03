package org.xpdojo.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.xpdojo.bank.Money.anAmountOf;
import static org.xpdojo.bank.Transaction.*;

public class WithAStatementWeCan {

    @Mock PrintStream printStream;

    @BeforeEach
    public void setUpMocks() {
        initMocks(this);
    }

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
