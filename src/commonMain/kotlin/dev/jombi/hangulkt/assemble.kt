package dev.jombi.hangulkt

public fun assemble(vararg words: String): String {
    val disassembled = disassemble(words.joinToString("")).split("").filter { it.isNotEmpty() }
    return disassembled.reduce("") { a, b -> Hangul.binaryAssemble(a, b[0]) }
}
