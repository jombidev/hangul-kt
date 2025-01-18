package dev.jombi.hangulkt

public fun disassembleCompleteCharacter(letter: Char): Triple<Char, String, String>? {
    val charCode = letter.code

    val isCompleteHangul = Constants.COMPLETE_HANGUL_START_CODE <= charCode && charCode <= Constants.COMPLETE_HANGUL_END_CODE

    if (!isCompleteHangul)
        return null

    val hangulCode = charCode - Constants.COMPLETE_HANGUL_START_CODE

    val jongseongIndex = hangulCode % Constants.NUMBER_OF_JONGSEONG
    val jungseongIndex = ((hangulCode - jongseongIndex) / Constants.NUMBER_OF_JONGSEONG) % Constants.NUMBER_OF_JUNGSEONG
    val choseongIndex = (hangulCode - jongseongIndex) / Constants.NUMBER_OF_JONGSEONG / Constants.NUMBER_OF_JUNGSEONG

    
    return Triple(
        Constants.CHOSEONGS[choseongIndex],
        Constants.JUNGSEONGS[jungseongIndex],
        Constants.JONGSEONGS[jongseongIndex],
    )
}
