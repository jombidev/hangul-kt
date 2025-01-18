package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class DisassembleCompleteCharacterTests {
    @Test
    fun `완성된 한글이 들어오면 자모를 분리한다`() {
        assertEquals(Triple('ㄱ', "ㅏ", "ㅂㅅ"), disassembleCompleteCharacter('값'), )
        assertEquals(Triple('ㄹ', "ㅣ", ""), disassembleCompleteCharacter('리'), )
        assertEquals(Triple('ㅂ', "ㅣ", "ㅈ"), disassembleCompleteCharacter('빚'), )
        assertEquals(Triple('ㅂ', "ㅏ", "ㄱ"), disassembleCompleteCharacter('박'), )
    }
    
    @Test
    fun `완전한 한글 문자가 아니면 null을 반환한다`() {
        assertEquals(null, disassembleCompleteCharacter('ㄱ'), )
        assertEquals(null, disassembleCompleteCharacter('ㅏ'), )
    }
}