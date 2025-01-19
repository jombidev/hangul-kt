package dev.jombi.hangulkt.hangul

public data class HangulChar(val choseong: Char, val jungseong: String, val jongseong: String = "") {
    public fun replace받침ㅎ(): HangulChar {
        return copy(
            jongseong = jongseong.replace("ㅎ", "")
        )
    }
}