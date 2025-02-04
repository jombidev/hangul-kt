package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Transform19thTests {
    @Test
    fun `받침 'ㅁㅇ' 뒤에 연결되는 'ㄹ'은 'ㄴ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('담')
        val next = disassembleCompleteCharacter('력')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄴ', "ㅕ", "ㄱ"),
            Transform19(current, next).second
        )
    }

    @Test
    fun `받침 'ㄱㅂ' 뒤에 연결되는 'ㄹ'도 'ㄴ'으로 발음한다`() {
        val current = disassembleCompleteCharacter('막')
        val next = disassembleCompleteCharacter('론')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄴ', "ㅗ", "ㄴ"),
            Transform19(current, next).second
        )
    }
}