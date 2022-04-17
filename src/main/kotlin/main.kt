const val CARD_VK = 1U
const val CARD_MIR = 2U
const val CARD_VISA = 3U
const val CARD_MC = 4U
const val CARD_MS = 5U

fun main() {
    print("Сумма перевода: ")
    val sum = readln().toUInt() * 100U;

    val card = getCard()
    val lmSum = getLastMonthSum()

    println()
    println("Комиссия: ${fee(sum, card, lmSum)}")
}

fun fee(sum: UInt, card: UInt = CARD_VK, lastMonthSum: UInt = 0U): UInt {
    var result: UInt = 0U

    when (card) {
        CARD_VK -> {
            result = 0U
        }
        CARD_MIR, CARD_VISA -> {
            val f = 0.0075 * sum.toDouble()
            result = if (f < 35_00) 35_00U else f.toUInt()
        }
        CARD_MC, CARD_MS -> {
            result = if (lastMonthSum < 75_000_00U)
                0U
            else {
                val f = 0.006 * sum.toDouble() + 20_00
                f.toUInt()
            }
        }

    }

    return result / 100U
}


fun getCard(): UInt {
    println("Коды карт: VKPay - $CARD_VK, MIR - $CARD_MIR, VISA - $CARD_VISA, Master - $CARD_MC, Maestro - $CARD_MS")
    print("код вашей карты (по умолчанию VKPay): ")

    var inp : String = readLine()?.trim() ?: ""

    val card = if (inp.trim() == "") CARD_VK else inp.toUInt()

    return if ((card < CARD_VK) || (card > CARD_MS))
        0U
    else
        card
}

fun getLastMonthSum(): UInt {
    print("Сумма переводов за прошлый месяц (по умолчанию 0): ")
    val inp : String = readLine()?.trim() ?: ""

    return if (inp == "")
        0U
    else
        inp.toUInt() * 100U
}