@file:Suppress("ObjectPropertyName")

package dev.jombi.hangulkt

private val 중성_알파벳_발음 = mapOf(
    // ------- 단모음
    'ㅏ' to "a",
    'ㅓ' to "eo",
    'ㅗ' to "o",
    'ㅜ' to "u",
    'ㅡ' to "eu",
    'ㅣ' to "i",
    'ㅐ' to "ae",
    'ㅔ' to "e",
    'ㅚ' to "oe",
    'ㅟ' to "wi",
    // -------
    // ------- 이중모음
    'ㅑ' to "ya",
    'ㅕ' to "yeo",
    'ㅛ' to "yo",
    'ㅠ' to "yu",
    'ㅒ' to "yae",
    'ㅖ' to "ye",
    'ㅘ' to "wa",
    'ㅙ' to "wae",
    'ㅝ' to "wo",
    'ㅞ' to "we",
    'ㅢ' to "ui",
)

private val 초성_알파벳_발음 = mapOf(
    // ------- 파열음
    'ㄱ' to "g",
    'ㄲ' to "kk",
    'ㅋ' to "k",
    'ㄷ' to "d",
    'ㄸ' to "tt",
    'ㅌ' to "t",
    'ㅂ' to "b",
    'ㅃ' to "pp",
    'ㅍ' to "p",
    // -------
    // ------- 파찰음
    'ㅈ' to "j",
    'ㅉ' to "jj",
    'ㅊ' to "ch",
    // -------
    // ------- 마찰음
    'ㅅ' to "s",
    'ㅆ' to "ss",
    'ㅎ' to "h",
    // -------
    // ------- 비음
    'ㄴ' to "n",
    'ㅁ' to "m",
    'ㅇ' to "",
    // -------
    // ------- 유음
    'ㄹ' to "r",
)

private val 종성_알파벳_발음 = mapOf(
    "ㄱ" to "k",
    "ㄴ" to "n",
    "ㄷ" to "t",
    "ㄹ" to "l",
    "ㅁ" to "m",
    "ㅂ" to "p",
    "ㅇ" to "ng",
    "" to "",
)

/**
 * 주어진 한글 문자열을 로마자로 변환합니다.
 * 
 * @param hangul 한글 문자열을 입력합니다.
 * @return 변환된 로마자를 반환합니다.
 */
public fun romanize(hangul: String): String {
    val changedHangul = standardizePronunciation(hangul, shouldHardConversion = false)

    return changedHangul
        .mapIndexed { index, _ -> romanizeSyllableHangul(changedHangul, index) }
        .joinToString("")
}

private fun romanizeSyllableHangul(arrayHangul: String, index: Int): String {
    val syllable = arrayHangul[index]

    if (syllable.isFullHangul()) {
        val disassemble = disassembleCompleteCharacter(syllable)!!

        var choseong = 초성_알파벳_발음[disassemble.choseong]!!
        val jungseong = 중성_알파벳_발음[assemble(disassemble.jungseong)[0]]!!
        val jongseong = 종성_알파벳_발음[disassemble.jongseong]!!

        // 'ㄹ'은 모음 앞에서는 'r'로, 자음 앞이나 어말에서는 'l'로 적는다. 단, 'ㄹㄹ'은 'll'로 적는다. (ex.울릉, 대관령),
        if (disassemble.choseong == 'ㄹ' && index > 0 && arrayHangul[index - 1].isFullHangul()) {
            val prevDisassemble = disassembleCompleteCharacter(arrayHangul[index - 1])

            if (prevDisassemble?.jongseong == "ㄹ") {
                choseong = "l"
            }
        }

        return choseong + jungseong + jongseong
    }

    if (syllable in 중성_알파벳_발음) {
        return 중성_알파벳_발음[syllable]!!
    }

    if (canBeChoseong(syllable)) {
        return 초성_알파벳_발음[syllable]!!
    }

    return syllable.toString()
}