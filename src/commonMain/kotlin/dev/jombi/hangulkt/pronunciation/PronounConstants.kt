@file:Suppress("NonAsciiCharacters", "ObjectPropertyName", "ConstPropertyName")

package dev.jombi.hangulkt.pronunciation

public object PronounConstants {
    public const val 음가가_없는_자음: Char = 'ㅇ'

    public val 한글_자모: List<String> = listOf("기역", "니은", "리을", "미음", "비읍", "시옷", "이응")
    public val 특별한_한글_자모: List<String> = listOf("디귿", "지읒", "치읓", "키읔", "티읕", "피읖", "히읗")
    public val 특별한_한글_자모의_발음: Map<String, Char> = mapOf(
        "ㄷ" to 'ㅅ',
        "ㅈ" to 'ㅅ',
        "ㅊ" to 'ㅅ',
        "ㅌ" to 'ㅅ',
        "ㅎ" to 'ㅅ',
        "ㅋ" to 'ㄱ',
        "ㅍ" to 'ㅂ',
    )

    // 17항
    public val 음의_동화_받침: Map<String, Char> = mapOf(
        "ㄷ" to 'ㅈ',
        "ㅌ" to 'ㅊ',
        "ㄹㅌ" to 'ㅊ',
    )

    // "ㄴ,ㄹ"이 덧나는 동화작용
    public val ㄴㄹ이_덧나는_모음: List<String> = listOf("ㅏ", "ㅐ", "ㅓ", "ㅔ", "ㅗ", "ㅜ", "ㅟ") // 모음의 ∙(아래아)가 하나일 경우
    public val ㄴㄹ이_덧나는_후속음절_모음: List<String> = listOf("ㅑ", "ㅕ", "ㅛ", "ㅠ", "ㅣ", "ㅒ", "ㅖ")
    public val ㄴㄹ이_덧나서_받침_ㄴ_변환: List<String> = listOf("ㄱ", "ㄴ", "ㄷ", "ㅁ", "ㅂ", "ㅇ")
    public val ㄴㄹ이_덧나서_받침_ㄹ_변환: List<String> = listOf("ㄹ")

    // 19항
    public val 자음동화_받침_ㄴ_변환: List<String> = listOf("ㅁ", "ㅇ", "ㄱ", "ㅂ")

    // 18항
    public val 비음화_받침_ㅇ_변환: List<String> = listOf("ㄱ", "ㄲ", "ㅋ", "ㄱㅅ", "ㄹㄱ")
    public val 비음화_받침_ㄴ_변환: List<String> = listOf("ㄷ", "ㅅ", "ㅆ", "ㅈ", "ㅊ", "ㅌ", "ㅎ")
    public val 비음화_받침_ㅁ_변환: List<String> = listOf("ㅂ", "ㅍ", "ㄹㅂ", "ㄹㅍ", "ㅂㅅ")

    // 12항
    public val 발음변환_받침_ㅎ: List<String> = listOf("ㅎ", "ㄴㅎ", "ㄹㅎ")
    public val 발음변환_받침_ㅎ_발음: Map<Char, Char> = mapOf(
        'ㄱ' to 'ㅋ',
        'ㄷ' to 'ㅌ',
        'ㅈ' to 'ㅊ',
        'ㅅ' to 'ㅆ',
    )
    public val 발음변환_첫소리_ㅎ: List<String> = listOf("ㄱ", "ㄹㄱ", "ㄷ", "ㅂ", "ㄹㅂ", "ㅈ", "ㄴㅈ")
    public val 발음변환_첫소리_ㅎ_발음: Map<String, Char> = mapOf(
        "ㄱ" to 'ㅋ',
        "ㄹㄱ" to 'ㅋ',
        "ㄷ" to 'ㅌ',
        "ㅂ" to 'ㅍ',
        "ㄹㅂ" to 'ㅍ',
        "ㅈ" to 'ㅊ',
        "ㄴㅈ" to 'ㅊ',
    )

    // 9항, 10항, 11항
    public val 받침_대표음_발음: Map<String, String> = mapOf(
        "ㄲ" to "ㄱ",
        "ㅋ" to "ㄱ",
        "ㄱㅅ" to "ㄱ",
        "ㄹㄱ" to "ㄱ",
        "ㅅ" to "ㄷ",
        "ㅆ" to "ㄷ",
        "ㅈ" to "ㄷ",
        "ㅊ" to "ㄷ",
        "ㅌ" to "ㄷ",
        "ㅍ" to "ㅂ",
        "ㅂㅅ" to "ㅂ",
        "ㄹㅍ" to "ㅂ",
        "ㄴㅈ" to "ㄴ",
        "ㄹㅂ" to "ㄹ",
        "ㄹㅅ" to "ㄹ",
        "ㄹㅌ" to "ㄹ",
        "ㄹㅁ" to "ㅁ",
    )

    public val 된소리: Map<Char, Char> = mapOf(
        'ㄱ' to 'ㄲ',
        'ㄷ' to 'ㄸ',
        'ㅂ' to 'ㅃ',
        'ㅅ' to 'ㅆ',
        'ㅈ' to 'ㅉ',
    )

    // 23항
    public val 된소리_받침: List<String> = listOf(
        "ㄱ",
        "ㄲ",
        "ㅋ",
        "ㄱㅅ",
        "ㄹㄱ",
        "ㄷ",
        "ㅅ",
        "ㅆ",
        "ㅈ",
        "ㅊ",
        "ㅌ",
        "ㅂ",
        "ㅍ",
        "ㄹㅂ",
        "ㄹㅍ",
        "ㅂㅅ",
    )

    // 24항, 25항
    public val 어간_받침: List<String> = listOf("ㄴ", "ㄴㅈ", "ㅁ", "ㄹㅁ", "ㄹㅂ", "ㄹㅌ")
}