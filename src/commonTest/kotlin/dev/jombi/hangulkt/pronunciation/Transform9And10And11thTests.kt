package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Transform9And10And11thTests {
    @Test
    fun `9항 - 받침 'ㄲㅋ' 'ㅅㅆㅈㅊㅌ' 'ㅍ'은 어말 또는 자음 앞에서 각각 대표음 'ㄱ' 'ㄷ' 'ㅂ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('닦')
        val next = disassembleCompleteCharacter('다')
        
        assertNotNull(current)
        
        assertEquals(HangulChar('ㄷ', "ㅏ", "ㄱ"), transform9And10And11th(current, next))
    }
    
    @Test
    fun `10항 - 겹받침 'ㄳ' 'ㄵ' 'ㄼㄽㄾ' 'ㅄ'은 어말 또는 자음 앞에서 각각 'ㄱ' 'ㄴ' 'ㄹ' 'ㅂ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('앉')
        val next = disassembleCompleteCharacter('다')
        
        assertNotNull(current)
        
        assertEquals(HangulChar('ㅇ', "ㅏ", "ㄴ"), transform9And10And11th(current, next))
    }
    
    @Test
    fun `11항 - 겹받침 'ㄺ' 'ㄻ' 'ㄿ'은 어말 또는 자음 앞에서 각각 'ㄱ' 'ㅁ' 'ㅂ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('흙')
        val next = disassembleCompleteCharacter('과')
        
        assertNotNull(current)
        
        assertEquals(HangulChar('ㅎ', "ㅡ", "ㄱ"), transform9And10And11th(current, next))
    }
}