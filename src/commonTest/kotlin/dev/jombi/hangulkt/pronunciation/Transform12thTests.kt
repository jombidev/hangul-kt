package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Transform12thTests {
    @Test
    fun `'ㅎㄶㅀ' 뒤에 'ㄱㄷㅈ'이 결합되는 경우에는 뒤 음절 첫소리와 합쳐서 'ㅋㅌㅊ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('놓')
        val next = disassembleCompleteCharacter('고')
        
        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㄴ', "ㅗ") to HangulChar('ㅋ', "ㅗ"),
            transform12th(current, next)
        )
    }
    
    @Test
    fun `받침 'ㄱㄺㄷㅂㄼㅈㄵ'이 뒤 음절 첫소리 'ㅎ'과 결합되는 경우에도 역시 두 음을 합쳐서 'ㅋㅌㅍㅊ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('각')
        val next = disassembleCompleteCharacter('하')
        
        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㄱ', "ㅏ") to HangulChar('ㅋ', "ㅏ"),
            transform12th(current, next)
        )
    }
    
    @Test
    fun `'ㅎㄶㅀ' 뒤에 'ㅅ'이 결합되는 경우에는 'ㅅ'을 'ㅆ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('닿')
        val next = disassembleCompleteCharacter('소')
        
        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㄷ', "ㅏ") to HangulChar('ㅆ', "ㅗ"),
            transform12th(current, next)
        )
    }
    
    @Test
    fun `'ㅎ' 뒤에 'ㄴ'이 결합되는 경우에는 'ㄴ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('놓')
        val next = disassembleCompleteCharacter('는')
        
        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㄴ', "ㅗ") to HangulChar('ㄴ', "ㅡ", "ㄴ"),
            transform12th(current, next)
        )
    }
    
    @Test
    fun `'ㄶㅀ' 뒤에 'ㄴ'이 결합되는 경우에는 'ㅎ'을 발음하지 않는다`() {
        val current = disassembleCompleteCharacter('않')
        val next = disassembleCompleteCharacter('네')
        
        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㅇ', "ㅏ", "ㄴ") to HangulChar('ㄴ', "ㅔ"),
            transform12th(current, next)
        )
    }
    
    @Test
    fun `'ㅎㄶㅀ' 뒤에 모음으로 시작된 어미나 접미사가 결합되는 경우에는 'ㅎ'을 발음하지 않는다`() {
        val current = disassembleCompleteCharacter('낳')
        val next = disassembleCompleteCharacter('은')
        
        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(
            HangulChar('ㄴ', "ㅏ") to HangulChar('ㅇ', "ㅡ", "ㄴ"),
            transform12th(current, next)
        )
    }
    
    @Test
    fun `'ㅎㄶㅀ' 뒤에 모음으로 시작된 어미나 접미사가 결합되는 경우에는 'ㅎ'을 발음하지 않는다 next 없음`() {
        val current = disassembleCompleteCharacter('많')
        val next = null
        
        assertNotNull(current)
        
        assertEquals(
            HangulChar('ㅁ', "ㅏ", "ㄴ") to null,
            transform12th(current, next)
        )
    }
}