package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals

class StandardizePronunciationTests {
    @Test
    fun `음절이 완성된 한글을 제외한 문자는 변경하지 않는다 - 단일 자모는 그대로 반환한다`() {
        assertEquals("ㄱㄴㄷㄹㅏㅓㅑㅙ", standardizePronunciation("ㄱㄴㄷㄹㅏㅓㅑㅙ"))
    }
    
    @Test
    fun `음절이 완성된 한글을 제외한 문자는 변경하지 않는다 - 특수문자는 그대로 반환한다`() {
        assertEquals("!?", standardizePronunciation("!?"))
    }
    
    @Test
    fun `음절이 완성된 한글을 제외한 문자는 변경하지 않는다 - 영어는 그대로 반환한다`() {
        assertEquals("hello", standardizePronunciation("hello"))
    }
    
    @Test
    fun `음절이 완성된 한글을 제외한 문자는 변경하지 않는다 - 숫자는 그대로 반환한다`() {
        assertEquals("1234567890", standardizePronunciation("1234567890"))
    }
    
    @Test
    fun `음절이 완성된 한글을 제외한 문자는 변경하지 않는다 - 빈 문자열은 그대로 반환한다`() {
        assertEquals("", standardizePronunciation(""))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 16항`() {
        // 한글 자모의 이름은 그 받침소리를 연음하되, "ㄷ, ㅈ, ㅊ, ㅋ, ㅌ, ㅍ, ㅎ"의 경우에는 특별히 다음과 같이 발음한다
        assertEquals("디그시", standardizePronunciation("디귿이"))
        assertEquals("지으시", standardizePronunciation("지읒이"))
        assertEquals("치으시", standardizePronunciation("치읓이"))
        assertEquals("키으기", standardizePronunciation("키읔이"))
        assertEquals("티으시", standardizePronunciation("티읕이"))
        assertEquals("피으비", standardizePronunciation("피읖이"))
        assertEquals("히으시", standardizePronunciation("히읗이"))
        
        // 자모의 이름이 "ㄱ, ㄴ, ㄹ, ㅁ, ㅂ, ㅅ, ㅇ"일 경우
        assertEquals("기여기", standardizePronunciation("기역이"))
        assertEquals("니으니", standardizePronunciation("니은이"))
        assertEquals("리으리", standardizePronunciation("리을이"))
        assertEquals("미으미", standardizePronunciation("미음이"))
        assertEquals("비으비", standardizePronunciation("비읍이"))
        assertEquals("이응이", standardizePronunciation("이응이"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 17항`() {
        // 받침 "ㄷ", "ㅌ(ㄾ)"이 조사나 접미사의 모음 "ㅣ"와 결합되는 경우에는, "ㅈ", "ㅊ"으로 바꾸어서 뒤 음절 첫소리로 옮겨 발음한다
        assertEquals("고지듣따", standardizePronunciation("곧이듣다"))
        assertEquals("구지", standardizePronunciation("굳이"))
        assertEquals("미다지", standardizePronunciation("미닫이"))
        assertEquals("땀바지", standardizePronunciation("땀받이"))
        assertEquals("바치", standardizePronunciation("밭이"))
        assertEquals("벼훌치", standardizePronunciation("벼훑이"))
        
        // "ㄷ" 뒤에 접미사 "히"가 결합되어 "티"를 이루는 것은 "치"로 발음한다
        assertEquals("구치다", standardizePronunciation("굳히다"))
        assertEquals("다치다", standardizePronunciation("닫히다"))
        assertEquals("무치다", standardizePronunciation("묻히다"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - ㄴㄹ이 덧나는 경우`() {
        // 받침이 "ㄱ, ㄴ, ㄷ, ㅁ, ㅂ, ㅇ"이고 다음 음절이 "야, 여, 요, 유, 이, 얘, 예"로 이어지는 경우
        assertEquals("항녀울", standardizePronunciation("학여울"))
        assertEquals("맨닙", standardizePronunciation("맨입"))
        assertEquals("담뇨", standardizePronunciation("담요"))
        assertEquals("영엄뇽", standardizePronunciation("영업용"))
        assertEquals("콩녇", standardizePronunciation("콩엿"))
        assertEquals("알략", standardizePronunciation("알약"))
        
        // 받침이 "ㄹ"이고 다음 음절이 "야, 여, 요, 유, 이, 얘, 예"로 이어지는 경우
        assertEquals("알략", standardizePronunciation("알약"))
        assertEquals("서울력", standardizePronunciation("서울역"))
        assertEquals("불려우", standardizePronunciation("불여우"))
        
        // ㄴ/ㄹ이 되기 위한 조건이지만 현재 음절의 중성의 ∙(아래아)가 하나가 아닐 경우에는 덧나지 않고 연음규칙이 적용된다
        assertEquals("고양이", standardizePronunciation("고양이"))
        assertEquals("윤녀정", standardizePronunciation("윤여정"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 19항`() {
        // 받침 "ㅁ, ㅇ" 뒤에 연결되는 "ㄹ"은 "ㄴ"으로 발음한다
        assertEquals("담녁", standardizePronunciation("담력"))
        assertEquals("침냑", standardizePronunciation("침략"))
        assertEquals("강능", standardizePronunciation("강릉"))
        assertEquals("항노", standardizePronunciation("항로"))
        assertEquals("대통녕", standardizePronunciation("대통령"))
        
        // 받침 "ㄱ, ㅂ" 뒤에 연결되는 "ㄹ"도 "ㄴ"으로 발음한다
        assertEquals("망논", standardizePronunciation("막론"))
        assertEquals("성뉴", standardizePronunciation("석류"))
        assertEquals("혐녁", standardizePronunciation("협력"))
        assertEquals("범니", standardizePronunciation("법리"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 18항`() {
        // 받침 "ㄱ, ㄲ, ㅋ, ㄳ, ㄺ" / "ㄷ, ㅅ, ㅆ, ㅈ, ㅊ, ㅌ, ㅎ" / "ㅂ, ㅍ, ㄼ, ㄿ, ㅄ"은 "ㄴ, ㅁ" 앞에서 "ㅇ, ㄴ, ㅁ"으로 발음한다
        
        // 받침 "ㄱ, ㄲ, ㅋ, ㄳ, ㄺ"일 경우
        assertEquals("멍는", standardizePronunciation("먹는"))
        assertEquals("깡는", standardizePronunciation("깎는"))
        assertEquals("키응만", standardizePronunciation("키읔만"))
        assertEquals("몽목씨", standardizePronunciation("몫몫이"))
        assertEquals("긍는", standardizePronunciation("긁는"))
        
        // 받침 "ㄷ, ㅅ, ㅆ, ㅈ, ㅊ, ㅌ, ㅎ"일 경우
        assertEquals("단는", standardizePronunciation("닫는"))
        assertEquals("진는", standardizePronunciation("짓는"))
        assertEquals("인는", standardizePronunciation("있는"))
        assertEquals("만는", standardizePronunciation("맞는"))
        assertEquals("쫀는", standardizePronunciation("쫓는"))
        assertEquals("분는", standardizePronunciation("붙는"))
        assertEquals("논는", standardizePronunciation("놓는"))
        
        // 받침 "ㅂ, ㅍ, ㄼ, ㄿ, ㅄ"일 경우
        assertEquals("잠는", standardizePronunciation("잡는"))
        assertEquals("암마당", standardizePronunciation("앞마당"))
        assertEquals("밤는", standardizePronunciation("밟는"))
        assertEquals("음는", standardizePronunciation("읊는"))
        assertEquals("엄는", standardizePronunciation("없는"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 20항`() {
        // "ㄴ"은 "ㄹ"의 앞이나 뒤에서 "ㄹ"로 발음한다
        assertEquals("날로", standardizePronunciation("난로"))
        assertEquals("실라", standardizePronunciation("신라"))
        assertEquals("철리", standardizePronunciation("천리"))
        assertEquals("대괄령", standardizePronunciation("대관령"))
        assertEquals("칼랄", standardizePronunciation("칼날"))
        
        // 첫소리 "ㄴ"이 "ㅀ, ㄾ" 뒤에 연결되는 경우에도 "ㄹ"로 발음한다
        assertEquals("달른", standardizePronunciation("닳는"))
        assertEquals("할레", standardizePronunciation("핥네"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 12항`() {
        // "ㅎ, ㄶ, ㅀ" 뒤에 "ㄱ, ㄷ, ㅈ"이 결합되는 경우에는, 뒤 음절 첫소리와 합쳐서 "ㅋ, ㅌ, ㅊ"으로 발음한다
        assertEquals("노코", standardizePronunciation("놓고"))
        assertEquals("조턴", standardizePronunciation("좋던"))
        assertEquals("싸치", standardizePronunciation("쌓지"))
        assertEquals("만코", standardizePronunciation("많고"))
        assertEquals("안턴", standardizePronunciation("않던"))
        assertEquals("달치", standardizePronunciation("닳지"))
        
        // 받침 "ㄱ, ㄺ, ㄷ, ㅂ, ㄼ, ㅈ, ㄵ"이 뒤 음절 첫소리 "ㅎ"과 결합되는 경우에도, 역시 두 음을 합쳐서 "ㅋ, ㅌ, ㅍ, ㅊ"으로 발음한다
        assertEquals("가카", standardizePronunciation("각하"))
        assertEquals("머키다", standardizePronunciation("먹히다"))
        assertEquals("발키다", standardizePronunciation("밝히다"))
        assertEquals("마텽", standardizePronunciation("맏형"))
        assertEquals("조피다", standardizePronunciation("좁히다"))
        assertEquals("널피다", standardizePronunciation("넓히다"))
        assertEquals("꼬치다", standardizePronunciation("꽂히다"))
        assertEquals("안치다", standardizePronunciation("앉히다"))
        
        // "ㅎ, ㄶ, ㅀ" 뒤에 "ㅅ"이 결합되는 경우에는, "ㅅ"을 "ㅆ"으로 발음한다
        assertEquals("다쏘", standardizePronunciation("닿소"))
        assertEquals("만쏘", standardizePronunciation("많소"))
        assertEquals("실쏘", standardizePronunciation("싫소"))
        
        // "ㅎ" 뒤에 "ㄴ"이 결합되는 경우에는 "ㄴ"으로 발음한다
        assertEquals("논는", standardizePronunciation("놓는"))
        assertEquals("싼네", standardizePronunciation("쌓네"))
        
        // "ㄶ, ㅀ" 뒤에 "ㄴ"이 결합되는 경우에는, "ㅎ"을 발음하지 않는다
        assertEquals("안네", standardizePronunciation("않네"))
        assertEquals("안는", standardizePronunciation("않는"))
        assertEquals("뚤레", standardizePronunciation("뚫네"))
        assertEquals("뚤른", standardizePronunciation("뚫는"))
        
        // "ㅎ, ㄶ, ㅀ" 뒤에 모음으로 시작된 어미나 접미사가 결합되는 경우에는 "ㅎ"을 발음하지 않는다
        assertEquals("나은", standardizePronunciation("낳은"))
        assertEquals("노아", standardizePronunciation("놓아"))
        assertEquals("싸이다", standardizePronunciation("쌓이다"))
        assertEquals("마나", standardizePronunciation("많아"))
        assertEquals("아는", standardizePronunciation("않은"))
        assertEquals("다라", standardizePronunciation("닳아"))
        assertEquals("시러도", standardizePronunciation("싫어도"))
        
        // 다음 음절이 없는 경우
        assertEquals("만", standardizePronunciation("많"))
        assertEquals("실", standardizePronunciation("싫"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 13항`() {
        // 홑받침이나 쌍받침이 모음으로 시작된 조사나 어미, 접미사와 결합되는 경우에는, 제 음가대로 뒤 음절 첫소리로 옮겨 발음한다
        assertEquals("까까", standardizePronunciation("깎아"))
        assertEquals("오시", standardizePronunciation("옷이"))
        assertEquals("이써", standardizePronunciation("있어"))
        assertEquals("나지", standardizePronunciation("낮이"))
        assertEquals("아프로", standardizePronunciation("앞으로"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 14항`() {
        // 겹받침이 모음으로 시작된 조사나 어미, 접미사와 결합되는 경우에는, 뒤엣것만을 뒤 음절 첫소리로 옮겨 발음한다
        assertEquals("달글", standardizePronunciation("닭을"))
        assertEquals("절머", standardizePronunciation("젊어"))
        assertEquals("골씨", standardizePronunciation("곬이"))
        assertEquals("할타", standardizePronunciation("핥아"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 9항`() {
        // 받침 "ㄲ, ㅋ" / "ㅅ, ㅆ, ㅈ, ㅊ, ㅌ" / "ㅍ"은 어말 또는 자음 앞에서 각각 대표음 "ㄱ, ㄷ, ㅂ"으로 발음한다
        assertEquals("닥따", standardizePronunciation("닦다"))
        assertEquals("키윽", standardizePronunciation("키읔"))

        assertEquals("옫", standardizePronunciation("옷"))
        assertEquals("읻따", standardizePronunciation("있다"))
        assertEquals("젇", standardizePronunciation("젖"))
        assertEquals("빋따", standardizePronunciation("빚다"))
        assertEquals("꼳", standardizePronunciation("꽃"))
        assertEquals("솓", standardizePronunciation("솥"))

        assertEquals("압", standardizePronunciation("앞"))
        assertEquals("덥따", standardizePronunciation("덮다"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 10항`() {
        // 겹받침 "ㄳ" / "ㄵ" / "ㄼ, ㄽ, ㄾ" / "ㅄ"은 어말 또는 자음 앞에서 각각 "ㄱ, ㄴ, ㄹ, ㅂ"으로 발음한다
        assertEquals("넉", standardizePronunciation("넋"))

        assertEquals("안따", standardizePronunciation("앉다"))

        assertEquals("여덜", standardizePronunciation("여덟"))
        assertEquals("외골", standardizePronunciation("외곬"))
        assertEquals("할따", standardizePronunciation("핥다"))

        assertEquals("갑", standardizePronunciation("값"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 11항`() {
        // 겹받침 "ㄺ" / "ㄻ" / "ㄿ"은 어말 또는 자음 앞에서 각각 "ㄱ, ㅁ, ㅂ"으로 발음한다
        assertEquals("닥", standardizePronunciation("닭"))
        assertEquals("막따", standardizePronunciation("맑다"))

        assertEquals("삼", standardizePronunciation("삶"))
        assertEquals("점따", standardizePronunciation("젊다"))

        assertEquals("읍꼬", standardizePronunciation("읊고"))
        assertEquals("읍따", standardizePronunciation("읊다"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 23항`() {
        // 받침 "ㄱ(ㄲ, ㅋ, ㄳ, ㄺ), ㄷ(ㅅ, ㅆ, ㅈ, ㅊ, ㅌ), ㅂ(ㅍ, ㄼ, ㄿ, ㅄ)" 뒤에 연결되는 "ㄱ, ㄷ, ㅂ, ㅅ, ㅈ"은 된소리로 발음한다
        assertEquals("국빱", standardizePronunciation("국밥"))
        assertEquals("깍따", standardizePronunciation("깎다"))
        assertEquals("넉빠지", standardizePronunciation("넋받이"))
        assertEquals("삭똔", standardizePronunciation("삯돈"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 24항`() {
        // 어간 받침 "ㄴ(ㄵ), ㅁ(ㄻ)" 뒤에 결합되는 어미의 첫소리 "ㄱ, ㄷ, ㅅ, ㅈ"은 된소리로 발음한다
        assertEquals("신꼬", standardizePronunciation("신고"))
        assertEquals("껴안따", standardizePronunciation("껴안다"))
        assertEquals("안꼬", standardizePronunciation("앉고"))
        assertEquals("언따", standardizePronunciation("얹다"))
        assertEquals("삼꼬", standardizePronunciation("삼고"))
        assertEquals("더듬찌", standardizePronunciation("더듬지"))
        assertEquals("담꼬", standardizePronunciation("닮고"))
        assertEquals("점찌", standardizePronunciation("젊지"))
    }
    
    @Test
    fun `한글은 음성 표기법으로 변경한다 - 25항`() {
        // 어간 받침 "ㄼ, ㄾ" 뒤에 결합되는 어미의 첫소리 "ㄱ, ㄷ, ㅅ, ㅈ"은 된소리로 발음한다
        assertEquals("널께", standardizePronunciation("넓게"))
        assertEquals("할따", standardizePronunciation("핥다"))
        assertEquals("훌쏘", standardizePronunciation("훑소"))
        assertEquals("떨찌", standardizePronunciation("떫지"))
    }
    
    @Test
    fun `경음화 등의 된소리를 적용하지 않는다 - 9항`() {
        assertEquals("닥다", standardizePronunciation("닦다", shouldHardConversion = false))
    }
    
    @Test
    fun `경음화 등의 된소리를 적용하지 않는다 - 10항`() {
        assertEquals("안다", standardizePronunciation("앉다", shouldHardConversion = false))
    }
    
    @Test
    fun `경음화 등의 된소리를 적용하지 않는다 - 11항`() {
        assertEquals("막다", standardizePronunciation("맑다", shouldHardConversion = false))
    }
    
    @Test
    fun `경음화 등의 된소리를 적용하지 않는다 - 17항`() {
        assertEquals("고지듣다", standardizePronunciation("곧이듣다", shouldHardConversion = false))
    }
    
    @Test
    fun `경음화 등의 된소리를 적용하지 않는다 - 23항`() {
        assertEquals("국밥", standardizePronunciation("국밥", shouldHardConversion = false))
    }
    
    @Test
    fun `경음화 등의 된소리를 적용하지 않는다 - 24항`() {
        assertEquals("신고", standardizePronunciation("신고", shouldHardConversion = false))
    }
    
    @Test
    fun `경음화 등의 된소리를 적용하지 않는다 - 25항`() {
        assertEquals("널게", standardizePronunciation("넓게", shouldHardConversion = false))
    }
}