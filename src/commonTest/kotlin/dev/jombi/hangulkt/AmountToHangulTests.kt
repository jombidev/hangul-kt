package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Suppress("NonAsciiCharacters", "DEPRECATION")
class AmountToHangulTests {
    @Test
    fun `금액 문자열을 한글로 표기`() {
        assertEquals("일천오백이십만천백", amountToHangul("15,201,100"))
        assertEquals("일억", amountToHangul("100000000"))
        assertEquals("일억백", amountToHangul("100000100"))
        assertEquals("영", amountToHangul("0"))
        assertEquals("", amountToHangul(""))
    }

    @Test
    fun `숫자로 된 금액이 80글자를 넘을 시 에러 발생`() {
        val veryLongNumber = "1".repeat(81)
        assertFailsWith<AssertionError> {
            amountToHangul(veryLongNumber)
        }
    }

    @Test
    fun `숫자 외 문자를 무시하여 반환`() {
        assertEquals("일십이만삼십", amountToHangul("120,030원"))
    }

    @Test
    fun `소수점이 있는 경우도 표기`() {
        assertEquals("영점영일영이", amountToHangul("0.01020"))
        assertEquals("영", amountToHangul("0.0000"))
        assertEquals("영", amountToHangul(".0000"))
        assertEquals("삼백구십이점이사", amountToHangul("392.24"))
        assertEquals("일만이천삼백사십오점육칠팔구", amountToHangul("12345.6789"))
    }

    @Test
    fun `금액 숫자를 한글로 표기`() {
        assertEquals("일천오백이십만천백", amountToHangul(15_201_100))
        assertEquals("일억백", amountToHangul(100000100))
        assertEquals("삼백구십이점이사", amountToHangul(392.24))
    }

    @Test
    fun `선행 혹은 후행 0이 있는 경우 처리`() {
        assertEquals("일천이십삼", amountToHangul("01023"))
        assertEquals("일천이십삼", amountToHangul("001023"))
        assertEquals("일천이십삼", amountToHangul("0001023"))
        
        assertEquals("일천이십삼점일", amountToHangul("1023.10"))
        assertEquals("일천이십삼점일", amountToHangul("1023.100"))
        assertEquals("일천이십삼점일", amountToHangul("1023.1000"))
    }
}