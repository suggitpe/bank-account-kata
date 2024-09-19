package org.xpdojo.bank;

import java.util.List;

public interface StatementWriter {

    void printBalanceSlipFrom(Account account);

    void printActivityStatementFor(Account account);
}
