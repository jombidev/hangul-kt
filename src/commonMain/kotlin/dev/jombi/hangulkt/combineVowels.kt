package dev.jombi.hangulkt

/**
 * 인자로 두 개의 모음을 받아 합성하여 겹모음을 생성합니다. 만약 올바른 한글 규칙으로 합성할 수 없는 모음들이라면 null을 반환합니다.
 * 
 * @param vowel1 첫 번째 모음
 * @param vowel2 두 번째 모음
 * @return 조합된 겹모음, 또는 null
 */
public fun combineVowelsOrNull(vowel1: String, vowel2: String): String? {
    val merged = "$vowel1$vowel2"
    return Constants.DISASSEMBLED_VOWELS_BY_VOWEL
        .toList()
        .firstOrNull { it.second == merged }
        ?.first
        ?.toString()
}

/**
 * 인자로 두 개의 모음을 받아 합성하여 겹모음을 생성합니다. 만약 올바른 한글 규칙으로 합성할 수 없는 모음들이라면 null을 반환합니다.
 *
 * @param vowel1 첫 번째 모음
 * @param vowel2 두 번째 모음
 * @return 조합된 겹모음, 또는 null
 */
public fun combineVowelsOrNull(vowel1: Char, vowel2: Char): String? = combineVowelsOrNull("$vowel1", "$vowel2")

/**
 * 인자로 두 개의 모음을 받아 합성하여 겹모음을 생성합니다. 만약 올바른 한글 규칙으로 합성할 수 없는 모음들이라면 단순 Join합니다.
 *
 * @param vowel1 첫 번째 모음
 * @param vowel2 두 번째 모음
 * @return 조합된 겹모음, 또는 단순 Join된 문자열
 */
public fun combineVowels(vowel1: String, vowel2: String): String {
    return combineVowelsOrNull(vowel1, vowel2) ?: "$vowel1$vowel2"
}

/**
 * 인자로 두 개의 모음을 받아 합성하여 겹모음을 생성합니다. 만약 올바른 한글 규칙으로 합성할 수 없는 모음들이라면 단순 Join합니다.
 *
 * @param vowel1 첫 번째 모음
 * @param vowel2 두 번째 모음
 * @return 조합된 겹모음, 또는 단순 Join된 문자열
 */
public fun combineVowels(vowel1: Char, vowel2: Char): String = combineVowels("$vowel1", "$vowel2")
