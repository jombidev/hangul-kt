package dev.jombi.hangulkt

/**
 * 인자로 받은 문자가 중성으로 위치할 수 있는 문자인지 검사합니다.
 * 
 * @param char 중성인지 검사 할 문자(열)
 */
public fun canBeJungseong(char: Char): Boolean = "$char" in Constants.JUNGSEONGS

/**
 * 인자로 받은 문자가 중성으로 위치할 수 있는 문자인지 검사합니다.
 *
 * @param char 중성인지 검사 할 문자(열)
 */
public fun canBeJungseong(char: String): Boolean = char in Constants.JUNGSEONGS
