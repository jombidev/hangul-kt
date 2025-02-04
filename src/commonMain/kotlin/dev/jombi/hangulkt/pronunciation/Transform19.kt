package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.MutableHangulChar

/**
 * 제19항을 적용합니다.
 *
 * > 19항 - 받침 ‘ㅁ, ㅇ’ 뒤에 연결되는 ‘ㄹ’은 'ㄴ'으로 발음한다.
 *
 * > (붙임) 받침 ‘ㄱ, ㅂ’ 뒤에 연결되는 ‘ㄹ’도 'ㄴ'으로 발음한다.
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 */
@Suppress("KDocUnresolvedReference") // this object can be invoked directly using 'operator fun ITransformer.invoke'.
public object Transform19 : ITransformer {
    public override fun transform(
        currentSyllable: MutableHangulChar,
        nextSyllable: MutableHangulChar,
        phrase: String,
        index: Int,
    ) {
        val 제19항조건 = currentSyllable.jongseong in PronounConstants.자음동화_받침_ㄴ_변환 && nextSyllable.choseong == 'ㄹ'

        if (제19항조건) {
            nextSyllable.choseong = 'ㄴ'
        }
    }
}