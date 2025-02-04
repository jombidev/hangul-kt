package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Transform18thTests {
    @Test
    fun `받침 'ㄱㄲㅋㄳㄺ'일 경우`() {
        val current = disassembleCompleteCharacter('먹')
        val next = disassembleCompleteCharacter('는')

        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㅁ', "ㅓ", "ㅇ"),
            transform18th(current, next)
        )
    }
    
    @Test
    fun `받침 'ㄷㅅㅆㅈㅊㅌㅎ'일 경우`() {
        val current = disassembleCompleteCharacter('닫')
        val next = disassembleCompleteCharacter('는')

        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㄷ', "ㅏ", "ㄴ"),
            transform18th(current, next)
        )
    }
    
    @Test
    fun `받침 'ㅂㅍㄼㄿㅄ'일 경우`() {
        val current = disassembleCompleteCharacter('잡')
        val next = disassembleCompleteCharacter('는')

        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㅈ', "ㅏ", "ㅁ"),
            transform18th(current, next)
        )
    }
}