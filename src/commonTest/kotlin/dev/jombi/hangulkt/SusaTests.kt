package dev.jombi.hangulkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SusaTests {
    @Test
    fun `1 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(1, "하나")  }
    @Test
    fun `1 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(1, "한") }

    @Test
    fun `2 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(2, "둘")  }
    @Test
    fun `2 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(2, "두") }

    @Test
    fun `3 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(3, "셋")  }
    @Test
    fun `3 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(3, "세") }

    @Test
    fun `4 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(4, "넷")  }
    @Test
    fun `4 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(4, "네") }

    @Test
    fun `5 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(5, "다섯")  }
    @Test
    fun `5 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(5, "다섯") }

    @Test
    fun `6 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(6, "여섯")  }
    @Test
    fun `6 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(6, "여섯") }

    @Test
    fun `7 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(7, "일곱")  }
    @Test
    fun `7 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(7, "일곱") }

    @Test
    fun `8 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(8, "여덟")  }
    @Test
    fun `8 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(8, "여덟") }

    @Test
    fun `9 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(9, "아홉")  }
    @Test
    fun `9 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(9, "아홉") }

    @Test
    fun `10 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(10, "열")  }
    @Test
    fun `10 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(10, "열") }

    @Test
    fun `11 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(11, "열하나")  }
    @Test
    fun `11 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(11, "열한") }

    @Test
    fun `12 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(12, "열둘")  }
    @Test
    fun `12 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(12, "열두") }

    @Test
    fun `20 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(20, "스물")  }
    @Test
    fun `20 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(20, "스무") }

    @Test
    fun `21 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(21, "스물하나")  }
    @Test
    fun `21 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(21, "스물한") }

    @Test
    fun `30 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(30, "서른")  }
    @Test
    fun `30 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(30, "서른") }

    @Test
    fun `99 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(99, "아흔아홉")  }
    @Test
    fun `99 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(99, "아흔아홉") }

    @Test
    fun `100 - 순 우리말 수사로 바꿔 반환해야 한다`() { wordMatch(100, "백")  }
    @Test
    fun `100 - 순 우리말 수 관형사가 있다면 수 관형사로 없다면 수사로 반환해야 한다`() { classifierMatch(100, "백") }
    
    @Test
    fun `0 - 유효하지 않은 숫자에 대해 오류를 발생시켜야 한다`() { invalid(0) }
    @Test
    fun `-1 - 유효하지 않은 숫자에 대해 오류를 발생시켜야 한다`() { invalid(-1) }
    @Test
    fun `101 - 유효하지 않은 숫자에 대해 오류를 발생시켜야 한다`() { invalid(101) }
    
    private fun wordMatch(num: Int, word: String) = assertEquals(word, susa(num))
    private fun classifierMatch(num: Int, classifier: String) = assertEquals(classifier, susa(num, classifier = true))
    private fun invalid(num: Int) = assertFailsWith<AssertionError> {
        susa(num)
    }
}