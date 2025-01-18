package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class CombineVowelsTests {
    @Test
    fun `겹모음이 될 수 있는 모음이 순서대로 입력되면 겹모음으로 합성한다`(){
        assertEquals(combineVowels('ㅗ', 'ㅏ'), "ㅘ")
        assertEquals(combineVowels("ㅜ", "ㅔ"), "ㅞ")
        assertEquals(combineVowels("ㅡ", "ㅣ"), "ㅢ")
    }

    @Test
    fun `겹모음이 될 수 있는 모음이라고 해도 틀린 순서로 입력되면 Join한다`() {
        assertEquals(combineVowels("ㅔ", "ㅜ"), "ㅔㅜ")
        assertEquals(combineVowels("ㅣ", "ㅡ"), "ㅣㅡ")
    }

    @Test
    fun `이미 겹모음인 문자와 모음을 합성하려고 시도하면 Join한다`() {
        assertEquals(combineVowels("ㅞ", "ㅡ"), "ㅞㅡ")
        assertEquals(combineVowels("ㅘ", "ㅜ"), "ㅘㅜ")
    }

    @Test
    fun `겹모음이 될 수 있는 모음이라고 해도 틀린 순서로 입력되면 null을 반환한다`() {
        assertEquals(combineVowelsOrNull('ㅔ', 'ㅜ'), null)
        assertEquals(combineVowelsOrNull("ㅣ", "ㅡ"), null)
    }

    @Test
    fun `이미 겹모음인 문자와 모음을 합성하려고 시도하면 null을 반환한다`() {
        assertEquals(combineVowelsOrNull('ㅞ', 'ㅡ'), null)
        assertEquals(combineVowelsOrNull("ㅘ", "ㅜ"), null)
    }
}