package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Transform17thTests {
    @Test
    fun `받침 'ㄷ'과 'ㅌ -ㄾ-'이 조사나 접미사의 모음 'ㅣ'와 결합되는 경우에는 각각 'ㅈ'과 'ㅊ'으로 바꾸어서 뒤 음절 첫소리로 옮겨 발음한다`() {
        val current = disassembleCompleteCharacter('굳')
        val next = disassembleCompleteCharacter('이')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄱ', "ㅜ") to HangulChar('ㅈ', "ㅣ"),
            Transform17(current, next)
        )
    }

    @Test
    fun `'ㄷ' 뒤에 접미사 '히'가 결합되어 '티'를 이루는 것은 '치'로 발음한다`() {
        val current = disassembleCompleteCharacter('굳')
        val next = disassembleCompleteCharacter('히')

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄱ', "ㅜ") to HangulChar('ㅊ', "ㅣ"),
            Transform17(current, next)
        )
    }
}