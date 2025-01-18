package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class CombineVowelsTests {
    @Test
    fun `겹모음이 될 수 있는 모음이 순서대로 입력되면 겹모음으로 합성한다`(){
        assertEquals("ㅘ", combineVowels('ㅗ', 'ㅏ'),)
        assertEquals("ㅞ", combineVowels("ㅜ", "ㅔ"),)
        assertEquals("ㅢ", combineVowels("ㅡ", "ㅣ"),)
    }

    @Test
    fun `겹모음이 될 수 있는 모음이라고 해도 틀린 순서로 입력되면 Join한다`() {
        assertEquals("ㅔㅜ", combineVowels("ㅔ", "ㅜ"), )
        assertEquals("ㅣㅡ", combineVowels("ㅣ", "ㅡ"), )
    }

    @Test
    fun `이미 겹모음인 문자와 모음을 합성하려고 시도하면 Join한다`() {
        assertEquals("ㅞㅡ", combineVowels("ㅞ", "ㅡ"), )
        assertEquals("ㅘㅜ", combineVowels("ㅘ", "ㅜ"), )
    }

    @Test
    fun `겹모음이 될 수 있는 모음이라고 해도 틀린 순서로 입력되면 null을 반환한다`() {
        assertEquals(null, combineVowelsOrNull('ㅔ', 'ㅜ'), )
        assertEquals(null, combineVowelsOrNull("ㅣ", "ㅡ"), )
    }

    @Test
    fun `이미 겹모음인 문자와 모음을 합성하려고 시도하면 null을 반환한다`() {
        assertEquals(null, combineVowelsOrNull('ㅞ', 'ㅡ'), )
        assertEquals(null, combineVowelsOrNull("ㅘ", "ㅜ"), )
    }
}