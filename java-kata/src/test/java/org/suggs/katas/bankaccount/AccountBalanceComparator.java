package org.suggs.katas.bankaccount;

import java.util.Comparator;

public class AccountBalanceComparator implements Comparator<Account> {

    @Override
    public int compare(Account account1, Account account2) {
        return account1.balance().compareTo(account2.balance());
    }

}
