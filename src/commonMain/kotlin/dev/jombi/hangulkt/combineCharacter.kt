package dev.jombi.hangulkt

import dev.jombi.hangulkt.hangul.HangulChar

public fun combineCharacter(char: HangulChar): Char = combineCharacter(char.choseong, char.jungseong, char.jongseong)
public fun combineCharacter(choseong: Char, jungseong: String, jongseong: String = ""): Char {
    if (!canBeChoseong(choseong) || !canBeJungseong(jungseong) || !canBeJongseong(jongseong)) {
        throw AssertionError("Invalid hangul Characters: $choseong, $jungseong, $jongseong")
    }

    val numOfJungseongs = Constants.JUNGSEONGS.size
    val numOfJongseongs = Constants.JONGSEONGS.size

    val choseongIndex = Constants.CHOSEONGS.indexOf(choseong)
    val jungseongIndex = Constants.JUNGSEONGS.indexOf(jungseong)
    val jongseongIndex = Constants.JONGSEONGS.indexOf(jongseong)

    val choseongOfTargetConsonant = choseongIndex * numOfJungseongs * numOfJongseongs
    val choseongOfTargetVowel = jungseongIndex * numOfJongseongs

    val unicode =
        Constants.COMPLETE_HANGUL_START_CODE + choseongOfTargetConsonant + choseongOfTargetVowel + jongseongIndex

    return unicode.toChar()
}

public fun combineCharacter(choseong: Char, jungseong: Char, jongseong: Char? = null): Char {
    return combineCharacter(choseong, jungseong.toString(), jongseong?.toString() ?: "")
}