package org.xpdojo.bank

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.xpdojo.bank.Money.Companion.amountOf

@DisplayName("With Money we can ...")
class MoneyTest {

    @Test
    fun `add two monies to create a new money`() {
        (amountOf(12.0) add amountOf(34.0)) shouldBe amountOf(46.0)
    }
}