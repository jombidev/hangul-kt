package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import dev.jombi.hangulkt.hangul.toMutable

public interface ITransformer {
    public fun transform(
        currentSyllable: MutableHangulChar,
        nextSyllable: MutableHangulChar,
        phrase: String,
        index: Int,
    )
}

public operator fun ITransformer.invoke(currentSyllable: HangulChar, nextSyllable: HangulChar): ReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable.toMutable()
    transform(current, next, "", 0)
    return current and next
}

public operator fun ITransformer.invoke(currentSyllable: HangulChar, nextSyllable: HangulChar, phrase: String, index: Int): ReturnSyllables {
    val current = currentSyllable.toMutable()
    val next = nextSyllable.toMutable()
    transform(current, next, phrase, index)
    return current and next
}