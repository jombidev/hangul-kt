package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Suppress("NonAsciiCharacters")
class DaysTests {
    @Test
    fun `순 우리말 날짜로 바꿔 반환해야 한다`() {
        val validNumbers = mapOf(
            1 to "하루",
            2 to "이틀",
            3 to "사흘",
            4 to "나흘",
            5 to "닷새",
            6 to "엿새",
            7 to "이레",
            8 to "여드레",
            9 to "아흐레",
            10 to "열흘",
            11 to "열하루",
            20 to "스무날",
            21 to "스무하루",
            30 to "서른날",
        )

        for ((key, value) in validNumbers) {
            assertEquals(value, days(key))
        }
    }
    
    @Test
    fun `유효하지 않은 숫자에 대해 오류를 발생시켜야 한다`() {
        // Inf, NegInf, NaN is only for Double in java/kotlin.
        assertFailsWith<AssertionError> {
            days(0)
        }
        assertFailsWith<AssertionError> {
            days(-1)
        }
        assertFailsWith<AssertionError> {
            days(31)
        }
    }
}