package dev.jombi.hangulkt

private val HANGUL_REGEX = Regex("^[가-힣]$")
private val HANGUL_ALPHABET_REGEX = Regex("^[ㄱ-ㅣ]$")
private val FULL_HANGUL_REGEX = Regex("^[가-힣ㄱ-ㅣ\\s]+$")

/**
 * isFullHangul은 문자가 조합/완성 된 한글 문자인지 판별한다.
 *
 * @return 완성된 한글 문자를 받으면 true를 반환
 */
public fun Char.isFullHangul(): Boolean = HANGUL_REGEX.matches(toString())

/**
 * isHangulAlphabet은 문자가 조합되지않은 한글 문자인지 판별한다.
 *
 * @return 조합되지않은 한글 문자를 받으면 true를 반환
 */
public fun Char.isHangulAlphabet(): Boolean = HANGUL_ALPHABET_REGEX.matches(toString())

/**
 * isHangul은 문자열이 한글인지 판별한다.
 *
 * @return 한글 문자열을 받으면 true를 반환
 */
public fun CharSequence.isHangul(): Boolean = FULL_HANGUL_REGEX.matches(this)

/**
 * assertHangul은 값이 한글인지 검사한다.
 * 검사에 실패하면 예외가 발생한다.
 *
 * @throws AssertionError with message
 */
public fun assertHangul(actual: Any?, message: String? = null) {
    val error = AssertionError(message ?: "$actual is not a valid hangul string")

    if (actual !is CharSequence)
        throw error

    if (!actual.isHangul())
        throw error
}

/**
 * parseHangul은 값이 한글인지 검사한다.
 * 검사에 실패하면 예외가 발생한다.
 *
 * @throws AssertionError with message
 * @return 한글인 문자열
 */
public fun parseHangul(actual: Any): String {
    assertHangul(actual)
    return actual as String
}

public fun parseHangulSafe(actual: Any): Result<String> {
    try {
        val parsed = parseHangul(actual)
        return Result.success(parsed)
    } catch (e: AssertionError) {
        return Result.failure(e)
    }
}
