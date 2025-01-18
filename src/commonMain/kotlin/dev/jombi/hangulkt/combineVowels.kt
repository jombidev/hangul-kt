package dev.jombi.hangulkt

public fun combineVowelsOrNull(vowel1: String, vowel2: String): String? {
    val merged = "$vowel1$vowel2"
    return Constants.DISASSEMBLED_VOWELS_BY_VOWEL
        .toList()
        .firstOrNull { it.second == merged }
        ?.first
        ?.toString()
}

public fun combineVowelsOrNull(vowel1: Char, vowel2: Char): String? = combineVowelsOrNull("$vowel1", "$vowel2")
public fun combineVowels(vowel1: String, vowel2: String): String {
    return combineVowelsOrNull(vowel1, vowel2) ?: "$vowel1$vowel2"
}

public fun combineVowels(vowel1: Char, vowel2: Char): String = combineVowels("$vowel1", "$vowel2")
