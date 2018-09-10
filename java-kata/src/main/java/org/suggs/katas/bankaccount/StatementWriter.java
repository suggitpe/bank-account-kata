package org.suggs.katas.bankaccount;

import java.io.PrintStream;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.suggs.katas.bankaccount.Money.anAmountOf;

public class StatementWriter {

    PrintStream printStream;

    public void printBalanceOf(Money balance) {
        printStream.println("------------------");
        printStream.println("| date | balance |");
        printStream.println("------------------");
        printStream.println("| " + now() + " | " + balance.toString() + " |");
        printStream.println("------------------");
    }

    public void printFullStatementWith(List<Transaction> transactions) {
        printStream.println("----------------------------------------");
        printStream.println("| date | debit/credit amount | balance |");
        printStream.println("----------------------------------------");
        transactions.stream().forEachOrdered(t -> printStatementLineTo(printStream, t, anAmountOf(0.0d)));
        printStream.println("----------------------------------------");
    }

    private void printStatementLineTo(PrintStream printStream, Transaction t, Money balance) {
        printStream.println("| " + t.getDate() + " | " + t.balanceImpact() + " | " + balance + " |");
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
