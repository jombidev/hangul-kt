package dev.jombi.hangulkt

private val SEOSUSA_MAP = mapOf(
    1 to "한",
    2 to "두",
    3 to "셋",
    4 to "넷",
    5 to "다섯",
    6 to "여섯",
    7 to "일곱",
    8 to "여덟",
    9 to "아홉",
    10 to "열",
    20 to "스물",
    30 to "서른",
    40 to "마흔",
    50 to "쉰",
    60 to "예순",
    70 to "일흔",
    80 to "여든",
    90 to "아흔",
    100 to "백",
)

private val SEOSUSA_SPECIAL_CASE_MAP = mapOf(
    1 to "첫",
    2 to "둘",
    20 to "스무",
)

/**
 * 숫자를 한글 서수사로 변환합니다.
 *
 * - **서수사**는 순서를 나타내는 단어입니다.
 * - 1부터 99까지의 정수는 순우리말 서수사 문자열로 변환합니다.
 * - 100 이상의 정수는 한자어 서수사 문자열로 변환합니다.
 *
 * @param num - 변환할 숫자
 * @return 변환된 서수사 문자열
 * @exception AssertionError 지원하지 않는 숫자인 경우
 *
 * @link https://es-hangul.slash.page/docs/api/seosusa
 */
public fun seosusa(num: Int): String {
    if (num < 1) {
        throw AssertionError("유효하지 않은 입력입니다. 1이상의 정수만 지원합니다.")
    }

    if (num in 1..99) {
        return "${getOrdinalWord(num)}째"
    }

    return "${numberToHangul(num)}째" // also throws assertion error
}

private fun getOrdinalWord(num: Int): String {
    return SEOSUSA_SPECIAL_CASE_MAP[num] ?: run {
        val tens = num / 10 * 10
        val ones = num % 10

        val tensWord = SEOSUSA_MAP[tens] ?: ""
        val onesWord = SEOSUSA_MAP[ones] ?: ""

        "${tensWord}${onesWord}"
    }

}