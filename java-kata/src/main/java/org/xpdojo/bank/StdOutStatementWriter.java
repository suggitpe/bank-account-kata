package org.xpdojo.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.time.LocalTime.now;
import static org.xpdojo.bank.Money.amountOf;

public class StdOutStatementWriter implements StatementWriter {

    private static final Logger log = LoggerFactory.getLogger(StdOutStatementWriter.class);

    @Override
    public void printBalanceSlipFrom(Account account) {
        log.debug("------------------");
        log.debug("| date | balance |");
        log.debug("------------------");
        log.debug("| {} | {} |", now(), account.balance().amount());
        log.debug("------------------");
    }

    @Override
    public void printActivityStatementFor(Account account) {
        log.debug("----------------------------------------");
        log.debug("| date | debit/credit amount | balance |");
        log.debug("----------------------------------------");
        printStatementLinesWithBalance(account.getTransactions(), amountOf(0.0));
        log.debug("----------------------------------------");
        log.debug("| Balance as of {} is {} |", now(), account.balance().amount());
        log.debug("----------------------------------------");
    }

    // note recursion
    private void printStatementLinesWithBalance(List<Transaction> transactions, Money money) {
        if (transactions.isEmpty()) return;
        log.debug("| {} | {} | {} |", transactions.get(0).getDate(), transactions.get(0).balanceImpact().amount(), money.add(transactions.get(0).balanceImpact()).amount());
        printStatementLinesWithBalance(transactions.subList(1, transactions.size()), money.add(transactions.get(0).balanceImpact()));
    }
}
