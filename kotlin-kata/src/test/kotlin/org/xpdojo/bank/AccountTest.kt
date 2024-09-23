package org.xpdojo.bank

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.xpdojo.bank.Account.Companion.accountWithOpeningBalanceOf
import org.xpdojo.bank.Money.Companion.amountOf

@DisplayName("With an account we can ...")
class AccountTest {

    @Test
    fun `deposit an amount to increase the balance`() {
        val account = accountWithOpeningBalanceOf(amountOf(10.0))
        account.deposit(amountOf(4.0))
        account.balance shouldBe amountOf(14.0)
    }
}