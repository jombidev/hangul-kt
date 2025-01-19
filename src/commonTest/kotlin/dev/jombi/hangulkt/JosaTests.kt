@file:Suppress("NonAsciiCharacters", "TestFunctionName")

package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

class JosaTests {
    @Test
    fun 주격조사() {
        assertEquals("샴푸가", josa("샴푸", JosaOption.이_가))
        assertEquals("칫솔이", josa("칫솔", JosaOption.이_가))
    }

    @Test
    fun 목적격조사() {
        assertEquals("샴푸를", josa("샴푸", JosaOption.을_를))
        assertEquals("칫솔을", josa("칫솔", JosaOption.을_를))
    }

    @Test
    fun `대조의 보조사`() {
        assertEquals("샴푸는", josa("샴푸", JosaOption.은_는))
        assertEquals("칫솔은", josa("칫솔", JosaOption.은_는))
    }

    @Test
    fun `방향의 격조사`() {
        assertEquals("바깥으로", josa("바깥", JosaOption.으로_로))
        assertEquals("내부로", josa("내부", JosaOption.으로_로))

    }

    @Test
    fun `방향의 격조사 ㄹ 받침 예외 처리`() {
        assertEquals("지름길로", josa("지름길", JosaOption.으로_로))

    }

    @Test
    fun `비교의 격조사`() {
        assertEquals("샴푸와", josa("샴푸", JosaOption.와_과))
        assertEquals("칫솔과", josa("칫솔", JosaOption.와_과))

    }

    @Test
    fun `선택의 보조사`() {
        assertEquals("샴푸나", josa("샴푸", JosaOption.이나_나))
        assertEquals("칫솔이나", josa("칫솔", JosaOption.이나_나))

    }

    @Test
    fun `화제의 보조사`() {
        assertEquals("샴푸란", josa("샴푸", JosaOption.이란_란))
        assertEquals("칫솔이란", josa("칫솔", JosaOption.이란_란))

    }

    @Test
    fun 호격조사() {
        assertEquals("철수야", josa("철수", JosaOption.아_야))
        assertEquals("길동아", josa("길동", JosaOption.아_야))

    }

    @Test
    fun 접속조사() {
        assertEquals("고기랑", josa("고기", JosaOption.이랑_랑))
        assertEquals("과일이랑", josa("과일", JosaOption.이랑_랑))

    }

    @Test
    fun `주제의 보조사`() {
        assertEquals("의사라", josa("의사", JosaOption.이라_라))
        assertEquals("선생님이라", josa("선생님", JosaOption.이라_라))

    }

    @Test
    fun `서술격조사와 종결어미`() {
        assertEquals("사과예요", josa("사과", JosaOption.이에요_예요))
        assertEquals("책이에요", josa("책", JosaOption.이에요_예요))

    }

    @Test
    fun `서술격조사와 종결어미-이- 로 끝나는 단어 예외 처리`() {
        assertEquals("때밀이예요", josa("때밀이", JosaOption.이에요_예요))

    }

    @Test
    fun `지위나 신분 또는 자격을 나타내는 위격조사`() {
        assertEquals("학생으로서", josa("학생", JosaOption.으로서_로서))
        assertEquals("부모로서", josa("부모", JosaOption.으로서_로서))

    }

    @Test
    fun `지위나 신분 또는 자격을 나타내는 위격조사 ㄹ 받침 예외 처리`() {
        assertEquals("라이벌로서", josa("라이벌", JosaOption.으로서_로서))

    }

    @Test
    fun `수단의 의미를 나타내는 도구격조사`() {
        assertEquals("토큰으로써", josa("토큰", JosaOption.으로써_로써))
        assertEquals("함수로써", josa("함수", JosaOption.으로써_로써))

    }

    @Test
    fun `수단의 의미를 나타내는 도구격조사 ㄹ 받침 예외 처리`() {
        assertEquals("건물로써", josa("건물", JosaOption.으로써_로써))

    }

    @Test
    fun `어떤 행동의 출발점이나 비롯되는 대상임을 나타내는 격 조사`() {
        assertEquals("역삼동으로부터", josa("역삼동", JosaOption.으로부터_로부터))
        assertEquals("저기로부터", josa("저기", JosaOption.으로부터_로부터))

    }

    @Test
    fun `어떤 행동의 출발점이나 비롯되는 대상임을 나타내는 격 조사 ㄹ 받침 예외 처리`() {
        assertEquals("동굴로부터", josa("동굴", JosaOption.으로부터_로부터))

    }

