package dev.jombi.hangulkt

public fun disassemble(str: String): String {
    return disassembleToGroups(str).reduce("") { a, b -> "${a}${b.joinToString("")}" }
}
