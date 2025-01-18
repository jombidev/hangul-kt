package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Suppress("NonAsciiCharacters")
class BinaryAssembleTests {
    @Test
    fun `초성과 중성만 조합`() {
        assertEquals(Hangul.binaryAssembleCharacters('ㄱ', 'ㅏ'), "가")
    }
    
    @Test
    fun `초성과 중성이 합쳐진 문자와 종성을 조합`() {
        assertEquals(Hangul.binaryAssembleCharacters('가', 'ㅇ'), "강")
    }
    
    @Test
    fun `초성과 중성과 종성이 합쳐진 문자와 자음을 조합하여 겹받침 만들기`() {
        assertEquals(Hangul.binaryAssembleCharacters('갑', 'ㅅ'), "값")
    }
    
    @Test
    fun `초성과 중성이 합쳐진 문자와 모음을 조립하여 겹모음 만들기`() {
        assertEquals(Hangul.binaryAssembleCharacters('고', 'ㅏ'), "과")
    }
    
    @Test
    fun `초성과 중성-겹모음-이 합쳐진 문자와 자음을 조합`() {
        assertEquals(Hangul.binaryAssembleCharacters('과', 'ㄱ'), "곽")
    }
    
    @Test
    fun `초성과 중성-겹모음-과 종성이 합쳐진 문자와 자음을 조합하여 겹받침 만들기`() {
        assertEquals(Hangul.binaryAssembleCharacters('완', 'ㅈ'), "왅")
    }
    
    @Test
    fun `모음만 있는 문자와 모음을 조합하여 겹모음 만들기`() {
        assertEquals(Hangul.binaryAssembleCharacters('ㅗ', 'ㅏ'), "ㅘ")
    }
    
    @Test
    fun `초성과 중성과 종성이 합쳐진 문자의 연음 법칙`() {
        assertEquals(Hangul.binaryAssembleCharacters('톳', 'ㅡ'), "토스")
    }
    
    @Test
    fun `초성과 중성과 종성-겹받침- 이 합쳐진 문자의 연음 법칙`() {
        assertEquals(Hangul.binaryAssembleCharacters('닭', 'ㅑ'), "달갸")
        assertEquals(Hangul.binaryAssembleCharacters('깎', 'ㅏ'), "까까")
    }
    
    @Test
    fun `문법에 맞지 않는 문자를 조합하면 단순 Join 한다 -문법 순서 틀림-`() {
        assertEquals(Hangul.binaryAssembleCharacters('ㅏ', 'ㄱ'), "ㅏㄱ")
        assertEquals(Hangul.binaryAssembleCharacters('까', 'ㅃ'), "까ㅃ")
        assertEquals(Hangul.binaryAssembleCharacters('ㅘ', 'ㅏ'), "ㅘㅏ")
    }
    
    @Test
    fun `순서대로 입력했을 때 조합이 불가능한 문자라면 단순 Join 한다`() {
        assertEquals(Hangul.binaryAssembleCharacters('뼈', 'ㅣ'), "뼈ㅣ")
    }
    
    @Test
    fun `다음 문자가 한글 문자 한 글자가 아니라면 Invalid next character 에러를 발생시킨다`() {
        assertFailsWith<AssertionError> {
            Hangul.binaryAssembleCharacters('ㄱ', 'a')
        }
    }
    
    @Test
    fun `문장과 모음을 조합하여 다음 글자를 생성한다`() {
        assertEquals(Hangul.binaryAssemble("저는 고양이를 좋아합니ㄷ", 'ㅏ'), "저는 고양이를 좋아합니다")
    }
    
    @Test
    fun `문장과 자음을 조합하여 홑받침을 생성한다`() {
        assertEquals(Hangul.binaryAssemble("저는 고양이를 좋아하", 'ㅂ'), "저는 고양이를 좋아합")
    }
    
    @Test
    fun `문장과 자음을 조합하여 겹받침을 생성한다`() {
        assertEquals(Hangul.binaryAssemble("저는 고양이를 좋아합", 'ㅅ'), "저는 고양이를 좋아핪")
    }
    
    @Test
    fun `조합이 불가능한 자음이 입력되면 단순 Join 한다`() {
        assertEquals(Hangul.binaryAssemble("저는 고양이를 좋아합", 'ㄲ'), "저는 고양이를 좋아합ㄲ")
        assertEquals(Hangul.binaryAssemble("저는 고양이를 좋아합", 'ㅂ'), "저는 고양이를 좋아합ㅂ")
    }
    
    @Test
    fun `조합이 불가능한 모음이 입력되면 단순 Join 한다`() {
        assertEquals(Hangul.binaryAssemble("저는 고양이를 좋아하", 'ㅏ'), "저는 고양이를 좋아하ㅏ")
        assertEquals(Hangul.binaryAssemble("저는 고양이를 좋아합니다", 'ㅜ'), "저는 고양이를 좋아합니다ㅜ")
    }
}