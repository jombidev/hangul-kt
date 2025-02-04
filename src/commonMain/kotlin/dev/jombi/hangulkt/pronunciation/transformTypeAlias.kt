package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.hangul.HangulChar
import dev.jombi.hangulkt.hangul.MutableHangulChar
import kotlin.jvm.JvmName

public typealias ReturnSyllables = Pair<HangulChar, HangulChar>
public typealias NullableReturnSyllables = Pair<HangulChar, HangulChar?>

public fun ReturnSyllables(current: MutableHangulChar, next: MutableHangulChar): ReturnSyllables =
    current.value to next.value


public fun NullableReturnSyllables(current: MutableHangulChar, next: MutableHangulChar?): NullableReturnSyllables =
    current.value to next?.value

public infix fun MutableHangulChar.and(next: MutableHangulChar): ReturnSyllables = ReturnSyllables(this, next)
@JvmName("andNullable")
public infix fun MutableHangulChar.and(next: MutableHangulChar?): NullableReturnSyllables =
    NullableReturnSyllables(this, next)
