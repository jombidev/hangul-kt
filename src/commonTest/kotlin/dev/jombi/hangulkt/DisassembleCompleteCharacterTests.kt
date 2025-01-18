package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class DisassembleCompleteCharacterTests {
    @Test
    fun `완성된 한글이 들어오면 자모를 분리한다`() {
        assertEquals(disassembleCompleteCharacter('값'), Triple('ㄱ', "ㅏ", "ㅂㅅ"))
        assertEquals(disassembleCompleteCharacter('리'), Triple('ㄹ', "ㅣ", ""))
        assertEquals(disassembleCompleteCharacter('빚'), Triple('ㅂ', "ㅣ", "ㅈ"))
        assertEquals(disassembleCompleteCharacter('박'), Triple('ㅂ', "ㅏ", "ㄱ"))
    }
    
    @Test
    fun `완전한 한글 문자가 아니면 null을 반환한다`() {
        assertEquals(disassembleCompleteCharacter('ㄱ'), null)
        assertEquals(disassembleCompleteCharacter('ㅏ'), null)
    }
}