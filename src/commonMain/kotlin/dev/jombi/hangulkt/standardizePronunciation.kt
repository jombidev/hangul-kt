package dev.jombi.hangulkt

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.toMutable
import dev.jombi.hangulkt.pronunciation.*


/**
 * 주어진 한글 문자열을 표준 발음으로 변환합니다.
 *
 * @param hangul 한글 문자열을 입력합니다.
 * @param shouldHardConversion 경음화 등의 된소리를 적용할지 여부를 설정합니다. 기본값은 true입니다.
 * @return 변환된 표준 발음 문자열을 반환합니다.
 */
public fun standardizePronunciation(hangul: String, shouldHardConversion: Boolean = true): String {
    if (hangul.isEmpty() || hangul.isBlank()) return ""

    val processSyllables = { syllables: List<HangulChar>, phrase: String ->
        syllables.toMutableList().let {
            it.mapIndexed { index, currentSyllable ->
                val nextSyllable = if (index < syllables.lastIndex) syllables[index + 1] else null

                val (current, next) = applyRules(currentSyllable, nextSyllable, index, phrase, shouldHardConversion)

                if (next != null) {
                    it[index + 1] = next
                }

                current
            }
        }
    }

    return hangul
        .split(' ')
        .joinToString(" ") {
            val (disassembleHangul, notHangulPhrase) = 음절분해(it)
            val processedSyllables = processSyllables(disassembleHangul, it)

            assembleChangedHangul(processedSyllables, notHangulPhrase)
        }
}

private fun 음절분해(hangulPhrase: String): Pair<List<HangulChar>, List<NotHangul>> {
    val notHangulPhrase: MutableList<NotHangul> = arrayListOf()
    val disassembleHangul = hangulPhrase
        .filterIndexed { index, syllable ->
            if (!syllable.isFullHangul() || syllable.isHangulAlphabet()) {
                notHangulPhrase.add(
                    NotHangul(
                        index,
                        syllable,
                    )
                )
                false
            } else true
        }
        .map(::disassembleCompleteCharacter)
        .filterNotNull()

    return disassembleHangul to notHangulPhrase.toList()
}

private data class NotHangul(val index: Int, val syllable: Char)

private fun applyRules(
    currentSyllable: HangulChar,
    nextSyllable: HangulChar?,
    index: Int,
    phrase: String,
    shouldHardConversion: Boolean,
): NullableReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable?.toMutable()

    if (next != null && shouldHardConversion) {
        TransformHardConversion.transform(current, next, phrase, index)
    }

    if (next != null) {
        val transformers = arrayOf(Transform16, Transform17, Transform19, TransformNLAssimilation, Transform18, Transform20)
        transformers.forEach {
            it.transform(current, next, phrase, index)
        }
    }

    Transform12.transform(current, next)

    if (next != null) {
        Transform13And14.transform(current, next, phrase, index)
    }

    Transform9And10And11.transform(current, next)

    return current and next
}

private fun assembleChangedHangul(disassembleHangul: List<HangulChar>, notHangulPhrase: List<NotHangul>): String {
    val changedSyllables = disassembleHangul
        .map(::combineCharacter)
        .toMutableList()

    for ((index, syllable) in notHangulPhrase) {
        changedSyllables.add(index, syllable)
    }

    return changedSyllables.joinToString("")
}