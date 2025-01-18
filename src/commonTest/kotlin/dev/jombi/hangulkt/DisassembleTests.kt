package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

class DisassembleTests {
    @Test
    fun test() {
        assertEquals(disassemble("값"), "ㄱㅏㅂㅅ")
        assertEquals(disassemble("값이 비싸다"), "ㄱㅏㅂㅅㅇㅣ ㅂㅣㅆㅏㄷㅏ")
        assertEquals(disassemble("사과 짱"), "ㅅㅏㄱㅗㅏ ㅉㅏㅇ")
        assertEquals(disassemble("ㄵ"), "ㄴㅈ")
        assertEquals(disassemble("ㅘ"), "ㅗㅏ")
    }
}