    @Test
    fun `단어가 빈 문자열일 경우 빈 문자열을 반환한다`() {
        assertEquals("", josa("", JosaOption.이_가))
    }
}

class JosaPickerTests {
    @Test
    fun `첫 번째 매개변수가 빈 문자열이라면 옵션 중 첫 번째 값을 반환한다`() {
        assertEquals("이", josaPicker("", JosaOption.이_가))
    }

    @Test
    fun 주격조사() {
        assertEquals("가", josaPicker("샴푸", JosaOption.이_가))
        assertEquals("이", josaPicker("칫솔", JosaOption.이_가))
    }

    @Test
    fun 목적격조사() {
        assertEquals("를", josaPicker("샴푸", JosaOption.을_를))
        assertEquals("을", josaPicker("칫솔", JosaOption.을_를))
    }

    @Test
    fun `대조의 보조사`() {
        assertEquals("는", josaPicker("샴푸", JosaOption.은_는))
        assertEquals("은", josaPicker("칫솔", JosaOption.은_는))
    }

    @Test
    fun `방향의 격조사`() {
        assertEquals("으로", josaPicker("바깥", JosaOption.으로_로))
        assertEquals("로", josaPicker("내부", JosaOption.으로_로))
    }

    @Test
    fun `방향의 격조사 ㄹ 받침 예외 처리`() {
        assertEquals("로", josaPicker("지름길", JosaOption.으로_로))
    }

    @Test
    fun `비교의 격조사`() {
        assertEquals("와", josaPicker("샴푸", JosaOption.와_과))
        assertEquals("과", josaPicker("칫솔", JosaOption.와_과))
    }

    @Test
    fun `선택의 보조사`() {
        assertEquals("나", josaPicker("샴푸", JosaOption.이나_나))
        assertEquals("이나", josaPicker("칫솔", JosaOption.이나_나))
    }

    @Test
    fun `화제의 보조사`() {
        assertEquals("란", josaPicker("샴푸", JosaOption.이란_란))
        assertEquals("이란", josaPicker("칫솔", JosaOption.이란_란))
    }

    @Test
    fun 호격조사() {
        assertEquals("야", josaPicker("철수", JosaOption.아_야))
        assertEquals("아", josaPicker("길동", JosaOption.아_야))
    }

    @Test
    fun 접속조사() {
        assertEquals("랑", josaPicker("고기", JosaOption.이랑_랑))
        assertEquals("이랑", josaPicker("과일", JosaOption.이랑_랑))
    }

    @Test
    fun `서술격조사와 종결어미`() {
        assertEquals("예요", josaPicker("사과", JosaOption.이에요_예요))
        assertEquals("이에요", josaPicker("책", JosaOption.이에요_예요))
    }

    @Test
    fun `서술격조사와 종결어미 -이- 로 끝나는 단어 예외 처리`() {
        assertEquals("예요", josaPicker("때밀이", JosaOption.이에요_예요))
    }

    @Test
    fun `지위나 신분 또는 자격을 나타내는 위격조사`() {
        assertEquals("으로서", josaPicker("학생", JosaOption.으로서_로서))
        assertEquals("로서", josaPicker("부모", JosaOption.으로서_로서))
    }

    @Test
    fun `지위나 신분 또는 자격을 나타내는 위격조사 ㄹ 받침 예외 처리`() {
        assertEquals("로서", josaPicker("라이벌", JosaOption.으로서_로서))
    }

    @Test
    fun `수단의 의미를 나타내는 도구격조사`() {
        assertEquals("으로써", josaPicker("토큰", JosaOption.으로써_로써))
        assertEquals("로써", josaPicker("함수", JosaOption.으로써_로써))
    }

    @Test
    fun `수단의 의미를 나타내는 도구격조사 ㄹ 받침 예외 처리`() {
        assertEquals("로써", josaPicker("건물", JosaOption.으로써_로써))
    }

    @Test
    fun `어떤 행동의 출발점이나 비롯되는 대상임을 나타내는 격 조사`() {
        assertEquals("으로부터", josaPicker("역삼동", JosaOption.으로부터_로부터))
        assertEquals("로부터", josaPicker("저기", JosaOption.으로부터_로부터))
    }

    @Test
    fun `어떤 행동의 출발점이나 비롯되는 대상임을 나타내는 격 조사 ㄹ 받침 예외 처리`() {
        assertEquals("로부터", josaPicker("동굴", JosaOption.으로부터_로부터))
    }
}