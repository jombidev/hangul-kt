package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import dev.jombi.hangulkt.hangul.toMutable


/**
 * 제20항을 적용합니다.
 *
 * > 20항 - ‘ㄴ’은 ‘ㄹ’의 앞이나 뒤에서 'ㄹ'로 발음한다.
 * > (붙임) 첫소리 ‘ㄴ’이 ‘ㅀ’, ‘ㄾ’ 뒤에 연결되는 경우에도 이에 준한다.
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 */
public fun transform20th(currentSyllable: HangulChar, nextSyllable: HangulChar): ReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable.toMutable()

    applyMainCondition(current, next)
    applySupplementaryCondition(current, next)

    return current and next
}

private fun applyMainCondition(current: MutableHangulChar, next: MutableHangulChar) {
    if (current.jongseong == "ㄴ" && next.choseong == 'ㄹ') {
        current.jongseong = "ㄹ"
    }
}

private fun applySupplementaryCondition(current: MutableHangulChar, next: MutableHangulChar) {
    if (next.choseong == 'ㄴ' && (current.jongseong == "ㄹ" || current.jongseong in arrayOf("ㄹㅎ", "ㄹㅌ"))) {
        next.choseong = 'ㄹ'
    }
}