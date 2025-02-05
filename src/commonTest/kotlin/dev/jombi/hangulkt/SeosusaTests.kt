package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SeosusaTests {
    @Test
    fun `1 - 순 우리말 서수사로 변환한다`() = valid(1, "첫째")
    @Test
    fun `2 - 순 우리말 서수사로 변환한다`() = valid(2, "둘째")
    @Test
    fun `3 - 순 우리말 서수사로 변환한다`() = valid(3, "셋째") // "셋째"가 표준어이고 "세째"는 비표준어이다.(표준어 사정 원칙 제6항)
    @Test
    fun `4 - 순 우리말 서수사로 변환한다`() = valid(4, "넷째") // "넷째"가 표준어이고 "네째"는 비표준어이다.(표준어 사정 원칙 제6항)
    @Test
    fun `5 - 순 우리말 서수사로 변환한다`() = valid(5, "다섯째")
    @Test
    fun `6 - 순 우리말 서수사로 변환한다`() = valid(6, "여섯째")
    @Test
    fun `7 - 순 우리말 서수사로 변환한다`() = valid(7, "일곱째")
    @Test
    fun `8 - 순 우리말 서수사로 변환한다`() = valid(8, "여덟째")
    @Test
    fun `9 - 순 우리말 서수사로 변환한다`() = valid(9, "아홉째")
    @Test
    fun `10 - 순 우리말 서수사로 변환한다`() = valid(10, "열째")
    @Test
    fun `11 - 순 우리말 서수사로 변환한다`() = valid(11, "열한째")
    @Test
    fun `12 - 순 우리말 서수사로 변환한다`() = valid(12, "열두째") // "둘째"는 십 단위 이상의 서수사에 쓰일 때에 "두째"로 한다.(표준어 사정 원칙 제6항)
    @Test
    fun `13 - 순 우리말 서수사로 변환한다`() = valid(13, "열셋째")
    @Test
    fun `14 - 순 우리말 서수사로 변환한다`() = valid(14, "열넷째")
    @Test
    fun `15 - 순 우리말 서수사로 변환한다`() = valid(15, "열다섯째")
    @Test
    fun `20 - 순 우리말 서수사로 변환한다`() = valid(20, "스무째")
    @Test
    fun `21 - 순 우리말 서수사로 변환한다`() = valid(21, "스물한째")
    @Test
    fun `22 - 순 우리말 서수사로 변환한다`() = valid(22, "스물두째")
    @Test
    fun `30 - 순 우리말 서수사로 변환한다`() = valid(30, "서른째")
    @Test
    fun `40 - 순 우리말 서수사로 변환한다`() = valid(40, "마흔째")
    @Test
    fun `90 - 순 우리말 서수사로 변환한다`() = valid(90, "아흔째")
    @Test
    fun `99 - 순 우리말 서수사로 변환한다`() = valid(99, "아흔아홉째")
    @Test
    fun `100 - 순 우리말 서수사로 변환한다`() = valid(100, "백째")
    @Test
    fun `101 - 순 우리말 서수사로 변환한다`() = valid(101, "백일째")

    @Test
    fun `0 - 유효하지 않은 숫자에 대해 오류를 발생시켜야 한다`() { invalid(0) }
    @Test
    fun `-1 - 유효하지 않은 숫자에 대해 오류를 발생시켜야 한다`() { invalid(-1) }
    
    private fun valid(num: Int, res: String) = assertEquals(res, seosusa(num))
    private fun invalid(num: Int) = assertFailsWith<AssertionError> {
        seosusa(num)
    }
}