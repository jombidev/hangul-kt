package dev.jombi.hangulkt

private val DAYS_MAP = mapOf(
    1 to "하루",
    2 to "이틀",
    3 to "사흘",
    4 to "나흘",
    5 to "닷새",
    6 to "엿새",
    7 to "이레",
    8 to "여드레",
    9 to "아흐레",
    10 to "열",
    20 to "스무",
)

private val DAYS_ONLY_TENS_MAP = mapOf(
    10 to "열흘",
    20 to "스무날",
    30 to "서른날",
)

public fun days(num: Int): String {
    return getNumberWord(num)
}

private fun getNumberWord(num: Int): String {
    validateNumber(num)

    val tens = num / 10 * 10
    val ones = num % 10

    if (ones == 0 && tens in DAYS_ONLY_TENS_MAP) {
        return DAYS_ONLY_TENS_MAP[tens]!!
    }

    val tensWord = if (tens in DAYS_MAP) DAYS_MAP[tens]!! else ""
    val onesWord = DAYS_MAP[ones] ?: ""

    return "$tensWord$onesWord"
}

private fun validateNumber(num: Int) {
    if (num <= 0 || num > 30) {
        throw AssertionError("지원하지 않는 숫자입니다: $num")
    }
}