package dev.jombi.hangulkt

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal

private val NOT_NUMBER = Regex("[^\\d.]+")

public fun amountToHangul(amount: String): String {
    val strip = NOT_NUMBER.replace(amount, "")
    if (strip.isEmpty()) return ""
    return amountToHangul(strip.toBigDecimal())
}

public fun amountToHangul(amount: Number): String {
    return amountToHangul(BigDecimal.fromDouble(amount.toDouble()))
}

public fun amountToHangul(amount: BigDecimal): String {
    val rawSplitNum = amount.toPlainString().split('.')

    val rawIntPart = rawSplitNum[0]
    val rawDecPart = rawSplitNum.getOrNull(1)

    val intPart = if (rawIntPart != "0") rawIntPart.dropWhile { it == '0' } else rawIntPart

    if (intPart.length > Constants.HANGUL_DIGITS_MAX)
        throw AssertionError("convert range exceeded : $amount")

    val decPart = rawDecPart?.dropLastWhile { it == '0' }

    val result = arrayListOf<String>()
    var shouldPronounDigits = true

    if (intPart == "0" || (intPart == "" && rawDecPart != null)) {
        result.add(Constants.HANGUL_NUMBERS_FOR_DECIMAL[0].toString())
    } else {
        for (i in 0..<intPart.length - 1) {
            val digit = intPart.length - i - 1

            if (intPart[i] > '1' || digit % 4 == 0 || i == 0) {
                val hangulNum = Constants.HANGUL_NUMBERS.getOrNull(intPart[i].digitToInt())

                if (hangulNum != null && hangulNum != '\u0000') {
                    result.add(hangulNum.toString())
                    shouldPronounDigits = true
                }
            }

            if (shouldPronounDigits && digit % 4 == 0) {
                result.add(Constants.HANGUL_DIGITS[digit / 4])
                shouldPronounDigits = false
            }

            if (intPart[i] != '0') {
                result.add(Constants.HANGUL_CARDINAL[digit % 4].toString())
            }
        }
        result.add(Constants.HANGUL_NUMBERS[intPart[intPart.length - 1].digitToInt()].toString())
    }
    
    if (decPart != null) {
        result.add("점")
        for (i in decPart.indices)
            result.add(Constants.HANGUL_NUMBERS_FOR_DECIMAL[decPart[i].digitToInt()].toString())
    }
    
    return result.joinToString("").replace("\u0000", "") // remove null char
}
