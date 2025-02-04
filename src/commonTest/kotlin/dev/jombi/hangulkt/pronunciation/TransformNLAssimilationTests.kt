package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TransformNLAssimilationTests {
    @Test
    fun `받침이 'ㄱㄴㄷㅁㅂㅇ'이고 다음 음절이 '야여요유이얘예'로 이어지는 경우`() {
        val current = disassembleCompleteCharacter('맨')
        val next = disassembleCompleteCharacter('입')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㅁ', "ㅐ", "ㄴ") to HangulChar('ㄴ', "ㅣ", "ㅂ"),
            TransformNLAssimilation(current, next)
        )
    }

    @Test
    fun `받침이 'ㄹ'이고 다음 음절이 '야여요유이얘예'로 이어지는 경우`() {
        val current = disassembleCompleteCharacter('알')
        val next = disassembleCompleteCharacter('약')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㅇ', "ㅏ", "ㄹ") to HangulChar('ㄹ', "ㅑ", "ㄱ"),
            TransformNLAssimilation(current, next)
        )
    }

    @Test
    fun `ㄴ 혹은 ㄹ이 되기 위한 조건이지만 현재 음절의 중성의 ∙ -아래아- 가 하나가 아닐 경우에는 덧나지 않고 연음규칙이 적용된다`() {
        val current = disassembleCompleteCharacter('양')
        val next = disassembleCompleteCharacter('이')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㅇ', "ㅑ", "ㅇ") to HangulChar('ㅇ', "ㅣ"),
            TransformNLAssimilation(current, next)
        )
    }
}