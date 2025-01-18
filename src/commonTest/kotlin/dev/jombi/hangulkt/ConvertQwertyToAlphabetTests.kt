package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class ConvertQwertyToAlphabetTests {
    @Test
    fun `영어 알파벳을 한글 음소로 바꾼다`() {
        assertEquals(convertQwertyToAlphabet("abc"), "ㅁㅠㅊ")
    }
    
    @Test
    fun `쌍 또는 자모음에 대응하지 않는 영어 알파벳을 한글 음소로 바꾼다`() {
        assertEquals(convertQwertyToAlphabet("ABC"), "ㅁㅠㅊ")
    }
    
    @Test
    fun `영어 알파벳은 한글 음소로 바꾸고 한글 음절은 유지한다`() {
        assertEquals(convertQwertyToAlphabet("vm론트"), "ㅍㅡ론트")
    }
    
    @Test
    fun `분해된 한글 음소는 유지한다`() {
        assertEquals(convertQwertyToAlphabet("ㅍㅡㄹㅗㄴㅌㅡ"), "ㅍㅡㄹㅗㄴㅌㅡ")
    }
    
    @Test
    fun `영어 알파벳이 아닌 입력은 유지한다`() {
        assertEquals(convertQwertyToAlphabet("4월/20dlf!"), "4월/20ㅇㅣㄹ!")
    }
    
    @Test
    fun `영문 대문자는 쌍 자음 또는 모음으로 바꾼다`() {
        assertEquals(convertQwertyToAlphabet("RㅏㄱEㅜrl"), "ㄲㅏㄱㄸㅜㄱㅣ")
        assertEquals(convertQwertyToAlphabet("ㅇPdml"), "ㅇㅖㅇㅡㅣ")
    }
    
    @Test
    fun `빈 문자열은 빈 문자열을 반환한다`() {
        assertEquals(convertQwertyToAlphabet(""), "")
    }
}