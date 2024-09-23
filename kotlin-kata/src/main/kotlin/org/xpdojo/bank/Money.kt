package org.xpdojo.bank

data class Money private constructor(private val amount: Double) {

    companion object{
        fun amountOf(amount: Double) = Money(amount)
    }

    infix fun add(toAdd: Money): Money {
        return Money(amount + toAdd.amount)
    }

    infix fun less(toRemove: Money): Money {
        return Money(amount - toRemove.amount)
    }
}