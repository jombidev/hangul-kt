package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

class DisassembleTests {
    @Test
    fun test() {
        assertEquals("ㄱㅏㅂㅅ", disassemble("값"))
        assertEquals("ㄱㅏㅂㅅㅇㅣ ㅂㅣㅆㅏㄷㅏ", disassemble("값이 비싸다"))
        assertEquals("ㅅㅏㄱㅗㅏ ㅉㅏㅇ", disassemble("사과 짱"))
        assertEquals("ㄴㅈ", disassemble("ㄵ"))
        assertEquals("ㅗㅏ", disassemble("ㅘ"))
    }
    
    @Test
    fun `빈값이 들어올 경우 그대로 반환한다`() {
        assertEquals("", disassemble(""))
    }
}