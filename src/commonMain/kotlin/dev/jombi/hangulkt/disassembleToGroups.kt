package dev.jombi.hangulkt

public fun disassembleToGroups(str: String): List<List<Char>> {
    return str.map { disassembleToGroup(it) }
}

public fun disassembleToGroup(char: Char): List<Char> =
    disassembleCompleteCharacter(char)?.let { listOf(it.first) + it.second.toList() + it.third.toList() }
        ?: Constants.DISASSEMBLED_CONSONANTS_BY_CONSONANT[char]?.toList()
        ?: Constants.DISASSEMBLED_VOWELS_BY_VOWEL[char]?.toList()
        ?: listOf(char)
