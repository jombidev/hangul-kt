package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TransformHardConversionTests {
    @Test
    fun `23항 - 받침 ㄱ-ㄲㅋㄳㄺ ㄷ-ㅅㅆㅈㅊㅌ ㅂ-ㅍㄼㄿㅄ 뒤에 연결되는 'ㄱㄷㅂㅅㅈ'은 된소리로 발음한다`() {
        val current = disassembleCompleteCharacter('국')
        val next = disassembleCompleteCharacter('밥')
        
        assertNotNull(current)
        assertNotNull(next)

        assertEquals(HangulChar('ㅃ', "ㅏ", "ㅂ"), TransformHardConversion(current, next).second)
    }
    
    @Test
    fun `24항 - 어간 받침 ㄴㄵ ㅁㄻ 뒤에 결합되는 어미의 첫소리 'ㄱㄷㅅㅈ'은 된소리로 발음한다`() {
        val current = disassembleCompleteCharacter('신')
        val next = disassembleCompleteCharacter('고')

        assertNotNull(current)
        assertNotNull(next)
        
        assertEquals(HangulChar('ㄲ', "ㅗ"), TransformHardConversion(current, next).second)
    }
    
    @Test
    fun `25항 - 어간 받침 ㄼㄾ 뒤에 결합되는 어미의 첫소리 'ㄱㄷㅅㅈ'은 된소리로 발음한다`() {
        val current = disassembleCompleteCharacter('넓')
        val next = disassembleCompleteCharacter('게')
        
        assertNotNull(current)
        assertNotNull(next)

        assertEquals(HangulChar('ㄲ', "ㅔ"), TransformHardConversion(current, next).second)
    }
}