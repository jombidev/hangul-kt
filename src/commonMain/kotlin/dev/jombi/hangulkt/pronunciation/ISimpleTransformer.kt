package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import dev.jombi.hangulkt.hangul.toMutable

public interface ISimpleTransformer {
    public fun transform(
        currentSyllable: MutableHangulChar,
        nextSyllable: MutableHangulChar?,
    )
}

public operator fun ISimpleTransformer.invoke(currentSyllable: HangulChar, nextSyllable: HangulChar?): NullableReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable?.toMutable()
    transform(current, next)
    return current and next
}
