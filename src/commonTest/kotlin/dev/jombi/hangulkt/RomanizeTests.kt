package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

class RomanizeTests {
    @Test
    fun `자음 사이에서 동화 작용이 일어나는 경우`() {
        assertEquals("baengma", romanize("백마"))
        assertEquals("jongno", romanize("종로"))
        assertEquals("wangsimni", romanize("왕십리"))
        assertEquals("byeollae", romanize("별래"))
        assertEquals("silla", romanize("신라"))
    }

    @Test
    fun `ㄴㄹ’이 덧나는 경우`() {
        assertEquals("hangnyeoul", romanize("학여울"))
        assertEquals("allyak", romanize("알약"))
    }

    @Test
    fun `구개음화가 되는 경우`() {
        assertEquals("haedoji", romanize("해돋이"))
        assertEquals("gachi", romanize("같이"))
        assertEquals("guchida", romanize("굳히다"))
    }

    @Test
    fun `'ㄱㄷㅂㅈ'이 'ㅎ'과 합하여 거센소리로 소리 나는 경우`() {
        assertEquals("joko", romanize("좋고"))
        assertEquals("nota", romanize("놓다"))
        assertEquals("japyeo", romanize("잡혀"))
        assertEquals("nachi", romanize("낳지"))
    }

    @Test
    fun `된소리되기는 표기에 반영하지 않는다`() {
        assertEquals("apgujeong", romanize("압구정"))
        assertEquals("nakdonggang", romanize("낙동강"))
        assertEquals("jukbyeon", romanize("죽변"))
        assertEquals("nakseongdae", romanize("낙성대"))
        assertEquals("hapjeong", romanize("합정"))
        assertEquals("paldang", romanize("팔당"))
        assertEquals("saetbyeol", romanize("샛별"))
        assertEquals("ulsan", romanize("울산"))
    }

    @Test
    fun `'ㄱ ㄷ ㅂ'은 모음 앞에서는 'g d b'로 자음 앞이나 어말에서는 'k t p'로 적는다`() {
        assertEquals("gumi", romanize("구미"))
        assertEquals("yeongdong", romanize("영동"))
        assertEquals("baegam", romanize("백암"))
        assertEquals("okcheon", romanize("옥천"))
        assertEquals("hapdeok", romanize("합덕"))
        assertEquals("hobeop", romanize("호법"))
        assertEquals("wolgot", romanize("월곶"))
        assertEquals("beotkkot", romanize("벚꽃"))
        assertEquals("hanbat", romanize("한밭"))
    }

    @Test
    fun `'ㄹ'은 모음 앞에서는 'r'로 자음 앞이나 어말에서는 'l'로 적는다 --- 단 'ㄹㄹ'은 'll'로 적는다`() {
        assertEquals("guri", romanize("구리"))
        assertEquals("seorak", romanize("설악"))
        assertEquals("chilgok", romanize("칠곡"))
        assertEquals("imsil", romanize("임실"))
        assertEquals("ulleung", romanize("울릉"))
        assertEquals("daegwallyeong", romanize("대관령"))
    }

    @Test
    fun `완성된 음절이 아닌 경우에는 그대로 반환한다`() {
        assertEquals("g", romanize("ㄱ"))
        assertEquals("ganadarambs", romanize("가나다라ㅁㅂㅅㅇ"))
        assertEquals("a", romanize("ㅏ"))
        assertEquals("wa", romanize("ㅘ"))
    }

    @Test
    fun `특수문자는 로마자 표기로 변경하지 않는다`() {
        assertEquals("annyeonghaseyo.", romanize("안녕하세요."))
        assertEquals("hangugeo!", romanize("한국어!"))
        assertEquals("", romanize(""))
        assertEquals("!?/", romanize("!?/"))
    }

    @Test
    fun `한글과 영어가 혼합된 경우에는 영어는 그대로 반환된다`() {
        assertEquals("annyeonghaseyo es-hangul", romanize("안녕하세요 es-hangul"))
        assertEquals("hangugeunkorea", romanize("한국은korea"))
        assertEquals("goyangineuncat", romanize("고양이는cat"))
    }
}