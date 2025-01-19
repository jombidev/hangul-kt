@file:Suppress("EnumEntryName", "NonAsciiCharacters", "ObjectPropertyName")

package dev.jombi.hangulkt

public enum class JosaOption(
    public val left: String,
    public val right: String,
) {
    이_가("이", "가"),
    을_를("을", "를"),
    은_는("은", "는"),
    으로_로("으로", "로"),
    와_과("와", "과"),
    이나_나("이나", "나"),
    이란_란("이란", "란"),
    아_야("아", "야"),
    이랑_랑("이랑", "랑"),
    이에요_예요("이에요", "예요"),
    으로서_로서("으로서", "로서"),
    으로써_로써("으로써", "로써"),
    으로부터_로부터("으로부터", "로부터"),
    이라_라("이라", "라"),
}

private val 로_조사: Array<JosaOption> = arrayOf(JosaOption.으로_로, JosaOption.으로서_로서, JosaOption.으로써_로써, JosaOption.으로부터_로부터)

public fun josa(word: String, josa: JosaOption): String {
    if (word.isEmpty())
        return word

    return word + josaPicker(word, josa)
}

public fun josaPicker(word: String, josa: JosaOption): String {
    if (word.isEmpty())
        return josa.left

    val has받침 = hasBatchim(word)
    var index = if (has받침) josa::left else josa::right
    
    val is종성ㄹ = has받침 && disassembleCompleteCharacter(word[word.length - 1])?.jongseong == "ㄹ"

    val isCaseOf로 = has받침 && is종성ㄹ && josa in 로_조사

    if (josa == JosaOption.와_과 || isCaseOf로)
        index = if (index == josa::left) josa::right else josa::left

    val isEndsWith이 = word[word.length - 1] == '이'

    if (josa == JosaOption.이에요_예요 && isEndsWith이) {
        index = josa::right
    }

    return index.get()
}
