package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Suppress("NonAsciiCharacters")
class CombineCharacterTests {
    @Test
    fun `종성으로 겹받침으로 구성될 수 있는 문자 두 개를 받으면 겹받침을 생성한다`() {
        assertEquals('값', combineCharacter('ㄱ', "ㅏ", "ㅂㅅ"))
    }
    
    @Test
    fun `종성이 입력되지 않았다면 받침이 없는 문자로 합성한다`() {
        assertEquals('토', combineCharacter('ㅌ', 'ㅗ'))
        assertEquals('꺄', combineCharacter('ㄲ', "ㅑ"))
    }
    
    @Test
    fun `종성이 입력되었다면 받침을 추가한다`() {
        assertEquals('톳', combineCharacter('ㅌ', 'ㅗ', 'ㅅ'))
        assertEquals('탁', combineCharacter('ㅌ', "ㅏ", "ㄱ"))
    }
    
    @Test
    fun `초성이 될 수 없는 문자가 초성으로 입력되면 에러를 반환한다`() {
        assertFailsWith<AssertionError> {
            combineCharacter('ㅏ', 'ㅏ', 'ㄱ')
        }
    }

    @Test
    fun `중성이 될 수 없는 문자가 중성으로 입력되면 에러를 반환한다`() {
        assertFailsWith<AssertionError> {
            combineCharacter('ㄱ', 'ㄱ')
        }
        assertFailsWith<AssertionError> {
            combineCharacter('ㄱ', "ㅗㅑ")
        }
    }

    @Test
    fun `종성이 될 수 없는 문자가 중성으로 입력되면 에러를 반환한다`() {
        assertFailsWith<AssertionError> {
            combineCharacter('ㄱ', 'ㅏ', 'ㅃ')
        }
        assertFailsWith<AssertionError> {
            combineCharacter('ㄱ', "ㅗㅏ", "ㅊㅂ")
        }
    }
    
    @Test
    fun `온전한 한글 문자가 하나라도 입력되면 에러를 반환한다`() {
        assertFailsWith<AssertionError> {
            combineCharacter('온', 'ㅗ', 'ㄴ')
        }
        assertFailsWith<AssertionError> {
            combineCharacter('ㅈ', '전', 'ㄴ')
        }
        assertFailsWith<AssertionError> {
            combineCharacter('ㅎ', 'ㅏ', '한')
        }
        
        assertFailsWith<AssertionError> {
            combineCharacter('온', "ㅗ", "ㄴ")
        }
        assertFailsWith<AssertionError> {
            combineCharacter('ㅈ', "전", "ㄴ")
        }
        assertFailsWith<AssertionError> {
            combineCharacter('ㅎ', "ㅏ", "한")
        }
        
        assertFailsWith<AssertionError> {
            combineCharacter('온', 'ㅗ')
        }
        assertFailsWith<AssertionError> {
            combineCharacter('ㅈ', '전')
        }
        
        assertFailsWith<AssertionError> {
            combineCharacter('온', "ㅗ")
        }
        assertFailsWith<AssertionError> {
            combineCharacter('ㅈ', "전")
        }
    }
}