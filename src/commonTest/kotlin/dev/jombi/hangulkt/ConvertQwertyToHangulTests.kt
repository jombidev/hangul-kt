package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class ConvertQwertyToHangulTests {
    @Test
    fun `영어 알파벳을 한글로 합성한다`() {
        assertEquals("뮻", convertQwertyToHangul("abc"))
        assertEquals("프론트엔드", convertQwertyToHangul("vmfhsxmdpsem"))
    }
    
    @Test
    fun `쌍자모음에 대응하지 않는 영어 알파벳을 한글로 합성한다`() {
        assertEquals("뮻", convertQwertyToHangul("ABC"))
        assertEquals("프론트", convertQwertyToHangul("VMFHSXM"))
    }
    
    @Test
    fun `영어 알파벳은 한글로 합성하고 한글 음절은 유지한다`() {
        assertEquals("프론트", convertQwertyToHangul("vm론트"))
    }
    
    @Test
    fun `인자가 한글 음소면 한글로 합성한다`() {
        assertEquals("프론트", convertQwertyToHangul("ㅍㅡㄹㅗㄴㅌㅡ"))
    }
    
    @Test
    fun `영문 대문자는 쌍자음 또는 쌍모음에 대응하며 한글로 합성한다`() {
        assertEquals("깍뚜기", convertQwertyToHangul("RㅏㄱEㅜrl"))
        assertEquals("예의", convertQwertyToHangul("ㅇPdml"))
    }
    
    @Test
    fun `빈 문자열은 빈 문자열을 반환한다`() {
        assertEquals("", convertQwertyToHangul(""))
    }
}