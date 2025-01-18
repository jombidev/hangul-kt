package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class AssembleTests {
    @Test
    fun `온전한 한글과 한글 문자 조합`() {
        assertEquals("아버지가 방에 들어갑니다", assemble("아버지가", " ", "방ㅇ", "ㅔ ", "들ㅇ", "ㅓ갑니다"))
    }
    
    @Test
    fun `온전한 한글만 조합`() {
        assertEquals("아버지가 방에 들어갑니다", assemble("아버지가", " ", "방에 ", "들어갑니다"))
    }
    
    @Test
    fun `온전하지 않은 한글만 조합`() {
        assertEquals("아버지", assemble("ㅇ", "ㅏ", "ㅂ", "ㅓ", "ㅈ", "ㅣ"))
    }
}