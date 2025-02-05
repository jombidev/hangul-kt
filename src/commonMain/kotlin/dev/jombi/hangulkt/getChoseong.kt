package dev.jombi.hangulkt

import doist.x.normalize.Form
import doist.x.normalize.normalize

/**
 * 단어에서 초성을 추출합니다. (예: `사과` -> `'ㅅㄱ'`)
 * 
 * @param word 초성을 추출할 단어
 * @return 추출된 초성
 */
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
