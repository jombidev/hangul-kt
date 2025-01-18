package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class RemoveLastCharacterTests {
    @Test
    fun `마지막 문자가 겹받침인 경우 홑받침으로 바꾼다`() {
        assertEquals("안녕하세요 갑", removeLastCharacter("안녕하세요 값"))
        assertEquals("안녕하세요 값", removeLastCharacter("안녕하세요 값ㅇ"))
    }
    
    @Test
    fun `마지막 문자가 초성과 중성의 조합으로 끝날 경우 초성만 남긴다`() {
        assertEquals("프론트엔ㄷ", removeLastCharacter("프론트엔드"))
        assertEquals("끓ㄷ", removeLastCharacter("끓다"))
        assertEquals("관ㅅ", removeLastCharacter("관사"))
        assertEquals("괴ㅅ", removeLastCharacter("괴사"))
    }

    @Test
    fun `마지막 문자가 초성과 중성과 종성의 조합으로 끝날 경우 초성과 중성이 조합된 문자만 남긴다`() {
        assertEquals("일요이", removeLastCharacter("일요일"))
        assertEquals("완저", removeLastCharacter("완전"))
        assertEquals("왅저", removeLastCharacter("왅전"))
        assertEquals("까", removeLastCharacter("깎"))
    }
    
    @Test
    fun `마지막 문자가 초성과 중성의 조합으로 끝나면서 중성 입력 시 국제 표준 한글 레이아웃 기준 단일키로 처리되지 않는 이중모음 -ㅗㅜㅡ 계 이중모음- 인 경우 초성과 중성의 시작 모음만 남긴다`() {
        assertEquals("전호", removeLastCharacter("전화"))
        assertEquals("예으", removeLastCharacter("예의"))
        assertEquals("신세ㄱ", removeLastCharacter("신세계")) // 'ㅖ'의 경우 단일키 처리가 가능한 이중모음이므로 모음이 남지 않는다.
    }
    
    @Test
    fun `마지막 문자가 초성과 중성과 종성의 조합으로 끝나면서 중성 입력 시 국제 표준 한글 레이아웃 기준 단일키로 처리되지 않는 이중모음 -ㅗㅜㅡ 계 이중모음- 인 경우 초성과 중성만 남긴다`() {
        assertEquals("수화", removeLastCharacter("수확"))
    }
    
    @Test
    fun `마지막 문자가 초성과 중성과 종성의 조합으로 끝나면서 종성이 겹자음인 경우 초성과 중성과 종성의 시작 자음만 남긴다`() {
        assertEquals("끌", removeLastCharacter("끓"))
    }
    
    @Test
    fun `마지막 문자가 초성과 중성과 종성의 조합으로 끝나면서 중성 입력 시 국제 표준 한글 레이아웃 기준 단일키로 처리되지 않는 이중모음 -ㅗㅜㅡ 계 이중모음- 이고 종성이 겹자음인 경우 초성과 중성과 종성의 시작 자음만 남긴다`() {
        assertEquals("완", removeLastCharacter("왅"))
    }
    
    @Test
    fun `빈 문자열일 경우 빈 문자열을 반환한다`() {
        assertEquals("", removeLastCharacter(""))
    }
}