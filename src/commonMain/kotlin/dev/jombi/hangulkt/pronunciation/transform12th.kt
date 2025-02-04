package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import dev.jombi.hangulkt.hangul.toMutable

/**
 * 제12항을 적용합니다.
 *
 * > 제12항 받침 ‘ㅎ’의 발음은 다음과 같다.
 * > ‘ㅎ(ㄶ, ㅀ)’ 뒤에 ‘ㄱ, ㄷ, ㅈ’이 결합되는 경우에는, 뒤 음절 첫소리와 합쳐서 [ㅋ, ㅌ, ㅊ]으로 발음한다.
 * 
 * > (붙임) 받침 ‘ㄱ(ㄺ), ㄷ, ㅂ(ㄼ), ㅈ(ㄵ)’이 뒤 음절 첫소리 ‘ㅎ’과 결합되는 경우에도, 역시 두 음을 합쳐서 [ㅋ, ㅌ, ㅍ, ㅊ]으로 발음한다.
 * 
 * > ‘ㅎ(ㄶ, ㅀ)’ 뒤에 ‘ㅅ’이 결합되는 경우에는, ‘ㅅ’을 (ㅆ)으로 발음한다.
 * > ‘ㅎ’ 뒤에 ‘ㄴ’이 결합되는 경우에는, (ㄴ)으로 발음한다.
 * 
 * > (붙임) ‘ㄶ, ㅀ’ 뒤에 ‘ㄴ’이 결합되는 경우에는, ‘ㅎ’을 발음하지 않는다.
 * 
 * > ‘ㅎ(ㄶ, ㅀ)’ 뒤에 모음으로 시작된 어미나 접미사가 결합되는 경우에는, ‘ㅎ’을 발음하지 않는다.
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 */
public fun transform12th(currentSyllable: HangulChar, nextSyllable: HangulChar?): NullableReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable?.toMutable()

    if (!current.hasJongseong())
        return current and next

    if (current.jongseong in PronounConstants.발음변환_받침_ㅎ) {
        if (next != null) {
            handleNextChoseongIsㄱㄷㅈㅅ(current, next)
            handleNextChoseongIsㄴ(current, next)
            handleNextChoseongIsㅇ(current, next)
        } else {
            handleCurrentJongseongIsㅇ(current)
        }
    }

    handleNextChoseongIsㅎ(current, next)

    return current and next
}

private fun handleNextChoseongIsㄱㄷㅈㅅ(current: MutableHangulChar, next: MutableHangulChar) {
    if (next.choseong in PronounConstants.발음변환_받침_ㅎ_발음) {
        next.choseong = PronounConstants.발음변환_받침_ㅎ_발음[next.choseong]!!
        current.replace받침ㅎ()
    }
}

private fun handleNextChoseongIsㄴ(current: MutableHangulChar, next: MutableHangulChar) {
    if (next.choseong == 'ㄴ' && current.jongseong in listOf("ㄴㅎ", "ㄹㅎ")) {
        current.replace받침ㅎ()
    }
}

private fun handleNextChoseongIsㅇ(current: MutableHangulChar, next: MutableHangulChar) {
    if (next.choseong == PronounConstants.음가가_없는_자음) {
        if (current.jongseong in listOf("ㄴㅎ", "ㄹㅎ")) {
            current.replace받침ㅎ()
        } else {
            current.jongseong = ""
        }
    } else {
        current.replace받침ㅎ()
    }
}

private fun handleCurrentJongseongIsㅇ(current: MutableHangulChar) {
    current.replace받침ㅎ()
}

private fun handleNextChoseongIsㅎ(current: MutableHangulChar, next: MutableHangulChar?) {
    if (current.jongseong in PronounConstants.발음변환_첫소리_ㅎ && next?.choseong == 'ㅎ') {
        next.choseong = PronounConstants.발음변환_첫소리_ㅎ_발음[current.jongseong]!!

        if (current.jongseong.length == 1) {
            current.jongseong = ""
        } else {
            current.jongseong = current.jongseong[0].toString()
        }
    }
}