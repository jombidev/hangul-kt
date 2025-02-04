package dev.jombi.hangulkt.hangul

public data class HangulChar(val choseong: Char, val jungseong: String, val jongseong: String = "") {
    public fun hasJongseong(): Boolean = jongseong.isNotEmpty()
    public fun replace받침ㅎ(): HangulChar {
        return copy(
            jongseong = jongseong.replace("ㅎ", "")
        )
    }
}

public fun HangulChar.toMutable(): MutableHangulChar = MutableHangulChar(this)

public class MutableHangulChar(char: HangulChar) {
    public var value: HangulChar = char
        private set
    
    public var choseong: Char
        get() = value.choseong
        set(value) {
            this.value = this.value.copy(choseong = value)
        }
    
    public var jungseong: String
        get() = value.jungseong
        set(value) {
            this.value = this.value.copy(jungseong = value)
        }
    
    public var jongseong: String
        get() = value.jongseong
        set(value) {
            this.value = this.value.copy(jongseong = value)
        }
    
    public fun hasJongseong(): Boolean = jongseong.isNotEmpty()
    public fun replace받침ㅎ(): MutableHangulChar {
        jongseong = jongseong.replace("ㅎ", "")
        return this
    }
}