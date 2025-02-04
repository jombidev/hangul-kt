package dev.jombi.hangulkt.pronunciation

import dev.jombi.hangulkt.disassembleCompleteCharacter
import dev.jombi.hangulkt.hangul.HangulChar
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Transform16thTests {
    @Test
    fun `한글 자모의 이름은 그 받침소리를 연음하되 'ㄷㅈㅊㅋㅌㅍㅎ'의 경우에는 특별히 다음과 같이 발음한다`() {
        val current = disassembleCompleteCharacter('귿')
        val next = disassembleCompleteCharacter('이')
        val phrase = "디귿이"
        val index = 1

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㄱ', "ㅡ") to HangulChar('ㅅ', "ㅣ"),
            transform16th(current, next, phrase, index)
        )
    }

    @Test
    fun `자모의 이름이 'ㄱㄴㄹㅁㅂㅅㅇ'일 경우`() {
        val current = disassembleCompleteCharacter('역')
        val next = disassembleCompleteCharacter('이')
        val phrase = "기역이"
        val index = 1

        assertNotNull(current)
        assertNotNull(next)

        assertEquals(
            HangulChar('ㅇ', "ㅕ") to HangulChar('ㄱ', "ㅣ"),
            transform16th(current, next, phrase, index)
        )
    }
}