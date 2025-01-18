package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class DisassembleToGroupsTests {
    @Test
    fun `테스트를 통과해야한다`() {
        assertEquals(listOf(listOf('ㄱ', 'ㅏ', 'ㅂ', 'ㅅ')), disassembleToGroups("값"))
        assertEquals(
            listOf(
                listOf('ㄱ', 'ㅏ', 'ㅂ', 'ㅅ'),
                listOf('ㅇ', 'ㅣ'),
                listOf(' '),
                listOf('ㅂ', 'ㅣ'),
                listOf('ㅆ', 'ㅏ'),
                listOf('ㄷ', 'ㅏ'),
            ),
            disassembleToGroups("값이 비싸다")
        )
        assertEquals(
            listOf(
                listOf('ㅅ', 'ㅏ'),
                listOf('ㄱ', 'ㅗ', 'ㅏ'),
                listOf(' '),
                listOf('ㅉ', 'ㅏ', 'ㅇ'),
            ),
            disassembleToGroups("사과 짱")
        )

        assertEquals(
            listOf(
                listOf('ㄴ', 'ㅈ'),
            ),
            disassembleToGroups("ㄵ"),
        )

        assertEquals(
            listOf(
                listOf('ㅗ', 'ㅏ'),
            ),
            disassembleToGroups("ㅘ")
        )
    }
}