package org.suggs.katas.bankaccount;

import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime date;
    private Direction direction;
    private Money amount;
}

enum Direction{
    DEBIT, CREDIT
}
