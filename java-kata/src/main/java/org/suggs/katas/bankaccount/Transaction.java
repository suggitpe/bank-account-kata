package org.suggs.katas.bankaccount;

import java.time.LocalDateTime;

public class Transaction {

    private final Money amount;
    private final Direction direction;
    private final LocalDateTime date;


    public static Transaction anOpeningBalanceOf(Money anAmount, LocalDateTime date) {
        return new Transaction(anAmount, Direction.CREDIT, date);
    }

    private Transaction(Money amount, Direction direction, LocalDateTime date) {
        this.amount = amount;
        this.direction = direction;
        this.date = date;
    }
}

enum Direction {
    DEBIT, CREDIT
}
