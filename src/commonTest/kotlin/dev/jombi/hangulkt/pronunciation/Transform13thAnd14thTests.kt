package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Transform13thAnd14thTests {
    @Test
    fun `13항을 적용합니다`() {
        val current = disassembleCompleteCharacter('깎')
        val next = disassembleCompleteCharacter('아')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄲ', "ㅏ") to HangulChar('ㄲ', "ㅏ"),
            Transform13And14(current, next)
        )
    }

    @Test
    fun `14항을 적용합니다`() {
        val current = disassembleCompleteCharacter('닭')
        val next = disassembleCompleteCharacter('을')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄷ', "ㅏ", "ㄹ") to HangulChar('ㄱ', "ㅡ", "ㄹ"),
            Transform13And14(current, next)
        )
    }
}