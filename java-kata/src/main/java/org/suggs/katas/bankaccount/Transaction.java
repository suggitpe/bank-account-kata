package org.suggs.katas.bankaccount;

import java.time.LocalDateTime;

import static org.suggs.katas.bankaccount.Direction.CREDIT;
import static org.suggs.katas.bankaccount.Direction.DEBIT;

/**
 * POJO class only.
 */
public class Transaction {

    private final Money amount;
    private final Direction direction;
    private final LocalDateTime date;


    public static Transaction anOpeningBalanceOf(Money anAmount, LocalDateTime date) {
        return new Transaction(anAmount, CREDIT, date);
    }

    public static Transaction aDepositOf(Money anAmount, LocalDateTime date) {
        return new Transaction(anAmount, CREDIT, date);
    }

    public static Transaction aWithDrawlOf(Money anAmount, LocalDateTime date) {
        return new Transaction(anAmount, DEBIT, date);
    }

    private Transaction(Money amount, Direction direction, LocalDateTime date) {
        this.amount = amount;
        this.direction = direction;
        this.date = date;
    }

    public Money getAmount() {
        return amount;
    }

    public Direction getDirection() {
        return direction;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Money balanceImpact() {
        if (direction == DEBIT)
            return amount.negative();
        else
            return amount;
    }

}

enum Direction {
    DEBIT, CREDIT
}
