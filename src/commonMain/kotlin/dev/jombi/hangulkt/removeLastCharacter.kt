package dev.jombi.hangulkt

public fun removeLastCharacter(words: String): String {
    val lastCharacter = words.lastOrNull() ?: return ""

    val result = removeLastCharacter(lastCharacter)
    
    return words.dropLast(1) + (result ?: "")
}

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
