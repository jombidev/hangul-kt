package dev.jombi.hangulkt

public enum class BatchimType {
    SINGLE, DOUBLE, BOTH
}

public fun hasBatchim(str: String, type: BatchimType = BatchimType.BOTH): Boolean {
    val lastChar = str.lastOrNull() ?: return false
    return hasBatchim(lastChar, type)
}

public fun hasBatchim(char: Char, type: BatchimType = BatchimType.BOTH): Boolean {
    val charCode = char.code
    val isNotCompleteHangul = charCode < Constants.COMPLETE_HANGUL_START_CODE || charCode > Constants.COMPLETE_HANGUL_END_CODE

    if (isNotCompleteHangul)
        return false

    val batchimCode = (charCode - Constants.COMPLETE_HANGUL_START_CODE) % Constants.NUMBER_OF_JONGSEONG
    val batchimLength = Constants.JONGSEONGS[batchimCode].length

    return when (type) {
        BatchimType.SINGLE -> batchimLength == 1
        BatchimType.DOUBLE -> batchimLength == 2
        BatchimType.BOTH ->batchimCode > 0
    }
}
