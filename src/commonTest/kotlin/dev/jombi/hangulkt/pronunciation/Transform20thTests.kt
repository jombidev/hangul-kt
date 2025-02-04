package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Transform20thTests {
    @Test
    fun `'ㄴ'은 'ㄹ'의 앞이나 뒤에서 'ㄹ'로 발음한다`() {
        val current = disassembleCompleteCharacter('난')
        val next = disassembleCompleteCharacter('로')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄴ', "ㅏ", "ㄹ") to HangulChar('ㄹ', "ㅗ"),
            Transform20(current, next)
        )
    }

    @Test
    fun `첫소리 'ㄴ'이 'ㅀ' 또는 'ㄾ' 뒤에 연결되는 경우에도 'ㄹ'로 발음한다`() {
        val current = disassembleCompleteCharacter('닳')
        val next = disassembleCompleteCharacter('는')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄷ', "ㅏ", "ㄹㅎ") to HangulChar('ㄹ', "ㅡ", "ㄴ"),
            Transform20(current, next)
        )
    }
}