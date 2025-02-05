package dev.jombi.hangulkt

import dev.jombi.hangulkt.hangul.HangulChar

/**
 * 인자로 한글 객체를 받아 하나의 한글 문자를 반환합니다.
 *
 * @param char 분해된 한글 객체
 * @return 조합된 한글 글자
 */
public fun combineCharacter(char: HangulChar): Char = combineCharacter(char.choseong, char.jungseong, char.jongseong)

/**
 * 인자로 초성, 중성, 종성을 받아 하나의 한글 문자를 반환합니다.
 *
 * @param choseong 초성
 * @param jungseong 조합된 중성 한글자
 * @param jongseong 조합된 종성 한글자
 * @return 조합된 한글 글자
 */
public fun combineCharacter(choseong: Char, jungseong: Char, jongseong: Char? = null): Char {
    return combineCharacter(choseong, disassemble(jungseong.toString()), disassemble(jongseong?.toString() ?: ""))
}

/**
 * 인자로 초성, 중성, 종성을 받아 하나의 한글 문자를 반환합니다.
 *
 * @param choseong 초성
 * @param jungseong 분해된 중성 문자열
 * @param jongseong 분해된 종성 문자열
 * @return 조합된 한글 글자
 */
public fun combineCharacter(choseong: Char, jungseong: String, jongseong: String = ""): Char {
    if (!canBeChoseong(choseong) || !canBeJungseong(jungseong) || !canBeJongseong(jongseong)) {
        throw AssertionError("Invalid hangul Characters: $choseong, $jungseong, $jongseong")
    }

    val numOfJungseongs = Constants.JUNGSEONGS.size
    val numOfJongseongs = Constants.JONGSEONGS.size

    val choseongIndex = Constants.CHOSEONGS.indexOf(choseong)
    val jungseongIndex = Constants.JUNGSEONGS.indexOf(jungseong)
    val jongseongIndex = Constants.JONGSEONGS.indexOf(jongseong)

    val choseongOfTargetConsonant = choseongIndex * numOfJungseongs * numOfJongseongs
    val choseongOfTargetVowel = jungseongIndex * numOfJongseongs

    val unicode =
        Constants.COMPLETE_HANGUL_START_CODE + choseongOfTargetConsonant + choseongOfTargetVowel + jongseongIndex

    return unicode.toChar()
}
