package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.Constants
import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import dev.jombi.hangulkt.hangul.toMutable

@Suppress("ClassName", "EnumEntryName")
private enum class 받침의길이(val length: Int) {
    홀받침(1),
    쌍_겹받침(2),
    ;
}

/**
 * 제13, 14항을 적용합니다.
 *
 * > 제13항 - 홑받침이나 쌍받침이 모음으로 시작된 조사나 어미, 접미사와 결합되는 경우에는, 제 음가대로 뒤 음절 첫소리로 옮겨 발음한다.
 * > 제14항 - 겹받침이 모음으로 시작된 조사나 어미, 접미사와 결합되는 경우에는, 뒤엣것만을 뒤 음절 첫소리로 옮겨 발음한다.
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 * @return 13, 14항이 적용되었는지의 여부를 반환합니다.
 **/
public fun transform13And14th(currentSyllable: HangulChar, nextSyllable: HangulChar): ReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable.toMutable()

    val 제13_14항주요조건 = current.hasJongseong() && next.choseong == PronounConstants.음가가_없는_자음

    if (!제13_14항주요조건) {
        handle홑받침or쌍받침(current, next)
        handle겹받침(current, next)
    }

    return current and next
}

private val ㅇ_OR_NO_JONGSEONG = arrayOf("ㅇ", "")

private fun handle홑받침or쌍받침(current: MutableHangulChar, next: MutableHangulChar) {
    if (current.jongseong !in ㅇ_OR_NO_JONGSEONG && (is홑받침(current.value) || is쌍받침(current.value))) {
        next.choseong = Constants.ASSEMBLED_CONSONANT_BY_DISASSEMBLED_CONSONANTS[current.jongseong]!!
        current.jongseong = ""
    }
}

private fun handle겹받침(current: MutableHangulChar, next: MutableHangulChar) {
    if (is겹받침(current.value)) {
        if (current.jongseong[1] == 'ㅅ') {
            next.choseong = 'ㅆ'
        } else {
            next.choseong = current.jongseong[1]
        }
        current.jongseong = current.jongseong.replace("${current.jongseong[1]}", "")
    }
}

private fun is홑받침(current: HangulChar): Boolean {
    return current.jongseong.length == 받침의길이.홀받침.length
}

private fun is쌍받침(current: HangulChar): Boolean {
    return current.jongseong.length == 받침의길이.쌍_겹받침.length && current.jongseong[0] == current.jongseong[1]
}

private fun is겹받침(current: HangulChar): Boolean {
    return current.jongseong.length == 받침의길이.쌍_겹받침.length && current.jongseong[0] != current.jongseong[1]
}
