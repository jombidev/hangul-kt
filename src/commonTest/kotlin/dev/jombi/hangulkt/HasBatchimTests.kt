package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class HasBatchimTests {
    @Test
    fun `받침이 있다고 판단되는 경우`() {
        // "값" 문자에서 받침이 있으므로 true를 반환한다.
        assertEquals(hasBatchim('값'), true)
        // "공" 문자에서 받침이 있으므로 true를 반환한다.
        assertEquals(hasBatchim('공'), true)
        // "읊" 문자에서 받침이 있으므로 true를 반환한다.
        assertEquals(hasBatchim('읊'), true)
    }
    
    @Test
    fun `받침이 없다고 판단되는 경우`() {
        // "토" 문자에서 받침이 없으므로 false를 반환한다.
        assertEquals(hasBatchim('토'), false)
        // "서" 문자에서 받침이 없으므로 false를 반환한다.
        assertEquals(hasBatchim('서'), false)
        //빈 문자열은 받침이 없으므로 false를 반환한다.
        assertEquals(hasBatchim(""), false)
    }

    @Test
    fun `한글이 자음 또는 모음으로만 구성된 경우 false를 반환한다`() {
        assertEquals(hasBatchim('ㄱ'), false)
        assertEquals(hasBatchim('ㅏ'), false)
    }

    @Test
    fun `한글 외의 문자를 입력하면 false를 반환한다`() {
        assertEquals(hasBatchim("cat"), false)
        assertEquals(hasBatchim('!'), false)
    }

    @Test
    fun `홑받침모드 일때 홑받침을 받으면 true를 반환한다`() {
        assertEquals(hasBatchim('공', BatchimType.SINGLE), true)
        assertEquals(hasBatchim("핫", BatchimType.SINGLE), true)
        assertEquals(hasBatchim('양', BatchimType.SINGLE), true)
        assertEquals(hasBatchim("신", BatchimType.SINGLE), true)
        assertEquals(hasBatchim('확', BatchimType.SINGLE), true)
    }

    @Test
    fun `홑받침모드 일때 겹받침을 받으면 false를 반환한다`() {
        assertEquals(hasBatchim('값', BatchimType.SINGLE), false)
        assertEquals(hasBatchim("읊", BatchimType.SINGLE), false)
        assertEquals(hasBatchim('웱', BatchimType.SINGLE), false)
    }

    @Test
    fun `홑받침모드 일때 받침이 없는 문자를 받으면 false를 반환한다`() {
        assertEquals(hasBatchim('토', BatchimType.SINGLE), false)
        assertEquals(hasBatchim("서", BatchimType.SINGLE), false)
        assertEquals(hasBatchim('와', BatchimType.SINGLE), false)
    }

    @Test
    fun `홑받침모드 일때 한글 외의 문자를 입력하면 false를 반환한다`() {
        assertEquals(hasBatchim("cat", BatchimType.SINGLE), false)
        assertEquals(hasBatchim("", BatchimType.SINGLE), false)
        assertEquals(hasBatchim('?', BatchimType.SINGLE), false)
    }

    @Test
    fun `겹받침모드 일때 겹받침을 받으면 true를 반환한다`() {
        assertEquals(hasBatchim('값', BatchimType.DOUBLE), true)
        assertEquals(hasBatchim("읇", BatchimType.DOUBLE), true)
        assertEquals(hasBatchim('웱', BatchimType.DOUBLE), true)
    }
    
    @Test
    fun `겹받침모드 일때 홑받침을 받으면 false를 반환한다`() {
        assertEquals(hasBatchim('공', BatchimType.DOUBLE), false)
        assertEquals(hasBatchim("핫", BatchimType.DOUBLE), false)
        assertEquals(hasBatchim('양', BatchimType.DOUBLE), false)
        assertEquals(hasBatchim("신", BatchimType.DOUBLE), false)
        assertEquals(hasBatchim('확', BatchimType.DOUBLE), false)
    }

    @Test
    fun `겹받침모드 일때 받침이 없는 문자를 받으면 false를 반환한다`() {
        assertEquals(hasBatchim('토', BatchimType.DOUBLE), false)
        assertEquals(hasBatchim("서", BatchimType.DOUBLE), false)
        assertEquals(hasBatchim('와', BatchimType.DOUBLE), false)
    }

    @Test
    fun `겹받침모드 일때 한글 외의 문자를 입력하면 false를 반환한다`() {
        assertEquals(hasBatchim("cat", BatchimType.DOUBLE), false)
        assertEquals(hasBatchim("", BatchimType.DOUBLE), false)
        assertEquals(hasBatchim('?', BatchimType.DOUBLE), false)
    }
}