package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import dev.jombi.hangulkt.hangul.toMutable

/**
 * 제17항을 적용합니다.
 *
 * > 17항 - 받침 ‘ㄷ', 'ㅌ(ㄾ)’이 조사나 접미사의 모음 ‘ㅣ’와 결합되는 경우에는, [ㅈ, ㅊ]으로 바꾸어서 뒤 음절 첫소리로 옮겨 발음한다.
 *
 * > 붙임: ‘ㄷ’ 뒤에 접미사 ‘히’가 결합되어 ‘티’를 이루는 것은 '치'로 발음한다.
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 * @return 17항이 적용되었는지의 여부를 반환합니다.
 */
public fun transform17th(currentSyllable: HangulChar, nextSyllable: HangulChar): ReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable.toMutable()

    val 제17항주요조건 = next.jungseong == "ㅣ"

    if (제17항주요조건) {
        handleChoseongIsㅇ(current, next)
        handleChoseongIsㅎAndㄷ(current, next)
    }

    return current and next
}

private fun handleChoseongIsㅇ(current: MutableHangulChar, next: MutableHangulChar) {
    if (next.choseong == 'ㅇ' && current.jongseong in PronounConstants.음의_동화_받침) {
        next.choseong = PronounConstants.음의_동화_받침[current.jongseong]!!
        current.jongseong = if (current.jongseong == "ㄹㅌ") "ㄹ" else ""
    }
}

private fun handleChoseongIsㅎAndㄷ(current: MutableHangulChar, next: MutableHangulChar) {
    if (next.choseong == 'ㅎ' && current.jongseong == "ㄷ") {
        next.choseong = 'ㅊ'
        current.jongseong = ""
    }
}