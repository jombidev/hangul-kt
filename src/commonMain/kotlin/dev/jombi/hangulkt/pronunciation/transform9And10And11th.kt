@file:Suppress("LocalVariableName")

package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar

/**
 * 제9, 10항, 11항을 적용합니다.
 *
 * > 제9항 - 받침 ‘ㄲ, ㅋ’, ‘ㅅ, ㅆ, ㅈ, ㅊ, ㅌ’, ‘ㅍ’은 어말 또는 자음 앞에서 각각 대표음 [ㄱ, ㄷ, ㅂ]으로 발음한다.
 * > 제10항 - 겹받침 ‘ㄳ’, ‘ㄵ’, ‘ㄼ, ㄽ, ㄾ’, ‘ㅄ’은 어말 또는 자음 앞에서 각각 [ㄱ, ㄴ, ㄹ, ㅂ]으로 발음한다.
 * > 제11항 - 겹받침 ‘ㄺ, ㄻ, ㄿ’은 어말 또는 자음 앞에서 각각 [ㄱ, ㅁ, ㅂ]으로 발음한다.
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 */
public fun transform9And10And11th(
    currentSyllable: HangulChar,
    nextSyllable: HangulChar?,
): HangulChar {
    var current = currentSyllable

    val is어말 = current.jongseong.isNotEmpty() && nextSyllable == null
    val is음가있는자음앞 = current.jongseong.isNotEmpty() && nextSyllable?.choseong != PronounConstants.음가가_없는_자음

    val 제9_10_11항주요조건 = (is어말 || is음가있는자음앞) && current.jongseong in PronounConstants.받침_대표음_발음

    if (제9_10_11항주요조건) {
        current = current.copy(
            jongseong = PronounConstants.받침_대표음_발음[current.jongseong]!!
        )
    }

    return current

}