package org.xpdojo.bank

class Account private constructor(var balance: Money) {

    companion object{
        fun accountWithOpeningBalanceOf(amount: Money) = Account(amount)
    }

    fun deposit(amount: Money) {
        balance = balance add amount
    }

    fun withdraw(amount: Money) {
        balance = balance less amount
    }
}
