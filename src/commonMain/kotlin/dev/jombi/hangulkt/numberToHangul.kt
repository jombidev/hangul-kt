package dev.jombi.hangulkt

import com.ionspin.kotlin.bignum.integer.BigInteger

public fun numberToHangul(input: Int, spacing: Boolean = false): String {
    return numberToHangul(BigInteger(input), spacing)
}

public fun numberToHangul(input: Long, spacing: Boolean = false): String {
    return numberToHangul(BigInteger(input), spacing)
}

public fun numberToHangul(input: BigInteger, spacing: Boolean = false): String {
    if (input < 0) {
        throw AssertionError("유효한 0 이상의 정수를 입력해주세요.")
    }

    if (input == BigInteger.ZERO)
        return "영"

    val koreanParts = arrayListOf<String>()
    var remainingDigits = input.toString()
    var placeIndex = 0

    while (remainingDigits.isNotEmpty()) {
        val currentPart = remainingDigits.takeLast(4)

        val koreanNumber = numberToKoreanUpToThousand(BigInteger.parseString(currentPart))
        if (koreanNumber.isNotEmpty()) {
            koreanParts.add(0, "${koreanNumber}${Constants.HANGUL_DIGITS[placeIndex]}")
        }

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

public fun numberToKoreanUpToThousand(num: BigInteger): String {
    val koreanDigits = num.toString()
        .reversed()
        .mapIndexed { index, digit -> if (digit == '0') "" else "${Constants.HANGUL_NUMBERS[digit.digitToInt()]}${Constants.HANGUL_CARDINAL[index]}".replace("\u0000", "") }
        .reversed()
        .joinToString("")

    return koreanDigits.replace("일천", "천").replace("일백", "백").replace("일십", "십")
}