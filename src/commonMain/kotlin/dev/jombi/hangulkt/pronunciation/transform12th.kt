package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar

/**
 * 제12항을 적용합니다.
 * 
 * > 제12항 받침 ‘ㅎ’의 발음은 다음과 같다.
 * > ‘ㅎ(ㄶ, ㅀ)’ 뒤에 ‘ㄱ, ㄷ, ㅈ’이 결합되는 경우에는, 뒤 음절 첫소리와 합쳐서 [ㅋ, ㅌ, ㅊ]으로 발음한다.
 * > (붙임) 받침 ‘ㄱ(ㄺ), ㄷ, ㅂ(ㄼ), ㅈ(ㄵ)’이 뒤 음절 첫소리 ‘ㅎ’과 결합되는 경우에도, 역시 두 음을 합쳐서 [ㅋ, ㅌ, ㅍ, ㅊ]으로 발음한다.
 * > ‘ㅎ(ㄶ, ㅀ)’ 뒤에 ‘ㅅ’이 결합되는 경우에는, ‘ㅅ’을 (ㅆ)으로 발음한다.
 * > ‘ㅎ’ 뒤에 ‘ㄴ’이 결합되는 경우에는, (ㄴ)으로 발음한다.
 * > (붙임) ‘ㄶ, ㅀ’ 뒤에 ‘ㄴ’이 결합되는 경우에는, ‘ㅎ’을 발음하지 않는다.
 * > ‘ㅎ(ㄶ, ㅀ)’ 뒤에 모음으로 시작된 어미나 접미사가 결합되는 경우에는, ‘ㅎ’을 발음하지 않는다.
 * 
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 */
public fun transform12th(currentSyllable: HangulChar, nextSyllable: HangulChar?): NullableReturnSyllables {
    var current = currentSyllable
    var next = nextSyllable

    if (!current.hasJongseong())
        return current to next

    if (current.jongseong in PronounConstants.발음변환_받침_ㅎ) {
        if (next != null) {
            var nextSealed: HangulChar = next

            handleNextChoseongIsㄱㄷㅈㅅ(current, nextSealed).run {
                current = first
                nextSealed = second
            }
            handleNextChoseongIsㄴ(current, nextSealed).run {
                current = first
                nextSealed = second
            }
            handleNextChoseongIsㅇ(current, nextSealed).run {
                current = first
                nextSealed = second
            }
            next = nextSealed
        } else {
            current = handleCurrentJongseongIsㅇ(current)
        }
    }

    handleNextChoseongIsㅎ(current, next).run {
        current = first
        next = second
    }

    return current to next
}

private fun handleNextChoseongIsㄱㄷㅈㅅ(current: HangulChar, next: HangulChar): ReturnSyllables {
    var updatedCurrent = current
    var updatedNext = next

    if (updatedNext.choseong in PronounConstants.발음변환_받침_ㅎ_발음) { // listOf('ㄱ', 'ㄷ', 'ㅈ', 'ㅅ')
        updatedNext = updatedNext.copy(choseong = PronounConstants.발음변환_받침_ㅎ_발음[updatedNext.choseong]!!)
        updatedCurrent = updatedCurrent.replace받침ㅎ()
    }

    return updatedCurrent to updatedNext
}

private fun handleNextChoseongIsㄴ(current: HangulChar, next: HangulChar): ReturnSyllables {
    var updatedCurrent = current

    if (next.choseong == 'ㄴ' && updatedCurrent.jongseong in listOf("ㄴㅎ", "ㄹㅎ")) {
        updatedCurrent = updatedCurrent.replace받침ㅎ()
    }

    return updatedCurrent to next
}

private fun handleNextChoseongIsㅇ(current: HangulChar, next: HangulChar): ReturnSyllables {
    var updatedCurrent = current

    updatedCurrent = if (next.choseong == PronounConstants.음가가_없는_자음) {
        if (updatedCurrent.jongseong in listOf("ㄴㅎ", "ㄹㅎ")) {
            updatedCurrent.replace받침ㅎ()
        } else {
            updatedCurrent.copy(jongseong = "")
        }
    } else {
        updatedCurrent.replace받침ㅎ()
    }
    return updatedCurrent to next
}

private fun handleCurrentJongseongIsㅇ(current: HangulChar): HangulChar {
    return current.replace받침ㅎ()
}

private fun handleNextChoseongIsㅎ(current: HangulChar, next: HangulChar?): NullableReturnSyllables {
    var updatedCurrent = current
    var updatedNext = next

    if (updatedCurrent.jongseong in PronounConstants.발음변환_첫소리_ㅎ && updatedNext?.choseong == 'ㅎ') {
        updatedNext = updatedNext.copy(choseong = PronounConstants.발음변환_첫소리_ㅎ_발음[updatedCurrent.jongseong]!!)

        updatedCurrent = if (updatedCurrent.jongseong.length == 1) {
            updatedCurrent.copy(jongseong = "")
        } else {
            updatedCurrent.copy(jongseong = updatedCurrent.jongseong[0].toString())
        }
    }
    return updatedCurrent to updatedNext
}