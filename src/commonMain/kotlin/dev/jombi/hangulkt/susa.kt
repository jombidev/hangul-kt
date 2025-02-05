package dev.jombi.hangulkt

private val SUSA_MAP = mapOf(
    1 to "하나",
    2 to "둘",
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

private val SUSA_CLASSIFIER_MAP = mapOf(
    1 to "한",
    2 to "두",
    3 to "세",
    4 to "네",
    20 to "스무",
)

/**
 * 숫자를 순 우리말 수사로 변환합니다. 주어진 숫자가 0보다 크고 100 이하일 때 유효합니다.
 *
 * - **수사**란 숫자를 나타내는 우리말 단어입니다. [자세히 알아보기](https://ko.dict.naver.com/#/entry/koko/d0ce2b674cae4b44b9028f648dd458b0)
 * - **수관형사**는 사물의 수나 양을 나타내는 관형사입니다. ‘두 사람’의 ‘두’, ‘세 근’의 ‘세’ 따위를 뜻 합니다. [자세히 알아보기](https://ko.dict.naver.com/#/entry/koko/c513782b82554ff499c80ec616c5b611)
 *
 * @param num 숫자를 입력합니다.
 * @param classifier 수관형사를 사용할지 여부를 입력합니다. 기본값은 false입니다.
 * @return 변환된 수사를 반환합니다.
 *
 * @link https://es-hangul.slash.page/docs/api/susa
 */
public fun susa(num: Int, classifier: Boolean = false): String {
    validateNumber(num)
    return if (classifier) getClassifierWord(num) else getNumberWord(num)
}

private fun getClassifierWord(num: Int): String {
    if (num == 20) return SUSA_CLASSIFIER_MAP[num]!!

    val tens = num / 10 * 10
    val ones = num % 10

    val tensWord = SUSA_MAP[tens] ?: ""
    val onesWord = SUSA_CLASSIFIER_MAP[ones] ?: SUSA_MAP[ones] ?: ""

    return "${tensWord}${onesWord}"
}

private fun validateNumber(num: Int) {
    if (num <= 0 || num > 100) {
        throw AssertionError("지원하지 않는 숫자입니다.")
    }
}

private fun getNumberWord(num: Int): String {
    if (num == 100) return SUSA_MAP[100]!!

    val tens = num / 10 * 10
    val ones = num % 10

    val tensWord = SUSA_MAP[tens] ?: ""
    val onesWord = SUSA_MAP[ones] ?: ""

    return "${tensWord}${onesWord}"
}