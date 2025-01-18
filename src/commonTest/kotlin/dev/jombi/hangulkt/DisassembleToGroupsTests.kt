package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class DisassembleToGroupsTests {
    @Test
    fun `테스트를 통과해야한다`() {
        assertEquals(disassembleToGroups("값"), listOf(listOf('ㄱ', 'ㅏ', 'ㅂ', 'ㅅ')))
        assertEquals(
            disassembleToGroups("값이 비싸다"),
            listOf(
                listOf('ㄱ', 'ㅏ', 'ㅂ', 'ㅅ'),
                listOf('ㅇ', 'ㅣ'),
                listOf(' '),
                listOf('ㅂ', 'ㅣ'),
                listOf('ㅆ', 'ㅏ'),
                listOf('ㄷ', 'ㅏ'),
            )
        )
        assertEquals(
            disassembleToGroups("사과 짱"),
            listOf(
                listOf('ㅅ', 'ㅏ'),
                listOf('ㄱ', 'ㅗ', 'ㅏ'),
                listOf(' '),
                listOf('ㅉ', 'ㅏ', 'ㅇ'),
            )
        )

        assertEquals(
            disassembleToGroups("ㄵ"),
            listOf(
                listOf('ㄴ', 'ㅈ'),
            )
        )
        
        assertEquals(
            disassembleToGroups("ㅘ"),
            listOf(
                listOf('ㅗ', 'ㅏ'),
            )
        )
    }
}