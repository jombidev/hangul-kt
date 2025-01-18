package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Suppress("NonAsciiCharacters")
class BinaryAssembleTests {
    @Test
    fun `초성과 중성만 조합`() {
        assertEquals("가", Hangul.binaryAssembleCharacters('ㄱ', 'ㅏ'))
    }
    
    @Test
    fun `초성과 중성이 합쳐진 문자와 종성을 조합`() {
        assertEquals("강", Hangul.binaryAssembleCharacters('가', 'ㅇ'), )
    }
    
    @Test
    fun `초성과 중성과 종성이 합쳐진 문자와 자음을 조합하여 겹받침 만들기`() {
        assertEquals("값", Hangul.binaryAssembleCharacters('갑', 'ㅅ'), )
    }
    
    @Test
    fun `초성과 중성이 합쳐진 문자와 모음을 조립하여 겹모음 만들기`() {
        assertEquals("과", Hangul.binaryAssembleCharacters('고', 'ㅏ'), )
    }
    
    @Test
    fun `초성과 중성-겹모음-이 합쳐진 문자와 자음을 조합`() {
        assertEquals("곽", Hangul.binaryAssembleCharacters('과', 'ㄱ'), )
    }
    
    @Test
    fun `초성과 중성-겹모음-과 종성이 합쳐진 문자와 자음을 조합하여 겹받침 만들기`() {
        assertEquals("왅", Hangul.binaryAssembleCharacters('완', 'ㅈ'), )
    }
    
    @Test
    fun `모음만 있는 문자와 모음을 조합하여 겹모음 만들기`() {
        assertEquals("ㅘ", Hangul.binaryAssembleCharacters('ㅗ', 'ㅏ'), )
    }
    
    @Test
    fun `초성과 중성과 종성이 합쳐진 문자의 연음 법칙`() {
        assertEquals("토스", Hangul.binaryAssembleCharacters('톳', 'ㅡ'), )
    }
    
    @Test
    fun `초성과 중성과 종성-겹받침- 이 합쳐진 문자의 연음 법칙`() {
        assertEquals("달갸", Hangul.binaryAssembleCharacters('닭', 'ㅑ'), )
        assertEquals("까까", Hangul.binaryAssembleCharacters('깎', 'ㅏ'), )
    }
    
    @Test
    fun `문법에 맞지 않는 문자를 조합하면 단순 Join 한다 -문법 순서 틀림-`() {
        assertEquals("ㅏㄱ", Hangul.binaryAssembleCharacters('ㅏ', 'ㄱ'), )
        assertEquals("까ㅃ", Hangul.binaryAssembleCharacters('까', 'ㅃ'), )
        assertEquals("ㅘㅏ", Hangul.binaryAssembleCharacters('ㅘ', 'ㅏ'), )
    }
    
    @Test
    fun `순서대로 입력했을 때 조합이 불가능한 문자라면 단순 Join 한다`() {
        assertEquals("뼈ㅣ", Hangul.binaryAssembleCharacters('뼈', 'ㅣ'), )
    }
    
    @Test
    fun `다음 문자가 한글 문자 한 글자가 아니라면 Invalid next character 에러를 발생시킨다`() {
        assertFailsWith<AssertionError> {
            Hangul.binaryAssembleCharacters('ㄱ', 'a')
        }
    }
    
    @Test
    fun `문장과 모음을 조합하여 다음 글자를 생성한다`() {
        assertEquals("저는 고양이를 좋아합니다", Hangul.binaryAssemble("저는 고양이를 좋아합니ㄷ", 'ㅏ'), )
    }
    
    @Test
    fun `문장과 자음을 조합하여 홑받침을 생성한다`() {
        assertEquals("저는 고양이를 좋아합", Hangul.binaryAssemble("저는 고양이를 좋아하", 'ㅂ'), )
    }
    
    @Test
    fun `문장과 자음을 조합하여 겹받침을 생성한다`() {
        assertEquals("저는 고양이를 좋아핪", Hangul.binaryAssemble("저는 고양이를 좋아합", 'ㅅ'), )
    }
    
    @Test
    fun `조합이 불가능한 자음이 입력되면 단순 Join 한다`() {
        assertEquals("저는 고양이를 좋아합ㄲ", Hangul.binaryAssemble("저는 고양이를 좋아합", 'ㄲ'), )
        assertEquals("저는 고양이를 좋아합ㅂ", Hangul.binaryAssemble("저는 고양이를 좋아합", 'ㅂ'), )
    }
    
    @Test
    fun `조합이 불가능한 모음이 입력되면 단순 Join 한다`() {
        assertEquals("저는 고양이를 좋아하ㅏ", Hangul.binaryAssemble("저는 고양이를 좋아하", 'ㅏ'), )
        assertEquals("저는 고양이를 좋아합니다ㅜ", Hangul.binaryAssemble("저는 고양이를 좋아합니다", 'ㅜ'), )
    }
}