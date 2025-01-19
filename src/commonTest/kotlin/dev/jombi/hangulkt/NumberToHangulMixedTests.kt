package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class NumberToHangulMixedTests {
    @Test
    fun `기본 변환`() {
        assertEquals("21만", numberToHangulMixed(210_000))
        assertEquals("1만2,345", numberToHangulMixed(12_345))
        assertEquals("1억2,345만6,780", numberToHangulMixed(123_456_780))
    }
    
    @Test
    fun `공백 포함 변환`() {
        assertEquals("21만", numberToHangulMixed(210_000, spacing = true))
        assertEquals("1만 2,345", numberToHangulMixed(12_345, spacing = true))
        assertEquals("1억 2,345만 6,780", numberToHangulMixed(123_456_780, spacing = true))
    }
    
    @Test
    fun `0과 10000보다 작은 경우`() {
        assertEquals("0", numberToHangulMixed(0))
        assertEquals("1", numberToHangulMixed(1))
        assertEquals("2", numberToHangulMixed(2))
        assertEquals("3", numberToHangulMixed(3))
        assertEquals("4", numberToHangulMixed(4))
        assertEquals("5", numberToHangulMixed(5))
        assertEquals("6", numberToHangulMixed(6))
        assertEquals("7", numberToHangulMixed(7))
        assertEquals("8", numberToHangulMixed(8))
        assertEquals("9", numberToHangulMixed(9))
        assertEquals("10", numberToHangulMixed(10))
        assertEquals("11", numberToHangulMixed(11))
        assertEquals("20", numberToHangulMixed(20))
        assertEquals("30", numberToHangulMixed(30))
        assertEquals("100", numberToHangulMixed(100))
        assertEquals("101", numberToHangulMixed(101))
        assertEquals("110", numberToHangulMixed(110))
        assertEquals("200", numberToHangulMixed(200))
        assertEquals("300", numberToHangulMixed(300))
        assertEquals("1,000", numberToHangulMixed(1_000))
        assertEquals("1,001", numberToHangulMixed(1_001))
        assertEquals("1,100", numberToHangulMixed(1_100))
        assertEquals("1,200", numberToHangulMixed(1_200))
        assertEquals("1,234", numberToHangulMixed(1_234))
        assertEquals("9,999", numberToHangulMixed(9_999))
    }
}