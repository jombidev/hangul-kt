package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import dev.jombi.hangulkt.hangul.toMutable

/**
 * 제16항을 적용합니다.
 *
 * > 제16항 - 한글 자모의 이름은 그 받침소리를 연음하되, ‘ㄷ, ㅈ, ㅊ, ㅋ, ㅌ, ㅍ, ㅎ’의 경우에는 특별히 다음과 같이 발음한다. ㄷ, ㅈ, ㅊ, ㅌ, ㅎ > ㅅ (디귿이:디그시, 지읒이:지으시, 치읓이:치으시, 티읕이:티으시, 히읗이:히으시), ㅋ > ㄱ (키읔이:키으기), ㅍ > ㅂ (피읖이:피으비)
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 * @param phrase 분리되지 않은 한글 구절을 입력합니다.
 * @param index 현재 음절의 순서를 입력합니다.
 * @return 16항이 적용되었는지의 여부를 반환합니다.
 */
public fun transform16th(currentSyllable: HangulChar, nextSyllable: HangulChar, phrase: String, index: Int): ReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable.toMutable()

    val 제16항주요조건 = current.hasJongseong() && next.choseong == PronounConstants.음가가_없는_자음

    if (!제16항주요조건)
        return current and next

    val combinedSyllables = "${phrase[index - 1]}${phrase[index]}"

    handleSpecialHangulCharacters(current, next, combinedSyllables)
    handleHangulCharacters(current, next, combinedSyllables)

    return current and next
}

private fun handleSpecialHangulCharacters(current: MutableHangulChar, next: MutableHangulChar, combinedSyllables: String) {
    if (combinedSyllables in PronounConstants.특별한_한글_자모) {
        val 다음_음절의_초성 = PronounConstants.특별한_한글_자모의_발음[current.jongseong]

        current.jongseong = ""
        next.choseong = 다음_음절의_초성!!
    }
}

private fun handleHangulCharacters(current: MutableHangulChar, next: MutableHangulChar, combinedSyllables: String) {
    if (combinedSyllables in PronounConstants.한글_자모) {
        next.choseong = current.jongseong[0] // 종성이 있으며, 한글자인것이 확정

        if (current.jongseong != "ㅇ") {
            current.jongseong = ""
        }
    }
}