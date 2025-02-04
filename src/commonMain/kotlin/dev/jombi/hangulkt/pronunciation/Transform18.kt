package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.MutableHangulChar

/**
 * 제18항을 적용합니다.
 *
 * > 18항 - 받침 ‘ㄱ(ㄲ, ㅋ, ㄳ, ㄺ), ㄷ(ㅅ, ㅆ, ㅈ, ㅊ, ㅌ, ㅎ), ㅂ(ㅍ, ㄼ, ㄿ, ㅄ)’은 ‘ㄴ, ㅁ’ 앞에서 [ㅇ, ㄴ, ㅁ]으로 발음한다.
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 */
@Suppress("KDocUnresolvedReference") // this object can be invoked directly using 'operator fun ITransformer.invoke'.
public object Transform18 : ITransformer {
    public override fun transform(
        currentSyllable: MutableHangulChar,
        nextSyllable: MutableHangulChar,
        phrase: String,
        index: Int,
    ) {
        val 제18항주요조건 = currentSyllable.hasJongseong() && nextSyllable.choseong in arrayOf('ㄴ', 'ㅁ')
        if (제18항주요조건) {
            handle비음화ㅇ(currentSyllable)
            handle비음화ㄴ(currentSyllable)
            handle비음화ㅁ(currentSyllable)
        }
    }

    private fun handle비음화ㅇ(current: MutableHangulChar) {
        if (current.jongseong in PronounConstants.비음화_받침_ㅇ_변환) {
            current.jongseong = "ㅇ"
        }
    }

    private fun handle비음화ㄴ(current: MutableHangulChar) {
        if (current.jongseong in PronounConstants.비음화_받침_ㄴ_변환) {
            current.jongseong = "ㄴ"
        }
    }

    private fun handle비음화ㅁ(current: MutableHangulChar) {
        if (current.jongseong in PronounConstants.비음화_받침_ㅁ_변환) {
            current.jongseong = "ㅁ"
        }
    }
}