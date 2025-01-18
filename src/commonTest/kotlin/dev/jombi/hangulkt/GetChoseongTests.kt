package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("NonAsciiCharacters")
class GetChoseongTests {
    @Test
    fun `'사과' 단어에서 초성 'ㅅㄱ' 추출한다`() {
        assertEquals(getChoseong("사과"), "ㅅㄱ")
    }
    
    @Test
    fun `프론트엔드' 단어에서 초성 'ㅍㄹㅌㅇㄷ'을 추출한다`() {
        assertEquals(getChoseong("프론트엔드"), "ㅍㄹㅌㅇㄷ")
    }
    
    @Test
    fun `ㄴㅈ' 단어에서 초성 'ㄴㅈ'을 추출한다`() {
        assertEquals(getChoseong("ㄴㅈ"), "ㄴㅈ")
    }
    
    @Test
    fun `리액트' 단어에서 초성 'ㄹㅇㅌ'을 추출한다`() {
        assertEquals(getChoseong("리액트"), "ㄹㅇㅌ")
    }
    
    @Test
    fun `띄어 쓰기' 단어에서 초성 'ㄸㅇ ㅆㄱ'을 추출한다`() {
        assertEquals(getChoseong("띄어 쓰기"), "ㄸㅇ ㅆㄱ")
    }
    
    @Test
    fun `ㅊㅅ섞기' 단어에서 초성 'ㅊㅅㅅㄱ'을 추출한다`() {
        assertEquals(getChoseong("ㅊㅅ섞기"), "ㅊㅅㅅㄱ")
    }
}