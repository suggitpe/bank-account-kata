package org.xpdojo.bank;

public interface StatementWriter {

    void printBalanceSlipFrom(Account account);

    void printActivityStatementFor(Account account);
}
