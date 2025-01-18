package dev.jombi.hangulkt

/**
 * 인자로 받은 문자가 초성으로 위치할 수 있는 문자인지 검사합니다.
 * 
 * @param char 초성인지 검사 할 문자
 */
public fun canBeChoseong(char: Char): Boolean = char in Constants.CHOSEONGS
