@file:Suppress("LocalVariableName")

package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.MutableHangulChar

/**
 * 제6장 경음화를 적용합니다.
 *
 * > 제23항 - 받침 ‘ㄱ(ㄲ, ㅋ, ㄳ, ㄺ), ㄷ(ㅅ, ㅆ, ㅈ, ㅊ, ㅌ), ㅂ(ㅍ, ㄼ, ㄿ, ㅄ)’ 뒤에 연결되는 ‘ㄱ, ㄷ, ㅂ, ㅅ, ㅈ’은 된소리로 발음한다.
 * > 제24항 - 어간 받침 ‘ㄴ(ㄵ), ㅁ(ㄻ)’ 뒤에 결합되는 어미의 첫소리 ‘ㄱ, ㄷ, ㅅ, ㅈ’은 된소리로 발음한다.
 * > 제25항 - 어간 받침 ‘ㄼ, ㄾ’ 뒤에 결합되는 어미의 첫소리 ‘ㄱ, ㄷ, ㅅ, ㅈ’은 된소리로 발음한다.
 *
 * @param currentSyllable 현재 음절을 입력합니다.
 * @param nextSyllable 다음 음절을 입력합니다.
 */
@Suppress("KDocUnresolvedReference") // this object can be invoked directly using 'operator fun ITransformer.invoke'.
public object TransformHardConversion : ITransformer {
    public override fun transform(
        currentSyllable: MutableHangulChar,
        nextSyllable: MutableHangulChar,
        phrase: String,
        index: Int,
    ) {
        if (nextSyllable.choseong in PronounConstants.된소리) {
            val 제23항조건 = currentSyllable.jongseong in PronounConstants.된소리_받침
            val 제24_25항조건 = currentSyllable.jongseong in PronounConstants.어간_받침 && nextSyllable.choseong != 'ㅂ'

            if (제23항조건 || 제24_25항조건) {
                nextSyllable.choseong = PronounConstants.된소리[nextSyllable.choseong]!! // validated by if statement
            }
        }
    }
}
