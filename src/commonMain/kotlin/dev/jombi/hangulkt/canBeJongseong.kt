package dev.jombi.hangulkt

/**
 * 인자로 받은 문자가 종성으로 위치할 수 있는 문자인지 검사합니다.
 * 
 * @param char 종성인지 검사 할 문자(열)
 */
public fun canBeJongseong(char: String): Boolean = char in Constants.JONGSEONGS

/**
 * 인자로 받은 문자가 종성으로 위치할 수 있는 문자인지 검사합니다.
 *
 * @param char 종성인지 검사 할 문자(열)
 */
public fun canBeJongseong(char: Char): Boolean = "$char" in Constants.JONGSEONGS
