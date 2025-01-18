package dev.jombi.hangulkt

public object Hangul {
    /**
     * 두 개의 한글 자모를 합칩니다. 완성된 한글 문자는 취급하지 않습니다.
     *
     * @return 완성된 한글 반환
     */
    public fun binaryAssembleAlphabets(source: Char, nextCharacter: Char): String {
        if (canBeJungseong("$source$nextCharacter"))
            return combineVowels(source, nextCharacter)

        val isConsonantSource = !canBeJungseong(source)
        if (isConsonantSource && canBeJungseong(nextCharacter)) {
            return combineCharacter(source, nextCharacter).toString()
        }

        return "$source$nextCharacter"
    }

    public fun linkHangulCharacters(source: Char, nextCharacter: Char): String {
        val sourceJamo = disassembleToGroup(source)
        val lastJamo = sourceJamo.last()

        return "${removeLastCharacter(source)}${combineCharacter(lastJamo, nextCharacter)}"
    }

    public fun binaryAssembleCharacters(source: Char, nextCharacter: Char): String {
        if (!source.isFullHangul() && !source.isHangulAlphabet())
            throw AssertionError("Invalid source character: ${source}. Source must be one character.")
        if (!nextCharacter.isHangulAlphabet())
            throw AssertionError("Invalid next character: ${nextCharacter}. Next character must be one of the choseong, jungseong, or jongseong.")

        val sourceJamos = disassembleToGroup(source)

        val isSingleCharacter = sourceJamos.size == 1
        if (isSingleCharacter) {
            val sourceCharacter = sourceJamos[0]
            return binaryAssembleAlphabets(sourceCharacter, nextCharacter)
        }

        val restJamos = sourceJamos.dropLast(1)
        val lastJamo = sourceJamos.last()
        val secondaryLastJamo = restJamos.last()

        val needLinking = canBeChoseong(lastJamo) && canBeJungseong(nextCharacter)
        if (needLinking) {
            return linkHangulCharacters(source, nextCharacter)
        }

        val builder = HangulBuilder(restJamos[0])

        if (canBeJungseong("${lastJamo}${nextCharacter}")) {
            return builder.setJungseong("${lastJamo}${nextCharacter}").toString()
        }

        if (canBeJungseong("${secondaryLastJamo}${lastJamo}") && canBeJongseong(nextCharacter)) {
            return builder.setJungseong("${secondaryLastJamo}${lastJamo}").setJongseong(nextCharacter).toString()
        }

        if (canBeJungseong(lastJamo) && canBeJongseong(nextCharacter)) {
            return builder.setJungseong(lastJamo).setJongseong(nextCharacter).toString()
        }

        if (hasBatchim(source, BatchimType.SINGLE) && canBeJongseong("$lastJamo${nextCharacter}")) {
            val combineJongseong = builder.setJungseong(
                if (canBeJungseong("${restJamos.getOrNull(1)}${restJamos.getOrNull(2)}"))
                    "${restJamos[1]}${restJamos[2]}"
                else
                    "${restJamos[1]}"
            )
            
            return combineJongseong.setJongseong("$lastJamo${nextCharacter}").toString()
        }

        return "$source$nextCharacter"
    }

    public fun binaryAssemble(source: String, nextCharacter: Char): String {
        val last = source.lastOrNull() 
            ?: return "$source$nextCharacter"
        if (last.isWhitespace() || nextCharacter.isWhitespace())
            return "$source$nextCharacter"
        
        val result = binaryAssembleCharacters(last, nextCharacter)
        return "${source.dropLast(1)}$result"
    }
}
