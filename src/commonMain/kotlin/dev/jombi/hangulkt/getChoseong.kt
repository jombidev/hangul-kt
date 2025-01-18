package dev.jombi.hangulkt

import dev.jombi.normalize.*

public fun getChoseong(word: String): String {
    return word.normalize(Form.NFD)
        .replace(EXTRACT_CHOSEONG_REGEX, "") // NFD ㄱ-ㅎ, NFC ㄱ-ㅎ 외 문자 삭제
        .replace(CHOOSE_NFD_CHOSEONG_REGEX) {
            Constants.CHOSEONGS[it.value[0].code - Constants.NFD_CHOSEONG_START.code].toString()
        } // NFD to NFC
}

// 1100 - Constants.NFD_CHOSEONG_START.code.toString(16)
// 1112 - Constants.NFD_CHOSEONG_END.code.toString(16)
public val EXTRACT_CHOSEONG_REGEX: Regex = Regex("[^\\u1100-\\u1112ㄱ-ㅎ\\s]+")
public val CHOOSE_NFD_CHOSEONG_REGEX: Regex = Regex("[\\u1100-\\u1112]")
