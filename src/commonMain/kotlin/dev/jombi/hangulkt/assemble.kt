package dev.jombi.hangulkt

/**
 * 인자로 받은 배열에 담긴 한글 문장과 문자를 한글 규칙에 맞게 합성합니다.
 * 
 * @param fragments 한글 문자와 문장을 담고 있는 배열
 * @return 조합된 한글 문자열
 */
public fun assemble(vararg fragments: String): String {
    val disassembled = disassemble(fragments.joinToString("")).split("").filter { it.isNotEmpty() }
    return disassembled.reduce("") { a, b -> Hangul.binaryAssemble(a, b[0]) }
}
