package dev.jombi.hangulkt

import dev.jombi.hangulkt.hangul.HangulChar

/**
 * 완전한 한글 문자열을 초성, 중성, 종성으로 분리합니다.
 * 완전한 한글이 아닐 경우, null을 반환합니다.
 *
 * @param letter 분리하고자 하는 완전한 한글 문자열
 * @return 한글 객체
 */
public fun disassembleCompleteCharacter(letter: Char): HangulChar? {
    val charCode = letter.code

    val isCompleteHangul = Constants.COMPLETE_HANGUL_START_CODE <= charCode && charCode <= Constants.COMPLETE_HANGUL_END_CODE

    if (!isCompleteHangul)
        return null

    val hangulCode = charCode - Constants.COMPLETE_HANGUL_START_CODE

    val jongseongIndex = hangulCode % Constants.NUMBER_OF_JONGSEONG
    val jungseongIndex = ((hangulCode - jongseongIndex) / Constants.NUMBER_OF_JONGSEONG) % Constants.NUMBER_OF_JUNGSEONG
    val choseongIndex = (hangulCode - jongseongIndex) / Constants.NUMBER_OF_JONGSEONG / Constants.NUMBER_OF_JUNGSEONG

    
    return HangulChar(
        Constants.CHOSEONGS[choseongIndex],
        Constants.JUNGSEONGS[jungseongIndex],
        Constants.JONGSEONGS[jongseongIndex],
    )
}
