package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Suppress("NonAsciiCharacters")
class NumberToHangulTests {
    @Test
    fun `기본 변환`() {
        assertEquals("이십일만", numberToHangul(210_000))
        assertEquals("일만이천삼백사십오", numberToHangul(12_345))
        assertEquals("일억이천삼백사십오만육천칠백팔십", numberToHangul(123_456_780))
        assertEquals("일억", numberToHangul(100_000_000))
        assertEquals("일조", numberToHangul(1_000_000_000_000))
    }

    @Test
    fun `공백 포함 변환`() {
        assertEquals("이십일만", numberToHangul(210_000, spacing = true))
        assertEquals("일만 이천삼백사십오", numberToHangul(12_345, spacing = true))
        assertEquals("일억 이천삼백사십오만 육천칠백팔십", numberToHangul(123_456_780, spacing = true))
    }

    @Test
    fun `0과 10000보다 작은 경우`() {
        assertEquals("영", numberToHangul(0))
        assertEquals("일", numberToHangul(1))
        assertEquals("이", numberToHangul(2))
        assertEquals("삼", numberToHangul(3))
        assertEquals("사", numberToHangul(4))
        assertEquals("오", numberToHangul(5))
        assertEquals("육", numberToHangul(6))
        assertEquals("칠", numberToHangul(7))
        assertEquals("팔", numberToHangul(8))
        assertEquals("구", numberToHangul(9))
        assertEquals("십", numberToHangul(10))
        assertEquals("십일", numberToHangul(11))
        assertEquals("이십", numberToHangul(20))
        assertEquals("삼십", numberToHangul(30))
        assertEquals("백", numberToHangul(100))
        assertEquals("백일", numberToHangul(101))
        assertEquals("백십", numberToHangul(110))
        assertEquals("이백", numberToHangul(200))
        assertEquals("삼백", numberToHangul(300))
        assertEquals("천", numberToHangul(1_000))
        assertEquals("천일", numberToHangul(1_001))
        assertEquals("천백", numberToHangul(1_100))
        assertEquals("천이백", numberToHangul(1_200))
        assertEquals("천이백삼십사", numberToHangul(1_234))
        assertEquals("구천구백구십구", numberToHangul(9_999))
    }
    
    @Test
    fun `유효하지 않은 숫자에 대한 오류 처리`() {
        assertFailsWith<AssertionError> {
            numberToHangul(-1)
        }
        assertFailsWith<AssertionError> {
            numberToHangul(-10000)
        }
    }
}