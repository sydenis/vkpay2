import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun fee_0_on_vkcard() {
        //arrange
        val sum = 1_000U * KOPEYKA
        val card = CARD_VK
        val lastMonthSum = 0U
        val expectedValue = 0U

        //act
        val actualValue = fee(sum, card, lastMonthSum)

        //assert
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun fee_35_on_mir() {
        //arrange
        val sum = 100U * KOPEYKA
        val card = CARD_MIR
        val lastMonthSum = 0U
        val expectedValue = 35U * KOPEYKA

        //act
        val actualValue = fee(sum, card, lastMonthSum)

        //assert
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun fee_big_fee_on_mir() {
        //arrange
        val sum = 10_000U * KOPEYKA
        val card = CARD_MIR
        val lastMonthSum = 0U
        val expectedValue = 35U * KOPEYKA

        //act
        val actualValue = fee(sum, card, lastMonthSum)

        //assert
        assertTrue(expectedValue < actualValue)
    }

    @Test
    fun fee_mastercard_less_75() {
        //arrange
        val sum = 1_000U * KOPEYKA
        val card = CARD_MC
        val lastMonthSum = 100U * KOPEYKA
        val expectedValue = 0U

        //act
        val actualValue = fee(sum, card, lastMonthSum)

        //assert
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun fee_mastercard_more_75() {
        //arrange
        val sum = 1_000U * KOPEYKA
        val card = CARD_MC
        val lastMonthSum = 75_000U * KOPEYKA
        val expectedValue = 0U

        //act
        val actualValue = fee(sum, card, lastMonthSum)

        //assert
        assertTrue(expectedValue < actualValue)
    }

    @Test
    fun getCard_default() {
        //arrange
        val input = ""
        val expectedValue = CARD_VK

        //act
        val actualValue = getCard(input)

        //assert
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getCard_zero_min() {
        //arrange
        val input = "0"
        val expectedValue = 0U

        //act
        val actualValue = getCard(input)

        //assert
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getCard_zero_max() {
        //arrange
        val input = "10"
        val expectedValue = 0U

        //act
        val actualValue = getCard(input)

        //assert
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getLastMonthSum_default() {
        //arrange
        val input = ""
        val expectedValue = 5550U

        //act
        val actualValue = getLastMonthSum(input)

        //assert
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun getLastMonthSum_no_default() {
        //arrange
        val input = "100"
        val expectedValue = 100U * KOPEYKA

        //act
        val actualValue = getLastMonthSum(input)

        //assert
        assertEquals(expectedValue, actualValue)
    }
}