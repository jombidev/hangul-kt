package dev.jombi.hangulkt

internal open class HangulBuilder(private val choseong: Char) {
    private var jongseong: String? = null

    open fun setJungseong(char: Char): BuildReadyHangulBuilder {
        return BuildReadyHangulBuilder(choseong, "$char").apply {
            jongseong = this@HangulBuilder.jongseong
        }
    }

    open fun setJungseong(jungseong: String): BuildReadyHangulBuilder {
        return BuildReadyHangulBuilder(choseong, jungseong).apply { 
            jongseong = this@HangulBuilder.jongseong
        }
    }
    
    open fun setJongseong(char: Char): HangulBuilder {
        jongseong = "$char"
        return this
    }

    open fun setJongseong(jongseong: String): HangulBuilder {
        this.jongseong = jongseong
        return this
    }
}

internal class BuildReadyHangulBuilder(private val choseong: Char, private var jungseong: String): HangulBuilder(choseong) {
    var jongseong: String? = null

    override fun setJungseong(char: Char): BuildReadyHangulBuilder {
        jungseong = "$char"
        return this
    }

    override fun setJungseong(jungseong: String): BuildReadyHangulBuilder {
        this.jungseong = jungseong
        return this
    }

    override fun setJongseong(char: Char): BuildReadyHangulBuilder {
        jongseong = "$char"
        return this
    }

    override fun setJongseong(jongseong: String): BuildReadyHangulBuilder {
        this.jongseong = jongseong
        return this
    }
    
    fun build() = combineCharacter(choseong, jungseong, jongseong ?: "")
    override fun toString() = build().toString()
}
