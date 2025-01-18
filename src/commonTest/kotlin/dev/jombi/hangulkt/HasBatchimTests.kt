package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class HasBatchimTests {
    @Test
    fun `받침이 있다고 판단되는 경우`() {
        // "값" 문자에서 받침이 있으므로 true를 반환한다.
        assertEquals(true, hasBatchim('값'))
        // "공" 문자에서 받침이 있으므로 true를 반환한다.
        assertEquals(true, hasBatchim('공'))
        // "읊" 문자에서 받침이 있으므로 true를 반환한다.
        assertEquals(true, hasBatchim('읊'))
    }
    
    @Test
    fun `받침이 없다고 판단되는 경우`() {
        // "토" 문자에서 받침이 없으므로 false를 반환한다.
        assertEquals(false, hasBatchim('토'))
        // "서" 문자에서 받침이 없으므로 false를 반환한다.
        assertEquals(false, hasBatchim('서'))
        //빈 문자열은 받침이 없으므로 false를 반환한다.
        assertEquals(false, hasBatchim(""))
    }

    @Test
    fun `한글이 자음 또는 모음으로만 구성된 경우 false를 반환한다`() {
        assertEquals(false, hasBatchim('ㄱ'))
        assertEquals(false, hasBatchim('ㅏ'))
    }

    @Test
    fun `한글 외의 문자를 입력하면 false를 반환한다`() {
        assertEquals(false, hasBatchim("cat"))
        assertEquals(false, hasBatchim('!'))
    }

    @Test
    fun `홑받침모드 일때 홑받침을 받으면 true를 반환한다`() {
        assertEquals(true, hasBatchim('공', BatchimType.SINGLE))
        assertEquals(true, hasBatchim("핫", BatchimType.SINGLE))
        assertEquals(true, hasBatchim('양', BatchimType.SINGLE))
        assertEquals(true, hasBatchim("신", BatchimType.SINGLE))
        assertEquals(true, hasBatchim('확', BatchimType.SINGLE))
    }

    @Test
    fun `홑받침모드 일때 겹받침을 받으면 false를 반환한다`() {
        assertEquals(false, hasBatchim('값', BatchimType.SINGLE))
        assertEquals(false, hasBatchim("읊", BatchimType.SINGLE))
        assertEquals(false, hasBatchim('웱', BatchimType.SINGLE))
    }

    @Test
    fun `홑받침모드 일때 받침이 없는 문자를 받으면 false를 반환한다`() {
        assertEquals(false, hasBatchim('토', BatchimType.SINGLE))
        assertEquals(false, hasBatchim("서", BatchimType.SINGLE))
        assertEquals(false, hasBatchim('와', BatchimType.SINGLE))
    }

    @Test
    fun `홑받침모드 일때 한글 외의 문자를 입력하면 false를 반환한다`() {
        assertEquals(false, hasBatchim("cat", BatchimType.SINGLE))
        assertEquals(false, hasBatchim("", BatchimType.SINGLE))
        assertEquals(false, hasBatchim('?', BatchimType.SINGLE))
    }

    @Test
    fun `겹받침모드 일때 겹받침을 받으면 true를 반환한다`() {
        assertEquals(true, hasBatchim('값', BatchimType.DOUBLE))
        assertEquals(true, hasBatchim("읇", BatchimType.DOUBLE))
        assertEquals(true, hasBatchim('웱', BatchimType.DOUBLE))
    }
    
    @Test
    fun `겹받침모드 일때 홑받침을 받으면 false를 반환한다`() {
        assertEquals(false, hasBatchim('공', BatchimType.DOUBLE))
        assertEquals(false, hasBatchim("핫", BatchimType.DOUBLE))
        assertEquals(false, hasBatchim('양', BatchimType.DOUBLE))
        assertEquals(false, hasBatchim("신", BatchimType.DOUBLE))
        assertEquals(false, hasBatchim('확', BatchimType.DOUBLE))
    }

    @Test
    fun `겹받침모드 일때 받침이 없는 문자를 받으면 false를 반환한다`() {
        assertEquals(false, hasBatchim('토', BatchimType.DOUBLE))
        assertEquals(false, hasBatchim("서", BatchimType.DOUBLE))
        assertEquals(false, hasBatchim('와', BatchimType.DOUBLE))
    }

    @Test
    fun `겹받침모드 일때 한글 외의 문자를 입력하면 false를 반환한다`() {
        assertEquals(false, hasBatchim("cat", BatchimType.DOUBLE))
        assertEquals(false, hasBatchim("", BatchimType.DOUBLE))
        assertEquals(false, hasBatchim('?', BatchimType.DOUBLE))
    }
}