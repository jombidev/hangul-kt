package dev.jombi.hangulkt

public enum class BatchimType {
    SINGLE, DOUBLE, BOTH
}

/**
 * 한글 문자열의 마지막 글자가 받침이 있는지 확인합니다.
 * 
 * @param str 글자에 받침이 있는지 확인하고 싶은 문자열
 * @param type 검사하고 싶은 받침의 속성
 * @return 받침 여부
 */
public fun hasBatchim(str: String, type: BatchimType = BatchimType.BOTH): Boolean {
    val lastChar = str.lastOrNull() ?: return false
    return hasBatchim(lastChar, type)
}

/**
 * 현재 글자가 받침이 있는지 확인합니다.
 *
 * @param char 글자에 받침이 있는지 확인하고 싶은 문자
 * @param type 검사하고 싶은 받침의 속성
 * @return 받침 여부
 */
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
