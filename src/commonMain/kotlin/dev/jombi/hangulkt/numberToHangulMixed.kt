package dev.jombi.hangulkt

import com.ionspin.kotlin.bignum.integer.BigInteger

public fun numberToHangulMixed(input: Int, spacing: Boolean = false): String =
    numberToHangulMixed(BigInteger(input), spacing)

public fun numberToHangulMixed(input: Long, spacing: Boolean = false): String =
    numberToHangulMixed(BigInteger(input), spacing)

public fun numberToHangulMixed(input: BigInteger, spacing: Boolean = false): String {
    if (input == BigInteger.ZERO)
        return "0"

    val koreanParts = arrayListOf<String>()
    var remainingDigits = input.toString()
    var placeIndex = 0

    while (remainingDigits.isNotEmpty()) {
        val currentPart = remainingDigits.takeLast(4).toInt()
        val zeroStripNum = currentPart.toString()

        if (currentPart > 0) {
            val toLocaleString = if (zeroStripNum.length >= 4) {
                "${zeroStripNum[0]},${zeroStripNum.drop(1)}"
            } else zeroStripNum
            
            koreanParts.add(0, "$toLocaleString${Constants.HANGUL_DIGITS[placeIndex]}")
        }

        koreanParts.add(0, "")

        remainingDigits = remainingDigits.dropLast(4)
        placeIndex++
    }

    if (spacing) {
        return koreanParts
            .filter { it.isNotEmpty() }
            .joinToString(" ")
            .trim()
    }

    return koreanParts.joinToString("")
}