package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class RemoveLastCharacterTests {
    @Test
    fun `마지막 문자가 겹받침인 경우 홑받침으로 바꾼다`() {
        assertEquals(removeLastCharacter("안녕하세요 값"), "안녕하세요 갑")
        assertEquals(removeLastCharacter("안녕하세요 값ㅇ"), "안녕하세요 값")
    }
    
    @Test
    fun `마지막 문자가 초성과 중성의 조합으로 끝날 경우 초성만 남긴다`() {
        assertEquals(removeLastCharacter("프론트엔드"), "프론트엔ㄷ")
        assertEquals(removeLastCharacter("끓다"), "끓ㄷ")
        assertEquals(removeLastCharacter("관사"), "관ㅅ")
        assertEquals(removeLastCharacter("괴사"), "괴ㅅ")
    }

    @Test
    fun `마지막 문자가 초성과 중성과 종성의 조합으로 끝날 경우 초성과 중성이 조합된 문자만 남긴다`() {
        assertEquals(removeLastCharacter("일요일"), "일요이")
        assertEquals(removeLastCharacter("완전"), "완저")
        assertEquals(removeLastCharacter("왅전"), "왅저")
        assertEquals(removeLastCharacter("깎"), "까")
    }
    
    @Test
    fun `마지막 문자가 초성과 중성의 조합으로 끝나면서 중성 입력 시 국제 표준 한글 레이아웃 기준 단일키로 처리되지 않는 이중모음 -ㅗㅜㅡ 계 이중모음- 인 경우 초성과 중성의 시작 모음만 남긴다`() {
        assertEquals(removeLastCharacter("전화"), "전호")
        assertEquals(removeLastCharacter("예의"), "예으")
        assertEquals(removeLastCharacter("신세계"), "신세ㄱ") // 'ㅖ'의 경우 단일키 처리가 가능한 이중모음이므로 모음이 남지 않는다.
    }
    
    @Test
    fun `마지막 문자가 초성과 중성과 종성의 조합으로 끝나면서 중성 입력 시 국제 표준 한글 레이아웃 기준 단일키로 처리되지 않는 이중모음 -ㅗㅜㅡ 계 이중모음- 인 경우 초성과 중성만 남긴다`() {
        assertEquals(removeLastCharacter("수확"), "수화")
    }
    
    @Test
    fun `마지막 문자가 초성과 중성과 종성의 조합으로 끝나면서 종성이 겹자음인 경우 초성과 중성과 종성의 시작 자음만 남긴다`() {
        assertEquals(removeLastCharacter("끓"), "끌")
    }
    
    @Test
    fun `마지막 문자가 초성과 중성과 종성의 조합으로 끝나면서 중성 입력 시 국제 표준 한글 레이아웃 기준 단일키로 처리되지 않는 이중모음 -ㅗㅜㅡ 계 이중모음- 이고 종성이 겹자음인 경우 초성과 중성과 종성의 시작 자음만 남긴다`() {
        assertEquals(removeLastCharacter("왅"), "완")
    }
    
    @Test
    fun `빈 문자열일 경우 빈 문자열을 반환한다`() {
        assertEquals(removeLastCharacter(""), "")
    }
}