package dev.jombi.hangulkt

private val QWERTY_KEYBOARD_MAP = mapOf(
    'q' to 'ㅂ',
    'Q' to 'ㅃ',
    'w' to 'ㅈ',
    'W' to 'ㅉ',
    'e' to 'ㄷ',
    'E' to 'ㄸ',
    'r' to 'ㄱ',
    'R' to 'ㄲ',
    't' to 'ㅅ',
    'T' to 'ㅆ',
    'y' to 'ㅛ',
    'Y' to 'ㅛ',
    'u' to 'ㅕ',
    'U' to 'ㅕ',
    'i' to 'ㅑ',
    'I' to 'ㅑ',
    'o' to 'ㅐ',
    'O' to 'ㅒ',
    'p' to 'ㅔ',
    'P' to 'ㅖ',
    'a' to 'ㅁ',
    'A' to 'ㅁ',
    's' to 'ㄴ',
    'S' to 'ㄴ',
    'd' to 'ㅇ',
    'D' to 'ㅇ',
    'f' to 'ㄹ',
    'F' to 'ㄹ',
    'g' to 'ㅎ',
    'G' to 'ㅎ',
    'h' to 'ㅗ',
    'H' to 'ㅗ',
    'j' to 'ㅓ',
    'J' to 'ㅓ',
    'k' to 'ㅏ',
    'K' to 'ㅏ',
    'l' to 'ㅣ',
    'L' to 'ㅣ',
    'z' to 'ㅋ',
    'Z' to 'ㅋ',
    'x' to 'ㅌ',
    'X' to 'ㅌ',
    'c' to 'ㅊ',
    'C' to 'ㅊ',
    'v' to 'ㅍ',
    'V' to 'ㅍ',
    'b' to 'ㅠ',
    'B' to 'ㅠ',
    'n' to 'ㅜ',
    'N' to 'ㅜ',
    'm' to 'ㅡ',
    'M' to 'ㅡ',
)

/**
 * 영어 알파벳을 qwerty 자판과 매칭되는 한글 음소로 변환합니다.
 * 
 * @param word 한글 음소로 변환하고자 하는 영문
 * @return 영어 알파벳이 포함되지 않은 한글 음소, 음절, 기타 문자로 이루어진 문자열
 */
public fun convertQwertyToAlphabet(word: String): String {
    return word.map { if (it in QWERTY_KEYBOARD_MAP) QWERTY_KEYBOARD_MAP[it] else it }.joinToString("")
}
