package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import dev.jombi.hangulkt.hangul.toMutable

/**
 * 'ㄴ,ㄹ'이 덧나는 경우(동화작용)를 적용합니다.
 *
 * > 합성어에서 둘째 요소가 ‘야, 여, 요, 유, 얘, 예’ 등으로 시작되는 말이면 ‘ㄴ, ㄹ’이 덧난다
 *
 * * https://www.youtube.com/watch?v=Mm2JX2naqWk
 * * http://contents2.kocw.or.kr/KOCW/data/document/2020/seowon/choiyungon0805/12.pdf
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 */
public fun transformNLAssimilation(currentSyllable: HangulChar, nextSyllable: HangulChar): ReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable.toMutable()

    val ㄴㄹ이덧나는조건 = current.hasJongseong() && next.choseong == 'ㅇ' && next.jungseong in PronounConstants.ㄴㄹ이_덧나는_후속음절_모음

    if (ㄴㄹ이덧나는조건) {
        applyㄴㄹ덧남(current, next)
    }
    return current and next
}

private fun applyㄴㄹ덧남(current: MutableHangulChar, next: MutableHangulChar) {
    if (current.jungseong in PronounConstants.ㄴㄹ이_덧나는_모음) {
        if (current.jongseong in PronounConstants.ㄴㄹ이_덧나서_받침_ㄴ_변환) {
            current.jongseong = if (current.jongseong == "ㄱ") "ㅇ" else current.jongseong
            next.choseong = 'ㄴ'
        }

        if (current.jongseong in PronounConstants.ㄴㄹ이_덧나서_받침_ㄹ_변환) {
            next.choseong = 'ㄹ'
        }
    } else {
        // ㄴ/ㄹ이 되기 위한 조건이지만 현재 음절의 중성의 ∙(아래아)가 하나가 아닐 경우에는 덧나지 않고 연음규칙이 적용된다
        next.choseong = current.jongseong[0]
    }
}