package dev.jombi.hangulkt

public object Constants {
    public const val NFD_CHOSEONG_START: Char = '\u1100' // 초성 ㄱ
    public const val NFD_JUNGSEONG_START: Char = '\u1161' // ㅏ
    public const val NFD_JONGSEONG_START: Char = '\u11A8' // 종성 ㄱ
    public const val NFD_CHOSEONG_END: Char = '\u1112' // 초성 ㅎ
    public const val NFD_JUNGSEONG_END: Char = '\u1175' // ㅣ
    public const val NFD_JONGSEONG_END: Char = '\u11C2' // 종성 ㅎ

    public const val COMPLETE_HANGUL_START_CODE: Int = '가'.code
    public const val COMPLETE_HANGUL_END_CODE: Int = '힣'.code

    public const val NUMBER_OF_JONGSEONG: Int = 28
    public const val NUMBER_OF_JUNGSEONG: Int = 21

    public val DISASSEMBLED_CONSONANTS_BY_CONSONANT: Map<Char, String> = mapOf(
        // 종성이 없는 경우 '빈' 초성으로 관리하는 것이 편리하여, 빈 문자열도 포함한다.
        '\u0000' to "",
        'ㄱ' to "ㄱ",
        'ㄲ' to "ㄲ",
        'ㄳ' to "ㄱㅅ",
        'ㄴ' to "ㄴ",
        'ㄵ' to "ㄴㅈ",
        'ㄶ' to "ㄴㅎ",
        'ㄷ' to "ㄷ",
        'ㄸ' to "ㄸ",
        'ㄹ' to "ㄹ",
        'ㄺ' to "ㄹㄱ",
        'ㄻ' to "ㄹㅁ",
        'ㄼ' to "ㄹㅂ",
        'ㄽ' to "ㄹㅅ",
        'ㄾ' to "ㄹㅌ",
        'ㄿ' to "ㄹㅍ",
        'ㅀ' to "ㄹㅎ",
        'ㅁ' to "ㅁ",
        'ㅂ' to "ㅂ",
        'ㅃ' to "ㅃ",
        'ㅄ' to "ㅂㅅ",
        'ㅅ' to "ㅅ",
        'ㅆ' to "ㅆ",
        'ㅇ' to "ㅇ",
        'ㅈ' to "ㅈ",
        'ㅉ' to "ㅉ",
        'ㅊ' to "ㅊ",
        'ㅋ' to "ㅋ",
        'ㅌ' to "ㅌ",
        'ㅍ' to "ㅍ",
        'ㅎ' to "ㅎ",
    )
    public val DISASSEMBLED_VOWELS_BY_VOWEL: Map<Char, String> = mapOf(
        'ㅏ' to "ㅏ",
        'ㅐ' to "ㅐ",
        'ㅑ' to "ㅑ",
        'ㅒ' to "ㅒ",
        'ㅓ' to "ㅓ",
        'ㅔ' to "ㅔ",
        'ㅕ' to "ㅕ",
        'ㅖ' to "ㅖ",
        'ㅗ' to "ㅗ",
        'ㅘ' to "ㅗㅏ",
        'ㅙ' to "ㅗㅐ",
        'ㅚ' to "ㅗㅣ",
        'ㅛ' to "ㅛ",
        'ㅜ' to "ㅜ",
        'ㅝ' to "ㅜㅓ",
        'ㅞ' to "ㅜㅔ",
        'ㅟ' to "ㅜㅣ",
        'ㅠ' to "ㅠ",
        'ㅡ' to "ㅡ",
        'ㅢ' to "ㅡㅣ",
        'ㅣ' to "ㅣ",
    )

    public val CHOSEONGS: List<Char> = listOf(
        'ㄱ',
        'ㄲ',
        'ㄴ',
        'ㄷ',
        'ㄸ',
        'ㄹ',
        'ㅁ',
        'ㅂ',
        'ㅃ',
        'ㅅ',
        'ㅆ',
        'ㅇ',
        'ㅈ',
        'ㅉ',
        'ㅊ',
        'ㅋ',
        'ㅌ',
        'ㅍ',
        'ㅎ',
    )

    public val JUNGSEONGS: List<String> = DISASSEMBLED_VOWELS_BY_VOWEL.values.toList()

    public val JONGSEONGS: List<String> = charArrayOf(
        '\u0000',
        'ㄱ',
        'ㄲ',
        'ㄳ',
        'ㄴ',
        'ㄵ',
        'ㄶ',
        'ㄷ',
        'ㄹ',
        'ㄺ',
        'ㄻ',
        'ㄼ',
        'ㄽ',
        'ㄾ',
        'ㄿ',
        'ㅀ',
        'ㅁ',
        'ㅂ',
        'ㅄ',
        'ㅅ',
        'ㅆ',
        'ㅇ',
        'ㅈ',
        'ㅊ',
        'ㅋ',
        'ㅌ',
        'ㅍ',
        'ㅎ',
    ).map { DISASSEMBLED_CONSONANTS_BY_CONSONANT[it]!! }

    public val HANGUL_DIGITS: Array<String> = arrayOf(
        "",
        "만",
        "억",
        "조",
        "경",
        "해",
        "자",
        "양",
        "구",
        "간",
        "정",
        "재",
        "극",
        "항하사",
        "아승기",
        "나유타",
        "불가사의",
        "무량대수",
        "겁",
        "업",
    )

    public val HANGUL_DIGITS_MAX: Int = HANGUL_DIGITS.size * 4
    public val HANGUL_NUMBERS: Array<Char> = arrayOf('\u0000', '일', '이', '삼', '사', '오', '육', '칠', '팔', '구')
    public val HANGUL_NUMBERS_FOR_DECIMAL: Array<Char> = arrayOf('영', '일', '이', '삼', '사', '오', '육', '칠', '팔', '구')
    public val HANGUL_CARDINAL: Array<Char> = arrayOf('\u0000', '십', '백', '천')
}