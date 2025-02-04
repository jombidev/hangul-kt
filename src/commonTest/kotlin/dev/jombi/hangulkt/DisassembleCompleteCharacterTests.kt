package dev.jombi.hangulkt

import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class DisassembleCompleteCharacterTests {
    @Test
    fun `완성된 한글이 들어오면 자모를 분리한다`() {
        assertEquals(HangulChar('ㄱ', "ㅏ", "ㅂㅅ"), disassembleCompleteCharacter('값'))
        assertEquals(HangulChar('ㄹ', "ㅣ"), disassembleCompleteCharacter('리'))
        assertEquals(HangulChar('ㅂ', "ㅣ", "ㅈ"), disassembleCompleteCharacter('빚'))
        assertEquals(HangulChar('ㅂ', "ㅏ", "ㄱ"), disassembleCompleteCharacter('박'))
    }
    
    @Test
    fun `완전한 한글 문자가 아니면 null을 반환한다`() {
        assertEquals(null, disassembleCompleteCharacter('ㄱ'))
        assertEquals(null, disassembleCompleteCharacter('ㅏ'))
    }
}