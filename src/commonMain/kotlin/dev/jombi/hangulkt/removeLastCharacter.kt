package dev.jombi.hangulkt

/**
 * 인자로 주어진 한글 문자열에서 가장 마지막 문자 하나를 제거합니다.
 *
 * @param words 한글 문자열
 * @return 마지막 문자가 지워진 한글 문자열
 */
public fun removeLastCharacter(words: String): String {
    val lastCharacter = words.lastOrNull() ?: return ""

    val result = removeLastCharacter(lastCharacter)

    return words.dropLast(1) + (result ?: "")
}

/**
 * 인자로 주어진 문자에서 가장 마지막 문자 하나를 제거합니다.
 *
 * @param words 한글 한글자
 * @return 마지막 문자가 지워진 한글 문자열
 */
public fun removeLastCharacter(words: Char): Char? {
    val disassembleLastCharacter = disassembleToGroup(words)
    val dropped = disassembleLastCharacter.dropLast(1)
    val result = if (dropped.size <= 3) {
        if (dropped.getOrNull(1) == null) // 중성이 없으면
            dropped.getOrNull(0)
        else if (dropped.getOrNull(2)?.let { canBeJungseong(it) } == true) // 중성이 있고, 3번째 위치가 중성이 될수 있을경우?
            combineCharacter(dropped[0], "${dropped[1]}${dropped[2]}")
        else combineCharacter(dropped[0], dropped[1], dropped.getOrNull(2)) // 걍합치기
    } else {
        val (choseong, firstJungseong, secondJungseong, jongseong) = dropped
        combineCharacter(choseong, "$firstJungseong$secondJungseong", jongseong.toString())
    }

    return result
}
